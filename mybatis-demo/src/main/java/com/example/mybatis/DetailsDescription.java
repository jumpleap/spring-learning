package com.example.mybatis;

// 知识点整合
public class DetailsDescription {
    /*
        Mybatis: MyBatis是⼀款优秀的持久层框架，⽤于简化JDBC的开发
            1.持久层：指的就是持久化操作的层,通常指数据访问层(dao),是⽤来操作数据库的.
            2.MyBatis更简单完成程序和数据库交互的框架，也就是更简单的操作和读取数据库⼯具

        Mybatis操作数据库的步骤:
            1. 准备⼯作(创建springboot⼯程、数据库表准备、实体类)
            2. 引⼊Mybatis的相关依赖，配置Mybatis(数据库连接信息)
            3. 编写相关操作代码, 编写SQL语句(注解/XML)
            4. 测试

        配置数据库连接字符串:
            spring:
              datasource:
                url: jdbc:mysql://127.0.0.1:3306/mybatis_test?characterEncoding=utf8&useSSL=false
                username: root
                password: 123456
                driver-class-name: com.mysql.cj.jdbc.Driver

        Mybatis的持久层接⼝规范⼀般都叫XxxMapper
            @Mapper注解：表⽰是MyBatis中的Mapper接⼝
            程序运⾏时,框架会⾃动⽣成接⼝的实现类对象(代理对象)，并给交Spring的IOC容器管理
            @Select注解：代表的就是select查询，也就是注解对应⽅法的具体实现内容.

        测试类上添加了注解@SpringBootTest，该测试类在运⾏时，就会⾃动加载Spring的运⾏环境.
        我们通过@Autowired这个注解,注⼊我们要测试的类

        Insert语句默认返回的是 受影响的⾏数

        #{} 和 ${}的区别
            1.#{}使用的是预编译sql, ${}使用的是字符替换
            2.#{}的性能更高: 预编译SQL，编译⼀次之后会将编译后的SQL语句缓存起来，后⾯再次执⾏这条语句时，不会再次编译
            3.#{}更安全, ${}会导致sql注入
            注: sql注入: 是通过操作输⼊的数据来修改事先定义好的SQL语句，以达到执⾏代码对服务器进⾏攻击的⽅法
            4.${}可用于排序, 而#{}不行

        数据库连接池: 避免频繁的创建连接,销毁连接
            1.没有使⽤数据库连接池的情况:每次执⾏SQL语句,要先创建⼀个新的连接对象,然后执⾏SQL语句,SQL
                语句执⾏完,再关闭连接对象释放资源.这种重复的创建连接,销毁连接⽐较消耗资源
            2.使⽤数据库连接池的情况:程序启动时,会在数据库连接池中创建⼀定数量的Connection对象,当客⼾
                请求数据库连接池,会从数据库连接池中获取Connection对象,然后执⾏SQL,
                SQL语句执⾏完,再把Connection归还给连接池
            3.优点: 减少了⽹络开销, 资源重⽤, 提升了系统的性能
            4.常见的数据库连接池: C3P0, DBCP, Druid(流行), Hikari(流行)
            注: Hikari是SpringBoot的默认数据库连接池

        MySQL规范:
            1.表名, 字段名使⽤⼩写字⺟或数字, 单词之间以下划线分割. 尽量避免出现数字开头或者两个下划线中间只出现数字.
            2.表必备三字段:id, create_time, update_time
            3.在表查询中, 避免使⽤*作为查询的字段列表,标明需要哪些字段
     */
}
