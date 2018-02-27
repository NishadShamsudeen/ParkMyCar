
<?php
include 'connect.php';
$n=$_REQUEST["src"];
$m=$_REQUEST["dest1"];
$o=$_REQUEST["time1"];
$p=$_REQUEST["time2"];
$q=$_REQUEST["type"];
//$n="Kacheripadi";
//$m="Ekm North";
//$q="not";
if($q!="not")
$ps=mysqli_query($con,"select busno,source,dest,busid from tbl_route,tbl_bus_det where tbl_route.routeid=tbl_bus_det.routeid and type='$q' and tbl_bus_det.routeid in(select routeid from tbl_busstop where stopname='$n' and routeid IN (select routeid from tbl_busstop where stopname='$m') ) ");
else
	$ps=mysqli_query($con,"select busno,source,dest,busid from tbl_route,tbl_bus_det where tbl_route.routeid=tbl_bus_det.routeid and tbl_bus_det.routeid in(select routeid from tbl_busstop where stopname='$n' and routeid IN (select routeid from tbl_busstop where stopname='$m') ) ");

$result="123";
while($row=mysqli_fetch_array($ps))
{
$result=$result.$row[0]."*".$row[1]."-".$row[2]."*".$row[3]."#";	
}

		//echo "$s".$s;
echo $result;

?>

