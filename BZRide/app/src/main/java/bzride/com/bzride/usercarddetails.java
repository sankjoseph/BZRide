package bzride.com.bzride;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class usercarddetails extends AppCompatActivity implements View.OnClickListener {

    private EditText txtCardDetailsNumber,txtCardDetailsAddress1,txtCardDetailsAddress2;
    private EditText txtCardDetailsExpMonth,txtCardDetailsExpYear,txtCardDetailsCVV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usercarddetails);
        findViewById(R.id.btnCardDetailsDone).setOnClickListener(this);

        //load if data there
        RadioGroup radiogrcardType = (RadioGroup) findViewById(R.id.radioGroupCardType);

        if (Utils.isEqualAndNotEmpty(BZAppManager.getInstance().bzRiderData.cardData.cardType, "D"))
        {
           RadioButton btn = (RadioButton) radiogrcardType.findViewById(R.id.radioButtonDC);
           btn.setChecked(true);
        }
        else if (Utils.isEqualAndNotEmpty(BZAppManager.getInstance().bzRiderData.cardData.cardType, "C"))
        {
           RadioButton btn = (RadioButton) radiogrcardType.findViewById(R.id.radioButtonCC);
           btn.setChecked(true);
        }

        //card vendor
        RadioGroup radiogrcardVendor = (RadioGroup) findViewById(R.id.radioGroupVendor);

        if (Utils.isEqualAndNotEmpty(BZAppManager.getInstance().bzRiderData.cardData.cardVendor, "M"))
        {
            RadioButton btn = (RadioButton) radiogrcardVendor.findViewById(R.id.radioButtonMC);
            btn.setChecked(true);
        }
        else if (Utils.isEqualAndNotEmpty(BZAppManager.getInstance().bzRiderData.cardData.cardVendor,"V"))
        {
            RadioButton btn = (RadioButton) radiogrcardVendor.findViewById(R.id.radioButtonVISA);
            btn.setChecked(true);
        }

        txtCardDetailsNumber = (EditText)findViewById(R.id.txtCardDetailsNumber);
        txtCardDetailsAddress1 = (EditText)findViewById(R.id.txtCardDetailsAddress1);
        txtCardDetailsAddress2 = (EditText)findViewById(R.id.txtCardDetailsAddress2);
        txtCardDetailsExpMonth = (EditText)findViewById(R.id.txtCardDetailsExpMonth);
        txtCardDetailsExpYear = (EditText)findViewById(R.id.txtCardDetailsExpYear);
        txtCardDetailsCVV = (EditText)findViewById(R.id.txtCardDetailsCVV);

        // load values if already fit in
        txtCardDetailsNumber.setText(BZAppManager.getInstance().bzRiderData.cardData.cardNumber);
        txtCardDetailsAddress1.setText(BZAppManager.getInstance().bzRiderData.cardData.cardBillingAddress1);
        txtCardDetailsAddress2.setText(BZAppManager.getInstance().bzRiderData.cardData.cardBillingAddress2);
        txtCardDetailsExpMonth.setText(BZAppManager.getInstance().bzRiderData.cardData.cardExpiryMonth);
        txtCardDetailsExpYear.setText(BZAppManager.getInstance().bzRiderData.cardData.cardExpiryYear);
        txtCardDetailsCVV.setText(BZAppManager.getInstance().bzRiderData.cardData.cardCVV);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCardDetailsDone:
                cardDetailsDoneAction();break;

        }
    }
    private void cardDetailsDoneAction() {
        RadioGroup radiogrcardType = (RadioGroup) findViewById(R.id.radioGroupCardType);
        int index = radiogrcardType.getCheckedRadioButtonId();
        RadioButton btn = (RadioButton) radiogrcardType.findViewById(index);
        switch (btn.getId()) {
            case R.id.radioButtonCC:
                BZAppManager.getInstance().bzRiderData.cardData.cardType = "C";
                break;
            case R.id.radioButtonDC:
                BZAppManager.getInstance().bzRiderData.cardData.cardType = "D";
                break;
        }
    /// card vendor
        RadioGroup radiogrcardVendor = (RadioGroup) findViewById(R.id.radioGroupVendor);
        int indexVendor = radiogrcardVendor.getCheckedRadioButtonId();
        RadioButton btnVendor = (RadioButton) radiogrcardVendor.findViewById(indexVendor);
        switch (btnVendor.getId()) {
            case R.id.radioButtonMC:
                BZAppManager.getInstance().bzRiderData.cardData.cardVendor = "M";
                break;
            case R.id.radioButtonVISA:
                BZAppManager.getInstance().bzRiderData.cardData.cardVendor = "V";
                break;
        }
        // other details
        BZAppManager.getInstance().bzRiderData.cardData.cardNumber = txtCardDetailsNumber.getText().toString();
        BZAppManager.getInstance().bzRiderData.cardData.cardBillingAddress1 = txtCardDetailsAddress1.getText().toString();
        BZAppManager.getInstance().bzRiderData.cardData.cardBillingAddress2 = txtCardDetailsAddress2.getText().toString();

        BZAppManager.getInstance().bzRiderData.cardData.cardExpiryMonth = txtCardDetailsExpMonth.getText().toString();
        BZAppManager.getInstance().bzRiderData.cardData.cardExpiryYear = txtCardDetailsExpYear.getText().toString();
        BZAppManager.getInstance().bzRiderData.cardData.cardCVV = txtCardDetailsCVV.getText().toString();


      super.onBackPressed();
    }

}
