<?php

    session_start();
include_once "BaseD.php";

    $db = mysqli_connect($hostnamedata, $usernameData, $passwordData, $database);

if ($db->connect_error) {
    die("Erreur de connexion: " . $db->connect_error);
}


$eventId = $_POST['id_event'];
$eventlog = $_SESSION['login'];


$query = "SELECT id FROM event WHERE id = '$eventId'";
$result = $db->query($query);


if ($result->num_rows > 0) {
    
$query = "INSERT INTO Participation (idEvent, idlogin) VALUES ($eventId, '$eventlog')";
$result = $db->query($query);

if ($result === TRUE) {
   echo "<script type='text/javascript'>alert('Inscription reussi ! '); window.location= '../evenement.php';</script>";
} else {
    echo "Erreur lors de la création de l'événement : " ;
    header("Location: ../inscrevent.php");
}


}else{
	header("Location: ../inscrevent.php");
}



$db->close();
?>	