<?php
include_once"BaseD.php"
?>

<?php
    
    session_start();

   
    if($_SERVER["REQUEST_METHOD"] == "POST") {
$login= filter_input(INPUT_POST,'login' ,FILTER_SANITIZE_STRING);
$password= filter_input(INPUT_POST,'password' ,FILTER_SANITIZE_STRING);

$db = mysqli_connect($hostnamedata,$usernameData,$passwordData,$database);



if ($db->connect_error) {
    die("Erreur de connexion: " . $db->connect_error);
}
        

if(!($login===false || $login===null || $password===false || $password===null)){

    $login = $db->real_escape_string($_POST['login']);
    $password = $db->real_escape_string($_POST['password']);
}

       
        if(empty($login) || empty($password)) {
            die("Le nom d'utilisateur et le mot de passe sont requis.");
        }

       
        

       
        if ($stmt = $db->prepare("SELECT * FROM Utilisateur WHERE login = ?")) {
            $stmt->bind_param("s", $login); 
            $stmt->execute();
            $result = $stmt->get_result();

            if($result->num_rows === 0) {
                   echo "<script type='text/javascript'>alert('utilisateur Invalide'); window.location= '../login.php';</script>";
             
            } else {
                $user = $result->fetch_assoc();
                if (password_verify($password, $user['password'])) {
               
                    
                    $_SESSION['login'] = $user['login'];
                    $_SESSION['password']=$user['password'];
                    $_SESSION['email']=$user['email'];
                    $_SESSION['role']=$user['role'];

                 header('Location: ../accueil.php');
            
                } else {
                    echo "<script type='text/javascript'>alert('Mot de passe incorrect'); window.location= '../login.php';</script>";
                }
            }
            $stmt->close();
        }
    } else {
        header('Location:../login.php');
    }
?>
