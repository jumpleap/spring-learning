-- 创建数据库
create database if not exists chat_project charset utf8mb4;

-- 指定数据库
use chat_project;

-- 当表存在删除
drop table if exists user;
-- 创建表
create table user(
    userId int primary key auto_increment,
    username varchar(20) unique,
    password varchar(20)
);
-- 插入测试用例
insert into user values(null, 'zhangsan', '123');
insert into user values(null, 'lisi', '123');