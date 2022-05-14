package com.group20.studyroomback.service.impl;

import com.group20.studyroomback.entity.History;
import com.group20.studyroomback.entity.Response;
import com.group20.studyroomback.entity.Seat;
import com.group20.studyroomback.entity.User;
import com.group20.studyroomback.service.HistoryService;
import com.group20.studyroomback.service.MessageService;
import com.group20.studyroomback.service.SeatService;
import com.group20.studyroomback.service.UserService;
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
import java.util.Date;
import java.util.List;


/**
 * @Author: zhouzhong
 * @Email: 21212010059@m.fudan.edu.cn
 * @Date: 2022/5/13 19:41
 * @Description:
 */
@Service
public class MessageServiceImpl implements MessageService {
    static final String MAILMESSAGE = "您预定的座位还有半小时就要开始签到了，请及时签到";
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


    @Override
    @RabbitListener(queues = "PROJECT_LATTER_QUEUE")
    public void consumerMessage(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        String[] messageList = msg.split("_");
        String content = messageList[0];
        int seatId = Integer.parseInt(messageList[1]);
        int userId = Integer.parseInt(messageList[2]);
        int type = Integer.parseInt(messageList[3]);
        String userMail = messageList[4];

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
                }
                //改history
                List<History> historyList = historyService.getByUserId(userId);
                History history = null;
                for (int i =0;i<=historyList.size()-1;i++){
                    if (historyList.get(i).getAlive()==1){
                        history = historyList.get(i);
                        history.setAlive(0);
                        break;
                    }
                }
                if (history != null){
                    historyService.updateHistory(history);
                }
            }
        }
            /**
             * 根据seatId去seat看看有没有签到
             * 如果签到了， 不动？
             * 如果没签到，把用户违约次数加1，并且把seat status 为1 , 把history alive 改成1
             *
             */

        System.out.println(new Date().toString());
        System.out.println(msg);
}

    @Override
    public void produceMessage(String content, int seatId, int userId, long preserveTime, String userMail){
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
        rabbitTemplate.convertAndSend("PROJECT_EXCHANGE", "DL", message2, correlationData ->{
            correlationData.getMessageProperties().setExpiration(Long.toString(time2));
            return correlationData;
        });

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

    @Scheduled(cron="0 50 23 * * ?")
    public void timingCloseStudyRoom(){
        System.out.println(new Date().toString());
    }
}