package com.group20.studyroomback.service;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

import java.io.IOException;

/**
 * @Author: zhouzhong
 * @Email: 21212010059@m.fudan.edu.cn
 * @Date: 2022/5/13 19:38
 * @Description:
 */
public interface MessageService {


    public int sentMail(String fromMailName, String mailMessage, String mailSubjectMessage, String toMailName);

    public void consumerMessage(Message message, Channel channel) throws IOException;

    public void produceMessage(String content, int seatId, int userId, long preserveTime, String userMail);
}