
<?php
include 'connect.php';
$n=$_REQUEST["lat"];
$m=$_REQUEST["lot"];
$n=10.1419;

$ps=mysqli_query($con,"select * from tbl_busstop");
$s=0;

$loc1="";
$loc2="";
$i=0;
$min=0;
while($row=mysqli_fetch_array($ps))
{
	if($i==0)
	{
		$s=abs($n-$row[3]);
		$min=abs($n-$row[3]);
		$loc1=$row[2];
		//$loc2=$row[2];
	}
	else if(abs($n-$row[3])<$s)
	{
		
		$min=$s;
		//$loc2=$row[2];
		$s=abs($n-$row[3]);
		$loc1=$row[2];		
		
	}
	else if(abs($n-$row[3])<$min)
	{
		//echo "$min".$s;
		$loc2=$row[2];
	}
$i++;
}

		//echo "$s".$s;
echo $loc1."*".$loc2;

?>

