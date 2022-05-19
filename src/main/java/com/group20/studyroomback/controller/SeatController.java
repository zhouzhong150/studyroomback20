package com.group20.studyroomback.controller;

import com.group20.studyroomback.entity.History;
import com.group20.studyroomback.entity.Response;
import com.group20.studyroomback.entity.Seat;
import com.group20.studyroomback.service.SeatService;
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
@RequestMapping("seat")
@CrossOrigin
public class SeatController {
    @Autowired
    private SeatService seatService;
    @GetMapping("/studyroom/{id}")
    public ResponseEntity<Response> getSeatsByStudyRoom(@PathVariable String id){
        MultiValueMap<String, String> headers = new HttpHeaders();
        Response<List<Seat>> response = new Response<>();
        if (id == null){
            response.setStatus(400);
            response.setDetail("参数错误");
            return new ResponseEntity(response,headers,400);
        }
        List<Seat> seats = seatService.getSeatsByRoomId(id);
        if (seats.size() == 0){
            response.setStatus(200);
            response.setDetail("查无seat数据！");
        }else{
            response.setStatus(200);
            if (seats.size() > 10){
                seats = seats.subList(0, 10);
            }
            response.setData(seats);
            response.setDetail("查询数据成功");
        }
        return new ResponseEntity(response,headers,200);
    }

    @GetMapping("/recommend")
    public ResponseEntity<Response> getRecommendSeat(){
        MultiValueMap<String, String> headers = new HttpHeaders();
        Response<Seat> response = new Response<>();
        Seat seat = seatService.getRecommendSeat();
        if (seat == null){
            response.setStatus(200);
            response.setDetail("无推荐");
        }else{
            response.setStatus(200);
            response.setDetail("推荐成功");
            response.setData(seat);
        }
        return new ResponseEntity(response,headers,200);
    }
}