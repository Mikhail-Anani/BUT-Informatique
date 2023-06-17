<?php

    session_start();
include_once "BaseD.php";

    $db = mysqli_connect($hostnamedata, $usernameData, $passwordData, $database);

if ($db->connect_error) {
    die("Erreur de connexion: " . $db->connect_error);
}

$randomId = rand(1,100);
$eventId = $randomId;


$query = "SELECT id FROM event WHERE id = '$eventId'";
$result = $db->query($query);


if ($result->num_rows > 0) {
    $eventId = rand(1,100);
}


$eventName = mysqli_real_escape_string($db, $_POST['new_titre']);
$eventDate = mysqli_real_escape_string($db, $_POST['new_date']);
$eventlog = $_SESSION['login'];
$eventtype = mysqli_real_escape_string($db, $_POST['typeevent']);

$query = "INSERT INTO event (Titre, date, id, Login, Type) VALUES ('$eventName', '$eventDate', $eventId, '$eventlog', '$eventtype')";


if ($db->query($query) === TRUE) {
   header("Location: ../evenement.php");
} else {
    echo "Erreur lors de la création de l'événement : " ;
}


$db->close();
?>