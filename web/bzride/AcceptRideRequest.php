<?php
include("includes/db.php");
include("includes/common.php");

/////
LOGDATA("Accepting ride request");

// Check connection
if (!$conn) {
      showError("Data base connection error");
} 
else
{  
//basic driver details
$requestId = $_REQUEST['rideRequestId'];
$token = $_REQUEST['token'];
LOGDATA($token);
$driverID = GetIdByCheckforTimeout($token);

$requestSQL = "UPDATE bztbl_riderequests SET Status = 'A',DriverId = ".$driverID. " where Id = " .$requestId ;

LOGDATA($requestSQL);

$resultUpdate = mysql_query($requestSQL,$conn);
if (!$resultUpdate) {
	showError(mysql_error());
}

$num_rows = mysql_affected_rows($resultUpdate);
LOGDATA($num_rows);
if ( $num_rows > 0) {
	$data = array();
	$data["status"] ="S";
	$data["info"] = "Accepting ride request success for driver";
	$data["firstName"] = $firstName;
	$data["middleName"] = $middleName;
	$data["lastName"] = $lastName;
	echo json_encode($data);
}
else
{
	$data = array();
	$data["status"] ="F";
	$data["info"] = "Get Profile failed for driver";
	echo json_encode($data);
}
mysql_close();
}
?>