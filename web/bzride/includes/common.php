<?php
//define("DEBUG_L","Console");
define("DEBUG_F","File");
$BASE_URL = 'http://bzride.com/bzride/';


function LOGDATA($value, $default = '')
{	
  if (defined('DEBUG_L')) {
      echo $value;
  }
   if (defined('DEBUG_F')) {
   $myfile = fopen("Logdata.txt", "a+");
   if ($myfile)
   {
		$date = date("D M d, Y G:i", time());
		$logtext = $date . " --- " . $value. "\n";
		fwrite($myfile,  $logtext);
		fclose($myfile);
   }
	else
	{
		echo "file opening failed";
	}
  }
}
function getIfSet($value, $default = ' ')
{
	
    return isset($value) ? "'".$value."'" : $default;
}

function showError($value, $default = ' ')
{ /* Output header */
	LOGDATA($value);
	header('Content-type: application/json');
	$data = array();
    $data["status"] ="F";
    $data["info"] = $value;
    echo json_encode($data);
    die();
}
function showSuccess($value, $default = ' ')
{ /* Output header */
	LOGDATA($value);
	header('Content-type: application/json');
	$data = array();
    $data["status"] ="S";
    $data["info"] = $value;
    echo json_encode($data);
    die();
}
?>