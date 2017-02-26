package com.gaggle.snoretrain.gaggle.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaggle.snoretrain.gaggle.R;
import com.gaggle.snoretrain.gaggle.holders.MyEventViewHolder;
import com.gaggle.snoretrain.gaggle.models.PartyModel;

import java.util.List;

/**
 * Created by Snore Train on 2/22/2017.
 */

public class MyEventRVAdapter extends RecyclerView.Adapter<MyEventViewHolder> {

    private List<PartyModel> myEvents;

    public MyEventRVAdapter(List<PartyModel> newDataSet){
        myEvents = newDataSet;
    }

    @Override
    public MyEventViewHolder onCreateViewHolder(ViewGroup parent,
                                              int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_event_layout, parent, false);

        return new MyEventViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyEventViewHolder holder, final int position) {

        final PartyModel party = myEvents.get(position);
        holder.bind(party);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount(){
        if (myEvents == null) return 0;
        return myEvents.size();
    }
}
