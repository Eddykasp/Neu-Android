package com.neuandroid.weatherapp.ui;

import android.Manifest;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.neuandroid.weatherapp.R;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private GoogleMap jMap;
    private GoogleApiClient jGoogleApiClient;
    private static final String TAG = MapsActivity.class.getSimpleName();
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private LocationRequest jLocationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        jGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        jLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)
                .setFastestInterval(1 * 1000);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        jMap = googleMap;

//         Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-999, 1621);
//        jMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        jMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = LocationServices.FusedLocationApi.getLastLocation(jGoogleApiClient);

        handleNewLocation(location);

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        Location location = LocationServices.FusedLocationApi.getLastLocation(jGoogleApiClient);

        if (location == null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            LocationServices.FusedLocationApi.requestLocationUpdates(jGoogleApiClient, jLocationRequest, this);

        }else {

            handleNewLocation(location);
        }
        Log.i(TAG,"Location services connected");
    }

    private void handleNewLocation(Location location) {

        Log.d(TAG, location.toString());

        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();
        LatLng latlng = new LatLng(currentLatitude, currentLongitude);

        MarkerOptions options = new MarkerOptions()
                .position(latlng)
                .title("I am here man!");

        jMap.addMarker(options);
        jMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng,10.2f));

    }

    @Override
    public void onConnectionSuspended(int i) {

        Log.i(TAG,"Location services suspended.Please reconnect.");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        if(connectionResult.hasResolution()){

            try {

                connectionResult.startResolutionForResult(this,CONNECTION_FAILURE_RESOLUTION_REQUEST);

            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        }else {

            Log.i(TAG,"Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }

    @Override
    protected void onResume(){

        super.onResume();
//        setUpMapIfNeeded();
        jGoogleApiClient.connect();

    }

    private void setUpMapIfNeeded() {

        if(jMap == null){

//            jMap = (getSupportFragmentManager().findFragmentById(R.id.map));
        }
        if(jMap != null){

            setUpMap();
        }
    }

    @Override
    protected void onPause(){

        super.onPause();
        if(jGoogleApiClient.isConnected()){

            LocationServices.FusedLocationApi.removeLocationUpdates(jGoogleApiClient,this);
            jGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        handleNewLocation(location);
    }

    private void setUpMap(){

        jMap.addMarker(new MarkerOptions().position(new LatLng(0,0)).title("Marker"));


    }
}
