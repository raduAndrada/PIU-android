package com.utcluj.laborator_1_android;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Student on 12/12/2017.
 */
public class Credentials {

    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @SerializedName("username")
    String username;

    @SerializedName("password")
    String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
