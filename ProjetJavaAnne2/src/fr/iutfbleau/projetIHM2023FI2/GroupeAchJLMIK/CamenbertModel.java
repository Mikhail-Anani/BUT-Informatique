package fr.iutfbleau.projetIHM2023FI2.GroupeAchJLMIK;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;


/**
 * Modèle de l'application de protocole
 *
 * @version 1.1
 * @author Mikhail Anani,Achok Samedi, Jean-Luc Nelet
 * 
 */

public class CamenbertModel {
    private Connection connection;
    /**
     * Constructeur de CamenbertModel.
     */
    public CamenbertModel() {
        try {
            // Connexion à la base de données
            connection = DriverManager.getConnection(
                "jdbc:mariadb://dwarves.iut-fbleau.fr/nelet","nelet", "Zigouigoui123"
            );
        } catch (SQLException e) {
            afficherErreur("Erreur lors de la connection a la base de donnée.");
            e.printStackTrace();
        }
    }
 /**
     * Vérifie si un protocole donné existe dans la base de données
     *
     * @param protocol Le nom du protocole à vérifier
     * @return true si le protocole existe, false sinon
     */
    public boolean checkProtocol(String protocol) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM Protocole2 WHERE reference = ?");
            preparedStatement.setString(1, protocol);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            afficherErreur("Erreur requête pour vérifier si le protocole est le bon.");
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Obtient l'identifiant d'un protocole dans la base de données
     *
     * @param protocol Le nom du protocole
     * @return L'identifiant du protocole
     */
    public String getIdProtocol(String protocol) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT idProtocole FROM Protocole2 WHERE reference = ?");
            preparedStatement.setString(1, protocol);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                return String.valueOf(id);
            }
        } catch (SQLException e) {
            afficherErreur("Erreur requête pour obtenir l'id du protocole.");
            e.printStackTrace();
        }

        return null;
    }

    /**
 * Obtient le nombre total d'actions uniques utilisées dans tous les résultats associés à un protocole donné
 *
 * @param idProtocol L'identifiant du protocole
 * @return Le nombre total d'actions uniques utilisées
 */
    public int getEveryUsedActions(String idProtocol) {
        ResultSet childrenResultSet = null;
        try {
            PreparedStatement childrenStatement = connection.prepareStatement("SELECT COUNT(DISTINCT S.option_id) FROM Selection S JOIN Resultat R ON S.resultat_id = R.IdResultat JOIN Menu M ON S.option_id = M.IdOption WHERE R.IdProtocole = ? AND S.option_id NOT IN (SELECT IdParent FROM Menu WHERE IdParent IS NOT NULL)");
            childrenStatement.setString(1, idProtocol);
            childrenResultSet = childrenStatement.executeQuery();
            if (childrenResultSet.next()) {
                return childrenResultSet.getInt(1);
            }
        } catch (SQLException e) {
            afficherErreur("Erreur requête pour obtenir le nombre total d'actions uniques pour un protocole.");
            e.printStackTrace();
        }
        return -1;
    }

    public boolean isGoodAction(String idProtocol,int idOption){
        boolean rep=false;
        
        return rep;
    }
    
    /**
     * Obtient la proportion de chaque action du protocole, excluant les actions de menus, dans tous les résultats associés à un protocole donné
     *
     * @param idProtocol L'identifiant du protocole
     * @param actions Une liste destinée à stocker le nom de chaque action
     * @return Une liste de proportions pour chaque action du protocole
     */
    public ArrayList<Double> getActions(String idProtocol,ArrayList<String> actions) {
        ArrayList<Double> proportion=new ArrayList<Double>();
        ResultSet childrenResultSet = null;
        // on récupère les différentes actions du protocole (hors menus) et leurs occurrences
        try {
            PreparedStatement childrenStatement = connection.prepareStatement("SELECT option_id,COUNT(S.option_id) AS count FROM Selection S JOIN Resultat R ON S.resultat_id = R.IdResultat JOIN Menu M ON S.option_id = M.IdOption WHERE R.IdProtocole = ? AND S.option_id NOT IN (SELECT IdParent FROM Menu WHERE IdParent IS NOT NULL) GROUP BY S.option_id");
            childrenStatement.setString(1, idProtocol);
            childrenResultSet = childrenStatement.executeQuery();
            while (childrenResultSet.next()) {
                int idOption=childrenResultSet.getInt("option_id");
                double count = Double.valueOf(childrenResultSet.getInt("count"));
                double calcul;
                calcul=count / getTotalCountActions(idProtocol);
                if(checkBonneAction(idOption, idProtocol,actions)==true){
                    calcul*=(-1);
                }
                proportion.add(calcul);
            }
        } catch (SQLException e) {
            afficherErreur("Erreur requête pour obtenir la proportion de chaque protocole.");
            e.printStackTrace();
        }
        return proportion;
    }

    /**
     * Obtient le nombre d'occurrences d'une action spécifique dans tous les résultats associés à un protocole donné
     *
     * @param idProtocol L'identifiant du protocole
     * @param id_action L'identifiant de l'action dont on souhaite compter les occurrences
     * @return Le nombre d'occurrences de l'action spécifiée
     */
    public int getEverySingleAction(String idProtocol, int id_action) {
        ResultSet childrenResultSet = null;
        try {
            PreparedStatement childrenStatement = connection.prepareStatement("SELECT COUNT(option_id) FROM Selection WHERE resultat_id IN (SELECT idResultat from Resultat WHERE idProtocole=?)AND option_id=?;");
            childrenStatement.setString(1, idProtocol);
            childrenStatement.setInt(2, id_action);
            childrenResultSet = childrenStatement.executeQuery();
            if (childrenResultSet.next()) {
                return childrenResultSet.getInt(1);
            }
        } catch (SQLException e) {
            afficherErreur("Erreur requête pour obtenir le nombre d'occurences pour une action pour un protocole donné.");
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Obtient le nombre total d'occurrences d'actions pour un protocole donné
     *
     * @param idProtocol L'identifiant du protocole
     * @return Le nombre total d'occurrences d'actions
     */
    public int getTotalCountActions(String idProtocol) {
        ResultSet childrenResultSet = null;
        try {
            PreparedStatement childrenStatement = connection.prepareStatement("SELECT COUNT(option_id) FROM Selection WHERE resultat_id IN (SELECT idResultat from Resultat WHERE idProtocole=(?));");
            childrenStatement.setString(1, idProtocol);
            childrenResultSet = childrenStatement.executeQuery();
            if (childrenResultSet.next()) {
                return childrenResultSet.getInt(1);
            }
        } catch (SQLException e) {
            afficherErreur("Erreur requête pour obtenir le nombre d'occurences totales pour un protocole donné.");
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Vérifie si une option spécifique correspond à la bonne action pour un protocole donné
     *
     * @param idOption L'identifiant de l'option à vérifier
     * @param idProtocol L'identifiant du protocole
     * @param actions La liste d'actions à remplir avec les noms des options
     * @return true si l'option correspond à la bonne action, false sinon
     */
    public boolean checkBonneAction(int idOption,String idProtocol,ArrayList<String> actions) {
        boolean rep = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Nom FROM Menu WHERE IdOption=?;");
            preparedStatement.setInt(1, idOption);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String OptionAction = resultSet.getString("Nom");
                if (OptionAction != null) {
                    actions.add(OptionAction);
                    String BonneAction = getBonneAction(idProtocol);
                    if (OptionAction.equals(BonneAction)) {
                        return true;
                    }
                }
            }
            
        } catch (SQLException e) {
            afficherErreur("Erreur requête pour vérifier si l'option choisi est la bonne action d'un protocole.");
            e.printStackTrace();
        }
        return rep;
    }

    /**
     * Obtient la bonne action associée à un protocole donné
     *
     * @param idProtocol L'identifiant du protocole
     * @return La bonne action associée au protocole
     */
    public String getBonneAction(String idProtocol) {
        String rep=null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT action FROM Protocole2 WHERE idProtocole=?; ");
            preparedStatement.setString(1, idProtocol);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                rep = resultSet.getString(1);
                return rep;
            }
        } catch (SQLException e) {
            afficherErreur("Erreur requête pour obtenir la bonne action associée a un protocole.");
            e.printStackTrace();
        }
        return rep;
    }


    /**
     * Affiche un message d'erreur
     *
     * @param message Le message d'erreur à afficher
     */
    private void afficherErreur(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
