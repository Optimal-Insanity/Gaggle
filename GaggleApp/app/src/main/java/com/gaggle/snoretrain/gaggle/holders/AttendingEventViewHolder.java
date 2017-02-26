package com.gaggle.snoretrain.gaggle.holders;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaggle.snoretrain.gaggle.R;
import com.gaggle.snoretrain.gaggle.models.PartyModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Snore Train on 2/23/2017.
 */

public class AttendingEventViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.party_title) TextView titleTextView;
    @BindView(R.id.party_description) TextView descriptionTextView;
    @BindView(R.id.party_distance) TextView distanceTextView;
    @BindView(R.id.party_date) TextView dateTextView;
    @BindView(R.id.num_attending) TextView attendingTextView;
    @BindView(R.id.party_photo) ImageView partyPhoto;

    public AttendingEventViewHolder(View itemView) {
        super(itemView);

        //Define View variables for class based on party_layout description
        ButterKnife.bind(this, itemView);
        //Set text trunctuation
        descriptionTextView.setEllipsize(TextUtils.TruncateAt.END);
    }

    public final void bind(final PartyModel party) {
        //set view info to model at specific model
        attendingTextView.setText("Attending: 35");
        titleTextView.setText(party.getTitle());
        dateTextView.setText(party.getDate());
        descriptionTextView.setText(party.getDescription());
        distanceTextView.setText(party.getDistance());
        partyPhoto.setImageResource(party.getPhotoID());

    }
}
