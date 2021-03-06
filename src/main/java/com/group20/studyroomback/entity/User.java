package com.group20.studyroomback.entity;

import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("user")
public class User {

    String id;
    /**
     * 用户角色 1代表管理员，2代表普通用户
     */
    int role;
    String username;
    String password;
    String stuNo;
    String phoneNum;
    String email;
    int delayTimes;




}