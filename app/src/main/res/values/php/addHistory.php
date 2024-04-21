<?php

    include("koneksi.php");
    

    $idPembaca = $_GET['idPembaca'];
    $idBerita = $_GET['idBerita'];

    $sql = "SELECT * FROM history INNER JOIN berita on history.idBerita = berita.idBerita WHERE history.idPembaca = '$idPembaca' AND history.idBerita = $idBerita";
    $result = $conn->query($sql);
    $data = array();

    if ($result->num_rows > 0) 
    {
        $data = ["result" => "error", "message" => "Data sudah ada"];
    }
    else{
        $stmt = $conn->prepare("INSERT INTO history(idPembaca, idBerita) VALUES(?,?)");

        $stmt->bind_param("si", $idPembaca, $idBerita);

        if ($stmt->execute()) 
        {
            $data = ["result" => "success"];
        } 
        else 
        {
            $data = ["result" => "error", "message" => "Gagal simpan data"];
        }
        echo json_encode($data);
        $stmt->close();
    }
    echo json_encode($data);
    $conn->close();

?>