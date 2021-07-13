package com.example.testwebsocket.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class ChatMessage  {
    @Id
    private String id;
    private String content;
    private String sender;
    private String receiver;
    private String time;

    public ChatMessage(String content, String sender, String receiver, String time) {
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.time = time;
    }
}
