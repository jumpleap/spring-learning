package com.example.onlinemusic.controller;

import com.example.onlinemusic.mapper.LoveMusicMapper;
import com.example.onlinemusic.mapper.MusicMapper;
import com.example.onlinemusic.model.Music;
import com.example.onlinemusic.model.User;
import com.example.onlinemusic.tools.Constant;
import com.example.onlinemusic.tools.ResponseBodyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequestMapping("/music")
@RestController
public class MusicController {

    // 文件保存目录
    @Value("${music.local.path}")
    private String SAVE_PATH/* = "D:/music/"*/;

    @Autowired
    private MusicMapper musicMapper;

    @Autowired
    private LoveMusicMapper loveMusicMapper;

    @RequestMapping("/upload")
    public ResponseBodyMessage<Boolean> insertMusic(@RequestParam String singer,
                                                    @RequestParam("filename") MultipartFile file,
                                                    HttpServletRequest request,
                                                    HttpServletResponse response) {
        // 检测是否登录
        HttpSession httpSession = request.getSession(false);
        // session为空 或 session中的属性不存在
        if (httpSession == null || httpSession.getAttribute(Constant.USERINFO_SESSION_KEY) == null) {
            System.out.println("未登录，请进行登录！");
            return new ResponseBodyMessage<>(-1, "请登录后上传", false);
        }

        // 登录之后查询数据库中是否一样的歌曲信息
        // 获取完整的文件名
        String fileName = file.getOriginalFilename();
        // 打印日志
        System.out.println("fileName: " + fileName);
        int index = fileName.lastIndexOf(".");
        String title = fileName.substring(0, index);

        // 根据歌曲名和歌手判断是不是同一首歌
        Music music = musicMapper.selectByMusic(title, singer);
        // 是同一首
        if (music != null) {
            return new ResponseBodyMessage<>(-1, "该歌曲已经存在!", false);
        }

        //把文件上传到服务器

        // 获取完整的文件名
        // String fileName = file.getOriginalFilename();
        // 打印日志
        // System.out.println("fileName: " + fileName);
        // 文件地址
        String path = SAVE_PATH + "/" + fileName;

        // 根据地址创建一个文件对象
        File dest = new File(path);

        // 判断这个文件对象对应的文件是否存在
        if (!dest.exists()) {
            // 不存在则创建
            dest.mkdir();
        }

        // 把文件上传到dest文件对象中
        try {
            file.transferTo(dest);
            // return new ResponseBodyMessage<>(0, "上传成功！", true);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseBodyMessage<>(-1, "服务器上传失败！", false);
        }
        // return new ResponseBodyMessage<>(-1, "上传失败！", false);

        // 进行数据库的上传
        // 先获取数据, id是自增长的，无需获取
        // int index = fileName.lastIndexOf(".");
        // String title = fileName.substring(0, index);

        // 获取userid
        User user = (User) httpSession.getAttribute(Constant.USERINFO_SESSION_KEY);
        int userid = user.getId();

        // 播放音乐 -- http请求
        String url = "/music/get?path=" + title;

        // 时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String time = simpleDateFormat.format(new Date());

        try {
            // 上传到数据库
            int ret = 0;
            ret = musicMapper.insert(title, singer, time, url, userid);

            // 判断是否上传成功
            if (ret == 1) {
                // 此处上传成功应该跳转音乐列表页
                response.sendRedirect("/list.html");
                return new ResponseBodyMessage<>(0, "数据库上传成功！", true);
            }
            return new ResponseBodyMessage<>(-1, "数据库上传失败！", false);
        } catch (Exception e) {
            System.out.println("数据库上传失败！");
            // 删除服务器中的文件
            dest.delete();
            return new ResponseBodyMessage<>(-1, "数据库上传失败！", false);
        }
    }

    // 播放音乐的路径: /music/get?path=xxx.mp3
    @RequestMapping("/get")
    public ResponseEntity<byte[]> get(String path) {
        /*byte[] a = {68, 69, 70, 71};
        // 500 -- 内部服务器错误
        // return ResponseEntity.internalServerError().build();
        // 404 -- 找不到
        // return ResponseEntity.notFound().build();
        // ok有两个方法： 无参 -> 返回状态码  有参 -> 返回状态码和body内容【响应体】
        return ResponseEntity.ok(a);*/

        // 获取文件
        File file = new File(SAVE_PATH + "/" + path);
        // 用于把文件转为字节保存
        byte[] a = null;
        try {
            // 把文件转为字节数组保存
            a = Files.readAllBytes(file.toPath());
            // 没有转化成功
            if (a == null) {
                return ResponseEntity.badRequest().build();
            }
            // 转化成功
            return ResponseEntity.ok(a);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }


    @RequestMapping("/delete")
    public ResponseBodyMessage<Boolean> deleteMusicById(@RequestParam String id) {
        //判断用户是否登录
        //TODO:

        // 字符串转为整数
        int musicId = Integer.parseInt(id);
        // 判断id对应的音乐是否存在
        Music music = musicMapper.findMusicById(musicId);
        // 不存在
        if (music == null) {
            return new ResponseBodyMessage<>(-1, "找不到该音乐！", false);
        }

        // 存在的情况下， 删除数据库中的音乐和服务器中的音乐
        // 删除数据库中的音乐
        int ret = musicMapper.deleteMusicById(musicId);
        // 删除失败
        if (ret != 1) {
            return new ResponseBodyMessage<>(-1, "删除数据库中的音乐失败！", false);
        }

        // 数据库中删除成功，进而删除服务器中的音乐
        // 获取文件名
        int index = music.getUrl().lastIndexOf("=");
        String fileName = music.getUrl().substring(index + 1);
        // 上述两行代码也可以一行搞定
        // String title = music.getTitle();

        // 获取文件地址
        File file = new File(SAVE_PATH + "/" + fileName + ".mp3");
        // 打印日志
        System.out.println("当前文件的路径：" + file.getPath());

        // 删除路径对应的服务器文件
        if (file.delete()) {
            // 同时删除收藏表中的音乐
            loveMusicMapper.deleteLoveMusicById(musicId);
            return new ResponseBodyMessage<>(0, "服务器音乐删除成功！", true);
        }
        return new ResponseBodyMessage<>(-1, "服务器音乐删除失败！", false);
    }


    // 批量删除音乐
    @RequestMapping("/delete-list")
    public ResponseBodyMessage<Boolean> deleteListMusic(@RequestParam("id[]") List<Integer> id) {
        // 用户是否登录
        // TODO:

        System.out.println("id[]: " + id);
        // 计数器，最后用于判断所有音乐是否删除成功
        int count = 0;
        // 判断这些音乐是否存在
        for (int musicId : id) {
            // 查找id对应的音乐
            Music music = musicMapper.findMusicById(musicId);
            // 不存在
            if (music == null) {
                return new ResponseBodyMessage<>(-1, "该音乐不存在！", false);
            }

            // 存在，删除数据库中的音乐
            int ret = musicMapper.deleteMusicById(musicId);
            // 删除失败
            if (ret != 1) {
                return new ResponseBodyMessage<>(-1, "数据库音乐删除失败！", false);
            }

            // 删除成功，进一步删除服务器中的音乐
            // 获取文件名
            String fileName = music.getTitle();
            // 获取文件的路径
            File file = new File(SAVE_PATH + "/" + fileName + ".mp3");

            // 删除成功
            if (file.delete()) {
                // 同时删除收藏表中的音乐
                loveMusicMapper.deleteLoveMusicById(musicId);
                count++;
            } else {
                // 删除失败
                return new ResponseBodyMessage<>(-1, "删除服务器音乐失败！", false);
            }
        }

        // 所有的id的音乐都删除成功
        if (count == id.size()) {
            System.out.println("批量删除成功！");
            return new ResponseBodyMessage<>(0, "批量删除成功！", true);
        } else {
            System.out.println("批量删除失败！");
            return new ResponseBodyMessage<>(-1, "批量删除失败！", false);
        }
    }


    @RequestMapping("/find-music")
    public ResponseBodyMessage<List<Music>> findMusic(@RequestParam(required = false) String musicName) {
        List<Music> list = null;
        // 为空，返回所有音乐
        if (musicName == null) {
            list = musicMapper.findMusic();
        } else {
            // 不为空，则返回指定名称的印业
            list = musicMapper.findMusicByName(musicName);
        }
        return new ResponseBodyMessage<>(0, "找到了所查询的音乐！", list);
    }
}
