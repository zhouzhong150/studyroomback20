package com.group20.studyroomback.controller;

import com.group20.studyroomback.entity.Response;
import com.group20.studyroomback.entity.Seat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: zhouzhong
 * @Email: 21212010059@m.fudan.edu.cn
 * @Date: 2022/5/13 20:46
 * @Description:
 */
@RestController
@RequestMapping("seat")
public class SeatController {
    @GetMapping("/studyroom/{id}")
    public ResponseEntity<Response> getSeatsByStudyRoom(@PathVariable int id){
        return null;
    }
    @GetMapping("/recommend")
    public ResponseEntity<Response> getRecommendSeat(){
        return null;
    }
}