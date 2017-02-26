package com.gaggle.snoretrain.gaggle.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaggle.snoretrain.gaggle.R;
import com.gaggle.snoretrain.gaggle.holders.PartyViewHolder;
import com.gaggle.snoretrain.gaggle.models.PartyModel;

import java.util.List;

/**
 * Created by Snore Train on 2/19/2017.
 */

public class PartyRVAdapter extends RecyclerView.Adapter<PartyViewHolder> {

    private List<PartyModel> parties;
    private int expandedPosition;

    public PartyRVAdapter(List<PartyModel> newDataSet){
        expandedPosition = -1;
        parties = newDataSet;
    }

    @Override
    public PartyViewHolder onCreateViewHolder(ViewGroup parent,
                                              int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.party_layout, parent, false);

        return new PartyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final PartyViewHolder holder, final int position) {

        final PartyModel party = parties.get(position);
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
        if (parties == null) return 0;
        return parties.size();
    }

}
