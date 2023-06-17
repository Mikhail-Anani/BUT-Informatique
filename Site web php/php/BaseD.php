
<?php

$hostnamedata="dwarves.iut-fbleau.fr";
$usernameData="ananim";
$passwordData="ananim";
$database="ananim";

$db = mysqli_connect($hostnamedata,$usernameData,$passwordData,$database);
if(!$db)
    die("Il y a un probleme lors de la connection a la base de donnee");
?>