package bzride.com.bzride;

import android.provider.Settings;

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
        retData += "&firstName=" + bzDriverData.FirstName;
        retData += "&middleName=" + bzDriverData.MiddleName;
        retData += "&lastName=" + bzDriverData.LastName;
        retData += "&email=" + bzDriverData.Email;
        retData += "&password=" + bzDriverData.Password;
        retData += "&address1=" + bzDriverData.Address1;
        retData += "&address2=" + bzDriverData.Address2;
        retData += "&phone=" + bzDriverData.PhoneNumber;
        retData += "&currentlat=" + bzDriverData.currentlat;
        retData += "&currentlong=" + bzDriverData.currentlong;
        retData += "&deviceType=A";

        retData += "&cardType=" + bzDriverData.cardData.cardType;
        retData += "&cardProvider=" + bzDriverData.cardData.cardVendor;

        retData += "&cardBillingAddress1=" + bzDriverData.cardData.cardBillingAddress1;
        retData += "&cardBillingAddress2=" + bzDriverData.cardData.cardBillingAddress2;
        retData += "&cardToken=" + "bxsabcsacb";

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
        retData += "&firstName=" + bzRiderData.FirstName;
        retData += "&middleName=" + bzRiderData.MiddleName;
        retData += "&lastName=" + bzRiderData.LastName;
        retData += "&email=" + bzRiderData.Email;
        retData += "&password=" + bzRiderData.Password;
        retData += "&address1=" + bzRiderData.Address1;
        retData += "&address2=" + bzRiderData.Address2;
        retData += "&phone=" + bzRiderData.PhoneNumber;
        retData += "&currentlat=" + bzRiderData.currentlat;
        retData += "&currentlong=" + bzRiderData.currentlong;
        retData += "&deviceType=A";


        retData += "&cardType=" + bzRiderData.cardData.cardType;
        retData += "&cardProvider=" + bzRiderData.cardData.cardVendor;
        retData += "&cardBillingAddress1=" + bzRiderData.cardData.cardBillingAddress1;
        retData += "&cardBillingAddress2=" + bzRiderData.cardData.cardBillingAddress2;
        retData += "&cardToken=" + "bxsabcsacb";
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

