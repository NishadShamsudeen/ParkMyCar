
<?php
include 'connect.php';
$n=$_REQUEST["rid"];


$ps=mysqli_query($con,"select * from tbl_location where busid='$n'");
$result="";
while($row=mysqli_fetch_array($ps))
{
$result=$row[0]."*".$row[1]."*".$row[2]."*".$row[3]."*".$row[4]."*".$row[5]."*";	
}

		//echo "$s".$s;
echo $result;

?>

