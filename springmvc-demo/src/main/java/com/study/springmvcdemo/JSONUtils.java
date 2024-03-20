package com.study.springmvcdemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.springmvcdemo.Person;

// Java对象和JSON字符串互转
public class JSONUtils {
    // 使用这个对象完成JSON字符串和Java对象的互转
    // writeValueAsString: 把对象转为JSON字符串
    // readValue: 把JSON字符串转为Java对象
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws JsonProcessingException {
        Person person = new Person();
        person.setId(1);
        person.setName("李华");
        person.setPassword("123456");

        // 对象转JSON字符串
        String jsonStr = objectMapper.writeValueAsString(person);
        System.out.println(jsonStr);

        // JSON字符串转为对象
        Person p = objectMapper.readValue(jsonStr, Person.class);
        System.out.println(p);
    }

    /*
    JSON相关知识:
        1) 键值对之间使用 , 隔开; 键和值之间使用 : 隔开
        2) {}表示对象, []表示数组
        3) 优点: 简单易用, 跨平台支持, 轻量级, 易于扩展, 安全性
     */
}
