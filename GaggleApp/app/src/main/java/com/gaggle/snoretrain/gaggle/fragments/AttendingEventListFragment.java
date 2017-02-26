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
import com.gaggle.snoretrain.gaggle.models.PartyModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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

        //create new faux data set, replace later with async task on HTTP manager
        List<PartyModel> partyData = new ArrayList<>();

        for (int i = 0; i < 6; i++)
            if((i % 2) == 0)
                partyData.add(new PartyModel("Kickback Sunday!", "A fun time with friends! " +
                        "We'll be getting together to celebrate how great animals are. " +
                        "It doesn't matter though because I have two dogs that you'll wanna meet.",
                        "3.1 miles", "2/26/2017", R.drawable.its_party_time));
            else partyData.add(new PartyModel("Easy Going", "Trying to eat food and " +
                    "watch the office with someone who will appreciate my cats",
                    "1.8 miles", "3/5/2017", R.drawable.its_party_time_2));

        //Create new adapter of PartyRVAdapter type and set RV adapter to it
        AttendingEventRVAdapter eventAttendingRVAdapter = new AttendingEventRVAdapter(partyData);
        partyRecycler.setAdapter(eventAttendingRVAdapter);

        //get the llm for this activity and make recycler use it
        LinearLayoutManager eventAttendingRVLayoutManager = new LinearLayoutManager(getActivity());
        partyRecycler.setLayoutManager(eventAttendingRVLayoutManager);

        return root;
    }

}
