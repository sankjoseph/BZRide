package bzride.com.bzride;

/**
 * Created by Santhosh.Joseph on 12-07-2016.
 */
public class BZCommonUserInfo {
    public String FirstName;
    public String MiddleName;
    public String LastName;
    public String Email;
    public String Password;
    public String ConfirmPassword;
    public String Address1;
    public String Address2;
    public String PhoneNumber;
    public String SSN;
    public String DeviceId;
    public String DeviceType;
    public String currentlat;
    public String currentlong;
    public BZCardInfo cardData;
    public BZCommonUserInfo()
    {
        cardData = new BZCardInfo();
    }

}