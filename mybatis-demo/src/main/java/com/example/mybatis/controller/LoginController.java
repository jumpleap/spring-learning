package com.example.mybatis.controller;

import com.example.mybatis.model.UserInfo;
import com.example.mybatis.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 登录中sql注入情况
@RequestMapping("/login")
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping("/query")
    public Boolean queryByUser(String username, String password) {
        UserInfo userInfo = loginService.queryByUser(username, password);
        if (userInfo == null) {
            return false;
        }
        return true;
    }
}
