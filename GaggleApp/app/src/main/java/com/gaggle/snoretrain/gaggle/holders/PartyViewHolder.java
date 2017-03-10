package com.gaggle.snoretrain.gaggle.holders;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaggle.snoretrain.gaggle.R;
import com.gaggle.snoretrain.gaggle.models.PartyModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Snore Train on 2/20/2017.
 */

public class PartyViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.party_title) TextView titleTextView;
    @BindView(R.id.party_description) TextView descriptionTextView;
    @BindView(R.id.party_distance) TextView distanceTextView;
    @BindView(R.id.party_date) TextView dateTextView;
    @BindView(R.id.party_photo) ImageView partyPhoto;
    @BindView(R.id.apply_button) ImageButton applyButton;
    @BindView(R.id.remove_button) ImageButton removeButton;
    @BindView(R.id.watch_button) ImageButton watchButton;


    public PartyViewHolder(View itemView) {
        super(itemView);
        //Define View variables for class based on party_layout description
        ButterKnife.bind(this, itemView);
    }

    public final void bind(final PartyModel party) {
        //set view info to model at specific model
        titleTextView.setText(party.getTitle());
        dateTextView.setText(party.getDate());
        descriptionTextView.setText(party.getDescription());
        distanceTextView.setText(party.getDistance());
        partyPhoto.setImageResource(party.getPhotoID());

    }

    public void clicked() {

    }

    public void notClicked() {
        //remove expansion if not clicked
    }

}
