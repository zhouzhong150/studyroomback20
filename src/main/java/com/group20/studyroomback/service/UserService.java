package com.group20.studyroomback.service;


import com.group20.studyroomback.entity.Response;
import com.group20.studyroomback.entity.User;

public interface UserService {

    public Response<User> selectByUserId(String userId);

    public Response<User> deleteByUserId(String userId);

    public Response insertUser(User user);

    public Response selectAll(int pageNum);

    public Response updateUser(User user);

    public Response selectByUsername(String username);

    public Response<User> selectByStuNo(String stuNo);

    public Response login(String username, String password);
}
