package com.gaggle.snoretrain.gaggle.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaggle.snoretrain.gaggle.R;
import com.gaggle.snoretrain.gaggle.models.GroupModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Snore Train on 2/22/2017.
 */

public class GroupViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.group_name) TextView groupNameTextView;
        @BindView(R.id.group_icon) ImageView groupImageView;

        public GroupViewHolder(View itemView){
            super(itemView);
            //Define View variables for class based on group_layout description
            ButterKnife.bind(this, itemView);
        }

        public final void bind(final GroupModel group){
            //set view info for each group in list
            groupNameTextView.setText(group.getGroupName());
            groupImageView.setImageResource(group.getPhotoID());
        }
}
