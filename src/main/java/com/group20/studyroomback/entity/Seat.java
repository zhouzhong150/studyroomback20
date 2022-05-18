package com.group20.studyroomback.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zhouzhong
 * @Email: 21212010059@m.fudan.edu.cn
 * @Date: 2022/5/13 19:23
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("seat")
public class Seat {
    String id;
    String studyRoomId;
    String seatName;
    int positionRow;
    int positionColumn;
    /**
     * 1代表靠窗，2代表不靠窗
     */
    int isNearWindow;
    /**
     * 1代表可以充电，2代表不可以
     */
    int hasPower;
    /**
     * 1 未占用，2已占用，3已签到，4不可用
     */
    int status;
}