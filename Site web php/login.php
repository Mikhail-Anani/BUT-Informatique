<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="css/login.css" rel="stylesheet" />
    <title>Connexion - SPA</title>
</head>
<body>
    <div class="background">
        <div class="shape"></div>
        <div class="shape"></div>
    </div>
    
    <form action="php/login2.php" method="POST">
        <h3>Connexion</h3>
        <label for="username">Login:</label>
        <input type="text" placeholder="login" id="login" name="login" required>
        <label for="password">Mot de passe:</label>
        <input type="password" placeholder="Mot de passe"id="password" name="password" required>
        <button type="submit" value="connexion" name="go">Se connecter</button>
        <div class="center"> <br>Vous n'avez pas de compte ? 
        <a href="inscription.php" >S'inscrire</a>
        </div>
        
    </form>

    <footer> Ce site n'a pas un but de commercialisation ou encore de s'appropier les droits des autres<br>
                © Enzo létocart & Mikhail Anani 
    </footer>
</body>
</html>
