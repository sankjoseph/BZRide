package bzride.com.bzride;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class registerDriver extends AppCompatActivity implements View.OnClickListener, OnPostExecuteListener {
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
        setContentView(R.layout.activity_register_driver);
        findViewById(R.id.btnLicenseDetails).setOnClickListener(this);
        findViewById(R.id.btnVehicleDetails).setOnClickListener(this);
        findViewById(R.id.btnRegistrationDetails).setOnClickListener(this);
        findViewById(R.id.btnInsuranceDetails).setOnClickListener(this);
        findViewById(R.id.btnRegisterDriverAction).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLicenseDetails:
                licenseaction();break;
            case R.id.btnVehicleDetails:
                vehicleDetailsaction();break;
            case R.id.btnRegistrationDetails:
                vehicleRegistrationDetailsaction();break;
            case R.id.btnInsuranceDetails:
                vehicleInsuranceDetailsaction();break;
            case R.id.btnRegisterDriverAction:
                registeraction();break;

        }
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

            BZAppManager.getInstance().bzDriverData.FirstName = firstName.getText().toString();
            BZAppManager.getInstance().bzDriverData.LastName = lastName.getText().toString();
            BZAppManager.getInstance().bzDriverData.Email = email.getText().toString();
            BZAppManager.getInstance().bzDriverData.Password = pwd.getText().toString();
            BZAppManager.getInstance().bzDriverData.ConfirmPassword = confirmpwd.getText().toString();
            BZAppManager.getInstance().bzDriverData.Address1 = address1.getText().toString();
            BZAppManager.getInstance().bzDriverData.Address2 = address2.getText().toString();
            BZAppManager.getInstance().bzDriverData.PhoneNumber = PhoneNumber.getText().toString();

            // Get all other info from drill down screens from this register screen ie  vehicle,license, registration, insurance
            // This is done in other child drill down screens

            BZRESTApiHandler api = new BZRESTApiHandler(this);
            api.setMessage("Registering new driver...");
            api.setPostExecuteListener(this);

            if (BZAppManager.getInstance().isDriver == true) {

                String urlCall = Utils.BASE_URL + Utils.REGISTER_DRIVER_URL ;
                String params = BZAppManager.getInstance().getDriverDataParamsFlat();
                api.putDetails(urlCall, Utils.REGISTER_DRIVER_URL, params);
            }

        } else {
            Utils.showInfoDialog(this, Utils.MSG_TITLE, Utils.MSG_NO_INTERNET, null);
        }
    }

    private void licenseaction() {
        Intent myIntent = new Intent(registerDriver.this, driverLicInfo.class);
        registerDriver.this.startActivity(myIntent);
    }

    private void vehicleDetailsaction() {
        Intent myIntent = new Intent(registerDriver.this, driverVehicleInfo.class);
        registerDriver.this.startActivity(myIntent);
    }

    private void vehicleRegistrationDetailsaction() {
        Intent myIntent = new Intent(registerDriver.this, driverVehicleRegInfo.class);
        registerDriver.this.startActivity(myIntent);
    }
    private void vehicleInsuranceDetailsaction() {
        Intent myIntent = new Intent(registerDriver.this, driverInsuranceInfo.class);
        registerDriver.this.startActivity(myIntent);
    }

    private void bankDetailsaction() {
        Intent myIntent = new Intent(registerDriver.this, driverInsuranceInfo.class);
        registerDriver.this.startActivity(myIntent);
    }

    @Override
    public void onSuccess(BZJSONResp model) {

        RegisterResp response = (RegisterResp)model;
        if (response.status.toString().equalsIgnoreCase(Utils.STATUS_SUCCESS)) {
            Intent myIntent = new Intent(registerDriver.this, EULA.class);
            myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            registerDriver.this.startActivity(myIntent);
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
