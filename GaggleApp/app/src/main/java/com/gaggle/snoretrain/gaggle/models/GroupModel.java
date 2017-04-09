package com.gaggle.snoretrain.gaggle.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Snore Train on 2/22/2017.
 */

public class GroupModel extends DataSet{

    @SerializedName("group_id")
    private int groupId;
    @SerializedName("group_name")
    private String groupName;
    @SerializedName("members")
    private ArrayList<UserModel> members;
    @SerializedName("group_icon_url")
    private String photoUrl;

    public GroupModel(String gName, ArrayList<UserModel> mem, String pID){

        groupName = gName;
        members = mem;
        photoUrl = pID;
    }
    public int getGroupId() {

        return groupId;
    }
    public String getGroupName(){
        return groupName;
    }
    public ArrayList<UserModel> getMembers(){
        return members;
    }
    public UserModel getMember(int pos) {
        return members.get(pos);
    }
    public String getPhotoUrl(){
        return photoUrl;
    }

}
