package bzride.com.bzride;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Santhosh.Joseph on 25-09-2016.
 */
public class DFragment extends DialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialogfragment, container,
                false);
        String message = getArguments().getString("message");
        getDialog().setTitle(message);
        // Do something else
        return rootView;
    }
}
