<?php
session_start();
$firstname= $_POST["txtfirstname"];
$lastname= $_POST["txtlastname"];
$email= $_POST["txtemail"];
$password= $_POST["txtpass"];

$addr1= $_POST["txtaddr1"];
$addr2= $_POST["txtaddr2"];
$ph= $_POST["txtph"];
$ssn= $_POST["txtssn"];

$vehicleyear= $_POST["txtyear"];
$vehiclemodel= $_POST["txtmodel"];
$vehiclemake= $_POST["txtmake"];
$vehiclecolor= $_POST["txtcolor"];

$regno= $_POST["txtregno"];
$regstate= $_POST["txtregstate"];
$regdate= $_POST["txtregdate"];
$regexpiry= $_POST["txtregexpiry"];

$inscompany= $_POST["txtinscompany"];
$inspolicyno= $_POST["txtpolicyno"];
$insdate= $_POST["txtinsdate"];
$insexpiry= $_POST["txtinsexpiry"];

$licenseno= $_POST["txtlicenseno"];
$licensestate= $_POST["txtlicensestate"];
$licensedate= $_POST["txtlicenseissue"];
$licenseexpiry= $_POST["txtlicenseexpiry"];

//$image=$_FILES["image"]["name"];
move_uploaded_file($_FILES['image']['tmp_name'],"upload/".$_FILES['image']['name']);
/*$licence= $_POST["licence"];*/

include("includes/db.php");

		//insert values: ID, FIRST NAME, LAST NAME, EMAIL, PASSWORD, ADDR1, ADDR2, PHONE, DEVICE ID , DEVICE TYPE, 
		//IS LICENCE ACCEPTED, IS ACTIVE, STATUS, CURRENT LATTITTUDE, CURRENT LONGITUDE, CREATED BY DATE  //
$driver_details="insert into bztbl_drivers values('', '$firstname', '$lastname', '$email', '$password',
									'$addr1', '$addr2', '$ph', '', '', '', '', '', '', '', '' )";
									
		// insert values: ID, DRIVER ID, VEHICLE MODEL, V MAKE, V COLOUR, V YEAR, 
		//  V REG NO, V REG STATE, V DATE REGISTERED, V EXPIRY DATE, CREATED BY DATE  //
$driver_vehicledetais = "insert into bztbl_drivervehicledetails values('', '', '$vehiclemodel', '$vehiclemake', '$vehiclecolor', '$vehicleyear', 
									'$regno', '$regstate', '$regdate', '$regexpiry', '')";									
									
		//insert values: ID, DRIVER ID, INSURANCE COMPANY, INS PLICY NO, INS DATE, INS EXPIRY DATE, CREATED BY DATE  //							
$driver_insdetails="insert into bztbl_driverinsurancedetails values('', '', '$inscompany', '$inspolicyno', 
									'$insdate','$insexpiry', ''  )";
									
		//insert values: ID, DRIVER ID, LICENCE NO, LICENCE ISSUE STATE, LIC ISSUE DATE, LIC EXPIRY DATE, CREATED BY DATE  //
$driver_licdetails="insert into bztbl_driverlicensedetails values('', '', '$licenseno', '$licensestate', 
									'$licensedate','$licenseexpiry', ''  )";											


$driver_details1 = mysql_query($driver_details, $conn);
$driver_vehicledetais1 = mysql_query($driver_vehicledetais, $conn);
$driver_insdetails1 = mysql_query($driver_insdetails, $conn);
$driver_licdetails1 = mysql_query($driver_licdetails, $conn);

if($driver_details1 == 1)
{
$_SESSION['msg']="Successfully Registered";
header("location:home.php");
}

?>