package com.group20.studyroomback.controller;

import com.group20.studyroomback.entity.Response;
import com.group20.studyroomback.entity.StudyRoom;
import com.group20.studyroomback.service.StudyRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/list")
    public ResponseEntity<Response> getAllStudyRooms(){
        List<StudyRoom> studyRooms = studyRoomService.selectRooms();
        MultiValueMap<String, String> headers = new HttpHeaders();
        Response<List<StudyRoom>> response = new Response<>();
        if (studyRooms.size() == 0){
            response.setDetail("查无数据");
        }else{
            response.setDetail("数据查询成功！");
            response.setData(studyRooms);
        }
        return new ResponseEntity(response,headers,200);
    }


    @PostMapping("/")
    public ResponseEntity<Response> updateStudyRoom(@RequestBody StudyRoom studyRoom){
        MultiValueMap<String, String> headers = new HttpHeaders();
        Response<StudyRoom> response = new Response<>();
        if (studyRoom.getRoomName() == null || studyRoom.getId() == 0){
            response.setDetail("Roomname和id不能为空");
            return new ResponseEntity(response,headers,400);
        }
        StudyRoom studyRoom1 = studyRoomService.updateEntity(studyRoom);
        if (studyRoom1 == null){
            response.setDetail("更新失败");
        }else{
            response.setDetail("更新成功");
            response.setData(studyRoom1);
        }
        return new ResponseEntity(response,headers,200);
    }

    @PostMapping("/")
    public ResponseEntity<Response> insertStudyRoom(@RequestBody StudyRoom studyRoom){
        MultiValueMap<String, String> headers = new HttpHeaders();
        int success_num = studyRoomService.insertStudyRoom(studyRoom);
        Response<StudyRoom> response = new Response<>();
        if (success_num == 0){
            response.setDetail("插入失败");
        }else{
            response.setDetail("插入成功");
            response.setData(studyRoom);
        }
        return new ResponseEntity(response,headers,200);
    }

    @DeleteMapping("/")
    public ResponseEntity<Response> deleteStudyRoom(@RequestParam int id){
        MultiValueMap<String, String> headers = new HttpHeaders();
        int delete_num = studyRoomService.deleteById(id);
        Response<StudyRoom> response = new Response<>();
        if (delete_num == 0){
            response.setDetail("删除失败");
        }else{
            response.setDetail("删除成功");
        }
        return new ResponseEntity(response,headers,200);
    }

}