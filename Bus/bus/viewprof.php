
<?php
include 'connect.php';
$n=$_REQUEST["rid"];

$n=1;
$ps=mysqli_query($con,"select * from tbl_register where regid='$n'");
$result="";
while($row=mysqli_fetch_array($ps))
{
$result=$row[1]."*".$row[2]."*".$row[3]."*".$row[4]."*";	
}

		//echo "$s".$s;
echo $result;

?>

