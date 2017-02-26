package com.gaggle.snoretrain.gaggle.models;

/**
 * Created by Snore Train on 2/22/2017.
 */

public class GroupModel {

    private String groupName;
    private int numMembers;
    private int photoID;

    public GroupModel(String gName, int members, int pID){

        groupName = gName;
        numMembers = members;
        photoID = pID;
    }
    public String getGroupName(){
        return groupName;
    }
    public int getNumMembers(){
        return numMembers;
    }
    public int getPhotoID(){
        return photoID;
    }

}
