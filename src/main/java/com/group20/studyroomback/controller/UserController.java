package com.group20.studyroomback.controller;
import com.group20.studyroomback.entity.Response;
import com.group20.studyroomback.entity.User;
import com.group20.studyroomback.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 * @Author: zhouzhong
 * @Email: 21212010059@m.fudan.edu.cn
 * @Date: 2022/5/13 20:02
 * @Description:
 */

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    /**
     *
     * @param page
     * @return Map
     */
    @GetMapping("/list")
    public ResponseEntity<Response> listUsers(@RequestParam("page") Integer page){
        return null;
    }

    @PostMapping("/user")
    public ResponseEntity<Response<User>> insertUser(@RequestBody User user){
        MultiValueMap<String, String> headers = new HttpHeaders();
        Response response = userService.insertUser(user);
        return new ResponseEntity(response, headers, response.getStatus());
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody User user){
        MultiValueMap<String, String> headers = new HttpHeaders();
        Response response = userService.login(user.getUsername(), user.getPassword());
        return new ResponseEntity<>(response, headers, response.getStatus());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable("id") Integer id){
        return null;
    }
}