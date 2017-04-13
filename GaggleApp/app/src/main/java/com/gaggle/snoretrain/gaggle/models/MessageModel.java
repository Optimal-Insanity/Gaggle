package com.gaggle.snoretrain.gaggle.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Snore Train on 3/16/2017.
 */

public class MessageModel extends DataSet{
    @SerializedName("message")
    String message;
    @SerializedName("sender_id")
    int senderId;
    @SerializedName("recipient_id")
    int recipientId;
    @SerializedName("message_status")
    int messageStatus;
    @SerializedName("m_time")
    String messageTime;
    @SerializedName("m_date")
    String messageDate;


    public String getMessage() {
        return message;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getRecipientId() {
        return recipientId;
    }

    public int getMessageStatus() {
        return messageStatus;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public String getMessageDate() {
        return messageDate;
    }
}
