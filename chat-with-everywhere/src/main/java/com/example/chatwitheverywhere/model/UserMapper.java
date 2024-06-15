package com.example.chatwitheverywhere.model;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    // 注册，把用户插入到用户表中
    int insert(User user);

    // 登录，从用户表中查找用户进行登录
    User selectByName(String name);
}
