<?php
include("includes/db.php");
include("includes/common.php");

LOGDATA("Update Driver bank details for first time");

// Check connection
if (!$conn) {
      showError("Data base connection error");
} 
else
{  
//basic driver details

$driverID = $_REQUEST['driverID'];
LOGDATA($driverID);
$AccountType = getIfSet($_REQUEST['AccountType']);
$BankName = getIfSet($_REQUEST['BankName']);
$AccountToken = getIfSet($_REQUEST['AccountToken']);
	
									
// update driver values in DB
$driver_details="UPDATE bztbl_driverbankdetails SET AccountType = $AccountType, BankName = $BankName, AccountToken = $AccountToken, LastModifiedDate=now() where userid = ".$driverID;
									
LOGDATA($driver_details);
$result = mysql_query($driver_details,$conn);
if (!$result) {
	showError(mysql_error());
}


$data = array();
$data["status"] ="S";
$data["info"] = "Driver bank details update completed";
echo json_encode($data);

mysql_close();
}
?>