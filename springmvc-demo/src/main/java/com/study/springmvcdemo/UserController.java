package com.study.springmvcdemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// @RequestMapping即可以作用于类上, 也可以作用于方法上; 作用于类上访问方法的时候需要加上类路径
// @RequestMapping用于注册接口的路由映射, 用于浏览器和程序的链接
// 路由映射: 当用户访问一个URL时, 将用户的请求对应到程序中某个类的某个方法的过程
@RequestMapping("/user")
// @RestController: 把当前类交给Spring进行管理
@RestController
public class UserController {
    // /加不加均可
    @RequestMapping("/sayHi")
    public String sayHi() {
        return "hello";
    }

    @RequestMapping("hi")
    public String hi() {
        return "hello";
    }

    // @RequestMapping的URL也可以是多级路径, 访问的时候类路径 + 方法路径即可
    @RequestMapping("/say/hi")
    public String say() {
        return "Hello, Spring";
    }

    // @RequestMapping即支持GET请求也支持POST请求(使用Postman测试)
    // 浏览器的访问都是GET请求

    // @RequestMapping指定访问类型
    @RequestMapping(value = "request", method = RequestMethod.POST)
    public String request() {
        return "返回类型";
    }
}
