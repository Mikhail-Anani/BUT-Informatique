<!DOCTYPE html>

<html>

	<head>
	<meta charset="UTF-8">
 	<link href="css/contact.css" rel="stylesheet" />
	<title>Contact - SPA</title>
	</head>

	<body >
    <header>
        <nav>
            <ul>
                <li><a  href="accueil.php" role="button">&#127968 Accueil</a></li>
                <li><a  href="evenement.php" role="button">Evenement</a></li>
               
                <li><a  href="contact.php" role="button">Contact</a></li>



            </ul>
            </nav>
    </header>

<h1>Prendre un rendez-vous</h1>


<form action="php/contact2.php" method="POST">


<label for="nom">Nom: </label>
<input id="nom" name="nom" type="text"  placeholder="votre Nom"/>


<label for="prenom">Prenom: </label>
<input id="prenom" name="prenom" type="text"  placeholder="votre Prenom" />


<label for="date">Date de naissance: </label>
<input id="date" name="date" type="date"/>


<label for="telephone">Téléphone: </label>
<input id="telephone" name="telephone" type="tel"  placeholder="votre téléphone" />


<label for="mail">Mail: </label>
<input id="mail" name="mail" type="email"  placeholder="votre mail" />

<label for="requete">Requête: </label>
<input id="requete" name="requete" type="text"  placeholder="Requête" />

<button type="submit" class="button-style" >Envoyer</button>

</form>


<footer> Ce site n'a pas un but de commercialisation ou encore de s'appropier les droits des autres<br>
        © Enzo letocart & Mikhail Anani 
    </footer>
	</body>

</html>
