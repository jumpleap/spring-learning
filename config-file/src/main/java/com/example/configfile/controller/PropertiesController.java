package com.example.configfile.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 读取properties配置文件
@RequestMapping("/properties")
@RestController
public class PropertiesController {
    // @Value: 读取配置文件的值, ${}中需要配置文件的key, 根据key获取value
    @Value("${spring}")
    private String key;

    @RequestMapping("/key")
    public String key() {
        return "读取到值: " + key;
    }

}
