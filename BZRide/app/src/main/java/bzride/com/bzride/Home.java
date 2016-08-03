package bzride.com.bzride;

import android.content.IntentSender;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
//import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.io.IOException;

public class Home extends AppCompatActivity implements  GoogleApiClient.ConnectionCallbacks ,
        GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

    protected  GoogleApiClient mGoogleApiClient;
    private boolean connectedstatus;
    private static final LatLngBounds BOUNDS_INDIA  = new LatLngBounds (new LatLng(-0,0), new LatLng(-0,0));
    private EditText mTextATEdit;
    private RecyclerView mrecyclerView;
    private LinearLayoutManager layoutMgr;
    private ImageView cleartext;
    private PlacesAutoCompleteAdapter  at_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mTextATEdit = (EditText)findViewById(R.id.txtSearchPlace);
        cleartext = (ImageView)findViewById(R.id.clearText);
        at_adapter = new PlacesAutoCompleteAdapter (this, R.layout.search_row,
                mGoogleApiClient, BOUNDS_INDIA, null);
        mrecyclerView = (RecyclerView) findViewById(R.id.recycler_view_search);
        layoutMgr = new LinearLayoutManager(this);
        mrecyclerView.setLayoutManager(layoutMgr);
        mrecyclerView.setAdapter(at_adapter);
        cleartext.setOnClickListener(this);

        try
        {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            mGoogleApiClient.connect();
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        mTextATEdit.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (!s.toString().equals("")) {
                    if (connectedstatus) {
                        at_adapter.getFilter().filter(s.toString());
                    }
                    else
                    {
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
                                if(places.getCount()==1){
                                    //Do the things here on Click.....
                                    Toast.makeText(getApplicationContext(),String.valueOf(places.get(0).getLatLng()),Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(getApplicationContext(), Utils.SOMETHING_WENT_WRONG,Toast.LENGTH_SHORT).show();
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
    public void onConnected(@Nullable Bundle bundle) {
        connectedstatus = true;
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onClick(View v) {

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