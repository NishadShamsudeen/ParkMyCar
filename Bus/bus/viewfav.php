
<?php
include 'connect.php';
$n=$_REQUEST["rid"];


$ps=mysqli_query($con,"select * from tbl_fav where regid='$n'");
$result="";
while($row=mysqli_fetch_array($ps))
{
$result=$row[2]."-".$row[3]."*".$row[2]."*".$row[3]."#";	
}

		//echo "$s".$s;
echo $result;

?>

