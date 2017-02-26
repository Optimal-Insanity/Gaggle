package com.gaggle.snoretrain.gaggle.models;


/**
 * Created by Snore Train on 2/20/2017.
 */

public class PartyModel {

    private String title;
    private String description;
    private String date;
    private String distance;
    private int photoID;

    public PartyModel(String t, String desc, String dist, String day, int pID) {
        title = t;
        description = desc;
        distance = dist;
        date = day;
        photoID = pID;
    }
    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    public String getDistance(){
        return distance;
    }
    public String getDate() {
        return date;
    }
    public int getPhotoID(){
        return photoID;
    }
}
