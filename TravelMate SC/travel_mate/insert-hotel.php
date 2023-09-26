<?php

    require 'init.php';
    include('res_handler.php');

    $status = false;
    $response = 'Request failed!';


    $name = $_GET['name'];
    $description = $_GET['description'];
    $map_link = $_GET['map_link'];
    $image = $_GET['image'];


    $query = "INSERT INTO `hotel`(`id`, `name`, `description`, `map_link`, `image`) VALUES (NULL,'$name','$description','$map_link','$image')";
    
    $result  = mysqli_query($con, $query);
    
    $status = true;
    send_response($status, $result, true);
?>