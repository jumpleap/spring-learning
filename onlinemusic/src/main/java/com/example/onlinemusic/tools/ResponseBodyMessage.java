package com.example.onlinemusic.tools;

import lombok.Data;

// 统一响应格式
@Data
public class ResponseBodyMessage<T> {
    // 状态码
    private int status;
    // 返回的信息【成功的信息/出错的信息】
    private String message;
    // 返回给前端的数据
    private T data;

    public ResponseBodyMessage(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
