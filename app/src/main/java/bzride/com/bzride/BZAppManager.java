package bzride.com.bzride;

import android.provider.Settings;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Santhosh.Joseph on 01-07-2016.
 */
public class BZAppManager {
    private static BZAppManager   _instance;
    public  boolean isDriver;
    public String currentUserId;
    public BZDriverInfo bzDriverData;
    public BZRiderInfo bzRiderData;
    public LatLng selectedPickUpLocation;
    public LatLng selectedDropLocation;
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
        retData += "&city=" + bzDriverData.City;
        retData += "&state=" + bzDriverData.State;
        retData += "&zip=" + bzDriverData.Zip;

        retData += "&phone=" + bzDriverData.PhoneNumber;
        retData += "&ssn=" + bzDriverData.SSN;
        retData += "&currentlat=" + bzDriverData.currentlat;
        retData += "&currentlong=" + bzDriverData.currentlong;
        retData += "&deviceType=A";

        retData += "&cardType=" + bzDriverData.cardData.cardType;
        retData += "&cardProvider=" + bzDriverData.cardData.cardVendor;

        retData += "&cardBillingAddress1=" + bzDriverData.cardData.cardBillingAddress1;
        retData += "&cardBillingAddress2=" + bzDriverData.cardData.cardBillingAddress2;
        retData += "&cardToken=" + "bxsabcsacb";//todo

        // Bank info
        retData += "&BankName=" + "testbank";//todo
        retData += "&AccountToken=" + "bzzbbz";//todo
        // Insurance Info
        retData += "&insCompany=" + bzDriverData.driverInsuranceInfo.insuranceCompany;
        retData += "&insPolicyNumber=" + bzDriverData.driverInsuranceInfo.insuranceNumber;
        retData += "&insValidFromDate=" + bzDriverData.driverInsuranceInfo.insurancedateFrom;
        retData += "&insExpDate=" + bzDriverData.driverInsuranceInfo.insurancedateOfExpiry;
        //license Info
        retData += "&licenseNumber=" + bzDriverData.driverLicenseInfo.licenseNumber;
        retData += "&licenceStateIssued=" + bzDriverData.driverLicenseInfo.licensestateIssued;
        retData += "&licenseDateIssued=" + bzDriverData.driverLicenseInfo.licensedateOfIssue;
        retData += "&licenseExpDate=" + bzDriverData.driverLicenseInfo.licensedateofExpiry;

        //vehicle Reg Info
        retData += "&vNumber=" + bzDriverData.driverVehRegInfo.vehicleNumberPlateNumber;
        retData += "&vStateRegistered=" + bzDriverData.driverVehRegInfo.vehicleRegistrationState;
        retData += "&vDateRegistered=" + bzDriverData.driverVehRegInfo.vehicledateOfRegistration;
        retData += "&vExpiryDate=" + bzDriverData.driverVehRegInfo.vehicledateOfExpiry;

        // vehicle model info
        retData += "&vYear=" + bzDriverData.driverVehicleInfo.vehicleYearOfManufacture;
        retData += "&vModel=" + bzDriverData.driverVehicleInfo.vehicleModel;
        retData += "&vMake=" + bzDriverData.driverVehicleInfo.vehicleMake;
        retData += "&vColor=" + bzDriverData.driverVehicleInfo.vehicleColor;

        return retData;
    }
    public  String getDriverBankDataParamsFlat()
    {
        String retData = "";
        retData += "&AccountType=" +"Savings";
        retData += "&BankName=" + bzDriverData.driverBankInfo.BankName;
        retData += "&AccountToken=" + "tokenest";
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

        retData += "&city=" + bzRiderData.City;
        retData += "&state=" + bzRiderData.State;
        retData += "&zip=" + bzRiderData.Zip;

        retData += "&phone=" + bzRiderData.PhoneNumber;
        retData += "&currentlat=" + bzRiderData.currentlat;
        retData += "&currentlong=" + bzRiderData.currentlong;
        retData += "&deviceType=A";


        retData += "&cardType=" + bzRiderData.cardData.cardType;
        retData += "&cardProvider=" + bzRiderData.cardData.cardVendor;
        retData += "&cardBillingAddress1=" + bzRiderData.cardData.cardBillingAddress1;
        retData += "&cardBillingAddress2=" + bzRiderData.cardData.cardBillingAddress2;
        retData += "&cardToken=" + "bxsabcsacb";//todo
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

