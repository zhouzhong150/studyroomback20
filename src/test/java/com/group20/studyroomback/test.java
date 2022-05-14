package com.group20.studyroomback;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * @Author: zhouzhong
 * @Email: 21212010059@m.fudan.edu.cn
 * @Date: 2022/5/14 20:55
 * @Description:
 */
@SpringBootTest
public class test {

    @Autowired
    public JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;


    @Test
    public void test(){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);//发送者
        mailMessage.setTo("1097449124@qq.com");//接收者
        mailMessage.setSubject("邮件测试主题");//邮件测试主题
        mailMessage.setText("邮件文本内容");
        javaMailSender.send(mailMessage);
        System.out.println("ok");




    }
}