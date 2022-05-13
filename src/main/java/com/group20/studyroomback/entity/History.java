package com.group20.studyroomback.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @Author: zhouzhong
 * @Email: 21212010059@m.fudan.edu.cn
 * @Date: 2022/5/13 19:23
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("history")
public class History {
    int id;
    int userId;
    int seatId;
    Long preserveTime;
    int alive;

}