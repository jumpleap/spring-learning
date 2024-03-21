package com.example.configfile.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 读取yml配置文件
@RequestMapping("/yml")
@RestController
public class YmlController {
    @Value("${string.value}")
    private String key;

    @RequestMapping("/string")
    public String getString() {
        return "读取到: " + key;
    }
}
