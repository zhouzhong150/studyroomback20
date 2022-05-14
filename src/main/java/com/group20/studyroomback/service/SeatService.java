package com.group20.studyroomback.service;

import com.group20.studyroomback.entity.Seat;

public interface SeatService {
    public Seat update(Seat seat);

    public Seat getSeatById(int id);
}
