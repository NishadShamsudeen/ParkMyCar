<?php

require "conf.php";

if (isset($_POST["origin"]) && isset($_POST["destination"]) && isset($_POST["dofjourney"])){
	$origin = $_POST["origin"];
	$destination = $_POST["destination"];
	$date = $_POST["dofjourney"];
	
	$query = "select busname,bustype,depature,arrival,seats,fare,journeytime,image_url from busdetails where origin like '".$origin."' and destination like '".$destination."' and busdate like '".$date."';";
	$result = mysqli_query($conn,$query);
	if(mysqli_num_rows($result)>0) {
		while($row = mysqli_fetch_assoc($result)){
			$data[] = $row;
		}
		echo json_encode($data);
	}
	else {
		echo "no_bus";
		
	}
	
}
mysqli_close($conn);

?>