package com.group20.studyroomback.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @Author: zhouzhong
 * @Email: 21212010059@m.fudan.edu.cn
 * @Date: 2022/5/13 19:23
 * @Description:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("study_room")
public class StudyRoom {
    int id;
    String roomName;
    Long closeTime;
    Long openTime;
    int roomRow;
    int roomColumn;
}