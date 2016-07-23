<?php
include("includes/db.php");
include("includes/common.php");

// Check connection
if (!$conn) {
      showError("Data base connection error");
} 
else
{  
//basic user details
$firstName = getIfSet($_REQUEST['firstName']);
$middleName = getIfSet($_REQUEST['middleName']);
$lastName = getIfSet($_REQUEST['lastName']);
$email = getIfSet($_REQUEST['email']);
$password = getIfSet($_REQUEST['password']);
$address1 = getIfSet($_REQUEST['address1']);
$address2 = getIfSet($_REQUEST['address2']);
$phone = getIfSet($_REQUEST['phone']);
$deviceId = getIfSet($_REQUEST['deviceId']);
$deviceType = getIfSet($_REQUEST['deviceType']);

$date = date("D M d, Y G:i", time());				

LOGDATA($firstName);
LOGDATA($date);
LOGDATA($deviceType);
// insert driver values in DB
$driver_details="insert into bztbl_drivers values('', $firstName, $middleName, $lastName, $email, $password,
									$address1, $address2, $phone, $deviceId, $deviceType, 
									'', '', '', '', '', $date )";
									
LOGDATA($driver_details);
$result = mysql_query($driver_details,$conn);
$last_id = mysqli_insert_id($conn);
LOGDATA("last inserted driver =" + $last_id);
if (!$result) {
	showError(mysql_error());
}

// bank details blank
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// insert values: ID, USER ID, TYPE, BANK NAME, AC NO, AC HOLDER NAME, ROUTING NO, CREATED BY DATE
$bank_details="insert into bztbl_userbankdetails values('', $last_id, '', '', '', '', '', $date )";

$result  = mysql_query($driver_details,$conn);
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
									$vNumber, $vStateRegistered ,$vDateRegistered, $vExpiryDate, $date)";			

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
$driver_insdetails="insert into bztbl_driverinsurancedetails values('', $last_id, $insCompany, $insPolicyNumber, $insValidFromDate,$insExpDate, $date )";
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
$driver_licdetails="insert into bztbl_driverlicensedetails values('', $last_id, $licenseNumber, $licenceStateIssued,$licenseDateIssued,$licenseExpDate, $date )";
LOGDATA($driver_licdetails);
$result = mysql_query($driver_licdetails,$conn);
if (!$result) {
	showError(mysql_error());
}

$data = array();
$data["status"] ="S";
$data["info"] = "Driver registration completed";
$data["Id"] = $last_id;
echo json_encode($data);
mysql_close();
}
?>