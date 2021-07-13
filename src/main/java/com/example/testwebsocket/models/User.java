package com.example.testwebsocket.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Getter
@Setter
@Document
public class User {

    @Id
    private String id ;
    private String username;
    private boolean online;

    public User(String id, String username) {
        this.id = id;
        this.username = username;
    }
}
