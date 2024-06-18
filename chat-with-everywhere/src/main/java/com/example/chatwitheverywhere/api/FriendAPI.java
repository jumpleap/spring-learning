package com.example.chatwitheverywhere.api;

import com.example.chatwitheverywhere.model.Friend;
import com.example.chatwitheverywhere.model.FriendMapper;
import com.example.chatwitheverywhere.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FriendAPI {
    @Resource
    private FriendMapper friendMapper;

    @GetMapping("/friendList")
    public Object getFriendList(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        // 会话不存在
        if (session == null) {
            System.out.println("[getFriendList] session 不存在！");
            return new ArrayList<Friend>();
        }

        // 会话存在, 获取对应会话中保存的对象
        User user = (User) session.getAttribute("user");
        // 如果对象不存在
        if (user == null) {
            System.out.println("session 中的 user 不存在！");
            return new ArrayList<Friend>();
        }

        // 对象存在，获取对象的id
        // 把对象id进行数据库查询，查找当前用户的好友
        List<Friend> friendList = friendMapper.selectFriendList(user.getUserId());
        return friendList;
    }
}
