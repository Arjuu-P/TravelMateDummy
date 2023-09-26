<?php
    function send_response($status, $response, $result){
        exit(json_encode(array("status"=>$status, "result"=> $result, "data"=>$response), JSON_UNESCAPED_UNICODE));
    }
?>