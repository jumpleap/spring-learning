package com.example.mybatis.mapper;

import com.example.mybatis.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

// #{} 和 ${}的区别
@Mapper
public interface TwoCharacterMapper {
    // Integer类型参数
    // 使用#{}: 预编译sql
    @Select("select username, age, gender, phone from user_info where id = #{id}")
    UserInfo queryById(Integer id);

    // 使用${}: 直接拼接
    @Select("select username, age, gender, phone from user_info where id = ${id}")
    UserInfo queryById2(Integer id);

    // String类型参数: #{}会判断字段类型
    @Select("select username, age, gender, phone from user_info where username = #{name}")
    UserInfo queryByName(String name);

    // ${}: 直接拼接, 不会判断字段类型, 不会给字符串加上'', 报错
//    @Select("select username, age, gender, phone from user_info where username = ${name}")
//    UserInfo queryByName2(String name);

    /*
        总结:
            1.#{}使用的是预编译sql, 通过?占位符的方式提前对sql进行编译, 然后把参数填充到sql语句中, #{}会根据参数类型,自动拼接''
            2.${}会直接进行字符替换, 一起对sql进行编译, 如果参数为字符串, 需要加上''
     */

    // ${}可能导致sql注入
    // sql注入: 是通过操作输⼊的数据来修改事先定义好的SQL语句，以达到执⾏代码对服务器进⾏攻击的⽅法
    @Select("select username, age, gender, phone from user_info where username = '${name}'")
    List<UserInfo> queryByName3(String name);

    // 试试相同情况下#{}的情况
    // 结果得知: 不会出现sql注入的情况
    @Select("select username, age, gender, phone from user_info where username = #{name}")
    List<UserInfo> queryByName4(String name);

}