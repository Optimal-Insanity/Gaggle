package com.gaggle.snoretrain.gaggle.network;

import com.gaggle.snoretrain.gaggle.models.EventListModel;
import com.gaggle.snoretrain.gaggle.models.GroupListModel;
import com.gaggle.snoretrain.gaggle.models.MessageResponseModel;
import com.gaggle.snoretrain.gaggle.models.MessagesModel;
import com.gaggle.snoretrain.gaggle.models.UserModel;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Snore Train on 4/16/2017.
 */

public interface GaggleApiAdapter {
    @POST("/authentication/login")
    Observable<UserModel> loginUser(@Field("username") String username, @Field("password") String password);

    @GET("/events/params")
    Observable<EventListModel> getEvents(
            @Query("longitude") String lon,
            @Query("latitude") String lat,
            @Query("radius") String rad
    );
    @GET("/messages/get")
    Observable<MessageResponseModel> getMessages();
    @GET("/messages/send")
    Observable<MessagesModel> sendMessage(
            @Query("receiver_id") int receiverId,
            @Query("message") String message
    );
    @GET("/groups/params")
    Observable<GroupListModel> getGroups();

}
