package com.gaggle.snoretrain.gaggle.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaggle.snoretrain.gaggle.R;
import com.gaggle.snoretrain.gaggle.models.EventListModel;
import com.gaggle.snoretrain.gaggle.viewholders.EventViewHolder;
import com.gaggle.snoretrain.gaggle.models.EventModel;

import java.util.ArrayList;

/**
 * Created by Snore Train on 2/19/2017.
 */

public class EventRVAdapter extends RecyclerView.Adapter<EventViewHolder> {

    private ArrayList<EventModel> eventModels;
    private int expandedPosition;

    public EventRVAdapter(ArrayList<EventModel> newDataSet){
        expandedPosition = -1;
        eventModels = newDataSet;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent,
                                              int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_layout, parent, false);

        return new EventViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final EventViewHolder holder, final int position) {
        if (eventModels == null){
            return;
        }
        final EventModel party = eventModels.get(position);
        holder.bind(party);

        final boolean isExpanded = position == expandedPosition;
        if (isExpanded){
            holder.clicked();
        } else {
            holder.notClicked();
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (expandedPosition >= 0){

                    notifyItemChanged(expandedPosition);
                }
                expandedPosition = isExpanded ? -1:position;
                notifyItemChanged(position);
            }
        });

    }

    @Override
    public int getItemCount(){
        if (eventModels == null) return 0;
        return eventModels.size();
    }

}
