<?php

    require 'init.php';
    include('res_handler.php');

    $status = false;
    $response = 'Request failed!';

    $id = $_GET['id'];

    $query = "DELETE FROM `review` WHERE `review`.`id` = '$id'";
    
    $result  = mysqli_query($con, $query);
    
    $status = true;
    send_response($status, $result, true);
?>