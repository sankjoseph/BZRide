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
$requestSQL = "SELECT R.Id, R.RequestorId, R.DriverId, D.FirstName,D.Phone,U.DeviceToken,V.VehicleNumber, V.VehicleModel FROM  bztbl_riderequests AS R INNER JOIN  bztbl_riders U ON R.RequestorId = U.Id INNER JOIN bztbl_drivers as D ON  D.Id =  R.DriverId  INNER JOIN bztbl_drivervehicledetails V ON V.DriverId = D.Id where R.Id = ".$requestId;

LOGDATA($requestSQL);

$resultIn = mysql_query($requestSQL,$conn);
if (!$resultIn) {
	showError(mysql_error());
}
$num_rows = mysql_num_rows($resultIn);
LOGDATA($num_rows);
if ( $num_rows > 0) {
	$rowIn = mysql_fetch_array($resultIn);
	$deviceToken = $rowIn["DeviceToken"];//rider device token
	// get driver and vehicle data
	$FirstName = $rowIn["FirstName"];
	$Phone = $rowIn["Phone"];
	$VehicleNumber = $rowIn["VehicleNumber"];
	$VehicleModel = $rowIn["VehicleModel"];
	//notify rider with details
	LOGDATA($deviceToken);			
	$pushMessage = "Your request accepted by".":".$FirstName.":".$Phone.":".$VehicleNumber. ":".$VehicleModel;
	LOGDATA($pushMessage);
	$apiKey = 'AIzaSyDpkMnJYFvd41lI7Bz8IrTZTw6V8WNOm40'; // Give api key here.
	LOGDATA('Android notification');
	androidpush($deviceToken,$pushMessage,$apiKey);
}
$data = array();
$data["status"] ="S";
$data["info"] = "Start ride success for driver";
echo json_encode($data);
mysql_close();
}
?>