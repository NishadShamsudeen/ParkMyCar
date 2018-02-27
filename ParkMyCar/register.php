<?php

$name = $_POST["name"];
$email = $_POST["email"];
$pass = $_POST["password"];
$phone = $_POST["phone"];
$address = $_POST["address"];

require "conf.php";

$query = "select * from userinfo where email like '".$email."';";
$result = mysqli_query($conn,$query);
if(mysqli_num_rows($result)>0) {
	
	$response = array();
	$code = "reg_false";
	$message = "user already exist";
	array_push($response,array("code"=>$code,"message"=>$message));
	echo json_encode(array("server_response"=>$response));
	
	
}

else {
	
	$query = "insert into userinfo values('".$name."','".$email."','".$pass."','".$phone."','".$address."');";
	$result = mysqli_query($conn,$query);
	
	if (!$result) {
		
	$response = array();
	$code = "reg_false";
	$message = "server error!";
	array_push($response,array("code"=>$code,"message"=>$message));
	echo json_encode(array("server_response"=>$response));
		
	}else{
		
	$response = array();
	$code = "reg_true";
	$message = "Registeration success";
	array_push($response,array("code"=>$code,"message"=>$message));
	echo json_encode(array("server_response"=>$response));
		
	}
	

}

	mysqli_close($conn);


?>