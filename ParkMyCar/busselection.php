<?php

require "conf.php";

if (isset($_POST["origin"]) && isset($_POST["destination"]) && isset($_POST["dofjourney"])){
	$origin = $_POST["origin"];
	$destination = $_POST["destination"];
	$date = $_POST["dofjourney"];
	$query = "select * from busdetails where origin like '".$origin."' and destination like '".$destination."' and busdate like '".$date."';";
	$result = mysqli_query($conn,$query);
	if(mysqli_num_rows($result)>0) {
		echo "buses_list";
		exit;
	}
	else {
		echo "no buses are available";
		exit;
	}
	
}
mysqli_close($conn);

?>