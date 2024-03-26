package com.example.mybatis.mapper;

import com.example.mybatis.model.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

// 使用注解的方式

// Mybatis的持久层接口规范一般都叫xxxMapper
// @Mapper: 表示是Mybatis中的Mapper接口
@Mapper
public interface UserInfoMapper {
    // 查询所有用户
    // @Select: 代表select查询, 也就是注解对于方法的具体实现内容
    @Select("select username, `password`, age, gender, phone from user_info")
    public List<UserInfo> queryAllUser();

    // 查询id为4的用户
    // 注: 会把查询结果赋给相应的字段, 然后这字段字段对于UseInfo中的字段
    @Select("select id, username, `password`, age, gender, phone from user_info where id = 4")
    public UserInfo queryById();

    // 使用#{}的方法获取方法中的参数, 防止固定写法
    // #{变量名} 和 方法中的参数名一致
    @Select("select username, `password`, age, gender, phone from user_info where id = #{id}")
    UserInfo queryById2(Integer id);

    // 使用@Param重命名后, #{变量名}中的变量名必须和@Param重命名后的名称一致
    @Select("select username, `password`, age, gender, phone from user_info where id = #{userId}")
    UserInfo queryById3(@Param("userId") Integer id);


    // 添加
    // 注: 传进来的对象, 对象中的属性会赋给values中的值, 进行对应
    @Insert("insert into user_info(username, `password`, age, gender, phone) " +
            "values (#{username}, #{password}, #{age}, " +
            "#{gender}, #{phone})")
    Integer insert(UserInfo userInfo);

    // insert 语句默认返回的是 受影响的行数
    // 使用@Param重命名后, 需要使用重命名后的名称.属性来获取对象中的值
    @Insert("insert into user_info(username, `password`, age, gender, phone)" +
            " values (#{userInfo.username}, #{userInfo.password}, #{userInfo.age}, " +
            "#{userInfo.gender}, #{userInfo.phone})")
    Integer insert2(@Param("userInfo") UserInfo userInfo);


    // useGeneratedKeys: 令Mybatis使用JDBC的getGeneratedKeys方法来取出数据库内部生成的主键, 默认值false
    // keyProperty: 指定能够唯一识别对象的属性, Mybatis会使用getGeneratedKeys的返回值或insert语句的selectKey子元素设置它的值
    // 注: 设置useGeneratedKeys=true之后, 方法的返回值依然是受影响的行数, 自增id会设置在上述keyProperty指定的属性中
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into user_info(username, `password`, age, gender, phone) " +
            "values (#{username}, #{password}, #{age}, " +
            "#{gender}, #{phone})")
    Integer insert3(UserInfo userInfo);


    // 删除
    @Delete("delete from user_info where id = #{id}")
    Integer delete(Integer id);

    @Delete("delete from user_info where id = #{id}")
    void delete2(Integer id);


    // 修改
    @Update("update user_info set username = #{username} where id = #{id}")
    Integer update(UserInfo userInfo);

    @Update("update user_info set username = #{username} where id = #{id}")
    void update2(String username, Integer id);


    // 查询进阶, 上述查询有几个值(_time)没有成功赋值
    // 方法用对象接收返回结果, MySQL查询出来的数据为一条, 就会自动赋值给对象
    // 方法用List<> 接收返回结果, MySQL查询出来的数据为一条或多条时, 也会自动赋给List
    // 如果MySQL查询返回多条数据, 但是使用对象接收, Mybatis会报错
    // @Select("select `id`, username, `password`, age, gender, phone," +
    //        " delete_flag, create_time, update_time from user_info")
    // 报错, 查询结果为多个, 但是只有一个对象接收
    // UserInfo queryAllUser3();

    @Select("select `id`, username, `password`, age, gender, phone," +
            " delete_flag, create_time, update_time from user_info")
    List<UserInfo> queryAllUser2();

    /*
     上述查询几个属性未赋值的原因:
        当自动映射查询结果时, Mybatis会获取结果中返回的列名并在Java类中查找相同名字的属性(忽略大小写)
     解决办法:
        1. 起别名: as
        2. 结果映射
        3. 开启驼峰命名
     */
    // 1. 起别名: 使用as
    @Select("select `id`, username, `password`, age, gender, phone," +
            " delete_flag as deleteFlag, create_time as createTime, update_time as updateTime" +
            " from user_info")
    List<UserInfo> queryAllUser4();

    // 2.结果映射
    @Results(id = "alter", value = {
            @Result(column = "delete_flag", property = "deleteFlag"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    @Select("select `id`, username, `password`, age, gender, phone," +
            " delete_flag, create_time, update_time from user_info")
    List<UserInfo> queryAllUser5();

    // 对结果映射进行复用: 使用@ResultMap主键
    @ResultMap(value = "alter")
    @Select("select `id`, username, `password`, age, gender, phone," +
            " delete_flag, create_time, update_time from user_info")
    List<UserInfo> queryAllUser6();

    // 3. 开启驼峰命名: yml文件中完成, 其他代码无需修改
    @Select("select `id`, username, `password`, age, gender, phone," +
            " delete_flag, create_time, update_time from user_info")
    List<UserInfo> queryAllUser7();
}