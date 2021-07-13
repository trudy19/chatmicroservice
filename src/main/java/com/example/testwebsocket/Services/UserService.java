package com.example.testwebsocket.Services;


import com.example.testwebsocket.models.*;
import com.example.testwebsocket.payload.request.ChatMessageRequest;
import com.example.testwebsocket.repositories.FriendLinkRepository;
import com.example.testwebsocket.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository ;

    @Autowired
    FriendLinkRepository friendLinkRepository ;

    public void userOnline(User user) throws  Exception{

        if (userRepository.existsById(user.getId())==false) {
            user.setOnline(true);
            userRepository.save(user);
        }else {

        User user1 = userRepository.findById(user.getId()).orElseThrow(() -> new Exception("User not found"));;
        user1.setOnline(true);
        userRepository.save(user1);}
    }

    public void userOffline(String userID) throws Exception{
        User user = userRepository.findById(userID).orElseThrow(() -> new Exception("User not found"));
        user.setOnline(false);
        userRepository.save(user);
    }

    public boolean addFriend(String username, String friendName,String userId ,String friendId ) {

        FriendLink friendLink = new FriendLink();
        friendLink.setUsername0(username);
        friendLink.setUsername1(friendName);
        friendLink.setUserId0(userId);
        friendLink.setUserId1(friendId);
        friendLink.setListofmessage(new HashSet<>());

        friendLinkRepository.save(friendLink);
//        notificationService.noticeUserOfNewChatroom(friendName);
        return true;
    }



    public List<chatconversation> getPrivateChatconversations(Map<String, Object> user) {
        String userId0 = (String) user.get("userId");
        String username = (String) user.get("username");
        List<chatconversation> chatconversations = new LinkedList<>();
        List<FriendLink> friendLinks = friendLinkRepository.findAllByUserId0OrUserId1(userId0, userId0);
        for (FriendLink friendLink : friendLinks) {
            String username0 = friendLink.getUsername0();
            String username1 = friendLink.getUsername1();
            String friendName,friendId;

            if (username0.equals(username)) {
                friendName = username1;
                friendId=friendLink.getUserId1();
            } else {
                friendName = username0;
                friendId=friendLink.getUserId0();

            }
            String chatconversationId = friendLink.getId();
            chatconversation chatroom = new chatconversation(chatconversationId, friendName, ConversationRoomType.PRIVATECHAT.getValue(),friendId);
            chatconversations.add(chatroom);
        }
        return chatconversations;
    }

    public List<chatconversation> getUserChatconversations(Map<String, Object> user) {
        List<chatconversation> chatconversations = new LinkedList<>();
        chatconversations.addAll(getPrivateChatconversations(user));
       // chatrooms.addAll(getGroupChatrooms(username));
        return chatconversations;
    }

    public void chatMessageSave(String conversationID, ChatMessage chatMessage) throws Exception {

chatMessage.setId(UUID.randomUUID().toString());

FriendLink frindlink=friendLinkRepository.findById(conversationID).orElseThrow(() -> new Exception("Conversation not found"));
        frindlink.getListofmessage().add(chatMessage);
        friendLinkRepository.save(frindlink);
    }
}
