package bzride.com.bzride;

/**
 * Created by Santhosh.Joseph on 01-07-2016.
 */
public class BZAppManager {
    private static BZAppManager   _instance;
    public  boolean isDriver;
    public BZDriverInfo bzDriverData;
    public BZRiderInfo bzRiderData;
    public  String getDriverDataParamsFlat()
    {
        String retData = "";
        ;
        retData = "&firstname="+ bzDriverData.FirstName +
                    "&lastname=" + bzDriverData.LastName ;

        ;


        return retData;
    }

    public  String getRiderDataParamsFlat()
    {
        String retData = "";

        return retData;
    }

    public static void clearData()
    {

    }
    private BZAppManager()
    {
        bzDriverData =  new BZDriverInfo();
        bzRiderData = new BZRiderInfo();
    }
    public synchronized static BZAppManager getInstance()
    {
        if (_instance == null)
        {
            _instance = new BZAppManager();
        }
        return _instance;
    }
}

