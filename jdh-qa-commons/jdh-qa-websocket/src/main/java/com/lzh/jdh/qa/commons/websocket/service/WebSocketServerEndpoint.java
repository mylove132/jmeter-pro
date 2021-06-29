package com.lzh.jdh.qa.commons.websocket.service;

import com.alibaba.fastjson.JSONObject;
import com.lzh.jdh.qa.commons.core.domain.BaseMqMessage;
import com.lzh.jdh.qa.commons.websocket.utils.LocalSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;


/**
 *  WebSocket 服务配置类
 *  定义 userId 为当前连接(在线) WebSocket 的用户
 */
@Component
@ServerEndpoint(value = "/ws/{userId}")
public class WebSocketServerEndpoint {

    private static Logger log = LoggerFactory.getLogger(WebSocketServerEndpoint.class);

    private Session session;
    private String userId;
    /**
     *  建立连接的回调
     *  session 建立连接的会话
     *  userId 当前连接用户id   路径参数
     */
    @OnOpen
    public void onOpen(Session session,  @PathParam("userId") String userId){
        this.session = session;
        this.userId = userId;
        LocalSession.addSession(userId, session);
        log.info("----[ WebSocket ]---- 用户id为 : {} 的用户进入WebSocket连接 ! 当前在线人数为 : {} 人 !--------",userId,LocalSession.localSession.size());
    }

    /**
     *  关闭连接的回调
     *  移除用户在线状态
     */
    @OnClose
    public void onClose() throws IOException {
        LocalSession.delSession(session);
        log.info("----[ WebSocket ]---- 用户id为 : {} 的用户退出WebSocket连接 ! 当前在线人数为 : {} 人 !--------",userId, LocalSession.localSession.size());
    }

    @OnMessage
    public void onMessage(String message, Session session,  @PathParam("userId") String userId) {
        log.info("--------收到用户id为 : {} 的用户发送的消息 ! 消息内容为 : ------------------",userId,message);
        BaseMqMessage jmeterMessage = JSONObject.parseObject(message, BaseMqMessage.class);
        LocalSession.sendMessage(session, jmeterMessage);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("----------------WebSocket发生错误----------------");
        log.error(error.getStackTrace().toString() + "");
    }

}
