package com.gaggle.snoretrain.gaggle.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Snore Train on 3/18/2017.
 */

public class EventListModel extends ArrayList<EventModel>{

    public EventModel getEvent(int pos) {
        return this.get(pos);
    }
}
