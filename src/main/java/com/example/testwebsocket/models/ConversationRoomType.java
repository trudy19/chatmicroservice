package com.example.testwebsocket.models;

public enum ConversationRoomType {

    PRIVATECHAT(0),
    GROUPCHAT(1);

    private final int value;

    ConversationRoomType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
