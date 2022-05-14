package com.group20.studyroomback.service;


import com.group20.studyroomback.entity.Response;
import com.group20.studyroomback.entity.User;

import java.util.List;

public interface UserService {

    public List<User> testSelect();

    public Response insertUser(User user);

    public Response selectByUsername(String username);

    public Response login(String username, String password);
}
