package com.example.ioc.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public void sayHi() {
        System.out.println("hi, UserService...");
    }
}
