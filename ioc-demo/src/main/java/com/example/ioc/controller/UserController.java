package com.example.ioc.controller;

import org.springframework.stereotype.Controller;

// 将对象存储到Spring中
@Controller
public class UserController {
    public void sayHi() {
        System.out.println("hi, UserController...");
    }
}
