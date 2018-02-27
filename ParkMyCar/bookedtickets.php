<?php

require "conf.php";

if (isset($_POST["name"])){
	$name = $_POST["name"];
	$query = "select * from ticketbooked where name like '".$name."';";
	$result = mysqli_query($conn,$query);
	if(mysqli_num_rows($result)>0) {
		while($row = mysqli_fetch_array($result)){
			$flag[]=$row;
		}
		print(json_encode($flag));
		exit;
	}
	else {
		echo "nobookings";
		exit;
	}
	
}
mysqli_close($conn);

?>