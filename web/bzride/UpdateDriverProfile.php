<?php
include("includes/db.php");
include("includes/common.php");

LOGDATA("Update Driver Profile");

// Check connection
if (!$conn) {
      showError("Data base connection error");
} 
else
{  
//basic driver details

$token = $_REQUEST['token'];
LOGDATA($token);
$driverID = GetIdByCheckforTimeout($token);


$firstName = getIfSet($_REQUEST['firstName']);
$middleName = getIfSet($_REQUEST['middleName']);
$lastName = getIfSet($_REQUEST['lastName']);
$email = getIfSet($_REQUEST['email']);
$password = getIfSet($_REQUEST['password']);
$address1 = getIfSet($_REQUEST['address1']);
$address2 = getIfSet($_REQUEST['address2']);
$phone = getIfSet($_REQUEST['phone']);
$ssn = getIfSet($_REQUEST['ssn']);

/*$cardType = getIfSet($_REQUEST['cardType']);
$cardProvider = getIfSet($_REQUEST['cardProvider']);
$cardBillingAddress1 = getIfSet($_REQUEST['cardBillingAddress1']);
$cardBillingAddress2 = getIfSet($_REQUEST['cardBillingAddress2']);
$cardToken = getIfSet($_REQUEST['cardToken']);*/

									
// insert driver values in DB
$driver_details="UPDATE bztbl_drivers SET FirstName = $firstName, MiddleName = $middleName,lastName= $lastName, email=$email,password=$password,address1=$address1
address2=$address2,phone=$phone, ssn = $ssn,LastModifiedDate=now() where Id = ".$driverID;
									
LOGDATA($driver_details);
$result = mysql_query($driver_details,$conn);
if (!$result) {
	showError(mysql_error());
}


$data = array();
$data["status"] ="S";
$data["info"] = "Driver profile updation completed";
echo json_encode($data);

mysql_close();
}
?>