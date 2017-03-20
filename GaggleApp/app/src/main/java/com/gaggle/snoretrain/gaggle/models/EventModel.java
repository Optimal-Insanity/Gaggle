package com.gaggle.snoretrain.gaggle.models;


import com.google.gson.annotations.SerializedName;

/**
 * Created by Snore Train on 2/20/2017.
 */

public class EventModel {

    @SerializedName("party_title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("event_date")
    private String date;
    @SerializedName("party_type")
    private String partyType;
    @SerializedName("event_time")
    private String eventTime;
    @SerializedName("distance")
    private double distance;
    @SerializedName("paid")
    private int paid;
    @SerializedName("verified")
    private int verified;
    @SerializedName("party_id")
    private int partyID;
    @SerializedName("num_attending")
    private int numAttending;
    @SerializedName("num_watching")
    private int numWatching;
    @SerializedName("max_attending")
    private int maxAttending;

    public EventModel(){
        distance = 3.1;
    }

    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    public String getDistance(){
        return String.format("%.1f", distance) + " mi";
    }
    public String getDate() {
        return date;
    }
    public String getPartyType(){
        return partyType;
    }
    public String getEventTime() {
        return eventTime;
    }
    public int getPaid() {
        return paid;
    }
    public int getVerified() {
        return verified;
    }
    public int getPartyId() {
        return partyID;
    }
    public int getNumWatching() {
        return numWatching;
    }
    public int getNumAttending() {
        return numAttending;
    }
    public int getMaxAttending() {
        return maxAttending;
    }

}
