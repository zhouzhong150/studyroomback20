package com.group20.studyroomback;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author: zhouzhong
 * @Email: 21212010059@m.fudan.edu.cn
 * @Date: 2022/5/13 19:22
 * @Description:
 */
@SpringBootApplication
@MapperScan("com.group20.studyroomback.mapper")
@EnableScheduling
public class StudyRoomBack20Application {
    public static void main(String[] args) {
        SpringApplication.run(StudyRoomBack20Application.class, args);
    }
}