package com.example.ioc.controller;

import com.example.ioc.model.User;
import com.example.ioc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

// 将对象存储到Spring中
@Controller
public class UserController3 {
    private UserService userService;

    // 方法3: setter方法注入
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    // 当存在多个对象时, @Autowired会存在问题, User存在非唯一的对象
    // 法三: @Resource -> 按照bean的名称进行注入, 通过name属性指定要注入的bean的名称
    @Resource(name = "u1")
    private User user;
    public void sayHi() {
        System.out.println("hi, UserController3...");
        userService.sayHi();
    }
}