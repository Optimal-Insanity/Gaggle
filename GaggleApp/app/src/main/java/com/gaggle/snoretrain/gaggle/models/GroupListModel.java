package com.gaggle.snoretrain.gaggle.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Snore Train on 3/27/2017.
 */

public class GroupListModel {
    @SerializedName("groups")
    private ArrayList<GroupModel> groups;
    public GroupListModel(){

    }
    public ArrayList<GroupModel> getGroups(){

        return groups;
    }
}
