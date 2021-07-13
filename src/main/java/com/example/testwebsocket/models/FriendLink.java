package com.example.testwebsocket.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;

@Document
@Getter
@Setter
public class FriendLink {

    @Id
    private String id;

    private String userId0;
    private String username0;

    private String username1;
    private String userId1;


    private HashSet<ChatMessage> listofmessage ;



}
