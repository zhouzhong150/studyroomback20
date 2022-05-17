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
import org.springframework.web.bind.annotation.*;

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

    /**
     * 学生获取历史座位
     * @param user_id 学生的userId
     * @return history列表
     */
    @GetMapping("/{user_id}")
    public ResponseEntity<Response> getHistoryByUserId(@PathVariable int user_id){
        MultiValueMap<String, String> headers = new HttpHeaders();
        Response<List<History>> response = new Response<>();
        if (user_id <= 0){
            response.setDetail("参数错误");
            return new ResponseEntity(response,headers,400);
        }
        List<History> histories = historyService.getByUserId(user_id);
        if (histories.size() == 0){
            response.setDetail("查无数据");
        }else{
            response.setDetail("数据查询成功！");
            response.setData(histories);
        }
        return new ResponseEntity(response,headers,200);
    }

    /**
     * 学生预约座位
     * @param history 新增history并改变seat状态
     * @return 新增的hisotry实体
     */
    @PostMapping("")
    public ResponseEntity<Response> insertHistory(@RequestBody History history){
        MultiValueMap<String, String> headers = new HttpHeaders();
        Response<History> response = new Response<>();
        if (history.getId() != 0 || history.getSeatId() == 0 || history.getUserId() == 0 || history.getAlive() == 2){
            response.setDetail("Bad Request");
            return new ResponseEntity(response,headers,400);
        }
        History history1 = historyService.insertHistory(history);
        if (history1 == null){
            response.setDetail("座位预约失败");
            return new ResponseEntity(response,headers,200);
        }else{
            response.setDetail("座位预约成功");
            response.setData(history1);
            return new ResponseEntity(response,headers,200);
        }
    }

    /**
     * 学生取消预约座位和签到和签退。
     * @param history 更新history状态和seat状态
     * @return 更新后的history
     */
    @PutMapping("")
    public ResponseEntity<Response> updateHistory(History history){
        MultiValueMap<String, String> headers = new HttpHeaders();
        Response<History> response = new Response<>();
        if (history.getSeatId() == 0 || history.getId() == 0 || history.getUserId() == 0){
            response.setDetail("id不能为0或必须传入id");
            if (history.getAlive() != 1 || history.getAlive() != 2){
                response.setDetail("alive参数必须为1或2");
            }
            return new ResponseEntity(response,headers,400);
        }
        History newHistory = historyService.updateHistoryByEntity(history);
        if (newHistory == null){
            response.setDetail("更新失败");
            return new ResponseEntity(response,headers,200);
        }else{
            response.setDetail("更新成功");
            response.setData(newHistory);
            return new ResponseEntity(response,headers,200);
        }
    }
}