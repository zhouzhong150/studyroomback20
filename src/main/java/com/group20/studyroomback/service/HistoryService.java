package com.group20.studyroomback.service;

import com.group20.studyroomback.entity.History;

import java.util.List;

public interface HistoryService {
    public History insertHistory(History history);
    public List<History> getByUserId(String  user_id);
    public History updateHistoryByEntity(History history);
    public List<History> updateHistoriesBySeatIds(List<String> ids);
}
