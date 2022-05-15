package com.group20.studyroomback.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.group20.studyroomback.entity.History;
import com.group20.studyroomback.entity.StudyRoom;
import com.group20.studyroomback.mapper.HistoryMapper;
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
    @Override
    public List<History> getByUserId(int user_id) {
        QueryWrapper<History> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user_id);
        List<History> histories = historyMapper.selectList(queryWrapper);
        return histories;
    }

    @Override
    public History updateHistoryByEntity(History history) {
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