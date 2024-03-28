package com.project.chat.mapper;

import com.project.chat.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void register() {
        User user = new User();
        user.setUsername("麻子");
        user.setPassword("12345");
        System.out.println(userMapper.register(user));
    }

    @Test
    void login() {
        System.out.println(userMapper.login("张三"));
    }
}