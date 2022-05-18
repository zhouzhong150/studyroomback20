package com.group20.studyroomback.service.impl;

import com.group20.studyroomback.entity.*;
import com.group20.studyroomback.service.*;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;


/**
 * @Author: zhouzhong
 * @Email: 21212010059@m.fudan.edu.cn
 * @Date: 2022/5/13 19:41
 * @Description:
 */
@Service
public class MessageServiceImpl implements MessageService {
    static final String MAILMESSAGE = "您预定的座位马上就要开始签到了，请及时签到";
    static final String MAILSUBJECTMESSAGE = "自习室座位预约";

    @Value("${spring.mail.username}")
    private String fromMailName;

    @Autowired
    public JavaMailSender javaMailSender;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private UserService userServicel;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private SeatService seatService;

    @Autowired
    private StudyRoomService studyRoomService;



    @Override
    @RabbitListener(queues = "PROJECT_LATTER_QUEUE")
    public void consumerMessage(Message message, Channel channel) throws IOException {

        String msg = new String(message.getBody());
        System.out.println("消费消息： " + msg);

        String[] messageList = msg.split("_");
        String content = messageList[0];
        String seatId = messageList[1];
        String userId = messageList[2];
        int type = Integer.parseInt(messageList[4]);
        String userMail = messageList[3];

        try{
            if (type==1){
                sentMail(fromMailName, MAILMESSAGE, MAILSUBJECTMESSAGE, userMail);
            }
            if (type==2){
                Seat seat = seatService.getSeatById(seatId);
                if (seat != null && seat.getStatus()== 2){
                    //改seat
                    seat.setStatus(1);
                    seatService.update(seat);
                    //改user
                    Response<User> userResponse = userServicel.selectByUserId(userId);
                    if (userResponse.getStatus()== 200 && userResponse.getData()!=null){
                        User user = userResponse.getData();
                        user.setDelayTimes(user.getDelayTimes() + 1);
                        userServicel.updateUser(user);
                    }
                    //改history
                    List<History> historyList = historyService.getByUserId(userId);
                    History history = null;
                    for (int i =0;i<=historyList.size()-1;i++){
                        if (historyList.get(i).getAlive()==1){
                            history = historyList.get(i);
                            history.setAlive(2);
                            break;
                        }
                    }
                    if (history != null){
                        historyService.updateHistoryByEntity(history);
                    }
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
}


    @Override
    public void produceMessage(String content, String seatId, String userId, long preserveTime, String userMail){
        String message = content + '_' + seatId + '_' + userId + "_" + userMail;
        String message1 = message + '_' + 1;
        String message2 = message + '_' + 2;
        long time1;
        long time2;

        long nowTime = System.currentTimeMillis();
        if(preserveTime - nowTime > 1000*60*30){
            time1 = preserveTime - 1000*60*30 - nowTime;
            time2 = time1 + 1000*60*30;
        }else {
            time1 = 0;
            time2 = time1 + 1000*60*30;
        }

        rabbitTemplate.convertAndSend("PROJECT_EXCHANGE", "DL", message1, correlationData ->{
            correlationData.getMessageProperties().setExpiration(Long.toString(time1));
            return correlationData;
        });
        System.out.println("产生消息： "+ message1 + "_" + Long.toString(time1));
        rabbitTemplate.convertAndSend("PROJECT_EXCHANGE", "DL", message2, correlationData ->{
            correlationData.getMessageProperties().setExpiration(Long.toString(time2));
            return correlationData;
        });
        System.out.println("产生消息： "+ message2 + "_" + Long.toString(time2));

    }

    @Override
    public int sentMail(String fromMailName, String mailMessage, String mailSubjectMessage, String toMailName) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(fromMailName);//发送者
            simpleMailMessage.setTo(toMailName);//接收者
            simpleMailMessage.setSubject(mailSubjectMessage);//邮件测试主题
            simpleMailMessage.setText(mailMessage);
            javaMailSender.send(simpleMailMessage);
            return 1;
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    @Scheduled(cron="0 * * * * ?")
    public void timingCloseStudyRoom(){
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int nowTime = hour * 100 + minute;
        System.out.println("定时任务：nowtime:" + Integer.toString(nowTime));
        //得到所有在该时间关闭的room
        List<StudyRoom> studyRoomList = studyRoomService.selectRoomsByCloseTime(nowTime);
        System.out.println("定时任务，得到所有该关闭的自习室数量: " + Integer.toString(studyRoomList.size()));
        if (studyRoomList.size() > 0){
            List<String> idList = new LinkedList<>();
            for (int i = 0; i <=studyRoomList.size()-1; i++){
                idList.add(studyRoomList.get(i).getId());
            }
            //

            List<Seat> seatList = seatService.updateSeatsByRoomIds(idList, 1);
            List<String> seatIdList = new LinkedList<>();
            if (seatList!= null){
                for (int i =0;i<=seatList.size()-1;i++){
                    seatIdList.add(seatList.get(i).getId());
                }
            }
            System.out.println("定时任务，得到所有该设置状态的的座位数量" + Integer.toString(seatIdList.size()));



            historyService.updateHistoriesBySeatIds(seatIdList);
        }



    }
}