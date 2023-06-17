


<!DOCTYPE html>
<html>
<head>
    <link href="css/gestion.css" rel="stylesheet" />
    <title>gestion - SPA</title>
</head>
<body>
    <header>
        
    </header>
    <div class="background">
        <div class="shape"></div>
        <div class="shape"></div>
    </div>
<body>
    <center>
    <h1 style="color: white;">Gestion de profil</h1>
    <h2 style="color: white;">Actions disponibles :</h2></center>
    <ul>
        <li><a href="accueil.php">Accueil</a></li>
        <li><a href="php/modifier_mdp.php">Modifier le mot de passe</a></li>
        <li><a href="php/modifier_login.php">Modifier le nom d'utilisateur</a></li>
         <li><a href="php/modifier_email.php">Modifier l'email</a></li>
         <?php
session_start();


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




    if(!isset($_SESSION['login'])){

   echo "<form action='' method='POST' >";
           echo '    <coucou> <li class="left"><button  role="button" name="login"  value="login" >Login</button></li> </coucou> ';

        echo '  <coucou> <li class="left"><button role="button" name="inscription" value="inscription">Inscription</button></li> </coucou>';
        echo ' </form>';

    }else{
        echo "<form action='' method='POST' >";
    echo '   <coucou> <li class="left"><button  role="button" value="deconnexion" name="deconnexion">Déconnexion</button></li> </coucou>';

   
echo ' </form>';
  }


?>
         

    </ul>
    <br><br><br>
    <div>
        <center>
    <img src="img/profil.png" alt="utilisateur" >
</center>
    <?php



if(isset($_SESSION['login'])){
    $login = $_SESSION['login'];

    echo '<center><h1 style= "color: white;">Vous etes connecter avec : '. $login . "</h1></center<br>";
}



?>
<h3 style="color: white;">
    Cliquez sur les boutons du haut pour changer vos informations personnels
</h3>

</div>
</center>  
</body>

    <footer> Ce site n'a pas un but de commercialisation ou encore de s'appropier les droits des autres<br>
        © Enzo letocart & Mikhail Anani 
    </footer>
</body>
</html>




