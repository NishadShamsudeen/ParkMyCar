
<?php
include 'connect.php';
$n=$_REQUEST["uname"];
$m=$_REQUEST["pass"];


$ps=mysqli_query($con,"select * from tbl_register where uname='$n' and pswd='$m'");
$s="0";
while($row=mysqli_fetch_array($ps))
{
	
$s=$row[0];

}

echo $s;

?>

