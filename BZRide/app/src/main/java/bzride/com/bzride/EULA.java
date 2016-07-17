package bzride.com.bzride;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

public class EULA extends AppCompatActivity implements View.OnClickListener, OnPostExecuteListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eul);

        findViewById(R.id.btnAcceptEULA).setOnClickListener(this);
        findViewById(R.id.btnDeclineEULA).setOnClickListener(this);

        WebView wv = (WebView) findViewById(R.id.webViewEULA);
        wv.setVerticalScrollBarEnabled(true);
        wv.loadUrl("file:///android_asset/BZRideTerms.html");
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAcceptEULA:
                acceptEULAaction();break;
            case R.id.btnDeclineEULA:
                declineEULAaction();break;
        }
    }
    private void acceptEULAaction() {
        if (NetworkListener.isConnectingToInternet(getApplicationContext())) {
            BZRESTApiHandler api = new BZRESTApiHandler(this);
            api.setMessage("Please wait...");
            api.setPostExecuteListener(this);

            if (BZAppManager.getInstance().isDriver == true) {

                String urlCall = Utils.BASE_URL + Utils.ACCEPT_EULA_DRIVER_URL;
                String params = "userid=" +BZAppManager.getInstance().currentUserId;
                api.putDetails(urlCall, Utils.ACCEPT_EULA_DRIVER_URL, params);
            }
        }
    }
    private void declineEULAaction() {

    }

    @Override
    public void onSuccess(BZJSONResp model) {

        if (model.status.toString().equalsIgnoreCase(Utils.STATUS_SUCCESS)) {
            Intent myIntent = new Intent(EULA.this, driverBankInfo.class);
            myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            EULA.this.startActivity(myIntent);
        }
        else {
            Utils.showInfoDialog(this, Utils.MSG_TITLE, model.info, null);
        }
    }


    @Override
    public void onFailure() {
        Utils.showInfoDialog(this, Utils.MSG_TITLE, Utils.MSG_ERROR_SERVER, null);
    }
}
