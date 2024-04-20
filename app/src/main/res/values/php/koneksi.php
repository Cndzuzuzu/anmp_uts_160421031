<?php 
	header("Access-Control-Allow-Origin: *");
	header("Access-Control-Allow-Headers: *");

	$servername = "localhost";
	$username = "root";
	$password = "";
	$dbname = "yarntopia";

	$conn = new mysqli($servername, $username, $password, $dbname);

	if ($conn->connect_error) 
	{
	  die("Connection failed: " . $conn->connect_error);
	} 	
 ?>