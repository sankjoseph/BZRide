<?php
include("includes/db.php");
include("includes/common.php");

/////
LOGDATA("Starting ride");

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

$requestSQL = "UPDATE bztbl_riderequests SET status = 'R' where Id = " .$requestId ;

LOGDATA($requestSQL);

$resultUpdate = mysql_query($requestSQL,$conn);
if (!$resultUpdate) {
	showError(mysql_error());
}

$num_rowsrequest = mysql_affected_rows($resultUpdate);
LOGDATA($num_rowsrequest);
//////
$requestSQLDriver = "UPDATE bztbl_Drivers SET status = 'D' where Id = " .$driverID ;

LOGDATA($requestSQLDriver);

$resultUpdateDriver = mysql_query($requestSQLDriver,$conn);
if (!$resultUpdateDriver) {
	showError(mysql_error());
}

$num_rowsDriver = mysql_affected_rows($resultUpdateDriver);
LOGDATA($num_rowsDriver);
//////

if ( $num_rowsrequest > 0 && num_rowsDriver >0) {
	$data = array();
	$data["status"] ="S";
	$data["info"] = "Start ride success for driver";
	echo json_encode($data);
}
else
{
	$data = array();
	$data["status"] ="F";
	$data["info"] = "Start ride failed for driver";
	echo json_encode($data);
}
mysql_close();
}
?>