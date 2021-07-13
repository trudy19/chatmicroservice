package com.example.testwebsocket.controller;


import com.example.testwebsocket.Services.UserService;
import com.example.testwebsocket.models.chatconversation;
import com.example.testwebsocket.payload.response.DataResponse;
import com.example.testwebsocket.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController

public class UserController {
@Autowired
    UserService userService ;


    @PostMapping("/user/friend")
    public ResponseEntity addFriend(@RequestBody Map<String, Object> params) {
       // JSONObject ret = new JSONObject();
        try {
            String username = (String) params.get("username");
            String userId = (String) params.get("userId");
            String userIdfriend = (String) params.get("userIdfriend");

            String friendName = (String) params.get("friendName");
            boolean success = userService.addFriend(username, friendName,userId,userIdfriend);
            return ResponseEntity.ok(new DataResponse(success));
        } catch (Exception e) {
            return ResponseEntity.ok(new MessageResponse("error"));

        }
    }



    @PostMapping("/user/chatconversation")
    public ResponseEntity getUserChatconversations(@RequestBody Map<String, Object> params) {
       // JsonObjec ret = new JsonObject();
        try {
            System.out.println("herere");
            String userId = (String) params.get("userId");
            String username = (String) params.get("username");

            System.out.println(userId);

            List<chatconversation> chatconversations = userService.getUserChatconversations(params);
          //  ret.put("success", true);
            //ret.put("chatrooms", chatrooms);

            return ResponseEntity.ok(new DataResponse(chatconversations));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));

          //  ret.put("success", false);
          //  ret.put("exc", e.getMessage());
        }
    }
}
