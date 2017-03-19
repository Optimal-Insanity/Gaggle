package com.gaggle.snoretrain.gaggle.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Snore Train on 2/23/2017.
 */

public class UserModel {

    @SerializedName("user_name")
    private String user_name;
    @SerializedName("fname")
    private String fname;
    @SerializedName("lname")
    private String lname;

    public String getUserName(){

        return user_name;
    }

}
