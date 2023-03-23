package com.example.springchatroom.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue()
    private Integer id;
    private String user;
    private String content;
    private LocalDateTime timestamp;

    public Message(String user, String content, LocalDateTime timestamp) {
        this.user = user;
        this.content = content;
        this.timestamp = timestamp;
    }
}
