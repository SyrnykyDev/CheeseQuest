package com.mykyda.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class SocketController {
    @MessageMapping("/{id}/send")
    @SendTo("/api/session/{id}")
    public String processMessageFromClient(String message) {
        return "message received";
    }
}
