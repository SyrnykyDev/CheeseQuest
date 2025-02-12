package com.mykyda.api.handler;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class GameWebSocketHandler {

    private final SimpMessagingTemplate messagingTemplate;

    public GameWebSocketHandler(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendUpdate(Long gameId, String message) {
        messagingTemplate.convertAndSend("/topic/game/" + gameId, message);
    }
}
