<?php

require "conf.php";

if (isset($_POST["id"])){
	$idstring = $_POST["id"];
	$id = (int)$idstring;
	$query = "delete from ticketbooked where bookingid like '".$id."';";
	if ($conn->query($query) === TRUE) {
    echo "success";
} else {
    echo "error";
}
	//$result = mysqli_query($conn,$query);
	

	
}
mysqli_close($conn);

?>