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
$currentLat = $_REQUEST['currentLat'];
$currentLong = $_REQUEST['currentLong'];

LOGDATA($token);
$driverID = GetIdByCheckforTimeout($token);
//set as riding
$requestSQL = "UPDATE bztbl_riderequests SET status = 'R', ActualStartLat = $currentLat,ActualStartLong = $currentLong, ActualRideDateTimeStart = now() where Id = " .$requestId ;
LOGDATA($requestSQL);
$resultUpdate = mysql_query($requestSQL,$conn);
if (!$resultUpdate) {
	showError(mysql_error());
}
// RequestorId

$requestSQL = "select U.DeviceToken, U.DeviceType from bztbl_riderequests as R INNER JOIN  bztbl_riders U ON R.RequestorId = U.Id where R.Id = ".$requestId;
LOGDATA($requestSQL);
$resultSQL = mysql_query($requestSQL,$conn);
if (!$resultSQL) {
	showError(mysql_error());
}
$num_rows = mysql_num_rows($resultSQL);
LOGDATA($num_rows);
if ( $num_rows > 0) {
	$row = mysql_fetch_array($resultSQL);
}
$deviceType = $row["DeviceType"];
$DeviceToken = $row["DeviceToken"];
if ($deviceType == 'A')
{
	$pushMessage = "You request is accepted and driver will reach soon";
	$apiKey = 'AIzaSyDpkMnJYFvd41lI7Bz8IrTZTw6V8WNOm40'; // Give api key here.
	LOGDATA('Android notification start');
	if (!androidpush($DeviceToken,$pushMessage,$apiKey)){
		LOGDATA('Android notification failed');
	}
}
$data = array();
$data["status"] ="S";
$data["info"] = "Start ride success for driver";
echo json_encode($data);
mysql_close();
}
?>