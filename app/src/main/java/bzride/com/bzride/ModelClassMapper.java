package bzride.com.bzride;

import java.util.HashMap;

/**
 * Created by Santhosh.joseph on 29-06-2016.
 */
public class ModelClassMapper {
    private static HashMap<String, Class> classHashMap;
    static {
        classHashMap = new HashMap<>();

        classHashMap.put(Utils.RIDE_REQUEST_I_URL, BZJSONResp.class);

        classHashMap.put(Utils.LOGIN_RIDER_URL, LoginResp.class);
        classHashMap.put(Utils.LOGIN_DRIVER_URL, LoginResp.class);
        classHashMap.put(Utils.REGISTER_DRIVER_URL, RegisterResp.class);
        classHashMap.put(Utils.REGISTER_RIDER_URL, RegisterResp.class);

        classHashMap.put(Utils.GET_BANK_INFO_URL, GetbankInfoResp.class);
        classHashMap.put(Utils.UPDATE_DEVICE_TOKEN_URL, BZJSONResp.class);
        classHashMap.put(Utils.UPDATE_DRIVER_AVAILABILITY_URL, BZJSONResp.class);
        classHashMap.put(Utils.UPDATE_DRIVER_LOCATION_URL, BZJSONResp.class);
        classHashMap.put(Utils.UPDATE_BANK_INITIAL_INFO_URL, BZJSONResp.class);
        classHashMap.put(Utils.ACCEPT_RIDE_REQUEST_URL, BZJSONResp.class);
        classHashMap.put(Utils.START_RIDE_URL, BZJSONResp.class);

        //classHashMap.put(Utils.NOTIFICATION_LIST_URL, NotificationRsp.class);
    }

    public static Class<BZJSONResp> getModelClass(String url) {
        return classHashMap.get(url);
    }
}
