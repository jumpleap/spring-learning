package com.study.springmvcdemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/index")
//@RestController
@Controller
public class IndexController {
    // 返回静态页面
    @RequestMapping("/returnHtml")
    public Object returnHtml() {
        // 返回index.html页面
        return "/index.html";
        // 注: 返回的文本数据, 并未返回页面, 与@RestController有关
    }

    /*
    @RestController和@Controller的联系和区别
        1) @RestController返回的是数据, @Controller返回的是页面
        2) @RestController = @Controller + @ResponseBody
        3) @Controller: 定义一个控制器, Spring框架启动时加载, 把这个对象交给Spring进行管理
        4) @ResponseBody: 定义返回的数据格式为非试图, 返回一个text/html信息

     */
}
