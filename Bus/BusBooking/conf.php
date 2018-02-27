<?php
$db_name = "bus101";
$mysql_username = "root";
$mysql_password = "";
$server_name = "localhost";

$conn = mysqli_connect($server_name,$mysql_username,$mysql_password,$db_name);
if(!$conn){
	//die("Error in database connection". mysqli_connect_error);
}
else {
	//echo "db connection success";
}
 
?>