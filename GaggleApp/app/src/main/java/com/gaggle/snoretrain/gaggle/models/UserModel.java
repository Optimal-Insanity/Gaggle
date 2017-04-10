package com.gaggle.snoretrain.gaggle.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Snore Train on 2/23/2017.
 */

public class UserModel implements Serializable{
    @SuppressWarnings("serial")
    @SerializedName("user_name")
    private String user_name;
    @SerializedName("fname")
    private String fname;
    @SerializedName("lname")
    private String lname;
    @SerializedName("user_id")
    private int userId;
    @SerializedName("token")
    private String token;
    public String getUserName(){

        return user_name;
    }
    public String getFname(){

        return fname;
    }
    public int getUserId(){

        return userId;
    }
    public String getLname() {
        return lname;
    }

    public String getToken() {
        return token;
    }
}
