package com.gaggle.snoretrain.gaggle.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaggle.snoretrain.gaggle.R;
import com.gaggle.snoretrain.gaggle.adapters.ConversationRVAdapter;
import com.gaggle.snoretrain.gaggle.models.MessageResponseModel;
import com.gaggle.snoretrain.gaggle.models.MessagesModel;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Snore Train on 4/8/2017.
 */

public class ConversationFragment extends Fragment {
    @BindView(R.id.fragment_recycler_view)
    RecyclerView conversationRecycler;
    MessagesModel messagesModel;
    public ConversationFragment(){

    }
    public void setMessages(MessagesModel models){
        messagesModel = models;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        //get context of view within activity and set to fragment recycler view layout
        View root = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        //bind list of views that need binding
        ButterKnife.bind(this, root);
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        ((Activity)getContext()).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float dpWidth = metrics.widthPixels;
        ConversationRVAdapter conversationRVAdapter = new ConversationRVAdapter(messagesModel.getMessages());
        conversationRVAdapter.setWidthDp(dpWidth);
        conversationRecycler.setAdapter(conversationRVAdapter);

        //get the layout manager for this activity and make RV use it
        LinearLayoutManager messageRVLayoutManager = new LinearLayoutManager(getActivity());
        messageRVLayoutManager.setStackFromEnd(true);
        conversationRecycler.setLayoutManager(messageRVLayoutManager);
        return root;
    }
}
