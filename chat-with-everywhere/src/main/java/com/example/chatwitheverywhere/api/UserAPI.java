package com.example.chatwitheverywhere.api;

import com.example.chatwitheverywhere.model.User;
import com.example.chatwitheverywhere.model.UserMapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class UserAPI {
    @Resource
    UserMapper userMapper;

    @PostMapping("/login")
    public Object login(String username, String password, HttpServletRequest req) {
        // 从数据库中查找username对应的对象是否存在
        User user = userMapper.selectByName(username);

        // 对象不存在 或 密码不匹配
        if (user == null || !user.getPassword().equals(password)) {
            System.out.println("用户名或密码错误！" + user);
            return new User();
        }

        // 用户名和密码均匹配
        // 创建会话，会话不存在则创建
        HttpSession session = req.getSession(true);
        session.setAttribute("user", user);
        return user;
    }

    @PostMapping("/register")
    public Object register(String username, String password) {
        User user = null;
        try {
            user = new User();
            user.setUsername(username);
            user.setPassword(password);
            int ret = userMapper.insert(user);
            System.out.println("注册：ret" + ret);
        } catch (DuplicateKeyException e) {
            // 上述操作出现异常，说明用户名出现了重复，返回空的对象
            return new User();
        }
        return user;
    }
}
