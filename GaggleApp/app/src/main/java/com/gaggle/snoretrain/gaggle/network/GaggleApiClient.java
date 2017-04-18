package com.gaggle.snoretrain.gaggle.network;

import android.util.Log;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Snore Train on 4/16/2017.
 */

public class GaggleApiClient {
    private static OkHttpClient client;

    private GaggleApiClient(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url = request.url();

                HttpUrl modifiedUrl = url.newBuilder()
                        .addQueryParameter(
                                "token", ApiValues.USER_TOKEN
                        ).build();
                Log.d("GaggleApiClient", modifiedUrl.toString());
                Request.Builder requestBuilder = request.newBuilder()
                        .url(modifiedUrl);
                Request modifiedRequest = requestBuilder.build();
                return chain.proceed(modifiedRequest);
            }
        });
        client = builder.build();
    }
    public OkHttpClient client() {
        return client;
    }

    public static class Builder{
        private GaggleApiClient client = new GaggleApiClient();

        public GaggleApiClient build() {
            GaggleApiClient out = client;
            client = null;
            return out;
        }
    }
}
