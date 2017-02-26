package com.gaggle.snoretrain.gaggle.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaggle.snoretrain.gaggle.R;
import com.gaggle.snoretrain.gaggle.holders.AttendingEventViewHolder;
import com.gaggle.snoretrain.gaggle.models.PartyModel;

import java.util.List;

/**
 * Created by Snore Train on 2/23/2017.
 */

public class AttendingEventRVAdapter extends RecyclerView.Adapter<AttendingEventViewHolder> {
    private List<PartyModel> parties;

    public AttendingEventRVAdapter(List<PartyModel> newDataSet){
        parties = newDataSet;
    }

    @Override
    public AttendingEventViewHolder onCreateViewHolder(ViewGroup parent,
                                              int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_attending_layout, parent, false);

        return new AttendingEventViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final AttendingEventViewHolder holder, final int position){

        final PartyModel party = parties.get(position);
        holder.bind(party);
    }

    @Override
    public int getItemCount(){
        if (parties == null) return 0;
        return parties.size();
    }
}
