package com.group20.studyroomback.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sun.org.apache.xpath.internal.objects.XString;
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
    String id;
    String userId;
    String seatId;
    /**
     * 预定时间，是一个时间戳
     */
    Long preserveTime;
    /**
     * 1表示未过期，2表示已过期
     */
    int alive;

}