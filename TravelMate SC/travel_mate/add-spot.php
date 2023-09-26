<?php

    require 'init.php';
    include('res_handler.php');

    $status = false;
    $response = 'Request failed!';


    $name = $_GET['name'];
    $district_id = $_GET['district_id'];
    $location_name = $_GET['location_name'];
    $location_desc = $_GET['location_desc'];
    $map_link = $_GET['map_link'];
    $image = $_GET['image'];
    $atm_link = $_GET['atm_link'];
    $pump_location = $_GET['pump_location'];
    $spot_type = $_GET['spot_type'];


    $query = "INSERT INTO `spot`(`id`, `name`, `district_id`, `location_name`, `location_desc`, `map_link`, `image`, `pump_location`, `atm_link`, `spot_type`) VALUES (NULL,'$name','$district_id','$location_name','$location_desc','$map_link','$image','$pump_location','$atm_link','$spot_type')";
    
    $result  = mysqli_query($con, $query);
    
    $status = true;
    send_response($status, $result, true);
?>x