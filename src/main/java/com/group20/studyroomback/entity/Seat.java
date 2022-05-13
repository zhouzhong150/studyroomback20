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
    int id;
    String seatName;
    int positionRow;
    int positionColumn;
    int isNearWindow;
    int hasPower;
    int status;




}