<?php

    require 'init.php';
    include('res_handler.php');

    $status = false;
    $response = 'Request failed!';

    $id = $_GET['id'];

    $query = "SELECT * FROM `bus` WHERE `id` = '$id'";

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