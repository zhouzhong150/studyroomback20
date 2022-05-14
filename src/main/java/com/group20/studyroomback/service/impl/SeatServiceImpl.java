package com.group20.studyroomback.service.impl;

import com.group20.studyroomback.entity.Seat;
import com.group20.studyroomback.mapper.SeatMapper;
import com.group20.studyroomback.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: zhouzhong
 * @Email: 21212010059@m.fudan.edu.cn
 * @Date: 2022/5/13 20:44
 * @Description:
 */
@Service
public class SeatServiceImpl implements SeatService {
    @Autowired
    private SeatMapper seatMapper;
    @Override
    public Seat update(Seat seat) {
        int successNum = seatMapper.updateById(seat);
        if (successNum == 0){
            return null;
        }else{
            return seatMapper.selectById(seat.getId());
        }
    }

    @Override
    public Seat getSeatById(int id) {
        return seatMapper.selectById(id);
    }
}