package bzride.com.bzride;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity  {

    DrawerLayout dLayout;
    ListView mDrawerList;
    ArrayAdapter<String> adapter;
    /* ActionBarDrawerToggle indicates the presence of Navigation Drawer in the action bar */
    private ActionBarDrawerToggle mDrawerToggle;

    /* Title of the action bar */
    private String mTitle = "Navigation Drawer";

    private int selectedPosition;

    /* Getting navigation items from array */
    private String[] items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //String pass = "Password1";
        //String encryptedPass =  Utils.md5encrypt(pass);

        /*BZAppManager.getInstance().isDriver = false;
        Intent myIntent = new Intent(MainActivity.this, registerDriver.class);
        //myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        MainActivity.this.startActivity(myIntent);*/

        Intent myIntent = new Intent(MainActivity.this, Home.class);
        //myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        MainActivity.this.startActivity(myIntent);

        /*Intent myIntent = new Intent(MainActivity.this, BZLanding.class);
        //myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        MainActivity.this.startActivity(myIntent);///good code*/

    }
}
