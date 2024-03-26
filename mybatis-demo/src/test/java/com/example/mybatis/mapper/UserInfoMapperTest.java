package com.example.mybatis.mapper;

import com.example.mybatis.model.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// @SpringBootTest: 加载Spring运行环境
@SpringBootTest
class UserInfoMapperTest {
    // 注入测试类, 进行测试
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    void queryAllUser() {
        List<UserInfo> userInfos = userInfoMapper.queryAllUser();
        System.out.println(userInfos);
    }

    @Test
    void queryById() {
        System.out.println(userInfoMapper.queryById());
    }

    @Test
    void queryById2() {
        System.out.println(userInfoMapper.queryById2(1));
    }

    @Test
    void queryById3() {
        System.out.println(userInfoMapper.queryById3(1));
    }

    @Test
    void insert() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("赵六");
        userInfo.setPassword("123456");
        userInfo.setAge(12);
        userInfo.setGender(2);
        userInfo.setPhone("12672537523");
        System.out.println(userInfoMapper.insert(userInfo));
    }

    @Test
    void insert2() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("王五");
        userInfo.setPassword("123456");
        userInfo.setAge(12);
        userInfo.setGender(1);
        userInfo.setPhone("126737523");
        System.out.println(userInfoMapper.insert2(userInfo));
    }

    @Test
    void insert3() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("jerry");
        userInfo.setPassword("1234567");
        userInfo.setAge(16);
        userInfo.setGender(2);
        userInfo.setPhone("126735423");
        System.out.println(userInfoMapper.insert3(userInfo));
        // 获取自增id
        System.out.println(userInfo.getId());
    }

    @Test
    void delete() {
        System.out.println(userInfoMapper.delete(11));
    }

    @Test
    void delete2() {
        userInfoMapper.delete2(12);
    }

    @Test
    void update() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("大哥");
        userInfo.setId(1);
        System.out.println(userInfoMapper.update(userInfo));
    }

    @Test
    void update2() {
        userInfoMapper.update2("admin", 1);
    }

    @Test
    void queryAllUser2() {
        System.out.println(userInfoMapper.queryAllUser2());
    }

    @Test
    void queryAllUser4() {
        System.out.println(userInfoMapper.queryAllUser4());
    }

    @Test
    void queryAllUser5() {
        System.out.println(userInfoMapper.queryAllUser5());
    }

    @Test
    void queryAllUser6() {
        System.out.println(userInfoMapper.queryAllUser6());
    }

    @Test
    void queryAllUser7() {
        System.out.println(userInfoMapper.queryAllUser6());
    }

//    @Test
//    void queryAllUser3() {
//        System.out.println(userInfoMapper.queryAllUser3());
//    }

}