package com.example.onlinemusic.controller;

import com.example.onlinemusic.mapper.UserMapper;
import com.example.onlinemusic.model.User;
import com.example.onlinemusic.tools.Constant;
import com.example.onlinemusic.tools.ResponseBodyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping("/login1")
    public ResponseBodyMessage<User> login1(@RequestParam String username, @RequestParam String password,
                                            HttpServletRequest request) {
        User userLogin = new User();
        userLogin.setUsername(username);
        userLogin.setPassword(password);

        // 根据用户名和密码去数据库中查找相同的用户
        User user = userMapper.login(userLogin);
        // 不存在
        if (user == null) {
            System.out.println("登录失败!");
            return new ResponseBodyMessage<>(-1, "登录失败！", userLogin);
        } else {
            // 匹配成功
            System.out.println("登录成功！");
            // 封装session
            // request.getSession().setAttribute("USERINFO_SESSION_KEY", user);
            request.getSession().setAttribute(Constant.USERINFO_SESSION_KEY, user);
            return new ResponseBodyMessage<>(0, "登录成功！", userLogin);
        }
    }

    @RequestMapping("/login")
    public ResponseBodyMessage<User> login(@RequestParam String username, @RequestParam String password,
                                           HttpServletRequest request) {
        /*User userLogin = new User();
        userLogin.setUsername(username);
        userLogin.setPassword(password);*/

        User user = userMapper.selectByName(username);

        // 没有找到用户名对应的用户
        if (user == null) {
            System.out.println("登录失败!");
            return new ResponseBodyMessage<>(-1, "用户名或者密码错误！", user);
        } else {
            // 加密后的密码和给定的密码进行校验
            boolean flg = bCryptPasswordEncoder.matches(password, user.getPassword());
            if (!flg) {
                // 密码不匹配
                return new ResponseBodyMessage<>(-1, "用户名或者密码错误！", user);
            }
            // 密码正常， 封装session
            request.getSession().setAttribute(Constant.USERINFO_SESSION_KEY, user);
            return new ResponseBodyMessage<>(0, "登录成功！", user);
        }
    }
}