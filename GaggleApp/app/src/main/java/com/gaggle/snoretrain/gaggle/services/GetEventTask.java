package com.gaggle.snoretrain.gaggle.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import com.gaggle.snoretrain.gaggle.activities.NavActivity;
import com.gaggle.snoretrain.gaggle.listener.IEventCallbackListener;
import com.gaggle.snoretrain.gaggle.models.EventListModel;
import com.gaggle.snoretrain.gaggle.network.ApiValues;
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
    private double longitude;
    private double latitude;

    public GetEventTask(final IEventCallbackListener ecl, double lat, double lon){
        eventCallbackListener = ecl;

        longitude = lon;
        latitude = lat;
    }

    @Override
    protected EventListModel doInBackground(String... strings) {
        EventListModel eventModels = new EventListModel();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(ApiValues.BASE_URL + "/events/params?token="
                + ApiValues.USER_TOKEN + "&longitude=" + Double.toString(longitude) +
                "&latitude=" + Double.toString(latitude) +
                "&radius=" + Integer.toString(100))
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
