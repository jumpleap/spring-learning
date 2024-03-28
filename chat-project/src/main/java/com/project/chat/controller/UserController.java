package com.project.chat.controller;

import com.project.chat.mapper.UserMapper;
import com.project.chat.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;

    // 登录
    @PostMapping("/login")
    public Object login(String username, String password, HttpServletRequest request) {
        // 从数据库中查询是否有这个用户
        User user = userMapper.login(username);

        // 若用户不存在或输入的密码为空, 返回新的对象, 对象中的属性都为空
        if (user == null || !user.getPassword().equals(password)) {
            System.out.println("登录失败, 账号或密码错误!");
            return new User();
        }

        // 如果用户名和密码都匹配, 登录成功, 就创建会话.
        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);

        // 不保留密码显示
        user.setPassword("");
        return user;
    }

    // 注册
    @PostMapping("/register")
    public Object register(String username, String password) {
        User user = null;
        try {
            user = new User();
            user.setUsername(username);
            user.setPassword(password);
            int ret = userMapper.register(user);
            System.out.println("注册: " + ret);
            user.setPassword("");
        } catch (DuplicateKeyException e) {
            // 捕获了这个异常, 则说明名字重复了, 注册失败(username是unique的)
            // 返回空对象
            user = new User();
        }
        return  user;
    }
}
