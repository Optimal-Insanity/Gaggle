package com.gaggle.snoretrain.gaggle.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaggle.snoretrain.gaggle.R;
import com.gaggle.snoretrain.gaggle.activities.NavActivity;
import com.gaggle.snoretrain.gaggle.models.GroupListModel;
import com.gaggle.snoretrain.gaggle.viewholders.GroupViewHolder;
import com.gaggle.snoretrain.gaggle.models.GroupModel;

import java.util.List;

/**
 * Created by Snore Train on 2/22/2017.
 */

public class GroupRVAdapter extends RecyclerView.Adapter<GroupViewHolder> {

    private List<GroupModel> groups;

    public GroupRVAdapter(GroupListModel newDataSet){
        groups = newDataSet.getGroups();
    }

    @Override
    public GroupViewHolder onCreateViewHolder(ViewGroup parent,
                                              int viewType){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.group_layout, parent, false);
        return new GroupViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final GroupViewHolder holder, final int position){

        final GroupModel group = groups.get(position);
        holder.bind(group);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavActivity.expandGroup(group);
            }
        });
    }
    @Override
    public int getItemCount(){
        if (groups == null) return 0;
        return groups.size();
    }
}
