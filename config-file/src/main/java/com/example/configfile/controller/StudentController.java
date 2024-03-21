package com.example.configfile.controller;

import com.example.configfile.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 读取yml配置文件中的对象
@RequestMapping("/stu")
@RestController
public class StudentController {
    // 注入对象
    @Autowired
    private Student student;

    @RequestMapping("/readInfo")
    public String readInfo() {
        return student.toString();
    }
}
