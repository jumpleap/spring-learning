package com.study.springmvcdemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@RequestMapping("/index")
@RestController
@Controller
//@ResponseBody
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
        5) @ResponseBody: 既是类注解, 也是方法注解; 若作用在类上, 表示该类的所有方法返回的都是数据; 若作用在方法上, 表示该方法返回的是数据
        注: 若一个类的方法中, 既有返回数据的, 又有返回页面的, 就把@ResponseBody注解添加到对应的方法上即可
     */

    // 返回数据
    // 注: 在使用多个注解时, 没有先后顺序
    @ResponseBody
    @RequestMapping("/returnData")
    public String returnData() {
        return "该方法返回数据";
    }

    // 把上述方法去掉@ResponseBody
    // 报错404, 因为我们使用的@Controller默认返回的是视图, 但是查找不到, 路径不存在
    @RequestMapping("/returnData2")
    public String returnData2() {
        return "该方法返回数据";
    }

    // 返回HTML代码片段: 数据中含有html片段, 会被渲染
    @ResponseBody
    @RequestMapping("/returnHtml2")
    public String returnHtml2() {
        return "<h1>Hello, Spring!</h1>";
    }

    /*
      响应中的Content-Type常见的取值:
        1) text/html: body数据格式是 HTML
        2) text/css: body数据格式是 CSS
        3) application/javascript: body数据格式是 JavaScript
        4) application/json: body数据格式是 JSON
     */

    // Content-Type为text/css
    @RequestMapping("/returnCss")
    public Object returnCss() {
        return "/a.css";
    }

    // Content-type为application/javascript
    @RequestMapping("/returnJs")
    public Object returnJs() {
        return "/b.js";
    }

    // 返回Json数据
    @ResponseBody
    @RequestMapping("/returnJson")
    public Map<String, String> returnJson() {
        Map<String, String> map = new HashMap<>();
        map.put("程序设计", "Java");
        map.put("数据结构", "C++");
        map.put("操作系统", "C");
        return map;
    }

    // 返回对象: 返回的数据格式也是Json
    @ResponseBody
    @RequestMapping("/returnPerson")
    public Person returnPerson() {
        Person person = new Person();
        person.setId(1);
        person.setPassword("123345");
        person.setName("张三");
        return person;
    }

    // 返回数组: 返回的也是Json数据
    @ResponseBody
    @RequestMapping("/returnArray")
    public String[] returnArray() {
        String[] strings = new String[2];
        strings[0] = "湛山";
        strings[1] = "黄沙";
        return strings;
    }

    // 设置状态码
    // 通过SpringMVC的内置对象HttpServletResponse提供的方法来进行设置
    @ResponseBody
    @RequestMapping("/setStatus")
    public String setStatus(HttpServletResponse response) {
        response.setStatus(401);
        return "状态码设置成功!";
    }

    // 设置Header, 通过@RequestMapping实现
    // 了解@RequestMapping源码
    /*
        @Target({ElementType.TYPE, ElementType.METHOD}): 表示注解的使用范围, TYPE:类型, METHOD: 方法
        @Retention(RetentionPolicy.RUNTIME) : 注解的生命周期
        @Documented: 一个注解被@Documented标注, 那么这个注解修饰的类, 生成文档时会显示该注解
        @Mapping
        public @interface RequestMapping {
            String name() default "";

            // value: 指定映射的URL
            @AliasFor("path")
            String[] value() default {};

            @AliasFor("value")
            String[] path() default {};
            // method: 指定请求的method类型, 如GET, POST等
            RequestMethod[] method() default {};
            // 指定request中必须包含的某些参数值, 才让该方法处理
            String[] params() default {};
            // headers: 指定request中必须包含某些指定的header值, 才能让该方法处理请求
            String[] headers() default {};
            // 指定处理请求的提交内容类型, 例如: application/json等
            String[] consumes() default {};
            // 指定返回的内容类型, 仅当request请求头中的类型中包含该指定类型才返回
            String[] produces() default {};
        }
     */

    // 设置Content-Type
    // 通过设置produces属性的值,设置响应的报头Content-Type
    // 若不设置produces, 方法返回结果为String时, SpringMVC默认返回类型是text/html
    // 设置返回类型时, 也可以同步设置响应编码
    @ResponseBody
    @RequestMapping(value = "/setConType", produces = "application/json; charset=utf8")
    public String setConType() {
        return "{\"success\": true}";
    }

    // 设置其他header
    // 设置其他Header的话, 需要使⽤SpringMVC的内置对象HttpServletResponse提供的⽅法来进⾏设置\
    // void setHeader(String name, String value)设置⼀个带有给定的名称和值的header.如果name已经存在,则覆盖旧的值
    @ResponseBody
    @RequestMapping("/setHeader")
    public String setHeader(HttpServletResponse response) {
        response.setHeader("header", "MyHeader");
        return "header 设置成功!";
    }
}