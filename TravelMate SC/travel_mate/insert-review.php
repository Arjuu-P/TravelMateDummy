<?php

    require 'init.php';
    include('res_handler.php');

    $status = false;
    $response = 'Request failed!';

    $title = $_GET['title'];
    $user_id = $_GET['user_id'];

    $query = "INSERT INTO `review` (`id`,`description`, `user_id`) VALUES (NULL,'$title', '$user_id')";
    
    $result  = mysqli_query($con, $query);
    
    $status = true;
    send_response($status, $result, true);
?>