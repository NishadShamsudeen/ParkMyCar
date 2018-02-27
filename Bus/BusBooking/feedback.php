<?php

$name = $_POST["name"];
$email = $_POST["email"];
$feedback = $_POST["feedback"];

require "conf.php";

	
	$query = "insert into feedback (name,email,feedback) values('".$name."','".$email."','".$feedback."');";
	$result = mysqli_query($conn,$query);
	
	if (!$result) {
		
	$response = array();
	$code = "update_false";
	$message = "server error!";
	array_push($response,array("code"=>$code,"message"=>$message));
	echo json_encode(array("server_response"=>$response));
		
	}else{
		
	$response = array();
	$code = "update_true";
	$message = "Feedback recieved!!";
	array_push($response,array("code"=>$code,"message"=>$message));
	echo json_encode(array("server_response"=>$response));
		
	}
	
	mysqli_close($conn);


?>