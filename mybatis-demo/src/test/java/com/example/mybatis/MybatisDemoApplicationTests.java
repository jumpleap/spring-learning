package com.example.mybatis;

import com.example.mybatis.mapper.UserInfoMapper;
import com.example.mybatis.model.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

// @SpringBootTest: 该测试类在运行时, 就会自动加载Spring的运行环境
@SpringBootTest
class MybatisDemoApplicationTests {
    // 通过@Autowired注入我们要测试的类, 就可以开始测试了
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Test
    void contextLoads() {
        List<UserInfo> userInfos = userInfoMapper.queryAllUser();
        System.out.println(userInfos);
    }

}
