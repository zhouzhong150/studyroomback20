package com.group20.studyroomback.service;

import com.group20.studyroomback.entity.Seat;

import java.util.List;

public interface SeatService {
    public Seat update(Seat seat);

    public Seat getSeatById(int id);

    public List<Seat> updateSeatsByRoomIds(List<Integer> roomIds, int status);
}
