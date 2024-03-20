package com.example.ioc.controller;

import com.example.ioc.model.User;
import com.example.ioc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// 将对象存储到Spring中
@Controller
public class UserController {
    // 方法1: 属性注入
    @Autowired
    private UserService userService;

    // 当存在多个对象时, @Autowired会存在问题, User存在非唯一的对象
    // 法一: @Primary -> 指定默认的对象
    @Autowired
    private User user;
    public void sayHi() {
        System.out.println("hi, UserController...");
        userService.sayHi();
        // System.out.println(user);
    }
}
