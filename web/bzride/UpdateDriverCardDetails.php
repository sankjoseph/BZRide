<?php
include("includes/db.php");
include("includes/common.php");

LOGDATA("Update driver card details");

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


$CardType = getIfSet($_REQUEST['CardType']);
$CardProvider = getIfSet($_REQUEST['CardProvider']);
$cardBillingAddress1 = getIfSet($_REQUEST['cardBillingAddress1']);
$cardBillingAddress2 = getIfSet($_REQUEST['cardBillingAddress2']);
$CardToken = getIfSet($_REQUEST['CardToken']);
					
// update driver values in DB
$driver_details="UPDATE bztbl_drivers SET CardType = $CardType, CardProvider = $CardProvider,
cardBillingAddress1 = $cardBillingAddress1,cardBillingAddress2=$cardBillingAddress2,CardToken=$CardToken, LastModifiedDate=now() where Id = ".$driverID;

									
LOGDATA($driver_details);
$result = mysql_query($driver_details,$conn);
if (!$result) {
	showError(mysql_error());
}

$num_rows = mysql_affected_rows($result);
LOGDATA($num_rows);
if ( $num_rows > 0) {
	$data = array();
	$data["status"] ="S";
	$data["info"] = "Update driver card details success";
	$data["firstName"] = $firstName;
	$data["middleName"] = $middleName;
	$data["lastName"] = $lastName;
	echo json_encode($data);
}
else
{
	$data = array();
	$data["status"] ="F";
	$data["info"] = "Update driver card details failed";
	echo json_encode($data);
}

mysql_close();
}
?>