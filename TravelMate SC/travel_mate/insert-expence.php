<?php

    require 'init.php';
    include('res_handler.php');

    $status = false;
    $response = 'Request failed!';

    $user_id = $_GET['user_id'];
    $name = $_GET['name'];
    $amount = $_GET['amount'];
    $description = $_GET['description'];


    $query = "INSERT INTO `expense`(`id`, `user_id`, `name`, `amount`, `description`) VALUES (NULL,'$user_id','$name','$amount','$description')";
    
    $result  = mysqli_query($con, $query);
    
    $status = true;
    send_response($status, $result, true);
?>