package com.example.onlinemusic.controller;

import com.example.onlinemusic.mapper.LoveMusicMapper;
import com.example.onlinemusic.model.Music;
import com.example.onlinemusic.model.User;
import com.example.onlinemusic.tools.Constant;
import com.example.onlinemusic.tools.ResponseBodyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestMapping("/love")
@RestController
public class LoveMusicController {
    @Autowired
    private LoveMusicMapper loveMusicMapper;

    @RequestMapping("/like-music")
    public ResponseBodyMessage<Boolean> likeMusic(@RequestParam String id, HttpServletRequest request) {
        // 获取音乐id
        int musicId = Integer.parseInt(id);
        System.out.println("musicId: " + musicId);

        // 判断用户是否登录
        HttpSession httpSession = request.getSession(false);
        // session为空 或 session中的属性不存在
        if (httpSession == null || httpSession.getAttribute(Constant.USERINFO_SESSION_KEY) == null) {
            System.out.println("未登录，请进行登录！");
            return new ResponseBodyMessage<>(-1, "请登录后上传", false);
        }

        // 获取user对象
        User user = (User) httpSession.getAttribute(Constant.USERINFO_SESSION_KEY);
        // 获取用户id
        int userId = user.getId();
        System.out.println("userId: " + userId);

        // 判断当前音乐是否收藏了
        Music music = loveMusicMapper.findLoveMusic(userId, musicId);
        // 已经收藏了
        if (music != null) {
            return new ResponseBodyMessage<>(-1, "该音乐已经收藏了！", false);
        }

        // 没有收藏, 插入到收藏表中去
        boolean ans = loveMusicMapper.insertLoveMusic(userId, musicId);

        // 收藏失败
        if (!ans) {
            return new ResponseBodyMessage<>(-1, "收藏失败！", false);
        }
        return new ResponseBodyMessage<>(0, "收藏成功！", true);
    }
}
