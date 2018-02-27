<?php

require "conf.php";

if (isset($_POST["busname"])){
	$busname = $_POST["busname"];
	$pname = $_POST["pname"];
	$page = $_POST["page"];
	$mobile = $_POST["mobile"];
	$email = $_POST["email"];
	$name = $_POST["username"];
	$push = "insert into ticketbooked (name,busname,pname,page,mobile,email) values('".$name."','".$busname."','".$pname."','".$page."','".$mobile."','".$email."');";
	$result = mysqli_query($conn,$push);
	echo "success";
	
}

mysqli_close($conn);

?>