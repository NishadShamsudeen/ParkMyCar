<?php

require "conf.php";

if (isset($_POST["amount"])){
	$busname = (int) $_POST["amount"];
	$query = "select fare from busdetails where busname like '".$busname."';";
	$result = mysqli_query($conn,$query);
	
	$row = $result->fetch_assoc();
    $fare = (int) $row['fare'];
	
}
	
else {
		echo 200;
		
	}
	
	mysqli_close($conn);

?>