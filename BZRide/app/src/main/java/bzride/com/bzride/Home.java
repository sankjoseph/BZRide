package bzride.com.bzride;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}
/*
    <android.support.v7.widget.RecyclerView
        android:id="@+id/recycler_view_search"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_below="@+id/txtSearchPlace"
        android:layout_marginTop="10dp"
        android:scrollbars="vertical" />
        */