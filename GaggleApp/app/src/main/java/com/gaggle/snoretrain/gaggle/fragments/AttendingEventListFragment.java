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
import com.gaggle.snoretrain.gaggle.models.EventListModel;
import com.gaggle.snoretrain.gaggle.models.EventModel;
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

/**
 * Created by Snore Train on 2/23/2017.
 */

public class AttendingEventListFragment extends Fragment {
    @BindView(R.id.fragment_recycler_view)
    RecyclerView partyRecycler;

    public AttendingEventListFragment(){

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
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("https://gaggleapi.herokuapp.com/parties/")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()){
                    throw new IOException("Unexpected Code " + response);
                } else {
                    EventListModel partyData = gson.fromJson(response.body().charStream(), EventListModel.class);
                    //Create new adapter of EventRVAdapter type and set RV adapter to it
                    AttendingEventRVAdapter eventAttendingRVAdapter = new AttendingEventRVAdapter(partyData.getEvents());
                    partyRecycler.setAdapter(eventAttendingRVAdapter);

                    //get the llm for this activity and make recycler use it
                    LinearLayoutManager eventAttendingRVLayoutManager = new LinearLayoutManager(getActivity());
                    partyRecycler.setLayoutManager(eventAttendingRVLayoutManager);
                }
            }
        });


        return root;
    }

}
