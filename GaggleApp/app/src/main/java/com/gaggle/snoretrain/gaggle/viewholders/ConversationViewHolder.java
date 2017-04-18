package com.gaggle.snoretrain.gaggle.viewholders;


import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gaggle.snoretrain.gaggle.R;
import com.gaggle.snoretrain.gaggle.models.MessageModel;
import com.gaggle.snoretrain.gaggle.models.MessagesModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Snore Train on 4/8/2017.
 */

public class ConversationViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.message)
    TextView message;
    @BindView(R.id.time_stamp)
    TextView messageTime;
    @BindView(R.id.chat_layout)
    LinearLayout chatLayout;
    int maxWidth;

    int userId;
    public ConversationViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        userId = 1;
    }
    public void setMessageMaxWidth(int max){
        maxWidth = (int) (max * .8f);
    }
    public final void bind(final MessageModel messageData){
        //set view info for each group in list
        message.setText(messageData.getMessage());
        message.setMaxWidth(maxWidth);
        if (messageData.getMessageTime().charAt(0) == '0'){
            messageData.setMessageTime(messageData.getMessageTime().substring(1));
        }
        messageTime.setText(messageData.getMessageTime());
        if (messageData.getSenderId() == userId){
            chatLayout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            message.setBackgroundResource(R.drawable.chat_out);
        } else {
            chatLayout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            message.setBackgroundResource(R.drawable.chat_in);
        }

    }
}
