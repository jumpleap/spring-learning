package com.example.ioc.controller;

import com.example.ioc.model.User;
import com.example.ioc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

// 将对象存储到Spring中
@Controller
public class UserController2 {
    private UserService userService;

    // 方法二: 构造方法注入
    @Autowired
    public UserController2(UserService userService) {
        this.userService = userService;
    }
    // 注: 如果类只有一个构造方法, 那么@Autowired注解可以省略;
    // 如果类中有多个构造方法, 那么需要添加上@Autowired明确指定到底使用那个构造方法

    // 当存在多个对象时, @Autowired会存在问题, User存在非唯一的对象
    // 法二: @Qualifier -> 指定要注入的bean对象, 在value属性中填写bean的名称
    // 必须搭配@Autowired使用
    @Qualifier("user2")
    @Autowired
    private User user;
    public void sayHi() {
        System.out.println("hi, UserController2...");
        userService.sayHi();
    }
}