package com.gaggle.snoretrain.gaggle.fragments;

import android.content.IntentSender;
import android.icu.text.DateFormat;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaggle.snoretrain.gaggle.R;
import com.gaggle.snoretrain.gaggle.adapters.EventRVAdapter;
import com.gaggle.snoretrain.gaggle.listener.IEventCallbackListener;
import com.gaggle.snoretrain.gaggle.models.EventListModel;

import com.gaggle.snoretrain.gaggle.services.GetEventTask;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;


import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Snore Train on 2/22/2017.
 */

public class EventListFragment extends Fragment implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener{
    @BindView(R.id.fragment_recycler_view)
    RecyclerView eventRecycler;
    private EventRVAdapter eventRVAdapter;
    private LinearLayoutManager eventAttendingRVLayoutManager;
    private Location mLastLocation;
    private LocationRequest mLocationRequest;
    private GetEventTask getEventTask;
    private IEventCallbackListener eventCallbackListener;
    private double latitude;
    private double longitude;
    private GoogleApiClient mGoogleApiClient;
    int LOCATION_REFRESH_TIME;
    int LOCATION_REFRESH_DISTANCE;


    public EventListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LOCATION_REFRESH_TIME = 10000;
        LOCATION_REFRESH_DISTANCE = 5;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //get context of view within activity and set to fragment recycler view layout
        View root = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        //bind views that need binding
        ButterKnife.bind(this, root);
        if (mGoogleApiClient == null){
            mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        mGoogleApiClient.connect();
        eventCallbackListener = new IEventCallbackListener() {
            @Override
            public void onSearchCallBack(EventListModel eventModels) {
                eventRVAdapter = new EventRVAdapter(eventModels);
                eventRecycler.setAdapter(eventRVAdapter);
                //get the llm for this activity and make recycler use it
                eventAttendingRVLayoutManager = new LinearLayoutManager(getActivity());
                eventRecycler.setLayoutManager(eventAttendingRVLayoutManager);
            }
        };

        return root;
    }
    @Override
    public void onPause(){
        stopLocationUpdates();
        super.onPause();
    }
    @Override
    public void onResume(){
        super.onResume();
        if (mGoogleApiClient.isConnected()){
            startLocationUpdates();
        }
    }
    @Override
    public void onStop(){
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(Bundle bundle){
        createLocationRequest();
        startLocationUpdates();
        LocationServices.FusedLocationApi.requestLocationUpdates(
            mGoogleApiClient, mLocationRequest, this);
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null){
            latitude = mLastLocation.getLatitude();
            longitude = mLastLocation.getLongitude();

        }
    }
    @Override
    public void onConnectionSuspended(int i){

    }
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult){

    }
    protected void stopLocationUpdates(){
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
    }
    protected void startLocationUpdates(){
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);

    }
    protected void createLocationRequest(){
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(50);
        mLocationRequest.setFastestInterval(10);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);
        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient,
                        builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult resultCallBack) {
                final Status status = resultCallBack.getStatus();
                final LocationSettingsStates states = resultCallBack.getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied. The client can
                        // initialize location requests here.
                        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                                mGoogleApiClient);
                        if (mLastLocation != null){
                            latitude = mLastLocation.getLatitude();
                            longitude = mLastLocation.getLongitude();
                        }
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied, but this can be fixed
                        // by showing the user a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(
                                    getActivity(),
                                    0x1);
                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settings are not satisfied. However, we have no way
                        // to fix the settings so we won't show the dialog.

                        break;
                }
            }
        });
    }
    private void updateUI(){
        getEventTask = new GetEventTask(eventCallbackListener, getActivity(), latitude, longitude);
        getEventTask.execute();
    }
    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        latitude = mLastLocation.getLatitude();
        longitude = mLastLocation.getLongitude();
        updateUI();
    }

}
