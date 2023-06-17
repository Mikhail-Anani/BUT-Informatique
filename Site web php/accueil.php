
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="css/accueil.css" rel="stylesheet" />
    <title>Accueil - SPA</title>
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
           echo '    <coucou> <li ><button  role="button" name="login"  value="login" >Login</button></li> </coucou> ';

        echo '  <coucou> <li ><button role="button" name="inscription" value="inscription">Inscription</button></li> </coucou>';
        echo ' </form>';

    }else{
        echo "<form action='' method='POST' >";
    echo '   <coucou> <li ><button  role="button" value="deconnexion" name="deconnexion">Déconnexion</button></li> </coucou>';

      echo '             <coucou> <li c><button style="font-size: 20px;" name="gestion" value="gestion">⚙</button></li> </coucou>  ';
echo ' </form>';
  }


?>
            </ul>
            </nav>
    </header>
   
    

    

    <h1 style="text-align: center; color: white; font-size: 66px;">SPA animalgix</h1>
<center>
<div>
    <img src="img/chien1.jpg" alt="Chien Bernard"class="image">
    <img src="img/lapin1.jpg" alt="Lapin Louis"class="image">
    <img src="img/chat1.jpg" alt="Chat Minou"class="image">
    <img src="img/chien2.jpg" alt="Chien Perle"class="image">
</div>
 </center>   
<p class="text1">

    La Société Protectrice des Animaux (SPA) est une organisation dévouée à la protection et au bien-être des animaux. 
    <br>
    Depuis notre création il y a plus de 150 ans, nous nous sommes engagés à secourir, soigner et défendre les animaux en détresse.
<br>
    Notre mission est de lutter contre la maltraitance et l'abandon des animaux de compagnie. 
    Chaque année, des milliers d'animaux sont abandonnés ou victimes de mauvais traitements, et c'est là que la SPA intervient.
     Nous offrons un refuge sûr et aimant à ces animaux, en veillant à ce qu'ils reçoivent les soins nécessaires, la nourriture et l'affection dont ils ont besoin.

</p>
<br>
<br>



<table>
<tr >
    <td class="case">
        <br>
   <center><img src="img/chien3.jpg" alt="Chien Bud"class="image2"></center> 
   <br>
</td>
    
    <td class="text2">
       
       Bud est un chien sensible et dynamique. C'est un loulou très joyeux et il a du mal à gérer ses émotions lorsqu'il est trop content.
        Il pourra vivre en maison ou appartement (min 60m2) avec accès extérieur et partager sa vie avec des enfants de + de 15 ans.
        Il ne pourra pas vivre avec des chats.
        Pour le bien-être de Bud, il est indispensable de se renseigner sur l'éducation bienveillante et les signaux d'apaisements.
        
</tr>
<tr>
   
    
    <td class="text2">
        Plume et Faucille sont arrivées de fourrière il y a quelques jours. Pour l'instant, elles sont apeurées par leur arrivée au refuge et appréhendent le contact avec l'humain ..
        Dans l'idéal, nous souhaiterions les placer ensemble, dans le même foyer. Elles se rassurent beaucoup mutuellement !  
        Nous leur cherchons donc une famille patiente, qui saura les rassurer et leur laisser le temps nécessaire à leur épanouissement. 
        Nous éviterons les enfants en bas âge.
    </td>
    <td class="case" >
        <br>
        <center><img src="img/chat2.jpg" alt="Chien Bud"class="image2"></center> 
        <br>
    </td>
</tr>
</table>
<br>
<br>
<h1 class="titre">Les conditions d’adoption</h1> 
<p class="text3">
   <br>
    Pour toute adoption, nous demandons des justificatifs afin de compléter le dossier de l’adoptant : 
    une pièce d’identité, un justificatif de domicile original de moins de 3 mois, et le certificat d’engagement et 
    de connaissance des besoins spécifiques de l’espèce complété et signé 7 jours avant l’acquisition de l’animal. 
    Découvrez cette nouvelle formalité.
    <br>
    Nous demandons également une participation financière, couvrant les frais vétérinaires dont l’animal 
    a bénéficié lors de sa prise en charge (stérilisation, identification, vaccination, etc..). Si l’animal
     n’est pas encore stérilisé lors de l’adoption, un chèque de caution de 300€ est demandé.

</p>
<br>
<br>
    <footer> Ce site n'a pas un but de commercialisation ou encore de s'appropier les droits des autres<br>
        © Enzo letocart & Mikhail Anani 
    </footer>
</body>
</html>
