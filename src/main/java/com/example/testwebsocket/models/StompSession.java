package com.example.testwebsocket.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class StompSession {
    @Id
    String id;
    String sessionId;
    String userID;


    public StompSession(String sessionId, String userID) {
        this.sessionId = sessionId;
        this.userID = userID;
    }
}
