<?php
include("includes/db.php");
include("includes/common.php");

/////
LOGDATA("Get Driver Profile");

// Check connection
if (!$conn) {
      showError("Data base connection error");
} 
else
{  
//basic driver details

$token = $_REQUEST['token'];
LOGDATA($token);
$riderID = GetIdByCheckforTimeout($token);

$requestSQL = "SELECT * FROM  bztbl_riders where Id = " .$riderID ;
LOGDATA($requestSQL);

$resultLogin = mysql_query($requestSQL,$conn);
if (!$resultLogin) {
	showError(mysql_error());
}
$num_rows = mysql_num_rows($resultLogin);
LOGDATA($num_rows);
if ( $num_rows > 0) {
	$rowIn = mysql_fetch_array($resultLogin);
	$Id = $rowIn["Id"];
	$firstName = $rowIn["FirstName"];
	$middleName = $rowIn["MiddleName"];
	$lastName = $rowIn["LastName"];
	
	LOGDATA($firstName);
	LOGDATA($middleName);
	LOGDATA($lastName);

	$data = array();
	$data["status"] ="S";
	$data["info"] = "Get Profile success for rider";
	$data["firstName"] = $firstName;
	$data["middleName"] = $middleName;
	$data["lastName"] = $lastName;
	echo json_encode($data);
}
else
{
	$data = array();
	$data["status"] ="F";
	$data["info"] = "Get Profile failed for rider";
	echo json_encode($data);
}
mysql_close();
}
?>