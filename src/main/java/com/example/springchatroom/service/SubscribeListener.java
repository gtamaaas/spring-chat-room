package com.example.springchatroom.service;

import com.example.springchatroom.model.Message;
import com.example.springchatroom.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.util.List;

@Service
public class SubscribeListener {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private MessageRepository messageRepository;

    @EventListener
    public void handleSubscribeEvent(SessionSubscribeEvent event) {
        List<Message> messages = messageRepository.findAll();
        template.convertAndSend("/topic/chat", messages);
    }
}
