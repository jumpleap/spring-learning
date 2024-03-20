package com.study.springmvcdemo.demo;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/message")
@RestController
public class MessageController {
    private List<MessageInfo> messageInfos = new ArrayList<>();

    @RequestMapping("/getList")
    public List<MessageInfo> getList() {
        return messageInfos;
    }

    @RequestMapping("/publish")
    public boolean publish(MessageInfo messageInfo) {
        // 打印信息
        System.out.println(messageInfo);
        // 判空操作
        if (StringUtils.hasLength(messageInfo.getFrom())
                && StringUtils.hasLength(messageInfo.getTo())
                && StringUtils.hasLength(messageInfo.getMessage())) {
            // 不为空添加到集合中
            messageInfos.add(messageInfo);
            return true;
        }
        // 有为空的
        return false;
    }
}
