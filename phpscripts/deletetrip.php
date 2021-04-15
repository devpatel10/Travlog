<?php

require "init.php";
$trip_id = $_GET["tripid"];

$sql="DELETE from trips where trip_id='$trip_id'";
if(mysqli_query($con,$sql))
{
    $status = "ok";
}
else
{
    $status="error";
}

echo json_encode(array("response"=>$status));
mysqli_close($con);
?>