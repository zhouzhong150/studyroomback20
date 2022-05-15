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
    public List<StudyRoom> selectRooms() {
        QueryWrapper<StudyRoom> queryWrapper = new QueryWrapper<>();
        List<StudyRoom> studyRoomList = studyRoomMapper.selectList(queryWrapper);
        return studyRoomList;
    }

    @Override
    public List<StudyRoom> selectRoomsByCloseTime() {
        return null;
    }

    @Override
    public StudyRoom updateEntity(StudyRoom studyRoom) {
        int success_num = studyRoomMapper.updateById(studyRoom);
        if (success_num > 0){
            return studyRoom;
        }else{
            return null;
        }
    }

    @Override
    public int deleteById(int id) {
        return studyRoomMapper.deleteById(id);
    }

    @Override
    public int insertStudyRoom(StudyRoom studyRoom) {
        return studyRoomMapper.insert(studyRoom);
    }
}