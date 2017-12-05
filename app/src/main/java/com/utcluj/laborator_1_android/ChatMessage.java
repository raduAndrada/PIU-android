package com.utcluj.laborator_1_android;

import java.util.Date;

/**
 * Created by Student on 12/5/2017.
 */
public class ChatMessage {

    private Date sentTime;

    private String message;

    private String username;

    private ChatMessageType type;


    public ChatMessage() {
    }

    public ChatMessage(String username, String message, Date sentTime, ChatMessageType type) {
        this.message = message;
        this.sentTime = sentTime;
        this.username = username;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSentTime() {
        return sentTime;
    }

    public void setSentTime(Date sentTime) {
        this.sentTime = sentTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ChatMessageType getType() {
        return type;
    }

    public void setType(ChatMessageType type) {
        this.type = type;
    }
}
