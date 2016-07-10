package bzride.com.bzride;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class registerDriver extends AppCompatActivity implements View.OnClickListener, OnPostExecuteListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_driver);


        if (NetworkListener.isConnectingToInternet(getApplicationContext())) {
            BZRESTApiHandler api = new BZRESTApiHandler(this);
            api.setMessage("Authenticating...");
            api.setPostExecuteListener(this);

            if (BZAppManager.getInstance().isDriver == true) {

                String urlCall = Utils.BASE_URL + Utils.REGISTER_DRIVER_URL ;
                String params = "&username="+ "santhosh" + "&password=" + "123456"+ "&test=" + "31231";
                api.putDetails(urlCall, Utils.REGISTER_DRIVER_URL, params);
            }
            else{
                String urlCall = Utils.BASE_URL + Utils.REGISTER_RIDER_URL;
                String params = "&username="+ "santhosh" + "&password=" + "123456"+ "&test=" + "31231";
                api.putDetails(urlCall, Utils.REGISTER_RIDER_URL,params);
            }

        } else {
            Utils.showInfoDialog(this, Utils.MSG_TITLE, Utils.MSG_NO_INTERNET, null);
        }

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:

                break;
        }
    }
    @Override
    public void onSuccess(BZJSONResp model) {

        RegisterResp response = (RegisterResp)model;
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
}
