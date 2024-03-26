package com.example.mybatis.mapper;

import com.example.mybatis.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

// 使用xml的方式
@Mapper
public interface UserInfoXmlMapper {
    // 查询
    List<UserInfo> queryByUser();

    // 添加
    Integer insert(UserInfo userInfo);

    // 重命名后添加
    Integer insert2(@Param("userInfo") UserInfo userInfo);

    // 返回自增id
    Integer insert3(UserInfo userInfo);

    // 删除
    Integer delete(Integer id);

    // 修改
    Integer update(Integer id, String name);

    // 查询
    List<UserInfo> queryByUser2();

    // 查询
    List<UserInfo> queryByUser3();
}
