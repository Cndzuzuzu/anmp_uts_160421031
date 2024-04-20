<?php

    include("koneksi.php");

    extract($_POST);
    // $username=$_GET['username'];
    // $password=$_GET['password'];
    // $email=$_GET['email'];
    // $namaDepan=$_GET['namaDepan'];
    // $namaBelakang=$_GET['namaBelakang'];


    $stmt = $conn->prepare("INSERT INTO users (username, password, email,namaDepan, namaBelakang) VALUES (?, ?, ?, ?,?)");

    $stmt->bind_param("sssss", $username, $password, $email,  $namaDepan,  $namaBelakang);

    if ($stmt->execute()) 
    {
        $arr = ["result" => "success"];
    } 
    else 
    {
        $arr = ["result" => "error", "message" => "Gagal simpan data"];
    }

    echo json_encode($arr);
    $stmt->close();
    $conn->close();

?>