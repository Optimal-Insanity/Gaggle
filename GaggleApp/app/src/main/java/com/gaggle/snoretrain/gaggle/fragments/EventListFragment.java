package com.gaggle.snoretrain.gaggle.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
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


import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.LOCATION_SERVICE;


/**
 * Created by Snore Train on 2/22/2017.
 */

public class EventListFragment extends Fragment implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, ActivityCompat.OnRequestPermissionsResultCallback{
    @BindView(R.id.fragment_recycler_view)
    RecyclerView eventRecycler;
    private EventRVAdapter eventRVAdapter;
    private LinearLayoutManager eventAttendingRVLayoutManager;
    private Location mLastLocation;
    public LocationManager mLocationManager;
    private GetEventTask getEventTask;
    private IEventCallbackListener eventCallbackListener;
    private double latitude;
    private double longitude;
    int LOCATION_REFRESH_TIME;
    int LOCATION_REFRESH_DISTANCE;

    public EventListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //get context of view within activity and set to fragment recycler view layout
        View root = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        //bind views that need binding
        ButterKnife.bind(this, root);
        LOCATION_REFRESH_TIME = 10000;
        LOCATION_REFRESH_DISTANCE = 5;
        mLocationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME,
                    LOCATION_REFRESH_DISTANCE, mLocationListener);
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
        }
        mLastLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        latitude = mLastLocation.getLatitude();
        longitude = mLastLocation.getLongitude();
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
        getEventTask = new GetEventTask(eventCallbackListener, getActivity(), latitude, longitude);
        getEventTask.execute();

        return root;
    }

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

            mLastLocation = location;
            getEventTask = new GetEventTask(eventCallbackListener,
                    getActivity(), location.getLatitude(), location.getLongitude());

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };
    @Override
    public void onConnected(Bundle bundle){

    }
    @Override
    public void onConnectionSuspended(int i){

    }
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult){

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 200: {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                            || ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME,
                                LOCATION_REFRESH_DISTANCE, mLocationListener);
                    }
                }
            }
        }
    }
}
