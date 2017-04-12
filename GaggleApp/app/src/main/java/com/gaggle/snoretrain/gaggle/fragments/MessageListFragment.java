package com.gaggle.snoretrain.gaggle.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaggle.snoretrain.gaggle.R;
import com.gaggle.snoretrain.gaggle.adapters.MessageRVAdapter;
import com.gaggle.snoretrain.gaggle.listener.IExpandCallbackListener;
import com.gaggle.snoretrain.gaggle.listener.IMessageCallbackListener;
import com.gaggle.snoretrain.gaggle.models.MessageResponseModel;
import com.gaggle.snoretrain.gaggle.models.UserModel;
import com.gaggle.snoretrain.gaggle.services.GetMessagesTask;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Snore Train on 4/8/2017.
 */

public class MessageListFragment extends Fragment {
    @BindView(R.id.fragment_recycler_view)
    RecyclerView threadRecycler;
    private UserModel user;
    private IExpandCallbackListener expandCallbackListener;
    public MessageListFragment(){

    }
    public void setUser(UserModel u){

        user = u;
    }
    public void setExpandCallbackListener(IExpandCallbackListener ecl){
        expandCallbackListener = ecl;
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

        IMessageCallbackListener messageCallbackListener = new IMessageCallbackListener() {
            @Override
            public void onSearchCallback(MessageResponseModel messages) {
                //Create new adapter of GroupRVAdapter type and set the RV adapter to it
                MessageRVAdapter messageRVAdapter = new MessageRVAdapter(messages);
                messageRVAdapter.setExpandCallbackListener(expandCallbackListener);
                threadRecycler.setAdapter(messageRVAdapter);

                //get the layout manager for this activity and make RV use it
                LinearLayoutManager messageRVLayoutManager = new LinearLayoutManager(getActivity());
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(threadRecycler.getContext(),
                        messageRVLayoutManager.getOrientation());
                dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.vp_margin, getContext().getTheme()));
                threadRecycler.addItemDecoration(dividerItemDecoration);
                threadRecycler.setLayoutManager(messageRVLayoutManager);
            }
        };
        GetMessagesTask getMessagesTask = new GetMessagesTask(messageCallbackListener, user.getUserId());
        getMessagesTask.execute();
        return root;
    }
}
