package com.example.mybatis.service;

import com.example.mybatis.mapper.LoginMapper;
import com.example.mybatis.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// 登录中sql注入情况
@Service
public class LoginService {
    @Autowired
    private LoginMapper loginMapper;

    public UserInfo queryByUser(String username, String password) {
        List<UserInfo> userInfos = loginMapper.queryByUser(username, password);

        if (userInfos == null || userInfos.size() == 0) return null;

        return userInfos.get(0);

    }
}
