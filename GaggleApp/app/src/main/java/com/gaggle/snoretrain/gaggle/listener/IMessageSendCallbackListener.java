package com.gaggle.snoretrain.gaggle.listener;

import com.gaggle.snoretrain.gaggle.models.MessageModel;

import java.util.ArrayList;

/**
 * Created by Snore Train on 4/12/2017.
 */

public interface IMessageSendCallbackListener {
    void onSendCallback(ArrayList<MessageModel> messages);
}
