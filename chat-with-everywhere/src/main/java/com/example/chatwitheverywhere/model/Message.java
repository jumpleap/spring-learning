package com.example.chatwitheverywhere.model;

import lombok.Data;

@Data
public class Message {
    private int messageId;
    private int fromId; // 表示发送者的id
    private String fromName; // 表示发送者的名字
    private int sessionId;
    private String content;
}
