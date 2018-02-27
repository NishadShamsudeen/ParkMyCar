
<?php
include 'connect.php';
$n=$_REQUEST["uname"];
$m=$_REQUEST["pass"];
$n1=$_REQUEST["id"];


$ps=mysqli_query($con,"insert into tbl_fav(regid,src,dest)values('$n1','$n','$m')");
if($ps==1)
{
echo 1;
}
else
{
echo 0;
}


//echo $s;

?>

