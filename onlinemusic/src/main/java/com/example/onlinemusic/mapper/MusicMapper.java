package com.example.onlinemusic.mapper;

import com.example.onlinemusic.model.Music;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MusicMapper {
    // 插入音乐
    int insert(String title, String singer, String time, String url, int userid);

    // 查询歌曲信息
    Music selectByMusic(String title, String singer);

    // 查询当前id是否存在
    Music findMusicById(int id);

    // 删除当前id的音乐
    int deleteMusicById(int musicId);

    // 查询所有音乐
    List<Music> findMusic();

    // 查询指定的音乐
    List<Music> findMusicByName(String musicName);
}
