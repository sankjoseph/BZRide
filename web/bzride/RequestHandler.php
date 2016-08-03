<?php
include("includes/common.php");
session_start();
$requestId = $_POST["rideRequestId"];
$startLat = $_POST["startLat"];
$startLong = $_POST["startLong"];

$findriverSQL = "SELECT * , (3956 * 2 * ASIN(SQRT( POWER(SIN(( $startLat - currentlat) *  pi()/180 / 2), 2) +COS( $startLat * pi()/180) * COS(currentlat * pi()/180) * POWER(SIN(( $startLong - currentlong) * pi()/180 / 2), 2) ))) as distance  from bztbl_drivers having  distance <= 10 order by distance"

LOGDATA($findriverSQL);

$result = mysql_query($findriverSQL,$conn);
if (!$result) {
	showError(mysql_error());
}
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
		$deviceId = $row["DeviceId"];
		$DeviceType = $row["DeviceType"];
		if ($deviceId == 'A')
		{
			//Android notification
		}
		else if ($deviceId == 'I')
		{
			//ios notification
		}
    }
} else {
   showError("Could not find any drivers avilable at this time. Please retry.");
}

?>