<?php

require "init.php";

$sql =" select  name, rating from hotel";
$result = mysqli_query($con,$sql);
if(mysqli_num_rows($result)>0)
{
    $row=mysqli_fetch_all($result);
    $status="ok";
    echo json_encode(array('response'=>$status,'trips'=>$row));
}
else
{
    $status="Sorry for inconvenience....we are looking into it";
    echo json_encode(array('response'=>$status));
}
mysqli_close($con);
?>