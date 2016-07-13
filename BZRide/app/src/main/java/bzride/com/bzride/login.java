package bzride.com.bzride;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class login extends AppCompatActivity implements View.OnClickListener, OnPostExecuteListener  {
    private EditText loginmobiletext;
    private EditText passwordtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginmobiletext = (EditText)findViewById(R.id.txtmobile);
        passwordtext = (EditText)findViewById(R.id.txtpassword);
        findViewById(R.id.btnLogin).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                loginaction();
                break;
        }
    }
    private void loginaction() {
        if (NetworkListener.isConnectingToInternet(getApplicationContext())) {
            BZRESTApiHandler api = new BZRESTApiHandler(this);
            api.setPostExecuteListener(this);

            if (BZAppManager.getInstance().isDriver == true) {
                api.setMessage("Authenticating driver...");
                String urlCall = Utils.BASE_URL + Utils.LOGIN_DRIVER_URL + "?username="+ loginmobiletext.getText() + "&password=" + passwordtext.getText();
                api.get(urlCall, Utils.LOGIN_DRIVER_URL);
            }
            else{
                api.setMessage("Authenticating rider...");
                String urlCall = Utils.BASE_URL + Utils.LOGIN_RIDER_URL+ "?username="+ loginmobiletext.getText() + "&password=" + passwordtext.getText();;
                api.get(urlCall, Utils.LOGIN_RIDER_URL);
            }

        } else {
            Utils.showInfoDialog(this, Utils.MSG_TITLE, Utils.MSG_NO_INTERNET, null);
        }
    }
    @Override
    public void onSuccess(BZJSONResp model) {

        LoginResp response = (LoginResp)model;
        if (response.status.toString().equalsIgnoreCase(Utils.STATUS_SUCCESS)) {
           Intent myIntent = new Intent(login.this, EULA.class);
            myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
           login.this.startActivity(myIntent);
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
