package com.example.chatwitheverywhere.model;

import lombok.Data;

import java.util.List;

@Data
// 这个类表示一个会话
public class MessageSession {
    private int sessionId;
    // 对应的朋友
    private List<Friend> friends;
    // 最后一条消息
    private String lastMessage;
}
