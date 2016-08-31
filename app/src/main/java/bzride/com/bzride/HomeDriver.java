package bzride.com.bzride;

import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class HomeDriver extends AppCompatActivity  implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks ,
        GoogleApiClient.OnConnectionFailedListener, View.OnClickListener, LocationListener, OnPostExecuteListener,
        GoogleMap.OnMarkerClickListener {

    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private static final int ACCESS_MAPS_PERMISSIONS_REQUEST = 1;

    protected  GoogleApiClient mGoogleApiClient;
    private boolean connectedstatus;
    private boolean isOffline;
    private static final LatLngBounds BOUNDS_INDIA  = new LatLngBounds (new LatLng(-0,0), new LatLng(-0,0));

    LatLng m_latLng;

    private GoogleMap m_map;
    private LocationRequest mLocationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_driver);
        isOffline = false;
        findViewById(R.id.btnToggleOffline).setOnClickListener(this);
        findViewById(R.id.btnFinish).setOnClickListener(this);

        BZRESTApiHandler api = new BZRESTApiHandler(this);
        api.setMessage("Creating Ride request...");
        String urlCall = Utils.BASE_URL + Utils.RIDE_REQUEST_I_URL ;

        String params = "&requestorId="+ "1" + "&startLocation=" + "roseville" + "&endLocation=" + "sacramento" +
                "&startLat=" + 38.7521 +  "&startLong=" + 121.2880 +
                "&endLat=" + 38.5816 +  "&endLong=" + 121.4944;

        api.putDetails(urlCall, Utils.RIDE_REQUEST_I_URL, params);
        api.setPostExecuteListener(this);
        /// get offline status and set;

        //?isOffline =
        FragmentManager myFragmentManager = getSupportFragmentManager();
        SupportMapFragment mySupportMapFragment = (SupportMapFragment)myFragmentManager.findFragmentById(R.id.mapdriver);
        mySupportMapFragment.getMapAsync(this);

        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds

        try
        {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .addApi(Places.GEO_DATA_API)
                    .addApi(Places.PLACE_DETECTION_API)
                    .build();
            mGoogleApiClient.connect();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onSuccess(BZJSONResp model) {

        BZJSONResp response = (BZJSONResp)model;
        if (response.status.toString().equalsIgnoreCase(Utils.STATUS_SUCCESS)) {

        }
        else {
            Utils.showInfoDialog(this, Utils.MSG_TITLE, response.info, null);
        }
    }


    @Override
    public void onFailure() {
        Utils.showInfoDialog(this, Utils.MSG_TITLE, Utils.MSG_ERROR_SERVER, null);
    }
    @Override
    public boolean onMarkerClick(Marker arg0) {


        arg0.showInfoWindow();
        return false;
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        m_map = googleMap;

        m_map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        UiSettings settings = m_map.getUiSettings();
        settings.setScrollGesturesEnabled(true);
        settings.setRotateGesturesEnabled(true);
        settings.setZoomControlsEnabled(true);
        settings.setZoomGesturesEnabled(true);
        settings.setMyLocationButtonEnabled(true);
        settings.setAllGesturesEnabled(true);
        settings.setCompassEnabled(true);
        m_map.setTrafficEnabled(true);

        m_map.setOnMarkerClickListener(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            m_map.setMyLocationEnabled(true);
        }

    }
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        connectedstatus = true;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    ACCESS_MAPS_PERMISSIONS_REQUEST);
        }
        else {
            Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

            if (location == null) {
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
            }
            else {
                handleNewLocation(location);
            }
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        handleNewLocation(location);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        // Make sure it's our original ACCESS_MAPS_PERMISSIONS_REQUEST
        if (requestCode == ACCESS_MAPS_PERMISSIONS_REQUEST) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

                if (location == null) {
                    LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
                }
                else {
                    handleNewLocation(location);
                }
            }
        }
        else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    @Override
    public void onConnectionSuspended(int i) {

    }

    public String getAddress(LatLng latlong) {
        String addressReturn = "";
        Geocoder geocoder = new Geocoder(HomeDriver.this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latlong.latitude, latlong.longitude, 1);
            Address obj = addresses.get(0);
            String add = obj.getAddressLine(0);
            /*add = add + "\n" + obj.getCountryName();
            add = add + "\n" + obj.getCountryCode();
            add = add + "\n" + obj.getAdminArea();
            add = add + "\n" + obj.getPostalCode();
            add = add + "\n" + obj.getSubAdminArea();
            add = add + "\n" + obj.getLocality();
            add = add + "\n" + obj.getSubThoroughfare();*/

            Log.v("IGA", "Address" + add);
            addressReturn = add;


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return addressReturn;
    }
    private void handleNewLocation(Location location) {

        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();

        m_latLng = new LatLng(currentLatitude, currentLongitude);

        String locationAddress = getAddress(m_latLng);

        //mMap.addMarker(new MarkerOptions().position(new LatLng(currentLatitude, currentLongitude)).title("Current Location"));
        MarkerOptions options = new MarkerOptions()
                .position(m_latLng)
                .title(locationAddress);
        m_map.addMarker(options);
        //m_map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        float zoomLevel = 16; //This goes up to 21
        m_map.moveCamera(CameraUpdateFactory.newLatLngZoom(m_latLng, zoomLevel));
        //update location in server

    }
    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }
    }

    private void setUpMapIfNeeded() {
        if (m_map != null) {
            m_map.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnToggleOffline:
                ToggleAction();
                break;
            case R.id.btnFinish:
                FinishAction();
                break;
        }
    }

    private void ToggleAction() {
        if (isOffline)
        {
            //change online and call webs ervice
            isOffline = false;
        }
        else
        {
            //change online and call webs ervice
            isOffline = true;
        }
    }

    private void FinishAction() {
        // call service for endride
    }
    public GoogleApiClient getAPIClient() {
        return mGoogleApiClient;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
      /*
         * Google Play services can resolve some errors it detects.
         * If the error has a resolution, try sending an Intent to
         * start a Google Play services activity that can resolve
         * error.
         */
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
                /*
                 * Thrown if Google Play services canceled the original
                 * PendingIntent
                 */
            } catch (IntentSender.SendIntentException e) {
                // Log the error
                e.printStackTrace();
            }
        } else {
            /*
             * If no resolution is available, display a dialog to the
             * user with the error.
             */
            Log.i("TAG", "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }
}