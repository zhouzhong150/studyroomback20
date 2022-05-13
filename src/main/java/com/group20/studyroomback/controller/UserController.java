package com.group20.studyroomback.controller;

import com.group20.studyroomback.entity.User;
import com.group20.studyroomback.service.UserService;
import com.group20.studyroomback.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: zhouzhong
 * @Email: 21212010059@m.fudan.edu.cn
 * @Date: 2022/5/13 20:02
 * @Description:
 */

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/test")
    public String testUser(){
        List<User> userList = userService.testSelect();
        System.out.println(userList);
        return "success";
    }
}