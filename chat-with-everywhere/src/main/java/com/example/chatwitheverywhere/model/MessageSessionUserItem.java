package com.example.chatwitheverywhere.model;

import lombok.Data;

@Data
// 这个类的对象表示 message_session_user表中的一条记录
public class MessageSessionUserItem {
    private int sessionId;
    private int userId;
}
