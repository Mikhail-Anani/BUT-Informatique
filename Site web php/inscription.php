<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="css/inscription.css" rel="stylesheet" />
    <title>Inscription - SPA</title>
</head>
<body>
    <div class="background">
        <div class="shape"></div>
        <div class="shape"></div>
    </div>
    <form action="php/inscription2.php" method="POST">
        <h3>Inscription</h3>
        <label for="username">Login :</label>
        <input type="text" placeholder="login" id="login" name="login" required>
        <label for="username">Nom :</label>
        <input type="text" placeholder="Nom" id="username" name="username" required>
        <label for="password">Mot de passe:</label>
        <input type="password" placeholder="Mot de passe" id="password" name="password" required>
        <label for="email">Email:</label>
        <input type="email" placeholder="Email" id="email" name="email" required>

        <input type='radio' name='role' value='Adherent'required> Adhérent<br>
        <input type='radio' name='role' value='Gestionaire' required> Gestionaire<br>

        <button type="submit" >S'inscrire</button>
        
        <div class="center"> <br>Vous avez déjà un compte ? 
            <a href="login.php" >Se connecter</a>
            </div>
    </form>

    <footer> Ce site n'a pas un but de commercialisation ou encore de s'appropier les droits des autres<br>
        © Enzo létocart & Mikhail Anani</footer>
</body>
</html>
