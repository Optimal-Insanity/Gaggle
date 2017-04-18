package com.gaggle.snoretrain.gaggle.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.media.VolumeProviderCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaggle.snoretrain.gaggle.R;
import com.gaggle.snoretrain.gaggle.adapters.MessageRVAdapter;
import com.gaggle.snoretrain.gaggle.interactor.ApiInteractor;
import com.gaggle.snoretrain.gaggle.interactor.GaggleApplicationView;
import com.gaggle.snoretrain.gaggle.interactor.Interactor;
import com.gaggle.snoretrain.gaggle.listener.IExpandCallbackListener;
import com.gaggle.snoretrain.gaggle.listener.IMessageCallbackListener;
import com.gaggle.snoretrain.gaggle.models.MessageResponseModel;
import com.gaggle.snoretrain.gaggle.models.UserModel;
import com.gaggle.snoretrain.gaggle.presenter.ViewPresenter;
import com.gaggle.snoretrain.gaggle.services.GetMessagesTask;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Snore Train on 4/8/2017.
 */

public class MessageListFragment extends Fragment implements GaggleApplicationView<MessageResponseModel>{
    @BindView(R.id.fragment_recycler_view)
    RecyclerView threadRecycler;
    private UserModel user;
    private IExpandCallbackListener expandCallbackListener;
    IMessageCallbackListener messageCallbackListener;
    GetMessagesTask getMessagesTask;
    public MessageListFragment(){

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
        LinearLayoutManager messageRVLayoutManager = new LinearLayoutManager(getActivity());
        threadRecycler.setLayoutManager(messageRVLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(threadRecycler.getContext(),
                messageRVLayoutManager.getOrientation());
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.vp_margin, getContext().getTheme()));
        threadRecycler.addItemDecoration(dividerItemDecoration);

        /*messageCallbackListener = new IMessageCallbackListener() {
            @Override
            public void onSearchCallback(MessageResponseModel messages) {
                //Create new adapter of GroupRVAdapter type and set the RV adapter to it
                MessageRVAdapter messageRVAdapter = new MessageRVAdapter(messages);
                messageRVAdapter.setExpandCallbackListener(expandCallbackListener);
                threadRecycler.setAdapter(messageRVAdapter);
            }
        };
        getMessagesTask = new GetMessagesTask(messageCallbackListener);
        getMessagesTask.execute();*/
        Interactor interactor = new ApiInteractor.Builder()
                .setAdapterMethod("getMessages")
                .setMethodParameters(null)
                .setMethodParameterTypes(null)
                .build();
        ViewPresenter presenter = new ViewPresenter(this, interactor);
        presenter.getData();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        /*Interactor interactor = new ApiInteractor.Builder()
                .setAdapterMethod("getMessages")
                .setMethodParameters(null)
                .setMethodParameterTypes(null)
                .build();
        ViewPresenter presenter = new ViewPresenter(this, interactor);
        presenter.getData();*/
    }

    @Override
    public void presentGaggleData(MessageResponseModel data) {
        MessageRVAdapter messageRVAdapter = new MessageRVAdapter(data);
        messageRVAdapter.setExpandCallbackListener(expandCallbackListener);
        threadRecycler.setAdapter(messageRVAdapter);
    }
}
