package com.gaggle.snoretrain.gaggle.services;

import android.content.Context;
import android.os.AsyncTask;

import com.gaggle.snoretrain.gaggle.listener.IGroupCallbackListener;
import com.gaggle.snoretrain.gaggle.models.GroupListModel;
import com.gaggle.snoretrain.gaggle.utils.GaggleApi;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Snore Train on 3/27/2017.
 */

public class GetGroupTask extends AsyncTask<String, String, GroupListModel> {

    private IGroupCallbackListener groupCallbackListener;
    private int userId;

    public GetGroupTask(final IGroupCallbackListener gcl, int uId){

        groupCallbackListener = gcl;
        userId = uId;
    }
    @Override
    protected GroupListModel doInBackground(String... strings){
        GroupListModel groupModels = new GroupListModel();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(GaggleApi.BASE_URL + "groups/params?token="+
                        GaggleApi.USER_TOKEN +
                        "&user_id=" + userId)
                .build();
        final Gson gson = new Gson();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        }catch (IOException e){
            e.printStackTrace();
        }
        if (response != null){
            groupModels = gson.fromJson(response.body().charStream(), GroupListModel.class);
        }
        return groupModels;
    }

    @Override
    protected void onPostExecute(GroupListModel groupModels){
        super.onPostExecute(groupModels);
        groupCallbackListener.onSearchCallback(groupModels);
    }
}
