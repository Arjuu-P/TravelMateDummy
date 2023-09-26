<?php

    require 'init.php';
    include('res_handler.php');

    $status = false;
    $response = 'Request failed!';

    $user_id = $_GET['user_id'];
    $spot_id = $_GET['spot_id'];

    $query = "INSERT INTO `wishlist`(`id`, `user_id`, `spot_id`) VALUES (NULL,'$user_id','$spot_id')";
    
    $result  = mysqli_query($con, $query);
    
    $status = true;
    send_response($status, $result, true);
?>