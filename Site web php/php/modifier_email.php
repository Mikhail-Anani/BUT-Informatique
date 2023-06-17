
<?php
session_start();

include_once "BaseD.php";

$db = mysqli_connect($hostnamedata, $usernameData, $passwordData, $database);

if ($db->connect_error) {
    die("Erreur de connexion: " . $db->connect_error);
}



$user_id = $_SESSION['login'];

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $email = $_POST["new_email"];

    if (empty($email)) {
        die("Le nouveau login est requis.");
    }


    $stmt = $db->prepare("UPDATE Utilisateur SET email = ? WHERE login = ?");
    $stmt->bind_param("ss", $email, $user_id);
    $stmt->execute();


    if ($stmt->affected_rows > 0) {
     
        $_SESSION['email'] = $email;
     
        header("Location: ../gestion.php");
        exit();
    } else {

        echo "Échec de la mise à jour du login.";
    }

    $stmt->close();
}
?>

<!DOCTYPE html>
<html>
<head>
       <meta charset="UTF-8">
    <link href="../css/modif.css" rel="stylesheet" />
    <titre>Modifier l'email</titre>

</head>
<body>
        <div class="background">
        <div class="shape"></div>
        <div class="shape"></div>


    <form method="POST" action="">
        <label for="new_login">Nouveau email :</label>
        <input type="text" id="new_email" name="new_email" required><br>

        <input type="submit" value="Enregistrer">
    </form>
</body>
</html>
