create database if not exists java_chatroom charset utf8;

use java_chatroom;

drop table if exists user;

create table user(
    userId int primary key auto_increment,
    username varchar(20) unique,
    password varchar(20)
);

insert into user values (null, 'zhangsan', '123');
insert into user values (null, 'lisi', '123');

-- 创建好友表
drop table if exists friend;
create table friend (
    userId int,
    friendId int
);

insert into friend values(1, 2);
insert into friend values(2, 1);
insert into friend values(1, 3);
insert into friend values(3, 1);
insert into friend values(1, 4);
insert into friend values(4, 1);

-- 创建会话表
drop table if exists message_session;
create table message_session(
    sessionId int primary key auto_increment,
    -- 上次访问时间
    lastTime datetime
);

insert into message_session values (1, '2023-06-12 12:12:12');
insert into message_session values (2, '2023-06-13 12:12:12');

-- 创建会话用户关联表
drop table if exists message_session_user;
create table message_session_user(
    sessionId int,
    userId int
);

-- 1号会话里有张三和李四
insert into message_session_user values (1, 1), (1, 2);
-- 2号会话里有张三和麻子
insert into message_session_user values (2, 1), (2, 3);