<?php
include("includes/db.php");
include("includes/common.php");

LOGDATA("Update rider");

// Check connection
if (!$conn) {
      showError("Data base connection error");
} 
else
{  
//basic driver details

$token = $_REQUEST['token'];
LOGDATA($token);
$riderID = GetIdByCheckforTimeout($token);


$firstName = getIfSet($_REQUEST['firstName']);
$middleName = getIfSet($_REQUEST['middleName']);

$lastName = getIfSet($_REQUEST['lastName']);
$email = getIfSet($_REQUEST['email']);
$password = getIfSet($_REQUEST['password']);
$address1 = getIfSet($_REQUEST['address1']);
$address2 = getIfSet($_REQUEST['address2']);
$phone = getIfSet($_REQUEST['phone']);
$cardType = getIfSet($_REQUEST['cardType']);
$cardProvider = getIfSet($_REQUEST['cardProvider']);
$cardBillingAddress1 = getIfSet($_REQUEST['cardBillingAddress1']);
$cardBillingAddress2 = getIfSet($_REQUEST['cardBillingAddress2']);
$cardToken = getIfSet($_REQUEST['cardToken']);

 
									
// insert rider values in DB
$rider_details="UPDATE bztbl_drivers SET FirstName = $firstName, MiddleName = $middleName,
LastName = $lastName, Email = $email, Password = $password, Address1= $address1, Address2= $address2,
Phone=$phone,CardType=$cardType,CardProvider = $cardProvider,cardBillingAddress1=$cardBillingAddress1,
cardBillingAddress2=$cardBillingAddress2,CardToken=$cardToken where Id = ".$riderID;
									
LOGDATA($rider_details);
$result = mysql_query($rider_details,$conn);
if (!$result) {
	showError(mysql_error());
}

/*
  
// bank details blank
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// insert values: ID, USER ID, TYPE, BANK NAME, AccountToken, CREATED BY DATE
$bank_details="insert into bztbl_driverbankdetails values('', $last_id, '', '','', now() )";
LOGDATA($bank_details);
$result  = mysql_query($bank_details,$conn);
if (!$result) {
	showError(mysql_error());
}

//vehicledetais 
$vModel = getIfSet($_REQUEST['vModel']);
$vMake = getIfSet($_REQUEST['vMake']);
$vColor = getIfSet($_REQUEST['vColor']);
$vYear = getIfSet($_REQUEST['vYear']);
$vNumber = getIfSet($_REQUEST['vNumber']);
$vDateRegistered = getIfSet($_REQUEST['vDateRegistered']);
$vStateRegistered = getIfSet($_REQUEST['vStateRegistered']);
$vExpiryDate = getIfSet($_REQUEST['vExpiryDate']);

// insert values: ID, DRIVER ID, VEHICLE MODEL, V MAKE, V COLOUR, V YEAR, V REG NO, V REG STATE, V DATE REGISTERED, V EXPIRY DATE, CREATED BY DATE 
$driver_vehicledetais = "insert into bztbl_drivervehicledetails values('', $last_id, $vModel, $vMake, $vColor, $vYear , 
									$vNumber, $vStateRegistered ,$vDateRegistered, $vExpiryDate,  now() )";			

LOGDATA($driver_vehicledetais);
$result = mysql_query($driver_vehicledetais,$conn);
if (!$result) {
	showError(mysql_error());
}

//vehicleinsurancedetais 
$insCompany = getIfSet($_REQUEST['insCompany']);
$insPolicyNumber = getIfSet($_REQUEST['insPolicyNumber']);
$insValidFromDate = getIfSet($_REQUEST['insValidFromDate']);
$insExpDate = getIfSet($_REQUEST['insExpDate']);

//insert values: ID, DRIVER ID, INSURANCE COMPANY, INS PLICY NO, INS DATE, INS EXPIRY DATE, CREATED BY DATE  //
$driver_insdetails="insert into bztbl_driverinsurancedetails values('', $last_id, $insCompany, $insPolicyNumber, $insValidFromDate,$insExpDate, now() )";
LOGDATA($driver_insdetails);
$result = mysql_query($driver_insdetails,$conn);
if (!$result) {
	showError(mysql_error());
}									

$licenseNumber = getIfSet($_REQUEST['licenseNumber']);
$licenceStateIssued = getIfSet($_REQUEST['licenceStateIssued']);
$licenseDateIssued = getIfSet($_REQUEST['licenseDateIssued']);
$licenseExpDate = getIfSet($_REQUEST['licenseExpDate']);
							
//insert values: ID, DRIVER ID, LICENCE NO, LICENCE ISSUE STATE,LIC ISSUE DATE, LIC EXPIRY DATE, CREATED BY DATE  //
$driver_licdetails="insert into bztbl_driverlicensedetails values('', $last_id, $licenseNumber, $licenceStateIssued,$licenseDateIssued,$licenseExpDate, now())";
LOGDATA($driver_licdetails);
$result = mysql_query($driver_licdetails,$conn);
if (!$result) {
	showError(mysql_error());
}

$data = array();
$data["status"] ="S";
$data["info"] = "Driver registration completed";
$data["Id"] = "".$last_id."";
echo json_encode($data);*/

mysql_close();
}
?>