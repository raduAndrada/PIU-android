package com.utcluj.laborator_1_android;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Andrada on 12/12/2017.
 */
public class MessageList {

    @SerializedName("messages")
    private List<ChatMessage> messageList ;

    public MessageList(List<ChatMessage> messageList) {
        this.messageList = messageList;
    }

    public List<ChatMessage> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<ChatMessage> messageList) {
        this.messageList = messageList;
    }
}
