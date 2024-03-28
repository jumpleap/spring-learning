package com.project.chat.mapper;

import com.project.chat.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    // 注册: 把用户信息添加到数据库中
    int register(User user);

    // 登录: 数据库中查找用户名
    User login(String username);
}
