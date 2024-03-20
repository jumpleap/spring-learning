package com.study.springmvcdemo.demo;

import lombok.Data;

// @Data: 自动生成getter, setter, toString, 无参构造器和有参构造器等方法
@Data
public class MessageInfo {
    private String from;
    private String to;
    private String message;
}
