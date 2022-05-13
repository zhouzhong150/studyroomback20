package com.group20.studyroomback.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhouzhong
 * @Email: 21212010059@m.fudan.edu.cn
 * @Date: 2021/11/26 12:41
 * @Description:
 */
@Configuration
public class DirectRabbitConfig {
    public static final String PROJECT_EXCHANGE = "PROJECT_EXCHANGE";
    public static final String PROJECT_DEAD_QUEUE = "PROJECT_DEAD_QUEUE";
    public static final String PROJECT_DEAD_EXCHANGE = "PROJECT_DEAD_EXCHANGE";
    public static final String PROJECT_LATTER_QUEUE = "PROJECT_LATTER_QUEUE";


    @Bean("PROJECT_EXCHANGE")
    public DirectExchange projectExchange(){
        return new DirectExchange(PROJECT_EXCHANGE);
    }


    @Bean("PROJECT_DEAD_EXCHANGE")
    public DirectExchange projectDeadExchange(){
        return new DirectExchange(PROJECT_DEAD_EXCHANGE);
    }

    @Bean("PROJECT_DEAD_QUEUE")
    public Queue projectDeadQueue(){
        Map<String, Object> args = new HashMap<>(3);
        //声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", PROJECT_DEAD_EXCHANGE);
        //声明当前队列的死信路由 key
        args.put("x-dead-letter-routing-key", "DL");
        //声明队列的 TTL
        //args.put("x-message-ttl", 10000);
        return QueueBuilder.durable(PROJECT_DEAD_QUEUE).withArguments(args).build();
    }

    @Bean
    public Binding queueBindingExchange(@Qualifier("PROJECT_DEAD_QUEUE") Queue projectDeadQueue,
                                  @Qualifier("PROJECT_EXCHANGE") DirectExchange projectExchange){
        return BindingBuilder.bind(projectDeadQueue).to(projectExchange).with("DL");
    }


    @Bean("PROJECT_LATTER_QUEUE")
    public Queue projectLatterQueue(){
        return new Queue(PROJECT_LATTER_QUEUE);
    }
    //声明死信队列 QD 绑定关系

    @Bean
    public Binding latterQueueBindingDeadExchange(@Qualifier("PROJECT_LATTER_QUEUE") Queue projectLatterQueue,
                                        @Qualifier("PROJECT_DEAD_EXCHANGE") DirectExchange projectDeadExchange){
        return BindingBuilder.bind(projectLatterQueue).to(projectDeadExchange).with("DL");
    }
}






