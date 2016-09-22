<?php
include("includes/common.php");
include("includes/db.php");
session_start();

$requestId = $_REQUEST['rideRequestId'];
$startLat = $_REQUEST['startLat'];
$startLong = $_REQUEST['startLong'];

//fixed values testing
//$requestId = '1';
//$startLat = '38.7521';
//$startLong = '121.2880';


/*$requestId = '5';
$startLat = '10.0268';
$startLong = '76.3487';*/

$requestId = '5';
$startLat = '9.8';
$startLong = '76.7487';


//$requestId = mysql_real_escape_string($requestId);
//$startLat = mysql_real_escape_string($startLat);
//$startLong = mysql_real_escape_string($startLong);

LOGDATA ('inside request handler');
LOGDATA($requestId);
LOGDATA($startLat);
LOGDATA($startLong);

$startLatFloat = floatval($startLat);
$startLongFloat = floatval($startLong);

LOGDATA($startLatFloat);
LOGDATA($startLongFloat);
			 
$findriverSQL = "SELECT * , (3956 * 2 * ASIN(SQRT( POWER(SIN(( $startLatFloat - currentlat) *  pi()/180 / 2), 2) +COS( $startLatFloat * pi()/180) * COS(currentlat * pi()/180) * POWER(SIN(( $startLongFloat - currentlong) * pi()/180 / 2), 2) ))) as distance  from bztbl_drivers WHERE STATUS =  'A'  AND isActive =1 having  distance <= 10 order by distance";

LOGDATA($findriverSQL);

$result = mysql_query($findriverSQL,$conn);
if (!$result) {
	showError(mysql_error());
}
$num_rows = mysql_num_rows($result);
LOGDATA($num_rows);
$goodcount= 0;

if ( $num_rows > 0) {
    // output data of each row
	LOGDATA('fetching driver');
    while($row = mysql_fetch_array($result)) {
		LOGDATA('fetching driver data');
		$deviceType = $row["DeviceType"];

		/*if ($isLicenseAccepted != true) continue;*/
		
		LOGDATA($deviceType);
		LOGDATA(status);
		LOGDATA($isActive);
		LOGDATA($isLicenseAccepted);
		
		
		if ($deviceType == 'A')
		{
			$requestSQL = "SELECT R.StartLocation,R.StartLat,R.StartLong, R.EndLocation,R.EndLat,R.EndLong, U.FirstName, U.Phone FROM  bztbl_riderequests AS R INNER JOIN  bztbl_riders U ON R.RequestorId = U.Id where R.Id = ".$requestId;
			LOGDATA($requestSQL);
			
			$resultIn = mysql_query($requestSQL,$conn);
			if (!$resultIn) {
				showError(mysql_error());
			}
			$num_rows = mysql_num_rows($resultIn);
			LOGDATA($num_rows);
			if ( $num_rows > 0) {
				$rowIn = mysql_fetch_array($resultIn);
				$start = $rowIn["StartLocation"];
				$startLat = $rowIn["StartLat"];
				$startLong = $rowIn["StartLong"];
				$end = $rowIn["EndLocation"];
				$endLat = $rowIn["EndLat"];
				$endLong = $rowIn["EndLong"];
				$firstName = $rowIn["FirstName"];
				$phone = $rowIn["Phone"];
				LOGDATA($start);
				LOGDATA($end);
				LOGDATA($firstName);
			}
			
			//Android notification
			$deviceToken = $row["DeviceToken"];
			//'dGxQW_4WW6M:APA91bHa_pRIqqH8SpO5LH7kiDAsFwErVkp4hYQTkxcZHSv0i-5FVByKKYhRIvybep6Q_X9rARa8VG5ycxbu6LEw4wihSA5MK4Yup6ZbchUAq2TdkLIjilKUXMnF8D_66hcb5-CHQfIi';
			//$row["DeviceId"];	
			LOGDATA($deviceToken);			
			$pushMessage = "You have a ride request from ". $firstName. " start from ". $start. " to ". $end. ":".$requestId. ":".$firstName.":".$phone.":".$startLat.":".$startLong.":".$endLat.":".$endLong;
			LOGDATA($pushMessage);
			$apiKey = 'AIzaSyDpkMnJYFvd41lI7Bz8IrTZTw6V8WNOm40'; // Give api key here.
			LOGDATA('Android notification');
			androidpush($deviceToken,$pushMessage,$apiKey);
			

		}
		else if ($deviceType == 'I')
		{
			//ios notification
			$devicetoken = '';  // Give device token here.
			$from = ''; // Give from name.
			$fromname = ''; // Give from.
			LOGDATA('IOS notification');
			apns($deviceToken,$from,$fromname);
		}
		
		sleep(8);//sleep for response from driver
		if (checkIfRequestAccepted($requestId,$conn))
		{
			LOGDATA('Request accepted '.$requestId);
			$goodcount= $goodcount+1;
			break;
		}
		LOGDATA('Request not accepted '.$requestId);
    }
	if($goodcount<=0)
	{
		showError("Could not find any drivers avilable at this time. Please retry.");
	}
} else {
   showError("Could not find any drivers avilable at this time. Please retry.");
}

function checkIfRequestAccepted($requestId,$conn )
{
	$statSQL = "SELECT Status FROM  bztbl_riderequests where Id = ".$requestId;
	LOGDATA($statSQL);
	
	$resultstat = mysql_query($statSQL,$conn);
	if (!$resultstat) {
		return false;
	}
	$num_rows = mysql_num_rows($resultstat);
	LOGDATA($num_rows);
	if ( $num_rows > 0) {
		$rowIn = mysql_fetch_array($resultstat);
		$Status = $rowIn["Status"];
		LOGDATA($Status);
		if ($Status == 'A')// Accepted
		{
			return true;
		}
	}
	return false;
}
// For Android devices
function androidpush($deviceToken,$pushMessage,$apiKey)
{
	LOGDATA('Android push notification begin');		
    $gcmRegID  = $deviceToken;
	LOGDATA($gcmRegID);	
	LOGDATA($pushMessage);	
	LOGDATA($apiKey);	
	
    if ($gcmRegID && $pushMessage) {    
            $registatoin_ids = array($gcmRegID);
            $message = array("m" => $pushMessage,'x' => 'call');
            
            $url = 'https://android.googleapis.com/gcm/send';
			$fields = array(
             'registration_ids' => $registatoin_ids,
             'data' => $message,
             'priority' => 'high'
          ); 
          $headers = array(
              'Authorization: key=' . $apiKey,
              'Content-Type: application/json'
          );
          $ch = curl_init();
          curl_setopt($ch, CURLOPT_URL, $url);
          curl_setopt($ch, CURLOPT_POST, true);
          curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
          curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
          curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, 0); 
          curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
          curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));
          $result = curl_exec($ch);    
		  LOGDATA($result);		  
          curl_close($ch);
	
	
		if (!$result)
			showError("Ride request message not delivered. Please retry.");
		else
			//showSuccess("Ride request message successfully delivered.");
		return true;
		
    }
	else
	{
		showError("Message or device token not set. Please retry.");
		 return false;
	}
    return true;
}
// For apple devices
function apns($deviceToken,$from,$fromname)
{
	LOGDATA('IOS push notification begin');	
      $passphrase = 'xxxxxxx';      
      $message['loc-key'] = "IC_MSG";
      $message['message'] = "$fromname";
      
      $ctx = stream_context_create();
      stream_context_set_option($ctx, 'ssl', 'local_cert', 'production.pem');
      stream_context_set_option($ctx, 'ssl', 'passphrase', $passphrase);
      
      // Open a connection to the APNS server
      $fp = stream_socket_client(
          'ssl://gateway.push.apple.com:2195', $err,
          $errstr, 60, STREAM_CLIENT_CONNECT|STREAM_CLIENT_PERSISTENT, $ctx);
      
      if (!$fp)
		  showError("Failed to connect. Please retry.");
          //exit("Failed to connect: $err $errstr" . PHP_EOL);
      else
	  {
		  showSuccess("Connected to APNS.");
	  }
      
      $body['aps'] = array(
          'alert' => "You have a ride request from ".$fromname,
          'sound' => "default"
    );
      
      // Encode the payload as JSON
      $payload = json_encode($body);
      
      // Build the binary notification
      $msg = chr(0) . pack('n', 32) . pack('H*', $deviceToken) . pack('n', strlen($payload)) . $payload;
      
      // Send it to the server
      $result = fwrite($fp, $msg, strlen($msg));
            
      if (!$result)
			showError("Ride request message not delivered. Please retry.");
      else
			showSuccess("Ride request message successfully delivered.");
      
      // Close the connection to the server
      fclose($fp);
}

?>