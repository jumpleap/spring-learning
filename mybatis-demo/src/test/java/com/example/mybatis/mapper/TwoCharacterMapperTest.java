package com.example.mybatis.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TwoCharacterMapperTest {
    @Autowired
    private TwoCharacterMapper twoCharacterMapper;

    @Test
    void queryById() {
        System.out.println(twoCharacterMapper.queryById(1));
    }

    @Test
    void queryById2() {
        System.out.println(twoCharacterMapper.queryById2(1));
    }

    @Test
    void queryByName() {
        System.out.println(twoCharacterMapper.queryByName("jack"));
    }

//    @Test
//    void queryByName2() {
//        System.out.println(twoCharacterMapper.queryByName2("jack"));
//    }

    @Test
    void queryByName3() {
        // 正常的访问情况
        // System.out.println(twoCharacterMapper.queryByName3("jack"));
        // sql注入情况
        System.out.println(twoCharacterMapper.queryByName3("' or 1 = '1"));
    }

    @Test
    void queryByName4() {
        // 正常的访问情况
        // System.out.println(twoCharacterMapper.queryByName4("jack"));
        // sql注入情况
        System.out.println(twoCharacterMapper.queryByName4("' or 1 = '1"));
    }
}