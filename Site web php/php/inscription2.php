<?php
include_once"BaseD.php"
?>
<?php
  
    session_start();

    if($_SERVER["REQUEST_METHOD"] == "POST") {
        
        $username= filter_input(INPUT_POST,'username' ,FILTER_SANITIZE_STRING);
        $password= filter_input(INPUT_POST,'password' ,FILTER_SANITIZE_STRING);
        $email= filter_input(INPUT_POST,'email' ,FILTER_SANITIZE_STRING);
        $login= filter_input(INPUT_POST,'login' ,FILTER_SANITIZE_NUMBER_INT);
        $role= filter_input(INPUT_POST,'role' ,FILTER_SANITIZE_STRING);

        $db = mysqli_connect($hostnamedata,$usernameData,$passwordData,$database);

        if ($db->connect_error) {
            die("Connection error: " . $db->connect_error);
        }

        
        
  
       
       
if(!($login===false || $login===null || $password===false || $password===null ||  $username=== false || $username === null
||  $email=== false || $email === null)){


    $login = $db->real_escape_string($_POST['login']);
    $password = $db->real_escape_string($_POST['password']);
    $username = $db->real_escape_string($_POST['username']);
    $email = $db->real_escape_string($_POST['email']);
    $role=$db->real_escape_string($_POST['role']);

}

    
        if(empty($username) || empty($password) || empty($login)  || empty($email) || empty($role)) {
            die("Tous les champs sont requis");
        }
        
        

        if ($stmt = $db->prepare("SELECT * FROM Utilisateur WHERE login = ?")) {
            $stmt->bind_param("s", $login); 
            $stmt->execute();
            $result = $stmt->get_result();

            if($result->num_rows > 0) {
              echo "<script type='text/javascript'>alert('login invalide'); window.location= '../inscription.php';</script>";
            }
            $stmt->close();
        }

   
        $hashed_password = password_hash($password, PASSWORD_DEFAULT);

        if ($stmt = $db->prepare("INSERT INTO Utilisateur (login, username, password, email,role) VALUES (?,?,?,?,?)")) {
            $stmt->bind_param("sssss",$login, $username, $hashed_password, $email,$role); 
            $stmt->execute();

            if($stmt->affected_rows === 0) {
                 echo "<script type='text/javascript'>alert('Il y a eu un probleme avec votre inscription'); window.location= '../inscription.php';</script>";
            } else {
                $_SESSION['role']=$role;
                header('Location:../login.php');

            }
            $stmt->close();
        }
    } else {
        header('Location:../login.php');
    }
?>
