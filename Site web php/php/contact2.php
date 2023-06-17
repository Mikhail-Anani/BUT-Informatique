<?php
include_once"BaseD.php"
?>
<?php
  
    session_start();
    

    if($_SERVER["REQUEST_METHOD"] == "POST") {

        
$nom= filter_input(INPUT_POST,'nom' ,FILTER_SANITIZE_STRING);
$prenom= filter_input(INPUT_POST,'prenom' ,FILTER_SANITIZE_STRING);
$Datenaissance= filter_input(INPUT_POST,'date' ,FILTER_SANITIZE_STRING);
$telephone= filter_input(INPUT_POST,'telephone' ,FILTER_SANITIZE_NUMBER_INT);
$mail= filter_input(INPUT_POST,'mail' ,FILTER_SANITIZE_EMAIL);
$requete= filter_input(INPUT_POST,'requete' ,FILTER_SANITIZE_STRING);

$db = mysqli_connect($hostnamedata,$usernameData,$passwordData,$database);

  
        if ($db->connect_error) {
            die("Connection error: " . $db->connect_error);
        }

if(!($nom===false || $nom===null || $prenom===false || $prenom===null ||  $Datenaissance=== false || $Datenaissance === null
||  $telephone=== false || $telephone === null
||  $mail=== false || $mail=== null
||  $requete=== false || $requete === null)){


     $nom = $db->real_escape_string($_POST['nom']);
    $prenom = $db->real_escape_string($_POST['prenom']);
     $Datenaissance = $db->real_escape_string($_POST['date']);
    $telephone = $db->real_escape_string($_POST['telephone']);
     $mail = $db->real_escape_string($_POST['mail']);
    $requete = $db->real_escape_string($_POST['requete']);

}
        
    
        if( empty($nom) || empty($prenom) || empty($Datenaissance) || empty($telephone) || empty($mail) || empty($requete)) {
            die("Tous les champs sont requis");
        }



        if ($stmt = $db->prepare("INSERT INTO Contact ( nom, prenom,date_de_naissance,telephone, mail,requete) VALUES (?,?,?,?,?,?)")) {
            $stmt->bind_param("ssssss",$nom,$prenom,$Datenaissance,$telephone, $mail,$requete); 
            $stmt->execute();

            if($stmt->affected_rows === 0) {
                   echo "<script type='text/javascript'>alert('il y a eu un probleme avec votre demande'); window.location= '../contact.php';</script>";
            } else {
                header('Location:../contact.php');

            }
            $stmt->close();
        }
    } else {
        header('Location:../contact.php');
    }
?>