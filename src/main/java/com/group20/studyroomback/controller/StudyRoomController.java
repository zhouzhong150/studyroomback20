package com.group20.studyroomback.controller;

import com.group20.studyroomback.entity.StudyRoom;
import com.group20.studyroomback.service.StudyRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: zhouzhong
 * @Email: 21212010059@m.fudan.edu.cn
 * @Date: 2022/5/13 20:46
 * @Description:
 */
@RestController
@RequestMapping("study_room")
public class StudyRoomController {

    @Autowired
    StudyRoomService studyRoomService;

    @GetMapping("test")
    public String test(){

        List<StudyRoom> studyRoomList = studyRoomService.selectByID(1);
        System.out.println(studyRoomList);
        return "hello world";
    }
}