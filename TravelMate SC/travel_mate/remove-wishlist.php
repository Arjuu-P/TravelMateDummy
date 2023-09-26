<?php

    require 'init.php';
    include('res_handler.php');

    $status = false;
    $response = 'Request failed!';

    $wishlist_id = $_GET['wishlist_id'];

    $query = "DELETE FROM `wishlist` WHERE `wishlist`.`id` = $wishlist_id";
    
    $result  = mysqli_query($con, $query);
    
    $status = true;
    send_response($status, $result, true);
?>