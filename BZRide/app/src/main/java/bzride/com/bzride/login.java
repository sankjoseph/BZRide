package bzride.com.bzride;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText loginemailtext = (EditText)findViewById(R.id.txtemail);
        final EditText passwordtext = (EditText)findViewById(R.id.txtpassword);
        Button btnLogin  = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener( new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Log.v("EditText", loginemailtext.getText().toString());
            }
        });

    }

}
