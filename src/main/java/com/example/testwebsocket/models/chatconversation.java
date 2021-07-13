package com.example.testwebsocket.models;

public class chatconversation {

    private String chatconversationId;
    private String name; // name of friend / chatroom
    private String userId ; //id of friend
    private int type;

    public chatconversation(String chatconversationId, String name, int type,String userId) {
        this.chatconversationId = chatconversationId;
        this.userId =userId;
        this.name = name;
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getchatconversationId() {
        return chatconversationId;
    }

    public void setchatconversationId(String chatconversationId) {
        this.chatconversationId = chatconversationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
