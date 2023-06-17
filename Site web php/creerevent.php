

<!DOCTYPE html>
<html>
   
<head>
 <meta charset="UTF-8">
    <link href="css/modif.css" rel="stylesheet" />
    <title>Creer un evenement</title>
</head>
<body>
        <div class="background">
        <div class="shape"></div>
        <div class="shape"></div>


    <form method="POST" action="php/event2.php">
        <label for="new_titre">Titre :</label>
        <input type="text" id="new_titre" name="new_titre" required><br>
         <label for="new_titre">Date  :</label>
        <input type="date" id="new_date" name="new_date" required><br>
        <label for="typeevent">Type d'évenemnt : </label>
            <select name='typeevent'>
                        <option value='vente' >Adoption</option>
                        <option value='Don'  selected>Don</option>
                     

                </select></td>


    

        <input type="submit" value="Enregistrer">
    </form>
</body>
</html>
