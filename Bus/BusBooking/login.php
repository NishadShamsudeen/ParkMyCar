<?php


$email = $_POST["email"];
$pass = $_POST["password"];


require "conf.php";

$query = "select * from userinfo where email like '".$email."' and password like '".$pass."';";
$result = mysqli_query($conn,$query);
if(mysqli_num_rows($result)>0) {
	
	$response = array();
	$code = "login_true";
	$row = mysqli_fetch_array($result);
	$name = $row[0];
	$email = $row[1];
	$phone = $row[3];
	$address = $row[4];
	$message = "".$name." ".$email." ".$phone." ".$address."";
	array_push($response,array("code"=>$code,"message"=>$message));
	echo json_encode(array("server_response"=>$response));
} 
else {
	
	$response = array();
	$code = "login_false";
	$message = "User not Found";
	array_push($response,array("code"=>$code,"message"=>$message));
	echo json_encode(array("server_response"=>$response));
	
}

mysqli_close($conn);

?>