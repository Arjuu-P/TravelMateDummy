<?php

    require 'init.php';
    include('res_handler.php');

    $status = false;
    $response = 'Request failed!';

    $hotel_booking = $_GET['hotel_booking'];
    $bus_booking = $_GET['bus_booking'];
    $user_id = $_GET['user_id'];
    $booking_id = $_GET['booking_id'];
    $day = $_GET['day'];
    $from_date = $_GET['from_date'];


    $query = "INSERT INTO `booking`(`id`, `hotel_booking`, `bus_booking`, `user_id`, `booking_id`, `day`, `from_date`) VALUES (NULL,'$hotel_booking','$bus_booking','$user_id','$booking_id','$day','$from_date')";
    
    $result  = mysqli_query($con, $query);
    
    $status = true;
    send_response($status, $result, true);
?>