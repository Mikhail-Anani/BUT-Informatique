<!DOCTYPE html>
<html>
<head>
    <link href="css/event.css" rel="stylesheet" />
    <title>evenement - SPA</title>
</head>
<body>
    <header>
        <nav>
            <ul>
                <li><a  href="accueil.php" role="button">&#127968 Accueil</a></li>
                <li><a  href="evenement.php" role="button">Evenement</a></li>
                <li><a  href="contact.php" role="button">Contact</a></li>
                  <?php
session_start();

if(isset($_GET['comment'])){
     echo "<script type='text/javascript'>alert('fonction pas disponible'); window.location= 'evenement.php';</script>";
}

if(isset($_GET['inscr'])){
    header("Location: inscrevent.php");
}


if(isset($_GET['creer'])){
                    header("Location: creerevent.php");
 }
            
if(isset($_POST['deconnexion'])){
    session_destroy();
    header("Location: accueil.php");
    exit();
}

if(isset($_POST['login'])){
  
    header("Location: login.php");
  
}
if(isset($_POST['inscription'])){
   
    header("Location: inscription.php");
   
}

if(isset($_POST['gestion'])){
    header("Location: gestion.php");
}


    if(!isset($_SESSION['login'])){

   echo "<form action='' method='POST' >";
           echo '    <coucou> <li class="left"><button  role="button" name="login"  value="login" >Login</button></li> </coucou> ';

        echo '  <coucou> <li class="left"><button role="button" name="inscription" value="inscription">Inscription</button></li> </coucou>';
        echo ' </form>';

    }else{
        echo "<form action='' method='POST' >";
    echo '   <coucou> <li class="left"><button  role="button" value="deconnexion" name="deconnexion">Déconnexion</button></li> </coucou>';

      echo '             <coucou> <li class="left"><button style="font-size: 20px;" name="gestion" value="gestion">⚙</button></li> </coucou>  ';
echo ' </form>';
  }


?>
            </ul>
            </nav>
    </header>
    <div class="background">
        <div class="shape"></div>
        <div class="shape"></div>
    </div>
   <center> 

 <div >
    <form method ="GET" >
        <table>
 
      
            <tr>
                <td style="color: white;"><label  >Titre</label></td>
              <td width="25px"><input type = "text" name = "titre"></td>
              <td ><button type="submit" value="rechercher" name="rechercher">Rechercher</button><td>
                <?php  

                if(isset($_SESSION['role'])){
                if($_SESSION['role'] == 'Gestionaire'){
                    echo '  <td ><button type="submit" value="creer" name="creer">Creer</button><td>';
                }
                 if($_SESSION['role'] == 'Adherent'){
                    echo '  <td ><button type="submit" value="inscr" name="inscr">inscription event</button><td>';
                     echo '  <td ><button type="submit" value="comment" name="comment">Commenter</button><td>';
                }
}
                ?>
            </tr>
    
            <tr>
                <td style="color: white;"><label for="d">Date</label></td>
              <td>
                    
                    <input type = "date" name = "date">
                </td>
            </tr>
    
            <tr>
                <td  ><label for='Type' style="color: white;" >Trier par </label></td>
                <td>
                    <select name='tri'>
                        <option value='Titre' >Titre</option>
                        <option value='Date'  selected>Date</option>
                          <option value='Date'  selected>Type</option>
                     

                </select></td>
            </tr>
        </table>
    </form>
</div>
 </center>
 <br><br>
 <center>
<?php
include_once "php/BaseD.php";
$db = mysqli_connect($hostnamedata, $usernameData, $passwordData, $database);

if ($db->connect_error) {
    die("Erreur de connexion: " . $db->connect_error);
}
if(isset($_GET['rechercher'])){

$titre = mysqli_real_escape_string($db, $_GET['titre']);
$date = mysqli_real_escape_string($db, $_GET['date']);

$query = "SELECT * FROM event WHERE Titre = '$titre' OR date = '$date'";
$result = mysqli_query($db, $query);


if (mysqli_num_rows($result) > 0) {

   
} else {
  
    echo "<script>alert('Erreur : veuillez entrer un titre ou une date valide.');</script>";
    $query = "SELECT * FROM event";
$result = mysqli_query($db, $query);
}

}else{

$query = "SELECT * FROM event";
$result = mysqli_query($db, $query);
}

?>

<table border="1">
    <tr>
        <th  style="color: white;">Titre</th>
        <th style="color: white;">id</th>
        <th style="color: white;">Date</th>
         <th style="color: white;">Login</th>
          <th style="color: white;">Type</th>
    </tr>

    <?php while ($row = mysqli_fetch_assoc($result)) { ?>
        <tr>
            <td style="width: 300px; color: white; font-size: 16px;" ><?php echo $row['Titre']; ?></td>
            <td style="width: 100px; color: white; font-size: 16px;"><?php echo $row['id']; ?></td>
            <td style="width: 100px; color: white; font-size: 16px;"><?php echo $row['date']; ?></td>
            <td style="width: 300px; color: white; font-size: 16px;" ><?php echo $row['Login']; ?></td>
            <td style="width: 100px; color: white; font-size: 16px;"><?php echo $row['Type']; ?></td>
        
        </tr>
    <?php } ?>
</table>





 </center>

    <footer> Ce site n'a pas un but de commercialisation ou encore de s'appropier les droits des autres<br>
        © Enzo letocart & Mikhail Anani 
    </footer>
</body>
</html>
