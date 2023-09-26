<?php

    require 'init.php';
    include('res_handler.php');

    $status = false;
    $response = 'Request failed!';

    $name = $_GET['name'];
    $description = $_GET['description'];
    $image = $_GET['image'];

    $query = "INSERT INTO `bus`(`id`, `name`, `description`, `image`) VALUES (NULL,'$name','$description','$image')";
    
    $result  = mysqli_query($con, $query);
    
    $status = true;
    send_response($status, $result, true);
?>