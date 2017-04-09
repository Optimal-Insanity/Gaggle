package com.gaggle.snoretrain.gaggle.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Snore Train on 4/8/2017.
 */

public class MessageResponseModel {
    @SerializedName("connections")
    ArrayList<MessagesModel> connections;
    public ArrayList<MessagesModel> getMessages(){
        return connections;
    }
}
