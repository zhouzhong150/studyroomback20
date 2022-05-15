package com.group20.studyroomback.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.group20.studyroomback.entity.Response;
import com.group20.studyroomback.entity.User;
import com.group20.studyroomback.mapper.UserMapper;
import com.group20.studyroomback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

/**
 * @Author: zhouzhong
 * @Email: 21212010059@m.fudan.edu.cn
 * @Date: 2022/5/13 20:00
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Response<User> selectByUserId(int userId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userId);
        List<User> userList = userMapper.selectList(queryWrapper);
        if (userList.size() == 1){
            return new Response<>(200, "查询成功", userList.get(0));
        }
        return new Response<>(200, "无此用户", null);

    }


    @Override
    public Response<User> insertUser(User user) {
        if (user.getUsername() == null | Objects.equals(user.getUsername(), "")|
        user.getEmail() == null | Objects.equals(user.getEmail(), "") |
        user.getPassword() == null | Objects.equals(user.getPassword(), "") |
        user.getPhoneNum() == null | Objects.equals(user.getPhoneNum(), "") |
        user.getStuNo() == null | Objects.equals(user.getStuNo(), "")
        ){
            return new Response<>(400, "注册失败，缺少必须数据，请重新填写注册数据", null);
        }

        Response<User> response = selectByUsername(user.getUsername());
        if (response.getStatus() == 400 | response.getData() != null){
            return new Response<>(400, "注册失败，账号已被使用", null);
        }

        Response<User> response_ = selectByStuNo(user.getStuNo());
        System.out.println(response_);
        if (response_.getStatus() == 400 | response_.getData() != null){
            return new Response<>(400, "注册失败，学号已被使用", null);
        }
        user.setRole(0);
        user.setDelayTimes(0);
        int status = userMapper.insert(user);

        if (status == 1){
            return new Response(200, "注册成功", user);
        }
        return new Response(400, "注册失败", null);

    }

    @Override
    public Response<User> updateUser(User user) {
        int num = userMapper.updateById(user);
        if (num == 0) {
            return new Response<>(400, "更新失败，id错误", null);
        }
        return selectByUserId(user.getId());
    }

    @Override
    public Response<User> selectByUsername(String username) {
        try{
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username", username);
            List<User> userList = userMapper.selectList(queryWrapper);
            if (userList.size()==0){
                return new Response(200, "查找成功", null);
            }else {
                return new Response(200, "查找成功", userList.get(0));
            }

        }catch(Exception e){
            return new Response(400, "查找失败", null);
        }

    }

    @Override
    public Response<User> selectByStuNo(String stuNo) {
        try{
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("stu_no", stuNo);
            List<User> userList = userMapper.selectList(queryWrapper);
            if (userList.size()==0){
                return new Response(200, "查找成功", null);
            }else {
                return new Response(200, "查找成功", userList.get(0));
            }

        }catch(Exception e){
            return new Response(400, "查找失败", null);
        }

    }


    @Override
    public Response login(String username, String password) {
        if (Objects.equals(username, "") | Objects.equals(password, "")){
            return new Response(400, "用户名或者密码为空", null);
        }

        Response<User> response = selectByUsername(username);
        if (response.getStatus() == 200 && response.getData() != null){
            if(Objects.equals(password, response.getData().getPassword())){
                return new Response(200, "登录成功", response.getData());
            }
            return new Response(400, "登录失败，密码错误", null);
        }
        return new Response(400, "该账号不存在", null);
    }


}