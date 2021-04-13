<?php

require "init.php";
$first_name=$_GET["fname"];
$last_name=$_GET["lname"];
$gender=$_GET["gender"];
$mobile=$_GET["phone"];
$address=$_GET["address"];
$emailid=$_GET["email"];
$password=$_GET["password"];
$confpassword=$_GET["confpassword"];
$date_of_birth=$_GET["dob"];
$sql ="select * from customer where emailid='$emailid'";
$result = mysqli_query($con,$sql);

if(mysqli_num_rows($result)>0)
{
    $status = "exist";
}
else if($confpassword!=$password)
{
    $status = "Passwords does not match";
}
else
{
    $sql="INSERT INTO customer(first_name, last_name, gender, address, mobile, emailid, password, dob)  VALUES ('$first_name', '$last_name', '$gender', '$address', '$mobile', '$emailid', '$password', '$date_of_birth')";
    if(mysqli_query($con,$sql))
    {
        $status = "ok";
    }
    else{
        $status="error";
    }
}
echo json_encode(array("response"=>$status));
mysqli_close($con);
?>