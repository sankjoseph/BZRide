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

$requestSQLCheck = "select * from bztbl_riderequests where Id = " .$requestId ;
LOGDATA($requestSQLCheck);
$resultCheck = mysql_query($requestSQLCheck,$conn);
if (!$resultCheck) {
	showError(mysql_error());
}

$num_rows = mysql_num_rows($resultCheck);
LOGDATA($num_rows);
if ( $num_rows > 0) {
	$rowIn = mysql_fetch_array($resultCheck);
	$status = $rowIn["Status"];
	if ($status == 'A')
	{
		$data = array();
		$data["status"] ="F";
		$data["info"] = "Timeout. Request already accepted by another driver";
		echo json_encode($data);
		die();
	}
}
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