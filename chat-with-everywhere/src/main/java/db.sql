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
