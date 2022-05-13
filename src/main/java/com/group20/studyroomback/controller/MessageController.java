package com.group20.studyroomback.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhouzhong
 * @Email: 21212010059@m.fudan.edu.cn
 * @Date: 2022/5/13 19:35
 * @Description:
 */
@RestController
@RequestMapping("message")
public class MessageController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @GetMapping("/sendMessage/{message}")
    public void sendMsg(@PathVariable String message){
        System.out.println("进入");
        rabbitTemplate.convertAndSend("PROJECT_EXCHANGE", "DL", "消息10S: "+message, correlationData ->{
            correlationData.getMessageProperties().setExpiration("10000");
            return correlationData;
        });
    }


    @GetMapping("/test")
    public String test(){

        return "hello world";

    }




}