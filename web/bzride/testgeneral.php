<?php

include("includes/db.php");
include("includes/common.php");



function ifNightRide($start, $end, $timetoCheck)
{
	return true;
}
function ifTimeBetween($start, $end, $timetoCheck)
{
	$date1 = DateTime::createFromFormat('H:i:s', $timetoCheck);
	$date2 = DateTime::createFromFormat('H:i:s', $start);
	$date3 = DateTime::createFromFormat('H:i:s', $end);
	if ($date1 > $date2 && $date1 < $date3)
	{
	   return true;
	}
	else
	{
		return false;
	}
}

function roundToTheNearestAnything($value, $roundTo)
{
    $mod = $value%$roundTo;
    return $value+($mod<($roundTo/2)?-$mod:$roundTo-$mod);
}

$fulltime = '2013-01-22 10:45:45 pm';
$time = date("H:i:s",strtotime($fulltime ));
echo $time;
echo '<br>';
echo ifTimeBetween("10:00:00 pm","11:59:00 pm",'10:45:45 pm');
echo ifTimeBetween("12:00:00 am","6:00:00 am",'10:45:45 am');

//echo ifTimeBetween("5:42:22 am","6:26:22 pm", "4:59:33 pm");
echo '<br>';
echo ceil(1234.33);
//echo roundToTheNearestAnything(ceil(1234.799), 10).'<br>';
return;
//phpinfo();

/*$dobin= getIfSet($_REQUEST['dob']);
$format = 'm/d/Y';
$date = DateTime::createFromFormat($format, $dobin);

echo "Format: $format; " . $date->format('Y-m-d H:i:s') . "\n";*/
$final = getMYSQLDate($_REQUEST['dob']);

LOGDATA($dateIn);
$driver_details="insert into bztbl_test values('',$final)";
									
LOGDATA($driver_details);
$result = mysql_query($driver_details,$conn);
if (!$result) {
	showError(mysql_error());
}

mysql_close();

?>
