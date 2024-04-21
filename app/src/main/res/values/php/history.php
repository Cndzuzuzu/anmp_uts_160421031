<?php

    include("koneksi.php");
	$idPembaca = $_GET['idPembaca'];
    $sql = "SELECT * FROM history INNER JOIN berita on history.idBerita = berita.idBerita WHERE history.idPembaca = '$idPembaca'";
    $result = $conn->query($sql);
    $data = array();

    if ($result->num_rows > 0) 
    {
        while ($row = $result->fetch_assoc()) 
        {
            $data[] = $row;
        }
        echo json_encode(array('result' => 'OK', 'data' => $data));
    } 
    else {
        echo json_encode(array('result'=> 'ERROR', 'message' => 'No data found'));
    }


    $conn->close();

?>