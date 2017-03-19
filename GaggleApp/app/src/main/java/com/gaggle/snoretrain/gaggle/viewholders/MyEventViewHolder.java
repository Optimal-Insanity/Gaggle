package com.gaggle.snoretrain.gaggle.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaggle.snoretrain.gaggle.R;
import com.gaggle.snoretrain.gaggle.models.EventModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Snore Train on 2/22/2017.
 */

public class MyEventViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.my_event_title) TextView titleTextView;
    @BindView(R.id.my_event_date) TextView dateTextView;
    @BindView(R.id.my_event_photo) ImageView myEventPhoto;

    public MyEventViewHolder(View itemView){
        super(itemView);

        //Bind variables for display
        ButterKnife.bind(this, itemView);

    }

    public final void bind(final EventModel party){
        //set info for views based on party
        titleTextView.setText(party.getTitle());
        dateTextView.setText(party.getDate());
        myEventPhoto.setImageResource(R.drawable.bar_icon);

    }



}
