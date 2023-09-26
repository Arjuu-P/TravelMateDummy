<?php

    require 'init.php';
    include('res_handler.php');

    $status = false;
    $response = 'Request failed!';

    $user_id = $_GET['user_id'];
    $spot_id = $_GET['spot_id'];
    
    $query = "SELECT * FROM `wishlist` WHERE `user_id` = '$user_id' AND `spot_id` = '$spot_id'";

    $result  = mysqli_query($con, $query);
    if (mysqli_num_rows($result) <= 0) send_response($status, $response);

    $row = null;
    $data = [];
    $count = 0;

    while ($row = mysqli_fetch_assoc($result)) {
        $data[$count++] = $row;
    }

    $status = true;
    send_response($status, $data, true);
?>