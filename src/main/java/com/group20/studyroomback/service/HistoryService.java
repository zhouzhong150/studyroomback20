package com.group20.studyroomback.service;

import com.group20.studyroomback.entity.History;

import java.util.List;

public interface HistoryService {

    public List<History> getByUserId(int user_id);
    public History updateHistory(History history);
}
