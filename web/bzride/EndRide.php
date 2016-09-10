<?php
include("includes/db.php");
include("includes/common.php");

function distanceCalculationGoogleAPI($point1_lat, $point1_long, $point2_lat, $point2_long, $unit = 'km', $decimals = 2) {
	
}
function TimeCalculationGoogleAPI($point1_lat, $point1_long, $point2_lat, $point2_long, $unit = 'minute', $decimals = 2) {
	
}

function distanceCalculation($point1_lat, $point1_long, $point2_lat, $point2_long, $unit = 'km', $decimals = 2) {
	// Calculate the distance in degrees
	$degrees = rad2deg(acos((sin(deg2rad($point1_lat))*sin(deg2rad($point2_lat))) + (cos(deg2rad($point1_lat))*cos(deg2rad($point2_lat))*cos(deg2rad($point1_long-$point2_long)))));
 
	// Convert the distance in degrees to the chosen unit (kilometres, miles or nautical miles)
	switch($unit) {
		case 'km':
			$distance = $degrees * 111.13384; // 1 degree = 111.13384 km, based on the average diameter of the Earth (12,735 km)
			break;
		case 'mi':
			$distance = $degrees * 69.05482; // 1 degree = 69.05482 miles, based on the average diameter of the Earth (7,913.1 miles)
			break;
		case 'nmi':
			$distance =  $degrees * 59.97662; // 1 degree = 59.97662 nautic miles, based on the average diameter of the Earth (6,876.3 nautical miles)
	}
	return round($distance, $decimals);
}

/////
LOGDATA("Ending ride");

// Check connection
if (!$conn) {
      showError("Data base connection error");
} 
else
{  
//basic driver details
$requestId = $_REQUEST['rideRequestId'];
$ActualEndLat = $_REQUEST['ActualEndLat'];
$ActualEndLong = $_REQUEST['ActualEndLong'];


$token = $_REQUEST['token'];
LOGDATA($token);
$driverID = GetIdByCheckforTimeout($token);

// update time for table
$updateSQL = "UPDATE bztbl_riderequests SET status = 'C', ActualRideDateTimeEnd = now(), ActualEndLong = ".$ActualEndLong.",ActualEndLat = ".$ActualEndLat. " where Id = " .$requestId ;
LOGDATA($updateSQL);

$result = mysql_query($updateSQL,$conn);
if (!$result) {
	showError(mysql_error());
}

// get data from request table for calculation
$requestSQL = "Select * from bztbl_riderequests where Id = " .$requestId ;

LOGDATA($requestSQL);

$result = mysql_query($requestSQL,$conn);
if (!$result) {
	showError(mysql_error());
}

$row = mysql_fetch_array($result);
$ActualStartLat = $row["ActualStartLat"];
$ActualStartLong = $row["ActualStartLong"];
$ActualEndLat = $row["ActualEndLat"];
$ActualEndLong = $row["ActualEndLong"];

$ActualRideDateTimeStart = $row["ActualRideDateTimeStart"];
$ActualRideDateTimeEnd = $row["ActualRideDateTimeEnd"];

$ActualRideDateTimeEnd = $row["ActualRideDateTimeEnd"];
$riderId = $row["RequestorId"];

// find distance
$distancetraveledmi = distanceCalculation(ActualStartLat,ActualStartLong,ActualEndLat,ActualEndLong,'mi');

$rateforDistanceCents = $distancetraveledmi * 1.28; //cents

// find time difference
$timetakenminutes = round(abs($ActualRideDateTimeEnd - $ActualRideDateTimeStart) / 60,2);

// fare time
$rateForTimeCents = $timetakenminutes * 15.0;
// calculate rate for above and fit in table
$baseFare = 12.0;// 12Dollar
$stateFee = 1.75;
$finalFare = $baseFare + $stateFee + ($rateforDistanceCents + $rateForTimeCents)/100;
// sum total fare and update in table

// update fare for table
$updateFareSQL = "UPDATE bztbl_riderequests SET ChargeDistance = rateforDistanceCents, ChargeTime = rateForTimeCents where Id = " .$requestId ;
LOGDATA($updateFareSQL);

$result = mysql_query($updateFareSQL,$conn);
if (!$result) {
	showError(mysql_error());
}

// get card details
// get data from request table for calculation
$requestCardSQL = "Select CardToken from bztbl_riders where Id = " .$riderId ;
LOGDATA($requestCardSQL);
$result = mysql_query($requestCardSQL,$conn);
if (!$result) {
	showError(mysql_error());
}
$rowToken = mysql_fetch_array($result);
$CardToken = $rowToken["CardToken"];

// charge card for the amount usinhg card token todo
// last ride will have fare details for user

//////
$requestSQLDriver = "UPDATE bztbl_Drivers SET status = 'A' where Id = " .$driverID ;

LOGDATA($requestSQLDriver);

$resultUpdateDriver = mysql_query($requestSQLDriver,$conn);
if (!$resultUpdateDriver) {
	showError(mysql_error());
}

$num_rowsDriver = mysql_affected_rows($resultUpdateDriver);
LOGDATA($num_rowsDriver);


if ( $num_rows > 0 && num_rowsDriver > 0 ) {
	$data = array();
	$data["status"] ="S";
	$data["info"] = "End ride success for driver";
	echo json_encode($data);
}
else
{
	$data = array();
	$data["status"] ="F";
	$data["info"] = "End ride failed for driver";
	echo json_encode($data);
}
mysql_close();
}
?>