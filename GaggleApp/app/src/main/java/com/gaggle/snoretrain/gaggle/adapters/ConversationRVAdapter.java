package com.gaggle.snoretrain.gaggle.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaggle.snoretrain.gaggle.R;
import com.gaggle.snoretrain.gaggle.activities.NavActivity;
import com.gaggle.snoretrain.gaggle.models.MessageModel;
import com.gaggle.snoretrain.gaggle.viewholders.ConversationViewHolder;
import com.gaggle.snoretrain.gaggle.viewholders.GroupViewHolder;

import java.util.ArrayList;

/**
 * Created by Snore Train on 4/8/2017.
 */

public class ConversationRVAdapter extends RecyclerView.Adapter<ConversationViewHolder> {

    private ArrayList<MessageModel> thread;
    private int width;
    public ConversationRVAdapter(ArrayList<MessageModel> data) {
        thread = data;
    }
    @Override
    public ConversationViewHolder onCreateViewHolder(ViewGroup parent,
                                              int viewType){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.thread_item_layout, parent, false);
        return new ConversationViewHolder(v);
    }
    public void setWidthDp(float widthDp){
        width = (int)widthDp;
    }

    @Override
    public void onBindViewHolder(final ConversationViewHolder holder, final int position){

        final MessageModel message = thread.get(position);
        holder.setMessageMaxWidth(width);
        holder.bind(message);
    }
    @Override
    public int getItemCount(){
        if (thread == null) return 0;
        return thread.size();
    }
}
