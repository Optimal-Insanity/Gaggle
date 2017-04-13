package com.gaggle.snoretrain.gaggle.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaggle.snoretrain.gaggle.R;
import com.gaggle.snoretrain.gaggle.listener.IExpandCallbackListener;
import com.gaggle.snoretrain.gaggle.models.MessageResponseModel;
import com.gaggle.snoretrain.gaggle.models.MessagesModel;
import com.gaggle.snoretrain.gaggle.viewholders.MessageViewHolder;

import java.util.List;

/**
 * Created by Snore Train on 4/8/2017.
 */

public class MessageRVAdapter extends RecyclerView.Adapter<MessageViewHolder> {
    private List<MessagesModel> messageSet;
    IExpandCallbackListener expandCallbackListener;
    public MessageRVAdapter(MessageResponseModel newDataSet){
        messageSet = newDataSet.getMessages();
    }
    public void setExpandCallbackListener(IExpandCallbackListener ecl){
        expandCallbackListener = ecl;
    }
    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent,
                                              int viewType){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_layout, parent, false);
        return new MessageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MessageViewHolder holder, final int position){

        final MessagesModel messages = messageSet.get(position);
        holder.bind(messages);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandCallbackListener.onTapCallback(messages);
            }
        });
    }
    @Override
    public int getItemCount(){
        if (messageSet == null) return 0;
        return messageSet.size();
    }
}
