<?php

    include("koneksi.php");

    extract($_POST);
    // $username=$_GET['username'];
    // $password=$_GET['password'];

    $stmt = $conn->prepare("SELECT * FROM users WHERE username=? AND password = ?");

    $stmt->bind_param("ss", $username, $password);
    $stmt->execute();
    $result = $stmt->get_result();

    if($result->num_rows > 0){
    //     $r = mysqli_fetch_assoc($result);
    //     $arr = ["result"=>"success","data"=>$r];
        while ($row = $result->fetch_assoc()) 
        {
            $data[] = $row;
        }
        echo json_encode(array('result' => 'success', 'data' => $data));
    }
    else{
    //     $arr = ["result"=> "error","message"=> "username atau password salah!"];
         echo json_encode(array('result'=> 'ERROR', 'message' => 'No data found'));
    }

    // echo json_encode($arr);
    $stmt->close();

    // $sql = "SELECT * FROM users WHERE username=$username AND password=$password";
    // $result = $conn->query($sql);
    // $data = array();

    // if ($result->num_rows > 0) 
    // {
    //     while ($row = $result->fetch_assoc()) 
    //     {
    //         $data[] = $row;
    //     }
    //     echo json_encode(array('result' => 'success', 'data' => $data));
    //     // echo json_encode(array('data' => $data));
    // } 
    // else {
    //     echo json_encode(array('result'=> 'ERROR', 'message' => 'No data found'));
    //     // echo json_encode(array('message' => 'No data found'));
    // }
    $conn->close();

?>