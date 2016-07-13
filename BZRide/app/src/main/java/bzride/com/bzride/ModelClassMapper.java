package bzride.com.bzride;

import java.util.HashMap;

/**
 * Created by Santhosh.joseph on 29-06-2016.
 */
public class ModelClassMapper {
    private static HashMap<String, Class> classHashMap;
    static {
        classHashMap = new HashMap<>();
        classHashMap.put(Utils.LOGIN_RIDER_URL, LoginResp.class);
        classHashMap.put(Utils.LOGIN_DRIVER_URL, LoginResp.class);
        classHashMap.put(Utils.REGISTER_DRIVER_URL, RegisterResp.class);
        classHashMap.put(Utils.REGISTER_RIDER_URL, RegisterResp.class);

        classHashMap.put(Utils.GET_BANK_INFO_URL, GetbankInfoResp.class);



        //classHashMap.put(Utils.NOTIFICATION_LIST_URL, NotificationRsp.class);
    }

    public static Class<BZJSONResp> getModelClass(String url) {
        return classHashMap.get(url);
    }
}
