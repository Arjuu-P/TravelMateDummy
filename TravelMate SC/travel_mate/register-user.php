<?php

    require 'init.php';
    include('res_handler.php');

    $status = false;
    $response = 'Request failed!';


    $email = $_GET['email'];
    $password = $_GET['password'];
    $name = $_GET['name'];
    $phone = $_GET['phone'];


    $query = "INSERT INTO `user` (`id`, `email`, `password`, `name`, `phone`) VALUES (NULL, '$email', '$password', '$name', '$phone')";
    
    $result  = mysqli_query($con, $query);
    
    $status = true;
    send_response($status, $result, true);
?>