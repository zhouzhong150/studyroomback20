package com.group20.studyroomback.controller;

import com.group20.studyroomback.entity.History;
import com.group20.studyroomback.entity.Response;
import com.group20.studyroomback.entity.StudyRoom;
import com.group20.studyroomback.mapper.HistoryMapper;
import com.group20.studyroomback.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: zhouzhong
 * @Email: 21212010059@m.fudan.edu.cn
 * @Date: 2022/5/13 20:45
 * @Description:
 */
@RestController
@RequestMapping("history")
public class HistoryController {
    @Autowired
    private HistoryService historyService;
    @GetMapping("/{user_id}")
    public ResponseEntity<Response> getHistoryByUserId(@PathVariable int user_id){
        List<History> histories = historyService.getByUserId(user_id);
        MultiValueMap<String, String> headers = new HttpHeaders();
        Response<List<History>> response = new Response<>();
        if (user_id <= 0){
            response.setDetail("参数错误");
            return new ResponseEntity(response,headers,400);
        }
        if (histories.size() == 0){
            response.setDetail("查无数据");
        }else{
            response.setDetail("数据查询成功！");
            response.setData(histories);
        }
        return new ResponseEntity(response,headers,200);
    }
}