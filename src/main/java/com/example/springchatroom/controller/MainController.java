package com.example.springchatroom.controller;

import com.example.springchatroom.model.InboundMessage;
import com.example.springchatroom.model.Message;
import com.example.springchatroom.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import java.time.LocalDateTime;

@Controller
public class MainController {

    @Autowired
    MessageRepository messageRepository;

    @MessageMapping("/sendMessage")
    @SendTo("/topic/chat")
    public Message greeting(InboundMessage inboundMessage) {
        Message message = new Message(inboundMessage.getName(), inboundMessage.getText(), LocalDateTime.now());
        messageRepository.save(message);
        return message;
    }
}
