<?php

require "init.php";

$username = $_GET["username"];
$sql =" select customer_id from customer where emailid='$username'";
$result = mysqli_query($con,$sql);
$row=mysqli_fetch_assoc($result);
$customer_id=$row['customer_id'];

$title=$_GET["title"];
$description=$_GET["description"];
$drive_link=$_GET["drive_link"];
$start_date=$_GET["start_date"];
$end_date=$_GET["end_date"];
$status_table=0;

date_default_timezone_set('Asia/Kolkata');

if($start_date>$end_date)
{
	$status="Trip cannot be added as end date cannot be before start date!";
}
else
{
	$sql="INSERT INTO trips(customer_id, title, description, drive_link, start_date, end_date, status)  VALUES ('$customer_id', '$title', '$description', '$drive_link', '$start_date', '$end_date', '$status_table')";
    if(mysqli_query($con,$sql))
    {
        $status = "ok";
    }
    else
    {
        $status="error";
    }
}
echo json_encode(array("response"=>$status));
mysqli_close($con);
?>