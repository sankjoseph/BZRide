<?php
include("includes/common.php");
include("includes/db.php");

 
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
$cardType = getIfSet($_REQUEST['cardType']);
$cardProvider = getIfSet($_REQUEST['cardProvider']);
$cardBillingAddress1 = getIfSet($_REQUEST['cardBillingAddress1']);
$cardBillingAddress2 = getIfSet($_REQUEST['cardBillingAddress2']);
$cardToken = getIfSet($_REQUEST['cardToken']);
//date taken as current time
//$date = date("D M d, Y G:i", time());

// insert rider values in DB
//(1, 'Ameer', '22','name' 'myemail', 'newpassword', 'my addr1', 'myaddr2', '234466', '44455588', 'android', 0, 0, 'debit', 'mastro', '', now()),
$rider_details="insert into bztbl_riders values('', $firstName,$middleName, $lastName, $email, $password,
									$address1, $address2, $phone, $deviceId, $deviceType,0,0, $cardType, $cardProvider, $cardBillingAddress1,$cardBillingAddress2,$cardToken, now() )"; 
									
$result = mysql_query($rider_details,$conn);
LOGDATA($rider_details);

if (!$result) {
	showError(mysql_error());
}
	
$last_id = mysql_insert_id();
LOGDATA("last inserted rider =".$last_id );


if (!$last_id) {
	showError(mysql_error());
}
$data = array();
$data["status"] ="S";
$data["info"] = "Rider registration completed";

$data["Id"] = "".$last_id."";

echo json_encode($data);
	
mysql_close();
}
?>