
<?php
include 'connect.php';
$n=$_REQUEST["str"];



$ps=mysqli_query($con,"select * from tbl_bus_det where busno='$n'");
$s="0";
while($row=mysqli_fetch_array($ps))
{
	
$s=$row[0];

}

echo $s;

?>

