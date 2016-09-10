package bzride.com.bzride;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by Santhosh.Joseph on 31-08-2016.
 */
public class BZRideGcmListenerService extends GcmListenerService {
    private static final String TAG = "BZRideGcmListenerService";

    @Override
    public void onMessageReceived(String from, Bundle data) {
        String message = data.getString("m");

        String message2 = data.getString("message");

        Log.d(TAG, "From: " + from);
        Log.d(TAG, "Message: " + message);
        Log.d(TAG, "Message2: " + message2);


        // [START_EXCLUDE]
        /**
         * Production applications would usually process the message here.
         * Eg: - Syncing with server.
         *     - Store message in local database.
         *     - Update UI.
         */

        /**
         * In some cases it may be useful to show a notification indicating to the user
         * that a message was received.
         */
        if (message != null) {
            sendNotification(message);
        }
        if (message2 != null)
        {
            sendNotification(message2);
        }
        // [END_EXCLUDE]
    }
    private void sendNotification(String message) {

        /*Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);*/
        //PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
               // PendingIntent.FLAG_ONE_SHOT);*/

        Intent intent = new Intent(this, RideRequestNotifiedDetails.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        String requestId = "0";
        requestId = message.substring(message.lastIndexOf(":") + 1);

        intent.putExtra("RequestId",requestId);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent, PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                //ic_stat_ic_notification todo
                .setSmallIcon(R.drawable.ic_stat_ic_notification)
                .setContentTitle("BZ Ride")
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}
