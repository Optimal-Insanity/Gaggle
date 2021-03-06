package com.gaggle.snoretrain.gaggle.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaggle.snoretrain.gaggle.R;
import com.gaggle.snoretrain.gaggle.adapters.EventRVAdapter;
import com.gaggle.snoretrain.gaggle.interactor.ApiInteractor;
import com.gaggle.snoretrain.gaggle.interactor.GaggleApplicationView;
import com.gaggle.snoretrain.gaggle.interactor.Interactor;
import com.gaggle.snoretrain.gaggle.models.EventListModel;

import com.gaggle.snoretrain.gaggle.presenter.ViewPresenter;

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

import static android.app.Activity.RESULT_OK;


/**
 * Created by Snore Train on 2/22/2017.
 */

public class EventListFragment extends Fragment implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener, GaggleApplicationView<EventListModel>{
    @BindView(R.id.fragment_recycler_view)
    RecyclerView eventRecycler;
    private EventRVAdapter eventRVAdapter;
    private LinearLayoutManager eventAttendingRVLayoutManager;
    private Location mLastLocation;
    private LocationRequest mLocationRequest;
    private double latitude;
    private double longitude;
    private GoogleApiClient mGoogleApiClient;
    public final int LOCATION_REFRESH_TIME = 10000;
    public final int LOCATION_REFRESH_DISTANCE = 5;
    public final static int REQUEST_LOCATION_PERMISSION = 0x1;


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
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        mGoogleApiClient.connect();
        createLocationRequest();
        eventAttendingRVLayoutManager = new LinearLayoutManager(getActivity());
        final DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(eventRecycler.getContext(),
                eventAttendingRVLayoutManager.getOrientation());
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.vp_margin, getContext().getTheme()));
        eventRecycler.addItemDecoration(dividerItemDecoration);
        eventRecycler.setLayoutManager(eventAttendingRVLayoutManager);

        return root;
    }

    @Override
    public void onPause() {
        if (mGoogleApiClient.isConnected()) {
            stopLocationUpdates();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mGoogleApiClient.isConnected()) {
            startLocationUpdates();
        }
    }

    @Override
    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
        }
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
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(
                    mGoogleApiClient, mLocationRequest, this);
        }


    }
    protected void createLocationRequest(){
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(50000);
        mLocationRequest.setFastestInterval(10000);
        mLocationRequest.setSmallestDisplacement(200);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
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
                        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
                        } else{
                            startLocationUpdates();
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
                                    REQUEST_LOCATION_PERMISSION);
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
        Interactor interactor = new ApiInteractor.Builder()
                .setAdapterMethod("getEvents")
                .setMethodParameters(
                        Double.toString(longitude),
                        Double.toString(latitude),
                        Integer.toString(100))
                .setMethodParameterTypes(String.class, String.class, String.class)
                .build();
        ViewPresenter presenter = new ViewPresenter(this, interactor);
        presenter.getData();
    }
    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        latitude = mLastLocation.getLatitude();
        longitude = mLastLocation.getLongitude();
        updateUI();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 200: {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        startLocationUpdates();

                }
            }
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_LOCATION_PERMISSION){
            if (resultCode == RESULT_OK){
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
                }else {
                    startLocationUpdates();
                }
            }
        }
    }

    @Override
    public void presentGaggleData(EventListModel data) {
        eventRVAdapter = new EventRVAdapter(data.getEvents());
        eventRecycler.setAdapter(eventRVAdapter);
    }
}
