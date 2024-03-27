package com.example.mybatis.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.ws.soap.Addressing;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleInfoMapperTest {
    @Autowired
    private ArticleInfoMapper articleInfoMapper;

    @Test
    void queryById() {
        System.out.println(articleInfoMapper.queryById(1));
    }

    @Test
    void queryById2() {
        System.out.println(articleInfoMapper.queryById2(1));
    }
}