package com.gaggle.snoretrain.gaggle.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Snore Train on 3/18/2017.
 */

public class EventListModel{

    @SerializedName("events")
    @Expose
    private ArrayList<EventModel> eventModels;

    public ArrayList<EventModel> getEvents() {
        return eventModels;
    }
}
