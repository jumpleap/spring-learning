package com.study.springmvcdemo;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/param")
@RestController
public class ParamController {
    // 传递单个参数, 使用查询字符串传递, 方法参数接收
    // 注: spring会根据查询字符串的参数名, 在方法中来查找对应的参数
    @RequestMapping("/m1")
    public String m1(String name) {
        return "接收到参数: " + name;
    }

    // 注: 对于数据可能为空的情况, 建议使用包装类
    // 使用基本类型来接收参数时, 参数必须传(boolean类型除外), 否则会报500错误
    // 类型不匹配, 会报400错误
    @RequestMapping("/m1/int")
    public Object IntM1(int num) {
        return "接收到参数: " + num;
    }

    // 传递多个参数: 也是使用查询将字符串和方法的参数对照, 名称必须一致
    // 当有多个参数时, 前后端进行参数匹配是以参数的名称进行匹配的, 因此参数的位置不影响后端获取参数的结果
    @RequestMapping("/m2")
    public String m2(String name, String password) {
        return "接收到参数name: " + name + ", 接收到参数password: " + password;
    }

    // 传递对象: 当参数很多的时候, 我们可以封装为对象进行传递
    // SpringMVC 会自动实现对象参数的赋值
    @RequestMapping("/m3")
    public String m3(Person person) {
        return person.toString();
    }

    // 后端参数重命名: @RequestParam(重命名)
    // 注: 重命名后, 不能再使用原先的名称进行访问了
    // 使用@RequestParam进行重命名之后,
    // 1) 请求参数只能和@RequestParam中声明的名称一致, 才能进行参数绑定和赋值
    // 2) 参数变成必传参数
    @RequestMapping("/m4")
    public String m4(@RequestParam("time") String createTime) {
        return "接收到参数name: " + createTime;
    }

    // 把@RequestParam的必传参数设置为非必传
    // boolean required() default true; 设为false即可
    // 注解属性赋值时, 没有指明key的话, 默认为value属性.
    // 如果需要有多个属性进⾏赋值时, 需要写上key
    @RequestMapping("/m4/info")
    public String info_m4(@RequestParam(value = "time", required = false) String createTime) {
        return "接收到参数name: " + createTime;
    }

    // 传递数组
    // 前端的查询字符串名均需要相同且多个(0-n)传递
    @RequestMapping("/m5")
    public String m5(String[] array) {
        return Arrays.toString(array);
    }

    // 传递集合
    // 传递集合的方式和传递数组的方式相同, 默认情况下是传递数组, 若想要传递集合, 使用RequestParam绑定
    @RequestMapping("/m6")
    public String m6(@RequestParam List<Integer> list) {
        return "" + list + ", size: " + list.size();
    }

    // 传递JSON数据
    // 接收JSON对象, 需要使用 @RequestBody注解
    // @RequestBody: 请求参数必须写在啊请求正文中
    @RequestMapping("/m7")
    public String m7(@RequestBody Person person) {
        return person.toString();
    }

    // 获取URL中的参数: @PathVariable
    // @PathVariable: 主要作用在请求URL路径上的数据绑定
    // @PathVariable: 可以重命名
    @RequestMapping("/m8/{id}/{name}")
    public String m8(@PathVariable Integer id, @PathVariable("name") String userName) {
        return "解析参数id: " + id + ", name: " + userName;
    }

    // 上传文件: @RequestPart
    // @RequestPart + MultipartFile: 指定传输的是文件
    @RequestMapping("/m9")
    public String getFile(@RequestPart("file") MultipartFile fileName) throws IOException {
        // 获取文件名称
        String file = fileName.getOriginalFilename();
        // 文件上传到指定路径
        fileName.transferTo(new File("D:/" + file));
        return "接收到的文件为: " + file;
    }

    // 获取Cookie(传统方法)
    // SpringMVC 是基于Servlet API构建的原始Web框架, 也是在Servlet的基础上实现的, 即SpringMVC对这两个对象进行了封装
    // HttpServletRequest, HttpServletResponse: 是Servlet 提供的两个类, 是SpringMVC中的内置对象, 需要时直接添加声明即可
    // HttpServletRequest 对象代表客户端的请求, 当客户端通过HTTP协议访问服务器时, HTTP请求头中的所有信息都封装在这个对象中
    // 通过 HttpServletRequest 对象提供的方法, 可以获得客户端请求的所有信息
    // HttpServletResponse 对象代表服务器的响应, HTTP响应的信息都在这个对象中, 比如向客户端发送的数据, 响应头, 状态码等,
    // 提供 HttpServletResponse 对象提供的方法, 可以获得服务器响应的所有内容
    @RequestMapping("/m10")
    public String getCookie(HttpServletRequest request, HttpServletResponse response) {
        // 获取所有 cookie 信息
        Cookie[] cookies = request.getCookies();
        // 打印 cookie 信息
        StringBuilder builder = new StringBuilder();
        // cookies判空
        if (cookies != null) {
            // 循环cookies数组
            for (Cookie cookie : cookies) {
                // 把cookies中的所有cookie的名称和值都添加到builder中
                builder.append(cookie.getName()).append(": ").append(cookie.getValue());
            }
        }
        // 打印出cookie的信息
        return "Cookie信息: "+ builder.toString();
    }

    // 获取Cookie(简洁方法)
    // 使用 CookieValue 获取 cookie的值
    @RequestMapping("/cookie")
    public String cookie(@CookieValue("bite") String bite) {
        return "bite: " + bite;
    }
}