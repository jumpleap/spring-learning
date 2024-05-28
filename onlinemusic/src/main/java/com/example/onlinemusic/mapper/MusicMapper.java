package com.example.onlinemusic.mapper;

import com.example.onlinemusic.model.Music;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MusicMapper {
    // 插入音乐
    int insert(String title, String singer, String time, String url, int userid);

    // 查询歌曲信息
    Music selectByMusic(String title, String singer);
}
