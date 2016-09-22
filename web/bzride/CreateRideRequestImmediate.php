<?php
include("includes/common.php");
include("includes/db.php");
session_start();
// call web service for putting data
$bz_req_url = $BASE_URL . 'RequestHandler.php';
$ch =  curl_init();
// insert request in DB
$requestorId = getIfSet($_REQUEST['requestorId']);
$startLocation = getIfSet($_REQUEST['startLocation']);
$endLocation = getIfSet($_REQUEST['endLocation']);
$startLat = getIfSet($_REQUEST['startLat']);
$startLong = getIfSet($_REQUEST['startLong']);
$endLat = getIfSet($_REQUEST['endLat']);
$endLong = getIfSet($_REQUEST['endLong']);

// insert ride request values in DB - I for immediate
$ride_request_details="insert into bztbl_riderequests values('', 'I', $requestorId, '',$startLocation, $endLocation, 
$startLat,$startLong,$endLat,$endLong,'','','','','','','', '', '','', now(),now())";

																
LOGDATA($ride_request_details);
$result = mysql_query($ride_request_details,$conn);
if (!$result) {
	showError(mysql_error());
}

$last_id = mysql_insert_id();
LOGDATA("last inserted request =".$last_id );
if (!$last_id) {
	showError(mysql_error());
}

$postData = http_build_query(array('rideRequestId' => $last_id,
					'startLat' => getIfSet($_REQUEST['startLat']),
					'startLong' => getIfSet($_REQUEST['startLong'])	));
curl_setopt($ch, CURLOPT_URL, $bz_req_url);
curl_setopt($ch, CURLOPT_POSTFIELDS, $postData);
curl_setopt($ch, CURLOPT_POST, 1);																							
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, 0);
curl_setopt($ch, CURLOPT_HTTPAUTH, CURLAUTH_BASIC);
curl_setopt($ch, CURLOPT_CONNECTTIMEOUT, 5);
curl_setopt($ch, CURLOPT_TIMEOUT, 10);
curl_setopt($ch, CURLOPT_HTTPHEADER, array('Accept: application/json'));
$result = curl_exec($ch);
LOGDATA($result);
		
if (preg_match("/Could not/i", $result)) {
    showError("Failed to handle the ride request, Please retry.");
 } 
 else
 {
	showSuccess("Immediate Ride request successfully created at server.");
 }
?>