package com.group20.studyroomback.controller;

import com.group20.studyroomback.service.MessageService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: zhouzhong
 * @Email: 21212010059@m.fudan.edu.cn
 * @Date: 2022/5/13 19:35
 * @Description:
 */
@RestController
@RequestMapping("message")
@CrossOrigin
public class MessageController {


    @Autowired
    public MessageService messageService;

    @GetMapping("/sendMessage")
    public void sendMessage(){
        System.out.println("进入");
        //messageService.produceMessage("test", 1, 1);

    }


    @GetMapping("/test")
    public String test(){

        return "hello world";

    }




}