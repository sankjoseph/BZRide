package bzride.com.bzride;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.stripe.android.Stripe.*;
import com.stripe.android.*;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.exception.AuthenticationException;

import org.json.JSONArray;
import org.json.JSONObject;

public class usercarddetails extends AppCompatActivity implements View.OnClickListener {

    private EditText txtCardDetailsNumber,txtCardDetailsAddress1,txtCardDetailsAddress2;
    private EditText txtCardDetailsCity,txtCardDetailsState,txtCardDetailsZip;
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
        txtCardDetailsCity = (EditText)findViewById(R.id.CardDetailsCity);
        txtCardDetailsState = (EditText)findViewById(R.id.CardDetailsState);
        txtCardDetailsZip = (EditText)findViewById(R.id.CardDetailsgZip);

        // load values if already fit in
        txtCardDetailsNumber.setText(BZAppManager.getInstance().bzRiderData.cardData.cardNumber);
        txtCardDetailsAddress1.setText(BZAppManager.getInstance().bzRiderData.cardData.cardBillingAddress1);
        txtCardDetailsAddress2.setText(BZAppManager.getInstance().bzRiderData.cardData.cardBillingAddress2);
        txtCardDetailsCity.setText(BZAppManager.getInstance().bzRiderData.cardData.cardBillingCity);
        txtCardDetailsState.setText(BZAppManager.getInstance().bzRiderData.cardData.cardBillingState);
        txtCardDetailsZip.setText(BZAppManager.getInstance().bzRiderData.cardData.cardBillingZip);

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


        Card card = new Card(txtCardDetailsNumber.getText().toString(),
                Integer.parseInt(txtCardDetailsExpMonth.getText().toString()),
                Integer.parseInt(txtCardDetailsExpYear.getText().toString()),
                txtCardDetailsCVV.getText().toString());

        if (!card.validateNumber())
        {
            Utils.showInfoDialog(this, Utils.MSG_TITLE, Utils.MSG_INVALID_CARD_NUMBER, null);
            return;
        }
        else {
            int b =0;
        }
        if (!card.validateExpiryDate())
        {
            Utils.showInfoDialog(this, Utils.MSG_TITLE, Utils.MSG_INVALID_CARD_EXPDATE, null);
        }
        if (!card.validateCVC())
        {
            Utils.showInfoDialog(this, Utils.MSG_TITLE, Utils.MSG_INVALID_CARD_CVV, null);
            return;
        }
        else{
            int b =0;
        }


        new Stripe().createToken(card, "pk_test_LL1jchCoTe2qzVPx5GfwGY4o",
                new TokenCallback() {
                    public void onSuccess(Token token) {
                        // Send token to your server
                        String tokenString = token.getId();

                        BZAppManager.getInstance().bzRiderData.cardData.cardToken = tokenString;
                        Log.d("Stripesample", tokenString);
                    }

                    public void onError(Exception error) {
                        // Show localized error message
                        Toast.makeText(getApplicationContext(),
                                "Some error while getting credit cark token",
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        );
        // other details
        BZAppManager.getInstance().bzRiderData.cardData.cardNumber = txtCardDetailsNumber.getText().toString();
        BZAppManager.getInstance().bzRiderData.cardData.cardBillingAddress1 = txtCardDetailsAddress1.getText().toString();
        BZAppManager.getInstance().bzRiderData.cardData.cardBillingAddress2 = txtCardDetailsAddress2.getText().toString();
        BZAppManager.getInstance().bzRiderData.cardData.cardBillingCity = txtCardDetailsCity.getText().toString();
        BZAppManager.getInstance().bzRiderData.cardData.cardBillingState = txtCardDetailsState.getText().toString();
        BZAppManager.getInstance().bzRiderData.cardData.cardBillingZip = txtCardDetailsZip.getText().toString();

        BZAppManager.getInstance().bzRiderData.cardData.cardExpiryMonth = txtCardDetailsExpMonth.getText().toString();
        BZAppManager.getInstance().bzRiderData.cardData.cardExpiryYear = txtCardDetailsExpYear.getText().toString();
        BZAppManager.getInstance().bzRiderData.cardData.cardCVV = txtCardDetailsCVV.getText().toString();


      super.onBackPressed();
    }

}
