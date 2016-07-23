package bzride.com.bzride;

/**
 * Created by Santhosh.joseph on 29-06-2016.
 */
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Santhosh.Joseph on 17-06-2016.
 */
public class Utils {
//http://www.lemonlabs.in/accidentapp/service/register.php?name=santhosh&mobile=9446431692&email=sankjoseph@gmail.com&device_id=xeq212121212121
    /*public static final String BASE_URL= "http://www.lemonlabs.in/accidentapp/service";
    public static final String LOGIN_URL = "/login/";
    public static final String REGISTER_URL = "/register.php";*/

    //public static final String BASE_URL= "http://www.intimationsoftware.com/ws/bzridedummy";

    //public static final String BASE_URL= "http://intimationsoftware.com/bzride/web/bzride";

    public static final String BASE_URL= "http://bzride.com/bzride";

    public static final String LOGIN_DRIVER_URL = "/LoginDriver.php";
    public static final String LOGIN_RIDER_URL = "/LoginRider.php";
    public static final String REGISTER_RIDER_URL = "/RegisterRider.php";
    public static final String REGISTER_DRIVER_URL = "/RegisterDriver.php";
    public static final String GET_BANK_INFO_URL = "/GetBankInfo.php";

    public static final String ACCEPT_EULA_DRIVER_URL = "/AcceptEULADriver.php";


    public static final String REGISTER_SUCCESS = "Login Success";

    public static final String STATUS_SUCCESS = "S";
    public static final String STATUS_FAILED = "F";

    public static final String MSG_TITLE = "BZRide";
    public static final String MSG_NO_INTERNET = "You are not connected to Internet. Please try later.";
    public static final String MSG_ERROR_SERVER = "Some error occured while connecting to server.";

    public static void showInfoDialog(Context c, String title, String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(c)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .show();
    }

    public static String getFormatedTime(String dateString, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date convertedDate = null;
        try {
            convertedDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat newFormat = new SimpleDateFormat(format);
        return newFormat.format(convertedDate);
    }
    public static boolean isEqualAndNotEmpty(String inputString,String strCompare)
    {
        if (!TextUtils.isEmpty(inputString))
        {
            if (inputString.equals(strCompare))
            {
                return true;
            }
        }
        return false;
    }
}
