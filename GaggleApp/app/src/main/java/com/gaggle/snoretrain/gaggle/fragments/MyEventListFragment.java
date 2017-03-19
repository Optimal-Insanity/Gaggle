package com.gaggle.snoretrain.gaggle.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaggle.snoretrain.gaggle.R;
import com.gaggle.snoretrain.gaggle.adapters.MyEventRVAdapter;
import com.gaggle.snoretrain.gaggle.models.EventModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Snore Train on 2/22/2017.
 */

public class MyEventListFragment extends Fragment {
    @BindView(R.id.fragment_recycler_view)
    RecyclerView myEventRecycler;

    public MyEventListFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //get context of view within activity and set to fragment recycler view
        View root = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        //bind views with butterknife
        ButterKnife.bind(this, root);

        //create faux data for my events, later to replace with async task at top level
        List<EventModel> myEventData = new ArrayList<>();

        for (int i = 0; i < 14; i++)
            if((i % 2) == 0)
            myEventData.add(new EventModel());
            else myEventData.add(new EventModel());

        //Create new MyEventRVAdapter to set for this recyclerview
        MyEventRVAdapter myEventRVAdapter = new MyEventRVAdapter(myEventData);
        myEventRecycler.setAdapter(myEventRVAdapter);

        //get the llm for this activity and use it in recycler
        LinearLayoutManager myEventRVLayoutManager = new LinearLayoutManager(getActivity());
        myEventRecycler.setLayoutManager(myEventRVLayoutManager);

        return root;
    }
}
