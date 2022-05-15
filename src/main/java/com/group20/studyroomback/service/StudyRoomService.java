package com.group20.studyroomback.service;

import com.group20.studyroomback.entity.StudyRoom;

import java.util.List;

public interface StudyRoomService {

    public List<StudyRoom> selectRooms();
    public List<StudyRoom> selectRoomsByCloseTime(int closeTime);
    public StudyRoom updateEntity(StudyRoom studyRoom);

    public int deleteById(int id);
    public int insertStudyRoom(StudyRoom studyRoom);
}
