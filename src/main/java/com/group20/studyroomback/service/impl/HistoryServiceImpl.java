package com.group20.studyroomback.service.impl;

import com.group20.studyroomback.entity.History;
import com.group20.studyroomback.service.HistoryService;
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
    @Override
    public List<History> getByUserId(int user_id) {
        return null;
    }

    @Override
    public History updateHistory(History history) {
        return null;
    }
}