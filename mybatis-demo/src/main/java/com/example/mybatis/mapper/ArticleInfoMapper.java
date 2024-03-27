package com.example.mybatis.mapper;

import com.example.mybatis.model.ArticleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

// 多表查询
@Mapper
public interface ArticleInfoMapper {
    @Select("select a.username, a.age, a.gender, b.id, b.title, b.content, b.uid from " +
            "user_info a, article_info b where b.uid = a.id;")
    List<ArticleInfo> queryById(Integer id);

    @Select("select a.username, a.age, a.gender, b.id, b.title, b.content, b.uid from " +
            "user_info a left join article_info b on b.uid = a.id where a.id = #{id};")
    ArticleInfo queryById2(Integer id);
}
