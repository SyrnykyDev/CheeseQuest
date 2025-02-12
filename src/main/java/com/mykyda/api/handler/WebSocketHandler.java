package com.mykyda.api.handler;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private final ConcurrentMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.put(session.getId(), session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Handle incoming messages if needed
    }

    public void sendProgressUpdate(String sessionId, String progress) throws Exception {
        WebSocketSession session = sessions.get(sessionId);
        if (session != null && session.isOpen()) {
            session.sendMessage(new TextMessage(progress));
        }
    }
}