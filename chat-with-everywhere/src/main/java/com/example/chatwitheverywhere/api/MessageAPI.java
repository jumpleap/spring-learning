package com.example.chatwitheverywhere.api;

import com.example.chatwitheverywhere.model.Message;
import com.example.chatwitheverywhere.model.MessageMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@RestController
public class MessageAPI {
    @Resource
    private MessageMapper messageMapper;

    @GetMapping("/message")
    public Object getMessage(int sessionId) {
        List<Message> messages = messageMapper.getMessagesBySessionId(sessionId);
        // 针对查询结果，进行逆置操作，因为界面上需要的是按照时间升序排列的信息，此处得到是降序排列的消息
        Collections.reverse(messages);
        return messages;
    }
}
