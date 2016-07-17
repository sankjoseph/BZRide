package bzride.com.bzride;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class registeruser extends AppCompatActivity  implements View.OnClickListener, OnPostExecuteListener {
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText pwd;
    private EditText confirmpwd;
    private EditText address1;
    private EditText address2;
    private EditText PhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeruser);
        findViewById(R.id.btnRegisterRiderAction).setOnClickListener(this);
        findViewById(R.id.btnRiderCardDetail).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegisterRiderAction:
                registeraction();break;

            case R.id.btnRiderCardDetail:
                cardDetailsAction();break;

        }
    }
    private void cardDetailsAction() {
        Intent myIntent = new Intent(registeruser.this, usercarddetails.class);
        registeruser.this.startActivity(myIntent);
    }
    private void registeraction() {
        if (NetworkListener.isConnectingToInternet(getApplicationContext())) {
            firstName = (EditText)findViewById(R.id.txtdriverFirstName);
            lastName = (EditText)findViewById(R.id.txtdriverLastName);
            email = (EditText)findViewById(R.id.txtdriveremail);
            pwd = (EditText)findViewById(R.id.txtdriverPwd);
            confirmpwd = (EditText)findViewById(R.id.txtdriverConfirmPwd);
            address1 = (EditText)findViewById(R.id.txtdriverAddress1);
            address2 = (EditText)findViewById(R.id.txtdriverAddress2);
            PhoneNumber = (EditText)findViewById(R.id.txtdriverPhoneNumber);

            BZAppManager.getInstance().bzRiderData.FirstName = firstName.getText().toString();
            BZAppManager.getInstance().bzRiderData.LastName = lastName.getText().toString();
            BZAppManager.getInstance().bzRiderData.Email = email.getText().toString();
            BZAppManager.getInstance().bzRiderData.Password = pwd.getText().toString();
            BZAppManager.getInstance().bzRiderData.ConfirmPassword = confirmpwd.getText().toString();
            BZAppManager.getInstance().bzRiderData.Address1 = address1.getText().toString();
            BZAppManager.getInstance().bzRiderData.Address2 = address2.getText().toString();
            BZAppManager.getInstance().bzRiderData.PhoneNumber = PhoneNumber.getText().toString();

            // Get all other info from drill down screens from this register screen ie  card info taken in other child screen

            BZRESTApiHandler api = new BZRESTApiHandler(this);
            api.setMessage("Registering new rider...");
            api.setPostExecuteListener(this);

            if (BZAppManager.getInstance().isDriver == false) {
                String urlCall = Utils.BASE_URL + Utils.REGISTER_RIDER_URL ;
                String params = BZAppManager.getInstance().getRiderDataParamsFlat();
                api.putDetails(urlCall, Utils.REGISTER_RIDER_URL, params);
            }

        } else {
            Utils.showInfoDialog(this, Utils.MSG_TITLE, Utils.MSG_NO_INTERNET, null);
        }
    }
    @Override
    public void onSuccess(BZJSONResp model) {

        RegisterResp response = (RegisterResp)model;
        if (response.status.toString().equalsIgnoreCase(Utils.STATUS_SUCCESS)) {
            Intent myIntent = new Intent(registeruser.this, Home.class);
            myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            registeruser.this.startActivity(myIntent);
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
