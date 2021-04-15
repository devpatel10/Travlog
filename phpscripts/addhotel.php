<?php

require "init.php";
$trip_id = $_GET["trip_id"];
$hotel_name = $_GET["hotel_name"];
$check_in = $_GET["check_in"];
$check_out = $_GET["check_out"];
$cost = $_GET["cost"];
$rows = "select hotelid from hotel where name='$hotel_name'";
$result=mysqli_query($con,$rows);
$row=mysqli_fetch_assoc($result);
$hotelid = $row['hotelid'];
$sql="INSERT INTO hotelbooking(hotelid,trip_id,checkin,checkout,cost)  VALUES
    ('$hotelid', '$trip_id', '$check_in', '$check_out', '$cost')";
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