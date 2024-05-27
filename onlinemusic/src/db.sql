drop database if exists `onlinemusic`;
create database if not exists `onlinemusic` character set utf8;

-- 使用数据库
use `onlinemusic`;

-- 创建用户表
drop table if exists `user`;
create table user(
    `id` int primary key auto_increment,
    `username` varchar(20) not null,
    `password` varchar(100) not null
);

-- 创建音乐表
drop table if exists `music`;
create table music(
    `id` int primary key auto_increment,
    `title` varchar(50) not null,
    `singer` varchar(30) not null,
    `time` varchar(13) not null,
    `url` varchar(1000) not null,
    `userid` int(11) not null
);

-- 创建收藏表
drop table if exists `lovemusic`;
create table lovemusic(
    `id` int primary key auto_increment,
    `user_id` int(11) not null,
    `music_id` int(11) not null
)