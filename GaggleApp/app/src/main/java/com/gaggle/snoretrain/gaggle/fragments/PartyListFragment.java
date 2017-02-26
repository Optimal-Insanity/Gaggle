package com.gaggle.snoretrain.gaggle.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaggle.snoretrain.gaggle.R;
import com.gaggle.snoretrain.gaggle.adapters.PartyRVAdapter;
import com.gaggle.snoretrain.gaggle.models.PartyModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Snore Train on 2/22/2017.
 */

public class PartyListFragment extends Fragment {
    @BindView(R.id.fragment_recycler_view)
    RecyclerView partyRecycler;

    public PartyListFragment(){

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

        //create new faux data set, replace later with async task on HTTP manager
        List<PartyModel> partyData = new ArrayList<>();

        for (int i = 0; i < 1000; i++)
            if((i % 2) == 0)
            partyData.add(new PartyModel("Fun with Friends", "A fun time with friends! " +
                    "We'll be getting together to celebrate some stuff. " +
                    "We'll Probably eat some food. Don't be a bitch show up",
                    "2.6 miles", "2/25/2017", R.drawable.its_party_time));
            else partyData.add(new PartyModel("Super Rager.", "Trying to get trashed and " +
                    "break some shit, might also shave Mike's head. Who knows..",
                    "3.4 miles", "3/4/2017", R.drawable.its_party_time_2));

        //Create new adapter of PartyRVAdapter type and set RV adapter to it
        PartyRVAdapter partyRVAdapter = new PartyRVAdapter(partyData);
        partyRecycler.setAdapter(partyRVAdapter);

        //get the llm for this activity and make recycler use it
        LinearLayoutManager partyRVLayoutManager = new LinearLayoutManager(getActivity());
        partyRecycler.setLayoutManager(partyRVLayoutManager);

        return root;
    }
}
