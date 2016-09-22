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
//request accepted
$requestSQL = "UPDATE bztbl_riderequests SET Status = 'A',DriverId = ".$driverID. " where Id = " .$requestId ;

LOGDATA($requestSQL);

$resultUpdate = mysql_query($requestSQL,$conn);
if (!$resultUpdate) {
	showError(mysql_error());
}

//driver status as driving
$requestSQLDriver = "UPDATE bztbl_drivers SET status = 'D' where Id = " .$driverID ;

LOGDATA($requestSQLDriver);

$resultUpdateDriver = mysql_query($requestSQLDriver,$conn);
if (!$resultUpdateDriver) {
	showError(mysql_error());
}

$data = array();
$data["status"] ="S";
$data["info"] = "Accepting ride request success for driver";
echo json_encode($data);
	
mysql_close();
}
?>