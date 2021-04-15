<?php
require "init.php";

$trip_id = $_GET["trip_id"];

$sql =" select title, description, drive_link, start_date, end_date from trips where trip_id='$trip_id' ";

date_default_timezone_set('Asia/Kolkata');

$result = mysqli_query($con,$sql);

if(mysqli_num_rows($result)>0)
{
	$row=mysqli_fetch_assoc($result);

	$arr = array();
	array_push($arr, $row['title'],$row['description'],$row['drive_link'],$row['start_date'],$row['end_date']);
	$end_date = $row['end_date'];
	$status_table = 1;
	if(date("Y/m/d")>$end_date)
	{
		$status_table = 0;
	}

	$sql =" SELECT type,trans_name,from_loc,to_loc,departure,arrival,cost from transportbooking WHERE trip_id = '$trip_id' ORDER BY departure ASC";
	$result = mysqli_query($con,$sql);
	$row1=mysqli_fetch_all($result);

	$sql =" SELECT name,address,checkin,checkout,cost from hotelbooking natural join hotel WHERE trip_id = '$trip_id' ORDER BY checkin ASC";
	$result = mysqli_query($con,$sql);
	$row2=mysqli_fetch_all($result);

	$len = count($row1) + count($row2);
	$total_cost = 0;
	$idx = 0;

	$i = 0;
	$j = 0;

	$event1 = array();

	for ($itr=0; $itr < $len; $itr++) 
	{
		$s = "";
		$a = array();

		if($i < count($row1))
		{
			$idx = 0;
			$s = $row1[$i][4];
		}
		if($j < count($row2))
		{
			if (s=="" or $s>$row2[$j][2] )
			{
				$idx = 1;
			}
		}
		if ($idx==0)
		{
			array_push($a,$row1[$i][0],$row1[$i][1],$row1[$i][2],$row1[$i][3],$row1[$i][4],$row1[$i][5],$row1[$i][6]);
			$total_cost+=(int)$row1[$i][6];
			$i++;
		}
		else
		{
			array_push($a,$row2[$j][0],$row2[$j][1],$row2[$j][2],$row2[$j][3],$row2[$j][4]);
			$total_cost+=(int)$row2[$j][4];
			$j++;
		}

		array_push($event1, $a);
	
	}
	$t_cost = strval($total_cost);
	$status_t = strval($status_table);
	array_push($arr,$t_cost,$status_t);

	$event = array();
	array_push($event,$arr);
	for ($i=0; $i < count($event1); $i++) { 
		array_push($event, $event1[$i]);
	}
    $status="ok";
    echo json_encode(array('response'=>$status,'event_list'=>$event));
}
else
{
    $status="failed";
    echo json_encode(array('response'=>$status));
}
mysqli_close($con);
?>