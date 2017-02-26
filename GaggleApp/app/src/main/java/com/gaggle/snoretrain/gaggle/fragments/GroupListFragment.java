package com.gaggle.snoretrain.gaggle.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaggle.snoretrain.gaggle.R;
import com.gaggle.snoretrain.gaggle.adapters.GroupRVAdapter;
import com.gaggle.snoretrain.gaggle.models.GroupModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Snore Train on 2/22/2017.
 */

public class GroupListFragment extends Fragment {
    @BindView(R.id.fragment_recycler_view)
    RecyclerView groupRecycler;

    public GroupListFragment(){

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

        //create new faux data set, replace later with async task on HTTP manager
        List<GroupModel> groupData = new ArrayList<>();

        for (int i = 0; i < 12; i++){
            groupData.add(new GroupModel("Homiez Want to Rage", 14, R.drawable.group_icon));
        }

        //Create new adapter of GroupRVAdapter type and set the RV adapter to it
        GroupRVAdapter groupRVAdapter = new GroupRVAdapter(groupData);
        groupRecycler.setAdapter(groupRVAdapter);

        //get the layout manager for this activity and make RV use it
        LinearLayoutManager groupRVLayoutManager = new LinearLayoutManager(getActivity());
        groupRecycler.setLayoutManager(groupRVLayoutManager);

        return root;
    }
}
