package com.example.multidatasource.controller;

import com.example.multidatasource.annotation.Router;
import com.example.multidatasource.entity.User;
import com.example.multidatasource.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : dengchao
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserMapper userMapper;



    @PostMapping("/save")
    @Router
    public void insertUser(User user){
        userMapper.insertUser(user);
    }
}
