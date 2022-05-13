package com.group20.studyroomback.service.impl;

import com.group20.studyroomback.service.MessageService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

/**
 * @Author: zhouzhong
 * @Email: 21212010059@m.fudan.edu.cn
 * @Date: 2022/5/13 19:41
 * @Description:
 */
@Service
public class MessageServiceImpl implements MessageService {
    @RabbitListener(queues = "PROJECT_LATTER_QUEUE")
    public void receiveD(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        System.out.println(new Date().toString());
        System.out.println(msg);


    }

}