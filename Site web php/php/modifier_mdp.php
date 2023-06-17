<?php

session_start();

include_once "BaseD.php";

$db = mysqli_connect($hostnamedata, $usernameData, $passwordData, $database);

if ($db->connect_error) {
    die("Erreur de connexion: " . $db->connect_error);
}

$user_id = $_SESSION['login'];

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $newPassword = $_POST["new_password"];

    if (empty($newPassword)) {
        die("Le nouveau mot de passe est requis.");
    }


    $stmt = $db->prepare("UPDATE Utilisateur SET password = ? WHERE login = ?");
    $hashedPassword = password_hash($newPassword, PASSWORD_DEFAULT);
    $stmt->bind_param("ss", $hashedPassword, $user_id);
    $stmt->execute();

    header("Location: ../gestion.php");
    exit();
}
?>

<!DOCTYPE html>
<html>
   
<head>
 <meta charset="UTF-8">
    <link href="../css/modif.css" rel="stylesheet" />
    <title>Modifier le mot de passe</title>
</head>
<body>
        <div class="background">
        <div class="shape"></div>
        <div class="shape"></div>


    <form method="POST" action="">
        <label for="new_password">Nouveau mot de passe :</label>
        <input type="password" id="new_password" name="new_password" required><br>

        <input type="submit" value="Enregistrer">
    </form>
</body>
</html>
