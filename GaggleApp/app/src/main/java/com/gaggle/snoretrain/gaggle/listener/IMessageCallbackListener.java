package com.gaggle.snoretrain.gaggle.listener;

import com.gaggle.snoretrain.gaggle.models.MessageResponseModel;

/**
 * Created by Snore Train on 4/8/2017.
 */

public interface IMessageCallbackListener {
    public void onSearchCallback(MessageResponseModel messages);
}
