package com.utcluj.laborator_1_android;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Andrada on 12/12/2017.
 */
public class Message {

    @SerializedName("message")
    String message;



    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
