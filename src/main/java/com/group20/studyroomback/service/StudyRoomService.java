package com.group20.studyroomback.service;

import com.group20.studyroomback.entity.StudyRoom;

import java.util.List;

public interface StudyRoomService {

    public List<StudyRoom> selectByID(int id);
}
