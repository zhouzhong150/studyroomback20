package com.group20.studyroomback.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.group20.studyroomback.entity.Seat;
import com.group20.studyroomback.entity.StudyRoom;
import com.group20.studyroomback.entity.User;
import com.group20.studyroomback.mapper.SeatMapper;
import com.group20.studyroomback.mapper.StudyRoomMapper;
import com.group20.studyroomback.service.StudyRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

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
    @Autowired
    private SeatMapper seatMapper;

    @Override
    public List<StudyRoom> selectRooms() {
        QueryWrapper<StudyRoom> queryWrapper = new QueryWrapper<>();
        List<StudyRoom> studyRoomList = studyRoomMapper.selectList(queryWrapper);
        return studyRoomList;
    }

    @Override
    public List<StudyRoom> selectRoomsByCloseTime(int closeTime) {
        QueryWrapper<StudyRoom> queryWrapper = new QueryWrapper<>();
        queryWrapper.le("close_time", closeTime);
        return studyRoomMapper.selectList(queryWrapper);

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
    public int deleteById(String id) {
        return studyRoomMapper.deleteById(id);
    }

    @Override
    public int insertStudyRoom(StudyRoom studyRoom) {
        studyRoom.setId(UUID.randomUUID().toString().trim().replaceAll("-", ""));
        int successNum = studyRoomMapper.insert(studyRoom);
        if (successNum == 0){
            return successNum;
        }
        for (int i=1; i<=studyRoom.getRoomRow(); i++)
        {
            for (int j=1; j<=studyRoom.getRoomColumn(); j++){
                Seat seat = new Seat();
                seat.setId(UUID.randomUUID().toString().trim().replaceAll("-", ""));
                if (Math.random() >0.8){
                    seat.setHasPower(1);
                }
                if (Math.random() >0.8){
                    seat.setIsNearWindow(1);
                }
                seat.setSeatName(Integer.toString(i) + "-" + Integer.toString(j));
                seat.setStatus(1);
                seat.setStudyRoomId(studyRoom.getId());
                seat.setPositionRow(i);
                seat.setPositionColumn(j);
                seatMapper.insert(seat);
            }
        }
        return successNum;
    }
}