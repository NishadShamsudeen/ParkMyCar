<?php

require "conf.php";

if (isset($_POST["busname"])){
	$busname = $_POST["busname"];
	$query = "select origin,destination,depature from busdetails where busname like '".$busname."';";
	$result = mysqli_query($conn,$query);
	if(mysqli_num_rows($result)>0) {
		while($row = mysqli_fetch_assoc($result)){
			$data[] = $row;
		}
		echo json_encode($data);
	}
	else {
		echo "something";
		
	}
	
}

mysqli_close($conn);

?>