package com.example.configfile.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 读取yml配置文件
@RequestMapping("/yml")
@RestController
public class YmlController {
    // 获取不同类型的值
    @Value("${string.value}")
    private String key;

    @Value("${boolean.value1}")
    private Boolean value;

    @Value("${int.value}")
    private Integer num;

    @RequestMapping("/string")
    public String getString() {
        return "读取到: " + key;
    }

    @RequestMapping("/boolean")
    public String getBoolean() {
        return "读取到: " + value;
    }

    @RequestMapping("/int")
    public String getInteger() {
        return "读取到: " + num;
    }


    // 特殊字符是否转义的测试
    @Value("${string.str1}")
    private String str1;
    @Value("${string.str2}")
    private String str2;
    @Value("${string.str3}")
    private String str3;

    @RequestMapping("/str1")
    public String readStr1() {
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);
        return str1;
    }

    @RequestMapping("/str2")
    public String readStr2() {
        return str2;
    }

    @RequestMapping("/str3")
    public String readStr3() {
        return str3;
    }

}
