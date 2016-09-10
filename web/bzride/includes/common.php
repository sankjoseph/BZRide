<?php
//define("DEBUG_L","Console");
define("DEBUG_F","File");
define('SECRET_KEY', "zxcvbnmasdfghjkl");//16 digit key

$BASE_URL = 'http://bzride.com/bzride/';


function GetIdByCheckforTimeout($token)
{
	$tokenGeneric = SECRET_KEY;
	$decToken = bz_crypt($tokenGeneric,$token,'decrypt');
	LOGDATA($decToken);

	$driverID = after(':', $decToken);
	$timecreated = before(':', $decToken);
	LOGDATA('time created'. $timecreated);

	$time = (int)($timecreated);
	$curtime = time();

	LOGDATA($time);

	LOGDATA('time now'.$curtime);
	
	$diff = $curtime-$time;

	LOGDATA('diff'.$diff);
	return $driverID;
	
	/*if($diff > 600) {   
		showError('Session time out. Please login again.');
	}
	else 
	{
		return $driverID;
	}*/

};

function after($this, $inthat)
{
	if (!is_bool(strpos($inthat, $this)))
	return substr($inthat, strpos($inthat,$this)+strlen($this));
};

function before($this, $inthat)
{
	return substr($inthat, 0, strpos($inthat, $this));
};
	
 function bz_crypt($key, $string, $action = 'encrypt'){
            $res = '';
            if($action !== 'encrypt'){
                $string = base64_decode($string);
            } 
            for( $i = 0; $i < strlen($string); $i++){
                    $c = ord(substr($string, $i));
                    if($action == 'encrypt'){
                        $c += ord(substr($key, (($i + 1) % strlen($key))));
                        $res .= chr($c & 0xFF);
                    }else{
                        $c -= ord(substr($key, (($i + 1) % strlen($key))));
                        $res .= chr(abs($c) & 0xFF);
                    }
            }
            if($action == 'encrypt'){
                $res = base64_encode($res);
            } 
            return $res;
    };
	

//This function is used to encrypt data.
 function bz_encrypt($text, $salt = "bzride.com")
 {
    return trim(base64_encode(mcrypt_encrypt(MCRYPT_RIJNDAEL_256, $salt, $text, MCRYPT_MODE_ECB, mcrypt_create_iv(mcrypt_get_iv_size(MCRYPT_RIJNDAEL_256, MCRYPT_MODE_ECB), MCRYPT_RAND))));
	
	// Create the initialization vector for added security.
/*$iv = mcrypt_create_iv(mcrypt_get_iv_size(MCRYPT_RIJNDAEL_256, MCRYPT_MODE_ECB), MCRYPT_RAND);

// Encrypt $string
$encrypted_string = mcrypt_encrypt(MCRYPT_RIJNDAEL_256, $salt, $text, MCRYPT_MODE_CBC, $iv);
return $encrypted_string;*/


}
// This function will be used to decrypt data.
 function bz_decrypt($text, $salt = "bzride.com")
 {
    return trim(mcrypt_decrypt(MCRYPT_RIJNDAEL_256, $salt, base64_decode($text), MCRYPT_MODE_ECB, mcrypt_create_iv(mcrypt_get_iv_size(MCRYPT_RIJNDAEL_256, MCRYPT_MODE_ECB), MCRYPT_RAND)));
	// Create the initialization vector for added security.
/*$iv = mcrypt_create_iv(mcrypt_get_iv_size(MCRYPT_RIJNDAEL_256, MCRYPT_MODE_ECB), MCRYPT_RAND);
// Decrypt $string
$decrypted_string = mcrypt_decrypt(MCRYPT_RIJNDAEL_256, $salt, $text, MCRYPT_MODE_CBC, $iv);
return $decrypted_string;*/
}

function LOGDATA($value, $default = null)
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
function getIfSet($value, $default = null)
{
	
    return isset($value) ? "'".$value."'" : $default;
}

function showError($value, $default = null)
{ /* Output header */
	LOGDATA($value);
	header('Content-type: application/json');
	$data = array();
    $data["status"] ="F";
    $data["info"] = $value;
    echo json_encode($data);
    die();
}
function showSuccess($value, $default = null)
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