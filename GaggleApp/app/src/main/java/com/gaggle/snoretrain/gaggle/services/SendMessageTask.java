package com.gaggle.snoretrain.gaggle.services;

import android.os.AsyncTask;

import com.gaggle.snoretrain.gaggle.listener.IMessageSendCallbackListener;
import com.gaggle.snoretrain.gaggle.models.MessageModel;
import com.gaggle.snoretrain.gaggle.models.MessagesModel;
import com.gaggle.snoretrain.gaggle.utils.GaggleApi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Snore Train on 4/11/2017.
 */

public class SendMessageTask extends AsyncTask<String, String, ArrayList<MessageModel>> {
    private IMessageSendCallbackListener messageSendCallbackListener;
    private String message;
    private int receiverId;

    public SendMessageTask(final IMessageSendCallbackListener mcl, String msg, int recipId){
        messageSendCallbackListener = mcl;
        message = msg;
        receiverId = recipId;
    }
    @Override
    protected ArrayList<MessageModel> doInBackground(String... strings){
        ArrayList<MessageModel> messageModels = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(GaggleApi.BASE_URL + "messages/send?token=" +
                        GaggleApi.USER_TOKEN + "&receiver_id=" +
                        receiverId + "&message=" + message)
                .build();
        final Gson gson = new Gson();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        }catch (IOException e){
            e.printStackTrace();
        }
        if (response != null){
            messageModels = gson.fromJson(response.body().charStream(), new TypeToken<ArrayList<MessageModel>>(){}.getType());
        }
        return messageModels;
    }

    @Override
    protected void onPostExecute(ArrayList<MessageModel> messageResponseModel){
        super.onPostExecute(messageResponseModel);
        messageSendCallbackListener.onSendCallback(messageResponseModel);
    }
}
