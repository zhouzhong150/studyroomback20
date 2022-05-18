package com.group20.studyroomback.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.group20.studyroomback.entity.History;
import com.group20.studyroomback.entity.Seat;
import com.group20.studyroomback.entity.StudyRoom;
import com.group20.studyroomback.mapper.HistoryMapper;
import com.group20.studyroomback.mapper.SeatMapper;
import com.group20.studyroomback.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Autowired
    private HistoryMapper historyMapper;
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
    public Seat getSeatById(String id) {
        return seatMapper.selectById(id);
    }

    @Override
    public List<Seat> updateSeatsByRoomIds(List<String> ids, int status) {
        QueryWrapper<Seat> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("study_room_id", ids);
        Seat seat = new Seat();
        seat.setStatus(status);
        int successNum = seatMapper.update(seat, queryWrapper);
        if (successNum > 0){
            return seatMapper.selectList(queryWrapper);
        }else{
            return null;
        }
    }

    @Override
    public List<Seat> getSeatsByRoomId(String roomId) {
        QueryWrapper<Seat> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("study_room_id", roomId);
        return seatMapper.selectList(queryWrapper);
    }

    @Override
    public Seat getRecommendSeat() {
        QueryWrapper<History> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("alive", 2);
        queryWrapper.orderByDesc("preserve_time");
        History history = historyMapper.selectOne(queryWrapper);
        return seatMapper.selectById(history.getId());
    }
}