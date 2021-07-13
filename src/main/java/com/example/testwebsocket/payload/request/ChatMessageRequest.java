package com.example.testwebsocket.payload.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageRequest {

    private String sender;
    private String receiver;
    private String time;
    private String content ;

}
