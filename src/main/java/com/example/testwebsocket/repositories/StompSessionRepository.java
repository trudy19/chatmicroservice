package com.example.testwebsocket.repositories;

import com.example.testwebsocket.models.StompSession;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StompSessionRepository extends MongoRepository<StompSession,String> {
    StompSession findStompSessionBySessionId(String sessionId);
}
