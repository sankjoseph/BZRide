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
$city = getIfSet($_REQUEST['city']);
$state = getIfSet($_REQUEST['state']);
$zip = getIfSet($_REQUEST['zip']);
$phone = getIfSet($_REQUEST['phone']);
								
// insert rider values in DB
$rider_details="UPDATE bztbl_drivers SET FirstName = $firstName, MiddleName = $middleName,
LastName = $lastName, Email = $email, Password = $password, Address1= $address1, Address2= $address2,
City = $city, State = $state,Zip = $zip,Phone=$phone where Id = ".$riderID;
									
LOGDATA($rider_details);
$result = mysql_query($rider_details,$conn);
if (!$result) {
	showError(mysql_error());
}


mysql_close();
}
?>