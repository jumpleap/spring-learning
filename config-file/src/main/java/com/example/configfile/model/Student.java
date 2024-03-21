package com.example.configfile.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

// 使用@ConfigurationProperties注解来读取yml中配置的对象
// @ConfigurationProperties() 括号中跟yml文件对象名
@ConfigurationProperties("student")
@Component
@Data
public class Student {
    private Integer id;
    private String name;
    private Integer age;
}
