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
//set as suspended
$requestSQL = "UPDATE bztbl_riderequests SET status = 'S' where Id = " .$requestId ;
LOGDATA($requestSQL);
$resultUpdate = mysql_query($requestSQL,$conn);
if (!$resultUpdate) {
	showError(mysql_error());
}


$requestSQL = "SELECT R.Id, R.RequestorId, R.DriverId,U.DeviceToken, U.DeviceType FROM  bztbl_riderequests AS R INNER JOIN  bztbl_riders U ON R.RequestorId = U.Id  where R.Id = ".$requestId;

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
	//notify rider with details
	LOGDATA('notify rider for cancel ride ');	
	$deviceType = $rowIn["DeviceType"];
	if ($deviceType == 'A')
	{
		LOGDATA($deviceToken);			
		$pushMessage = "Your ride request is cancelled by driver and cancellation charges are applicable.";
		LOGDATA($pushMessage);
		$apiKey = $ANDROID_GCM_KEY; // Give api key here.
		LOGDATA('Android notification');
		androidpush($deviceToken,$pushMessage,$apiKey);		
	}
	else if ($deviceType == 'I')
	{
		// ios notifications
	}
}
else
{
	LLOGDATA('notify rider for cancel ride failed');
}

$data = array();
$data["status"] ="S";
$data["info"] = "Cancel ride success for driver";
echo json_encode($data);
mysql_close();
}
?>