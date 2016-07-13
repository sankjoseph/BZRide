package bzride.com.bzride;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

public class EULA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eul);

        WebView wv = (WebView) findViewById(R.id.webViewEULA);
        wv.setVerticalScrollBarEnabled(true);
        wv.loadUrl("file:///android_asset/BZRideTerms.html");
    }

}
