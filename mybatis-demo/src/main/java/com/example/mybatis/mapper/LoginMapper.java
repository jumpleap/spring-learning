package com.example.mybatis.mapper;

import com.example.mybatis.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

// 登录中sql注入情况
@Mapper
public interface LoginMapper {
    List<UserInfo> queryByUser(String username, String password);

    // 我们要少使用${}, 防止sql注入, 但是我们可以使用${]来进行排序
    @Select("select id, username, age, gender, phone from user_info order by id ${sort}")
    List<UserInfo> queryAllUser(String sort);

    // 模糊查询, 我们使用MySQL的内置函数concat()函数处理, 尽量不使用${}的方式, 防止sql注入
    // concat: 将多个字符串拼接成一个字符串
    @Select("select id, username, age, gender, phone from user_info " +
            "where username like concat('%', #{key}, '%')")
    List<UserInfo> queryAllUserByLike(String key);
}
