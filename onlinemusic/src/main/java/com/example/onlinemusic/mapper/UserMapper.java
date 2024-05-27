package com.example.onlinemusic.mapper;

import com.example.onlinemusic.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    // 登录
    User login(User loginUser);

    // 加密登录
    User selectByName(String username);
}
