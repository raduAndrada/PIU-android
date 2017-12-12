package com.utcluj.laborator_1_android;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Student on 12/5/2017.
 */
public class ChatMessage {

    @SerializedName("timestamp")
    private String timestamp;

    @SerializedName("text")
    private String text;

    @SerializedName("sender")
    private String sender;

    private ChatMessageType type;


    public ChatMessage() {
    }

    public ChatMessage(String sender, String message, String sentTime, ChatMessageType type) {
        this.text = message;
        this.timestamp = sentTime;
        this.sender = sender;
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return sender;
    }

    public void setUsername(String sender) {
        this.sender = sender;
    }

    public ChatMessageType getType() {
        return type;
    }

    public void setType(ChatMessageType type) {
        this.type = type;
    }
}
