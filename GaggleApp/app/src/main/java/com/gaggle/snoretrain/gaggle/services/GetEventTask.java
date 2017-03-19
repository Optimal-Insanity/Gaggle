package com.gaggle.snoretrain.gaggle.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import com.gaggle.snoretrain.gaggle.activities.NavActivity;
import com.gaggle.snoretrain.gaggle.listener.IEventCallbackListener;
import com.gaggle.snoretrain.gaggle.models.EventListModel;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Snore Train on 3/18/2017.
 */

public class GetEventTask extends AsyncTask<String, String, EventListModel> {

    private IEventCallbackListener eventCallbackListener;
    private Context currContext;
    public GetEventTask(final IEventCallbackListener ecl, Context someContext){
        eventCallbackListener = ecl;
        currContext = someContext;
    }

    @Override
    protected EventListModel doInBackground(String... strings) {
        EventListModel eventModels = new EventListModel();

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://gaggleapi.herokuapp.com/parties/")
                .build();
        final Gson gson = new Gson();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response != null) {
            eventModels = gson.fromJson(response.body().charStream(), EventListModel.class);
        }
        return eventModels;
    }

    @Override
    protected void onPostExecute(EventListModel eventModels){
        super.onPostExecute(eventModels);
        eventCallbackListener.onSearchCallBack(eventModels);
    }
}
