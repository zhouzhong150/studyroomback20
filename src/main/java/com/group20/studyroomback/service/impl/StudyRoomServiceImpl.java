package com.group20.studyroomback.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.group20.studyroomback.entity.StudyRoom;
import com.group20.studyroomback.entity.User;
import com.group20.studyroomback.mapper.StudyRoomMapper;
import com.group20.studyroomback.service.StudyRoomService;
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
public class StudyRoomServiceImpl implements StudyRoomService {
    @Autowired
    StudyRoomMapper studyRoomMapper;

    @Override
    public List<StudyRoom> selectByID(int id) {
        QueryWrapper<StudyRoom> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        List<StudyRoom> studyRoomList = studyRoomMapper.selectList(queryWrapper);

        return studyRoomList;
    }
}