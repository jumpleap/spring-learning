package com.example.chatwitheverywhere.api;

import com.example.chatwitheverywhere.model.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class MessageSessionAPI {
    @Resource
    private MessageSessionMapper messageSessionMapper;
    @Resource
    private MessageMapper messageMapper;

    @GetMapping("/sessionList")
    public Object getMessageSessionList(HttpServletRequest req) {
        List<MessageSession> messageSessionList = new ArrayList<>();
        // 1.获取当前用户的 userId（从spring中的session中获取）
        HttpSession session = req.getSession(false);
        if (session == null) {
            System.out.println("getMessageSessionList session == null");
            return messageSessionList;
        }
        User user = (User) session.getAttribute("user");
        if (user == null) {
            System.out.println("getMessageSessionList user == null");
            return messageSessionList;
        }

        // 2.根据获取的 userId 查询数据库有哪些会话id
        List<Integer> sessionIds = messageSessionMapper.getSessionIdsByUserId(user.getUserId());

        for (int sessionId : sessionIds) {
            // 创建出一个会话对象
            MessageSession messageSession = new MessageSession();
            messageSession.setSessionId(sessionId);
            // 3.遍历会话id，查询出每个会话里涉及的好友都有谁（除掉自己本身）
            List<Friend> friends = messageSessionMapper.getFriendsBySessionId(sessionId, user.getUserId());
            messageSession.setFriends(friends);
            // 4.遍历会话id，查询出每个会话id的最后一条信息
            String lastMessage = messageMapper.getLastMessageBySessionId(sessionId);
            // 没有查到消息【可能是新创建的会话】
            if (lastMessage == null) {
                // 设为空
                messageSession.setLastMessage("");
            } else {
                messageSession.setLastMessage(lastMessage);
            }
            // messageSession.setLastMessage("晚上吃啥？");
            messageSessionList.add(messageSession);
        }

        // 最终目标就是构造出一个 MessageSession 对象数组
        return messageSessionList;
    }

    @PostMapping("/session")
    // 加上事务，保证所有数据都插入成功，或都插入失败
    @Transactional
    public Object addMessageSession(int toUserId, @SessionAttribute("user") User user) {
        HashMap<String, Integer> resp = new HashMap<>();
        // 给数据库进行插入数据
        MessageSession messageSession = new MessageSession();
        // 第一次插入数据：插入到 message_session 表中
        // 执行完下述代码后，sessionId有了，lastTime也有了，friends参数用不上
        messageSessionMapper.addMessageSession(messageSession);

        // 构造 MessageSessionUserItem 对象
        MessageSessionUserItem item1 = new MessageSessionUserItem();
        item1.setSessionId(messageSession.getSessionId());
        item1.setUserId(user.getUserId());
        // 插入到 message_session_user 表中
        // 当前插入的是 当前用户id 和 当前sessionId
        messageSessionMapper.addMessageSessionUser(item1);

        MessageSessionUserItem item2 = new MessageSessionUserItem();
        item2.setSessionId(messageSession.getSessionId());
        item2.setUserId(toUserId);
        // 当前插入的是朋友 id 和当亲的会话id
        messageSessionMapper.addMessageSessionUser(item2);

        // 最后我们需要返回sessionId
        resp.put("sessionId", messageSession.getSessionId());
        // 返回的对象是一个普通对象也可以，或是一个Map也可也，jackson都能进行处理
        return resp;
    }
}
