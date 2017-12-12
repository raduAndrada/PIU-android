package com.utcluj.laborator_1_android;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Student on 12/12/2017.
 */
public class User {

    @SerializedName("token")
    String token;
    @SerializedName("id")
    String id;
    @SerializedName("display")
    String display;


    public User(String token, String id, String display) {
        this.token = token;
        this.id = id;
        this.display = display;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
