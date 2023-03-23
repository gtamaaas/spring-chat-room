package com.example.springchatroom.controller;

import com.example.springchatroom.model.InboundMessage;
import com.example.springchatroom.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import java.time.LocalDateTime;

@Controller
public class MainController {

    @MessageMapping("/sendMessage")
    @SendTo("/topic/chat")
    public Message greeting(InboundMessage message) {
        return new Message(message.getName(), message.getText(), LocalDateTime.now());
    }
}
