package com.example.mybatis.model;

import lombok.Data;

import java.util.Date;

@Data
// 字段需要和数据库中表的字段相匹配
public class UserInfo {
    private Integer id;
    private String username;
    private String password;
    private Integer age;
    private Integer gender;
    private String phone;
    private Integer deleteFlag;
    private Date createTime;
    private Date updateTime;
    private String genderCN;
}
