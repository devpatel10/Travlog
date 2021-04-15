<?php

require "init.php";
$trip_id = $_GET["trip_id"];
$type = $_GET["type"];
$from = $_GET["from"];
$to = $_GET["to"];
$trans_name = $_GET["trans_name"];
$cost = $_GET["cost"];
$departure = $_GET["departure"];
$arrival = $_GET["arrival"];



    $sql="INSERT INTO transportbooking(type,from_loc,to_loc,trans_name,cost,departure,arrival,trip_id)  VALUES
    ('$type','$from','$to','$trans_name','$cost','$departure','$arrival','$trip_id')";
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