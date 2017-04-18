package com.gaggle.snoretrain.gaggle.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Snore Train on 4/16/2017.
 */

public class GaggleApi {
    public static GaggleApi api;

    private GaggleApi() {
        api = this;
    }

    final public static GaggleApiAdapter adapter(){
        Gson gson = new GsonBuilder().create();

        final GaggleApiAdapter apiAdapter = new Retrofit.Builder()
                .baseUrl(ApiValues.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(new GaggleApiClient.Builder().build().client())
                .build()
                .create(GaggleApiAdapter.class);
        return apiAdapter;
    }
}
