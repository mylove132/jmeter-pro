package com.lzh.jmeter.commons.websocket.utils;

import com.alibaba.fastjson.JSONObject;
import com.lzh.jmeter.commons.core.domain.BaseMqMessage;
import com.lzh.jmeter.commons.core.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LocalSession {

    private static Logger log = LoggerFactory.getLogger(LocalSession.class);

    public static Map<String, Session> localSession = new ConcurrentHashMap<>();

    public static void addSession (String userId, Session session) {
        localSession.put(userId, session);
    }

    public static void delSession (Session session) throws IOException {
        for (String userId : localSession.keySet()) {
            if (!"".equals(userId) && null != userId) {
                if (localSession.get(userId) != null) {
                    localSession.get(userId).close();
                    localSession.remove(userId);
                }
            }
        }
    }

    /**
     * 向单独某个用户发送消息
     * @param session
     * @param message
     */
    public static void sendMessage (Session session, BaseMqMessage message) {
        try {
            session.getBasicRemote().sendText(message.getContents());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 向全部用户发送消息
     * @param message
     */
    public static void sendMessageToAll (String message) {
        log.info("发送的全部消息： {}",  message);
        BaseMqMessage jmeterMessage = JSONObject.parseObject(message, BaseMqMessage.class);
        localSession.forEach(
                (userId, session) -> {
                    sendMessage(session, jmeterMessage);
                }
        );
    }

    /**
     * 通过用户ID获取session
     * @param userId
     * @return
     */
    public static Session getLocalSession(String userId) {
        if (StringUtils.isNotBlank(userId)) {
            return localSession.get(userId);
        }
        return null;
    }

    public static void setLocalSession(Map<String, Session> localSession) {
        LocalSession.localSession = localSession;
    }
}
