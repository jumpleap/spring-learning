package com.project.chat.controller;

import com.project.chat.mapper.UserMapper;
import com.project.chat.model.User;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class UserController {
    @Resource
    private UserMapper userMapper;

    // 登录
    @PostMapping("/login")
    @ResponseBody
    public Object login(String username, String password, HttpServletRequest req) {
        // 1. 先去数据库中查询, 看username能否找到对应的user对象
        //    如果能找到, 则查看一下密码是否匹配
        User user = userMapper.selectByName(username);
        if (user == null || !user.getPassword().equals(password)) {
            // 两条件具备一个, 就登录失败, 同时返回一个空的对象即可
            System.out.println("登录失败! 用户名或密码错误!" + user);
            return new User();
        }

        // 2.如果都匹配, 登录成功! 创建会话
        HttpSession session = req.getSession(true);
        session.setAttribute("user", user);
        // 在返回前, 把password给隐藏, 防止返回隐私数据
        user.setPassword("");
        return user;
    }

    // 注册
    @PostMapping("/register")
    @ResponseBody
    public Object register(String username, String password) {
        User user = null;
        try {
            // 创建对象
            user = new User();
            // 赋值
            user.setUsername(username);
            user.setPassword(password);
            // 把对象记录插入到数据库中
            int ret = userMapper.insert(user);
            System.out.println("注册 ret:" + ret);
            // 防止隐私泄露
            user.setPassword("");
        } catch (DuplicateKeyException e) {
            // 如果 insert 方法抛出上述异常, 说明名字重复了, 注册失败
            user = new User();
            System.out.println("注册失败! username = " + username);
        }
        return user;
    }

    @GetMapping("/userInfo")
    @ResponseBody
    public Object getUserInfo(HttpServletRequest req) {
        // 1.先从请求中获取到会话
        HttpSession session = req.getSession(false);
        if (session == null) {
            // 会话不存在, 用户尚未登录, 此时返回一个空的对象即可
            System.out.println("[getUserInfo] 当前获取不到 session 对象!");
            return new User();
        }
        // 从会话中获取到之前保存的对象
        User user = (User)session.getAttribute("user");

        if (user == null) {
            System.out.println("[getUserInfo] 当前获取不到 user 对象!");
            return new User();
        }
        user.setPassword("");
        return user;
    }
}
