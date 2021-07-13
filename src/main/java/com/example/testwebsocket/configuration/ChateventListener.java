package com.example.testwebsocket.configuration;


import com.example.testwebsocket.Services.UserService;
import com.example.testwebsocket.models.StompSession;
import com.example.testwebsocket.models.User;
import com.example.testwebsocket.repositories.StompSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class ChateventListener {

@Autowired
    StompSessionRepository stompSessionRepository ;
@Autowired
    UserService userService ;
    @EventListener
    public void handleWebSocketConnectedListener(SessionConnectedEvent event) {
        System.out.println("Received a new web socket connection");
    }

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) throws Exception {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = headerAccessor.getSessionId();
        try {
            String userID = headerAccessor.getNativeHeader("userId").get(0);
            System.out.println(userID);
            String username = headerAccessor.getNativeHeader("username").get(0);
            User user=new User(userID,username);
            StompSession stompSession = new StompSession(sessionId,userID);
            stompSessionRepository.save(stompSession);
            userService.userOnline(user);
            System.out.println("[" + username + "] is online");
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }


    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) throws Exception {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = headerAccessor.getSessionId();
        try {
            StompSession stompSession = stompSessionRepository.findStompSessionBySessionId(sessionId);
            String userID = stompSession.getUserID();
            stompSessionRepository.delete(stompSession);
            userService.userOffline(userID);
            System.out.println("[" + userID + "] is offline");
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

}
