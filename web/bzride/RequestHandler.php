<?php
include("includes/common.php");
include("includes/db.php");
session_start();

$requestId = $_REQUEST['rideRequestId'];
$startLat = $_REQUEST['startLat'];
$startLong = $_REQUEST['startLong'];

//fixed values testing
$startLat = '38.7521';
$startLong = '121.2880';


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
			 
$findriverSQL = "SELECT * , (3956 * 2 * ASIN(SQRT( POWER(SIN(( $startLatFloat - currentlat) *  pi()/180 / 2), 2) +COS( $startLatFloat * pi()/180) * COS(currentlat * pi()/180) * POWER(SIN(( $startLongFloat - currentlong) * pi()/180 / 2), 2) ))) as distance  from bztbl_drivers having  distance <= 10 order by distance";

LOGDATA($findriverSQL);

$result = mysql_query($findriverSQL,$conn);
if (!$result) {
	showError(mysql_error());
}
$num_rows = mysql_num_rows($result);
LOGDATA($num_rows);

if ( $num_rows > 0) {
    // output data of each row
	LOGDATA('fetching driver');
    while($row = mysql_fetch_array($result)) {
		LOGDATA('fetching driver data');
		$deviceId = $row["DeviceId"];
		$DeviceType = $row["DeviceType"];
		
		LOGDATA($deviceId);
		LOGDATA($DeviceType);
		
		if ($DeviceType == 'A')
		{
			//Android notification
			$devicetoken = '';  // Give device token here.
			$pushMessage = ''; // Give push message here.
			$apikey = ''; // Give api key here.
			LOGDATA('Android notification');
			androidpush($devicetoken,$pushMessage,$apikey);

		}
		else if ($DeviceType == 'I')
		{
			//ios notification
			$devicetoken = '';  // Give device token here.
			$from = ''; // Give from name.
			$fromname = ''; // Give from.
			LOGDATA('IOS notification');
			apns($deviceToken,$from,$fromname);
		}
    }
} else {
   showError("Could not find any drivers avilable at this time. Please retry.");
}
// For Android devices
function androidpush($devicetoken,$pushMessage,$apikey)
{
	LOGDATA('Android push notification begin');		
    $gcmRegID  = $devicetoken;
    if (isset($gcmRegID) && isset($pushMessage)) {    
            $registatoin_ids = array($gcmRegID);
            $message = array("m" => $pushMessage,'x' => 'call');
            
            $url = 'https://android.googleapis.com/gcm/send';
			$fields = array(
             'registration_ids' => $registatoin_ids,
             'data' => $message,
             'priority' => 'high'
          );
          define("GOOGLE_API_KEY", "$apikey");    
          $headers = array(
              'Authorization: key=' . GOOGLE_API_KEY,
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
			showSuccess("Ride request message successfully delivered.");
		
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