<?php
    include("koneksi.php");

    extract($_POST);
    $stmt = $conn->prepare("UPDATE users SET password=? WHERE username=?");
    $stmt->bind_param("ss", $password, $username);

    if ($stmt->execute()) {
        $arr = ["result" => "success"];
    } else {
        $arr = ["result" => "error", "message" => "Gagal update data"];
    }
    echo json_encode($arr);
    $stmt->close();
    $conn->close();

?>