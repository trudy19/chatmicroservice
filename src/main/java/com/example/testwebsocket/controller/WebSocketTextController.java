package com.example.testwebsocket.controller;

import com.example.testwebsocket.Services.UserService;
import com.example.testwebsocket.models.ChatMessage;
import com.example.testwebsocket.models.TextMessageDTO;
import com.example.testwebsocket.payload.request.ChatMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class WebSocketTextController {


    @Autowired
    UserService userService ;
    @Autowired
    SimpMessagingTemplate template;

    @PostMapping("/send")
    public ResponseEntity<Void> sendMessage(@RequestBody TextMessageDTO textMessageDTO) {
        template.convertAndSend("/topic/message", textMessageDTO);
        System.out.println("step333333333333333333");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @MessageMapping("/sendMessage")
    public void receiveMessage( String textMessageDTO) {
        // receive message from client
        System.out.println("ste33333333333333333333333333p2");
    }


    @SendTo("/topic/message")
    public TextMessageDTO broadcastMessage(@Payload TextMessageDTO textMessageDTO) {
        System.out.println("hekkio");
        return textMessageDTO;
    }



    @MessageMapping("/chatconversation/{conversation}")
    @SendTo("/topic/chatconversation.{conversation}")
    public ChatMessage sendPrivateMessage(@DestinationVariable String conversation, @Payload ChatMessage chatMessage) throws Exception{
        userService.chatMessageSave(conversation,chatMessage);
        System.out.println("msg a "+conversation);
        return chatMessage;
    }
}

