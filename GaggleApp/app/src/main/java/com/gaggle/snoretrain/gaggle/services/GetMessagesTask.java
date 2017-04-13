package com.gaggle.snoretrain.gaggle.services;

import android.os.AsyncTask;

import com.gaggle.snoretrain.gaggle.listener.IMessageCallbackListener;
import com.gaggle.snoretrain.gaggle.models.MessageResponseModel;
import com.gaggle.snoretrain.gaggle.models.MessagesModel;
import com.gaggle.snoretrain.gaggle.utils.GaggleApi;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Snore Train on 4/8/2017.
 */

public class GetMessagesTask extends AsyncTask<String, String, MessageResponseModel> {
    private IMessageCallbackListener messageCallbackListener;

    public GetMessagesTask(final IMessageCallbackListener mcl){

        messageCallbackListener = mcl;
    }
    @Override
    protected MessageResponseModel doInBackground(String... strings){
        MessageResponseModel messageModels = new MessageResponseModel();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(GaggleApi.BASE_URL + "messages/get?token="+
                        GaggleApi.USER_TOKEN)
                .build();
        final Gson gson = new Gson();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        }catch (IOException e){
            e.printStackTrace();
        }
        if (response != null){
            messageModels = gson.fromJson(response.body().charStream(), MessageResponseModel.class);
        }
        return messageModels;
    }

    @Override
    protected void onPostExecute(MessageResponseModel messageResponseModel){
        super.onPostExecute(messageResponseModel);
        messageCallbackListener.onSearchCallback(messageResponseModel);
    }
}
