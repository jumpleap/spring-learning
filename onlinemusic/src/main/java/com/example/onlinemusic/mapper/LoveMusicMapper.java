package com.example.onlinemusic.mapper;

import com.example.onlinemusic.model.Music;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoveMusicMapper {

    // 查询喜欢的音乐
    Music findLoveMusic(int userId, int musicId);

    // 收藏音乐
    boolean insertLoveMusic(int userId, int musicId);

    // 查询当前用户收藏的所有音乐
    List<Music> findLoveMusicByUserId(int userId);

    // 根据音乐名查询当前用户下是否有收藏音乐
    List<Music> findLoveMusicByName(String musicName, int userId);
}
