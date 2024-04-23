package com.project.chat.model;

import lombok.Data;

@Data
public class User {
    private int userId;
    // 防止返回的新对象中返回的都是null
    private String username = "";
    private String password = "";
}
