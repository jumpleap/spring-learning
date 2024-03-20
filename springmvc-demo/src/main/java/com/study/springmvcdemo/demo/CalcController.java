package com.study.springmvcdemo.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/calc")
@RestController
public class CalcController {
    @RequestMapping("/sum")
    public String getSum(Integer num1, Integer num2) {
        return "<h1>计算器计算结果为: " + (num1 + num2) + "</h1>";
    }
}
