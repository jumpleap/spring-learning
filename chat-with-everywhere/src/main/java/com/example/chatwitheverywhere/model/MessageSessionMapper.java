package com.example.chatwitheverywhere.model;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageSessionMapper {
    // 根据userId获取当前用户都在哪些对话中存在，返回的是一组sessionId
    List<Integer> getSessionIdsByUserId(int userId);

    // 根据sessionId来查询这个会话包含哪些用户【自身除外】
    List<Friend> getFriendsBySessionId(int sessionId, int selfUserId);

    // 新增一个会话记录，返回会话的id
    // 此处获取 sessionId 是通过参数的 messageSession 中的 sessionId 属性获取的
    // 这样方法的返回值 int 表示的是插入操作影响到几行
    // 这个方法表示 插入一个会话到会话表中
    int addMessageSession(MessageSession messageSession);

    // 给 message_session_user 表也新增对应的记录
    void addMessageSessionUser(MessageSessionUserItem messageSessionUserItem);
}
