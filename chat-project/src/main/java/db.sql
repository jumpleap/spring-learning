create database if not exists chat_project charset = utf8mb4;

use chat_project;

drop table if exists user;

create table user(
    `id` int primary key auto_increment,
    username varchar(20) unique,
    `password` varchar(20) not null
);

insert into user(id, username, `password`) values (null, 'zhansgan', '123');
insert into user(id, username, `password`) values (null, 'lisi', '123');
