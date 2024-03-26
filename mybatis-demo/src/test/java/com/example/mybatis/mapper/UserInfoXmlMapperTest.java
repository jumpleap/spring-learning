package com.example.mybatis.mapper;

import com.example.mybatis.model.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserInfoXmlMapperTest {
    @Autowired
    private UserInfoXmlMapper userInfoXmlMapper;

    @Test
    void queryByUser() {
        System.out.println(userInfoXmlMapper.queryByUser());
    }

    @Test
    void insert() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("可爱");
        userInfo.setPassword("123131");
        userInfo.setAge(12);
        userInfo.setGender(1);
        userInfo.setPhone("1856716763");
        System.out.println(userInfoXmlMapper.insert(userInfo));
    }

    @Test
    void insert2() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("多可爱");
        userInfo.setPassword("123131");
        userInfo.setAge(12);
        userInfo.setGender(1);
        userInfo.setPhone("1856716763");
        System.out.println(userInfoXmlMapper.insert2(userInfo));
    }

    @Test
    void insert3() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("可爱多");
        userInfo.setPassword("1231321");
        userInfo.setAge(12);
        userInfo.setGender(1);
        userInfo.setPhone("1856716753");
        System.out.println(userInfoXmlMapper.insert3(userInfo));
        // 获取自增id
        System.out.println(userInfo.getId());
    }

    @Test
    void delete() {
        System.out.println(userInfoXmlMapper.delete(10));
    }

    @Test
    void update() {
        System.out.println(userInfoXmlMapper.update(4, "jack"));
    }

    @Test
    void queryByUser2() {
        System.out.println(userInfoXmlMapper.queryByUser2());
    }

    @Test
    void queryByUser3() {
        System.out.println(userInfoXmlMapper.queryByUser3());
    }
}