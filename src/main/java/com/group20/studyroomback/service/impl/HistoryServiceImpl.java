package com.group20.studyroomback.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.group20.studyroomback.entity.History;
import com.group20.studyroomback.entity.Seat;
import com.group20.studyroomback.entity.StudyRoom;
import com.group20.studyroomback.entity.User;
import com.group20.studyroomback.mapper.HistoryMapper;
import com.group20.studyroomback.mapper.SeatMapper;
import com.group20.studyroomback.mapper.UserMapper;
import com.group20.studyroomback.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zhouzhong
 * @Email: 21212010059@m.fudan.edu.cn
 * @Date: 2022/5/13 20:43
 * @Description:
 */
@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private HistoryMapper historyMapper;
    @Autowired
    private SeatMapper seatMapper;

    @Autowired
    private MessageServiceImpl messageService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public History insertHistory(History history) {
        Seat seat = new Seat();
        seat.setStatus(2);
        seat.setId(history.getSeatId());
        seatMapper.updateById(seat);
        long time = System.currentTimeMillis();
        history.setPreserveTime(time);

        User user = userMapper.selectById(history.getUserId());

        int successNum = historyMapper.insert(history);
        if (successNum > 0){
            messageService.produceMessage("test", history.getSeatId(), history.getUserId(), time, user.getEmail());

            return history;
        }else{


            return null;
        }
    }

    @Override
    public List<History> getByUserId(int user_id) {
        QueryWrapper<History> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user_id);
        List<History> histories = historyMapper.selectList(queryWrapper);
        return histories;
    }

    @Override
    public History updateHistoryByEntity(History history) {
        // 如果是取消预约，需要更新seat状态
        if (history.getAlive() == 2){
            Seat seat = new Seat();
            seat.setId(history.getSeatId());
            seat.setStatus(1);
            seatMapper.updateById(seat);
        }
        if (history.getAlive() == 1){
            Seat seat = new Seat();
            seat.setId(history.getSeatId());
            seat.setStatus(3);
            seatMapper.updateById(seat);
        }
        int success_num = historyMapper.updateById(history);
        if (success_num == 0){
            return null;
        }else{
            return historyMapper.selectById(history.getId());
        }
    }

    @Override
    public List<History> updateHistoriesBySeatIds(List<Integer> ids) {
        QueryWrapper<History> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("seat_id", ids);
        History history = new History();
        history.setAlive(0);
        int successNum = historyMapper.update(history, queryWrapper);
        if (successNum > 0){
            return historyMapper.selectList(queryWrapper);
        }else{
            return null;
        }
    }
}