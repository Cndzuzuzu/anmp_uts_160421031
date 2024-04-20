<?php

    include("koneksi.php");

    $sql = "SELECT * FROM berita ORDER by tanggal DESC";
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