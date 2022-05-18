package com.group20.studyroomback.service;

import com.group20.studyroomback.entity.Seat;

import java.util.List;

public interface SeatService {
    public Seat update(Seat seat);

    public Seat getSeatById(String id);

    public List<Seat> updateSeatsByRoomIds(List<String> roomIds, int status);

    public List<Seat> getSeatsByRoomId(String roomId);

    public Seat getRecommendSeat();
}
