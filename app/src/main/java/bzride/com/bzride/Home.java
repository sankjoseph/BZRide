package bzride.com.bzride;

import android.Manifest;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
//OnMapReadyCallback
public class Home extends FragmentActivity  implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks ,
        GoogleApiClient.OnConnectionFailedListener, View.OnClickListener, LocationListener {
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

    private static final int ACCESS_MAPS_PERMISSIONS_REQUEST = 1;

    protected  GoogleApiClient mGoogleApiClient;
    private boolean connectedstatus;
    private static final LatLngBounds BOUNDS_INDIA  = new LatLngBounds (new LatLng(-0,0), new LatLng(-0,0));
    private EditText mTextATEdit;
    private RecyclerView mrecyclerView;
    private LinearLayoutManager layoutMgr;
    private ImageView cleartext;
    private PlacesAutoCompleteAdapter  at_adapter;
    private GoogleMap m_map;
    private LocationRequest mLocationRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mTextATEdit = (EditText)findViewById(R.id.txtSearchAddress);
        cleartext = (ImageView)findViewById(R.id.clearText);
        cleartext.setOnClickListener(this);
        mrecyclerView = (RecyclerView) findViewById(R.id.recycler_view_search);

        FragmentManager myFragmentManager = getSupportFragmentManager();

        SupportMapFragment mySupportMapFragment = (SupportMapFragment)myFragmentManager.findFragmentById(R.id.map);

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
        at_adapter = new PlacesAutoCompleteAdapter (this, R.layout.search_row,
                mGoogleApiClient, BOUNDS_INDIA, null);

        layoutMgr = new LinearLayoutManager(this);
        mrecyclerView.setLayoutManager(layoutMgr);
        mrecyclerView.setAdapter(at_adapter);

        mTextATEdit.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (!s.toString().equals("")) {
                    if (connectedstatus) {
                        at_adapter.getFilter().filter(s.toString());
                    } else {
                        Toast.makeText(getApplicationContext(), Utils.API_NOT_CONNECTED, Toast.LENGTH_SHORT).show();
                        Log.e(Utils.PlacesTag, Utils.API_NOT_CONNECTED);

                    }
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void afterTextChanged(Editable s) {

            }
        });
        mrecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        final PlacesAutoCompleteAdapter.PlaceAutocomplete item = at_adapter.getItem(position);
                        final String placeId = String.valueOf(item.placeId);
                        Log.i("TAG", "Autocomplete item selected: " + item.description);
                        /*
                             Issue a request to the Places Geo Data API to retrieve a Place object with additional details about the place.
                         */

                        PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                                .getPlaceById(mGoogleApiClient, placeId);
                        placeResult.setResultCallback(new ResultCallback<PlaceBuffer>() {
                            @Override
                            public void onResult(PlaceBuffer places) {
                                if (places.getCount() == 1) {
                                    //Do the things here on Click.....
                                    Toast.makeText(getApplicationContext(), String.valueOf(places.get(0).getLatLng()), Toast.LENGTH_SHORT).show();
                                    //move camera
                                    m_map.moveCamera(CameraUpdateFactory.newLatLng(places.get(0).getLatLng()));

                                } else {
                                    Toast.makeText(getApplicationContext(), Utils.SOMETHING_WENT_WRONG, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        Log.i("TAG", "Clicked: " + item.description);
                        Log.i("TAG", "Called getPlaceById to get Place details for " + item.placeId);
                    }
                })
        );
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
        settings.setCompassEnabled(true);

        m_map.setTrafficEnabled(true);
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

    private void handleNewLocation(Location location) {

        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();

        LatLng latLng = new LatLng(currentLatitude, currentLongitude);

        //mMap.addMarker(new MarkerOptions().position(new LatLng(currentLatitude, currentLongitude)).title("Current Location"));
        MarkerOptions options = new MarkerOptions()
                .position(latLng)
                .title("I am here!");
        m_map.addMarker(options);
        //m_map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        float zoomLevel = 16; //This goes up to 21
        m_map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel));

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