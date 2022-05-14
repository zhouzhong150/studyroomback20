package com.group20.studyroomback.service;


import com.group20.studyroomback.entity.Response;
import com.group20.studyroomback.entity.User;

import java.util.List;

public interface UserService {

    public Response<User> selectByUserId(int userId);

    public Response insertUser(User user);


    public Response updateUser(User user);

    public Response selectByUsername(String username);

    public Response<User> selectByStuNo(String stuNo);

    public Response login(String username, String password);
}
