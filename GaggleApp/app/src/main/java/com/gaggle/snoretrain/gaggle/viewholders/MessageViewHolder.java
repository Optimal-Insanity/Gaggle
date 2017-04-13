package com.gaggle.snoretrain.gaggle.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaggle.snoretrain.gaggle.R;
import com.gaggle.snoretrain.gaggle.models.MessagesModel;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Snore Train on 4/8/2017.
 */

public class MessageViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.connection_name)
    TextView contactNameTextView;
    @BindView(R.id.last_message)
    TextView lastThreadMessage;
    @BindView(R.id.connection_icon)
    ImageView contactImageView;

    public MessageViewHolder(View itemView){
        super(itemView);
        //Define View variables for class based on group_layout description
        ButterKnife.bind(this, itemView);
    }

    public final void bind(final MessagesModel thread){
        //set view info for each group in list
        contactNameTextView.setText(thread.getFname() + " " + thread.getLname());
        if (thread.getMessages().size() > 0){
            lastThreadMessage.setText(
                    thread.getMessages()
                            .get(thread.getMessages().size() - 1)
                            .getMessage()
            );

        }

    }
}
