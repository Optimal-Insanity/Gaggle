package com.gaggle.snoretrain.gaggle.viewholders;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaggle.snoretrain.gaggle.R;
import com.gaggle.snoretrain.gaggle.models.EventModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Snore Train on 2/20/2017.
 */

public class EventViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.party_title) TextView titleTextView;
    @BindView(R.id.party_description) TextView descriptionTextView;
    @BindView(R.id.party_distance) TextView distanceTextView;
    @BindView(R.id.party_date) TextView dateTextView;
    @BindView(R.id.event_time) TextView timeTextView;
    @BindView(R.id.party_photo) ImageView partyPhoto;
    @BindView(R.id.apply_button) ImageButton applyButton;
    @BindView(R.id.watch_button) ImageButton watchButton;
    @BindView(R.id.like_button) ImageButton likeButton;
    @BindView(R.id.share_button) ImageButton shareButton;
    @BindView(R.id.paid_indicator) ImageView paidIndicator;
    @BindView(R.id.verified_indicator) ImageView verifiedIndicator;

    public EventViewHolder(View itemView) {
        super(itemView);
        //Define View variables for class based on event_layout description
        ButterKnife.bind(this, itemView);
    }

    public final void bind(final EventModel party) {
        //set view info to model at specific model
        titleTextView.setText(party.getTitle());
        dateTextView.setText(party.getDate());
        descriptionTextView.setText(party.getDescription());
        distanceTextView.setText(party.getDistance());
        timeTextView.setText(party.getEventTime());
        partyPhoto.setImageResource(getResource(party.getPartyType()));
        if (party.getPaid() == 0){
            paidIndicator.setVisibility(View.GONE);
        }
        if (party.getVerified() == 0){
            verifiedIndicator.setVisibility(View.GONE);
        }



    }
    public int getResource(String eventType){
        if (eventType.equals("Outdoor"))
            return R.drawable.outdoor_icon;
        if (eventType.equals("Kickback"))
            return R.drawable.kickback_icon;
        if (eventType.equals("Birthday"))
            return R.drawable.birthday_icon;
        if (eventType.equals("Charity"))
            return R.drawable.charity_icon;
        if (eventType.equals("HomeEvent"))
            return R.drawable.house_icon;
        if (eventType.equals("Bar"))
            return R.drawable.bar_icon;
        return 0;
    }
    public void clicked() {

    }

    public void notClicked() {
        //remove expansion if not clicked
    }

}
