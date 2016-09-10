package bzride.com.bzride;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class driverBankInfo extends AppCompatActivity  implements   View.OnClickListener, OnPostExecuteListener {

    EditText txtBankName,txtBankAccountNumber,txtBankAccountHolderName,txtBankAccountRoutingNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_bank_info);
        findViewById(R.id.btnBankDriverDone).setOnClickListener(this);

        txtBankName =  (EditText)  findViewById(R.id.txtBankName);
        txtBankAccountNumber = (EditText)  findViewById(R.id.txtBankAccountNumber);
        txtBankAccountHolderName = (EditText)  findViewById(R.id.txtBankAccountHolderName);
        txtBankAccountRoutingNumber = (EditText)  findViewById(R.id.txtBankAccountRoutingNumber);

        // load values if already fit in
        txtBankName.setText(BZAppManager.getInstance().bzDriverData.driverBankInfo.BankName);
        txtBankAccountNumber.setText(BZAppManager.getInstance().bzDriverData.driverBankInfo.BankAccountNumber);
        txtBankAccountHolderName.setText(BZAppManager.getInstance().bzDriverData.driverBankInfo.BankAccountHolderName);
        txtBankAccountRoutingNumber.setText(BZAppManager.getInstance().bzDriverData.driverBankInfo.BankAccountRoutingNumber);

    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBankDriverDone:
                updateBankInfoaction();
                break;
        }
    }
    private void updateBankInfoaction() {
        if (NetworkListener.isConnectingToInternet(getApplicationContext())) {
        BZAppManager.getInstance().bzDriverData.driverBankInfo.BankName  = txtBankName.getText().toString();
        BZAppManager.getInstance().bzDriverData.driverBankInfo.BankAccountNumber  = txtBankAccountNumber.getText().toString();
        BZAppManager.getInstance().bzDriverData.driverBankInfo.BankAccountHolderName  = txtBankAccountHolderName.getText().toString();
        BZAppManager.getInstance().bzDriverData.driverBankInfo.BankAccountRoutingNumber  = txtBankAccountRoutingNumber.getText().toString();

        BZRESTApiHandler api = new BZRESTApiHandler(this);
        api.setMessage("updating bank details for driver...");
        api.setPostExecuteListener(this);

        String urlCall = Utils.BASE_URL + Utils.UPDATE_BANK_INITIAL_INFO_URL ;

        String params = BZAppManager.getInstance().getDriverBankDataParamsFlat();
        params = params + "&driverID=" + BZAppManager.getInstance().currentUserId;

        api.putDetails(urlCall, Utils.UPDATE_BANK_INITIAL_INFO_URL, params);


    } else {
        Utils.showInfoDialog(this, Utils.MSG_TITLE, Utils.MSG_NO_INTERNET, null);
    }
}
    @Override
    public void onSuccess(BZJSONResp model) {
        BZJSONResp response = (BZJSONResp)model;
        if (response.status.toString().equalsIgnoreCase(Utils.STATUS_SUCCESS)) {
            startActivity(new Intent(getApplicationContext(), BZLanding.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
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
