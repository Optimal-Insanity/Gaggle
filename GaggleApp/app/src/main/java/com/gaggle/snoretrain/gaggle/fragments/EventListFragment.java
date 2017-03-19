package com.gaggle.snoretrain.gaggle.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaggle.snoretrain.gaggle.R;
import com.gaggle.snoretrain.gaggle.adapters.AttendingEventRVAdapter;
import com.gaggle.snoretrain.gaggle.adapters.EventRVAdapter;
import com.gaggle.snoretrain.gaggle.listener.IEventCallbackListener;
import com.gaggle.snoretrain.gaggle.models.EventListModel;
import com.gaggle.snoretrain.gaggle.models.EventModel;
import com.gaggle.snoretrain.gaggle.services.GetEventTask;
import com.gaggle.snoretrain.gaggle.utils.GaggleApi;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Snore Train on 2/22/2017.
 */

public class EventListFragment extends Fragment {
    @BindView(R.id.fragment_recycler_view)
    RecyclerView eventRecycler;
    private EventRVAdapter eventRVAdapter;
    private LinearLayoutManager eventAttendingRVLayoutManager;


    public EventListFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        //get context of view within activity and set to fragment recycler view layout
        View root = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        //bind views that need binding
        ButterKnife.bind(this, root);

        final Gson gson = new Gson();

        IEventCallbackListener eventCallbackListener = new IEventCallbackListener() {
            @Override
            public void onSearchCallBack(EventListModel eventModels) {
                eventRVAdapter = new EventRVAdapter(eventModels);
                eventRecycler.setAdapter(eventRVAdapter);
                //get the llm for this activity and make recycler use it
                eventAttendingRVLayoutManager = new LinearLayoutManager(getActivity());
                eventRecycler.setLayoutManager(eventAttendingRVLayoutManager);
            }
        };

        GetEventTask getEventTask = new GetEventTask(eventCallbackListener, getActivity());
        getEventTask.execute();

        return root;
    }
}
