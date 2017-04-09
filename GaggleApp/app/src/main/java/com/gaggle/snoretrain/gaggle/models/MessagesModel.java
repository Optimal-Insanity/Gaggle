package com.gaggle.snoretrain.gaggle.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Snore Train on 4/2/2017.
 */

public class MessagesModel extends DataSet{
    @SuppressWarnings("serial")
    @SerializedName("messages")
    ArrayList<MessageModel> messages;
    @SerializedName("fname")
    String fname;
    @SerializedName("lname")
    String lname;
    @SerializedName("user_id")
    int userId;

    public ArrayList<MessageModel> getMessages() {
        return messages;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public int getUserId() {
        return userId;
    }
}
