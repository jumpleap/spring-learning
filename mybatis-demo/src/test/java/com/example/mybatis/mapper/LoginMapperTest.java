package com.example.mybatis.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginMapperTest {
    @Autowired
    private LoginMapper loginMapper;

    @Test
    void queryAllUser() {
        System.out.println(loginMapper.queryAllUser("desc"));
    }

    @Test
    void queryAllUserByLike() {
        System.out.println(loginMapper.queryAllUserByLike("赵"));
    }
}