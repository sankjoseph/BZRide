package bzride.com.bzride;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

public class driverLicInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_lic_info);
    }
    public  void onStart(){
        super.onStart();
        EditText txtDatelicIssued = (EditText)findViewById(R.id.txtdatelicenseissued);
        txtDatelicIssued.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    DateDialog dlg = new DateDialog(v);
                    FragmentTransaction ftMgr = getFragmentManager().beginTransaction();
                    dlg.show(ftMgr, "");
                }

            }
        });


        EditText txtDatelicExpiry = (EditText)findViewById(R.id.txtdatelicenseexpiry);
        txtDatelicExpiry.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    DateDialog dlg = new DateDialog(v);
                    FragmentTransaction ftMgr = getFragmentManager().beginTransaction();
                    dlg.show(ftMgr,"");
                }

            }
        });
    }
}