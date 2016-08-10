package bzride.com.bzride;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class BZLanding extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bzlanding);

        findViewById(R.id.btnLoginDriver).setOnClickListener(this);
        findViewById(R.id.btnLoginRider).setOnClickListener(this);

        findViewById(R.id.btnRegisterDriver).setOnClickListener(this);
        findViewById(R.id.btnRegisterRider).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLoginDriver:
                BZAppManager.getInstance().isDriver = true;
                loginaction();
                break;
            case R.id.btnLoginRider:
                BZAppManager.getInstance().isDriver = false;
                loginaction();
                break;
            case R.id.btnRegisterDriver:
                BZAppManager.getInstance().isDriver = true;
                resgisteractionDriver();
                break;
            case R.id.btnRegisterRider:
                BZAppManager.getInstance().isDriver = false;
                resgisteractionRider();
                break;
        }
    }


    private void loginaction() {

        Intent myIntent = new Intent(BZLanding.this, login.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        BZLanding.this.startActivity(myIntent);

    }

    private void resgisteractionDriver() {

        Intent myIntent = new Intent(BZLanding.this, registerDriver.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        BZLanding.this.startActivity(myIntent);
    }

    private void resgisteractionRider() {

        Intent myIntent = new Intent(BZLanding.this, registeruser.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        BZLanding.this.startActivity(myIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
