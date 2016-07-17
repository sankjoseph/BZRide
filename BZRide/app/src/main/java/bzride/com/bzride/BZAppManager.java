package bzride.com.bzride;

/**
 * Created by Santhosh.Joseph on 01-07-2016.
 */
public class BZAppManager {
    private static BZAppManager   _instance;
    public  boolean isDriver;
    public String currentUserId;
    public BZDriverInfo bzDriverData;
    public BZRiderInfo bzRiderData;
    public  String getDriverDataParamsFlat()
    {
        String retData = "";
        retData += "&firstname=" + bzDriverData.FirstName;
        retData += "&lastname=" + bzDriverData.LastName;
        retData += "&email=" + bzDriverData.Email;
        retData += "&password=" + bzDriverData.Password;
        retData += "&confirmpassword=" + bzDriverData.ConfirmPassword;
        retData += "&address1=" + bzDriverData.Address1;
        retData += "&address2=" + bzDriverData.Address2;
        retData += "&phonenumber=" + bzDriverData.PhoneNumber;
        retData += "&deviceid=" + bzDriverData.DeviceId;
        retData += "&devicetype=" + bzDriverData.DeviceId;
        retData += "&currentlat=" + bzDriverData.currentlat;
        retData += "&currentlong=" + bzDriverData.currentlong;

        retData += "&cardtype=" + bzDriverData.userCardInfo.cardType;
        retData += "&cardvendor=" + bzDriverData.userCardInfo.cardVendor;
        retData += "&cardnumber=" + bzDriverData.userCardInfo.cardNumber;
        retData += "&cardbillingaddress1=" + bzDriverData.userCardInfo.cardBillingAddress1;
        retData += "&cardbillingaddress2=" + bzDriverData.userCardInfo.cardBillingAddress2;
        retData += "&cardexpirymonth=" + bzDriverData.userCardInfo.cardExpiryMonth;
        retData += "&cardexpiryyear=" + bzDriverData.userCardInfo.cardExpiryYear;
        retData += "&cardcvv=" + bzDriverData.userCardInfo.cardCVV;
        // Bank info
        retData += "&bankname=" + bzDriverData.driverBankInfo.BankName;
        retData += "&bankaccountnumber=" + bzDriverData.driverBankInfo.BankAccountNumber;
        retData += "&bankaccountholdername=" + bzDriverData.driverBankInfo.BankAccountHolderName;
        retData += "&bankaccountroutingnumber=" + bzDriverData.driverBankInfo.BankAccountRoutingNumber;
        // Insurance Info
        retData += "&insuranceCompany=" + bzDriverData.driverInsuranceInfo.insuranceCompany;
        retData += "&insuranceNumber=" + bzDriverData.driverInsuranceInfo.insuranceNumber;
        retData += "&insurancedateFrom=" + bzDriverData.driverInsuranceInfo.insurancedateFrom;
        retData += "&insurancedateOfExpiry=" + bzDriverData.driverInsuranceInfo.insurancedateOfExpiry;
        //license Info
        retData += "&licenseNumber=" + bzDriverData.driverLicenseInfo.licenseNumber;
        retData += "&licensestateIssued=" + bzDriverData.driverLicenseInfo.licensestateIssued;
        retData += "&licensedateOfIssue=" + bzDriverData.driverLicenseInfo.licensedateOfIssue;
        retData += "&licensedateofExpiry=" + bzDriverData.driverLicenseInfo.licensedateofExpiry;

        //vehicle Reg Info
        retData += "&vehicleNumberPlateNumber=" + bzDriverData.driverVehRegInfo.vehicleNumberPlateNumber;
        retData += "&vehicleRegistrationState=" + bzDriverData.driverVehRegInfo.vehicleRegistrationState;
        retData += "&vehicledateOfRegistration=" + bzDriverData.driverVehRegInfo.vehicledateOfRegistration;
        retData += "&vehicledateOfExpiry=" + bzDriverData.driverVehRegInfo.vehicledateOfExpiry;

        // vehicle model info
        retData += "&vehicleYearOfManufacture=" + bzDriverData.driverVehicleInfo.vehicleYearOfManufacture;
        retData += "&vehicleModel=" + bzDriverData.driverVehicleInfo.vehicleModel;
        retData += "&vehicleMake=" + bzDriverData.driverVehicleInfo.vehicleMake;
        retData += "&vehicleColor=" + bzDriverData.driverVehicleInfo.vehicleColor;

        return retData;
    }

    public  String getRiderDataParamsFlat()
    {
        String retData = "";
        retData += "&firstname=" + bzRiderData.FirstName;
        retData += "&lastname=" + bzRiderData.LastName;
        retData += "&email=" + bzRiderData.Email;
        retData += "&password=" + bzRiderData.Password;
        retData += "&confirmpassword=" + bzRiderData.ConfirmPassword;
        retData += "&address1=" + bzRiderData.Address1;
        retData += "&address2=" + bzRiderData.Address2;
        retData += "&phonenumber=" + bzRiderData.PhoneNumber;
        retData += "&deviceid=" + bzRiderData.DeviceId;
        retData += "&devicetype=" + bzRiderData.DeviceId;
        retData += "&currentlat=" + bzRiderData.currentlat;
        retData += "&currentlong=" + bzRiderData.currentlong;

        retData += "&cardtype=" + bzRiderData.userCardInfo.cardType;
        retData += "&cardvendor=" + bzRiderData.userCardInfo.cardVendor;
        retData += "&cardnumber=" + bzRiderData.userCardInfo.cardNumber;
        retData += "&cardbillingaddress1=" + bzRiderData.userCardInfo.cardBillingAddress1;
        retData += "&cardbillingaddress2=" + bzRiderData.userCardInfo.cardBillingAddress2;
        /*
        retData += "&cardexpirymonth=" + bzRiderData.userCardInfo.cardExpiryMonth;
        retData += "&cardexpiryyear=" + bzRiderData.userCardInfo.cardExpiryYear;
        retData += "&cardcvv=" + bzRiderData.userCardInfo.cardCVV;*/ //not required to be sent instead use token



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

