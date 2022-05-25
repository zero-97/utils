package com.zero.chat.service;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zero
 */
@ServerEndpoint("/webSocket/{from}/{to}")
@Component
public class WebSocketServer {

    // 全局管理 session。
    private static ConcurrentHashMap<String, Session> sessionMap = new ConcurrentHashMap<>();

    // 建立连接
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "from") String from) {
        sessionMap.put(from, session);
    }

    // 关闭连接
    @OnClose
    public void onClose(@PathParam(value = "from") String from) {
        sessionMap.remove(from);
    }

    // 接收客户端消息
    @OnMessage
    public void onMessage(String message, @PathParam(value = "from") String from, @PathParam(value = "to") String to) throws IOException {
        // 心跳检测
        if("keep living！".equals(message)) {
            return;
        }
        Session session = sessionMap.get(to);
        if(session == null) {
            session = sessionMap.get(from);
            session.getBasicRemote().sendText("对方不在线！");
            return;
        }
        session.getBasicRemote().sendText(message);
    }

    // 发生错误
    @OnError
    public void onError(Session session, Throwable throwable) {

    }
}
