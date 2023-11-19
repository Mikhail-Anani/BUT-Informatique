package fr.iutfbleau.projetIHM2023FI2.GroupeAchJLMIK;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * Modèle associé à la deuxième vue du camembert
 * Gère l'accès à la base de données et les opérations liées aux résultats du protocole
 * 
 * @version 1.1
 * @author Mikhail Anani,Achok Samedi, Jean-Luc Nelet
 * 
 */

public class LeDeuxiemeCamembertModel {
    private Connection connection;
    private String protocol;
    private ArrayList<Integer> idResult;
    private int TotalMenu;
    private int[] sousMenuCounts;
    private int sessionsTot;

    /**
     * Constructeur de la classe LeDeuxiemeCamembertModel
     *
     * @param protocolId L'identifiant du protocole associé au modèle
     */
    public LeDeuxiemeCamembertModel(String protocolId) {
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/nelet", "nelet", "Zigouigoui123");
        } catch (SQLException e) {
            afficherErreur("Erreur lors de la connection a la base de donnée.");
            e.printStackTrace();
        }
        this.protocol = protocolId;
        idResult = getIdResult(protocolId);
        sessionsTot = getSessTot(protocolId);
        sousMenuCounts = getSousMenuCounts(idResult);
    }

    /**
     * Récupère le nombre total de sessions pour un protocole donné
     *
     * @param idProtocole L'identifiant du protocole
     * @return Le nombre total de sessions
     */
    public int getSessTot(String idProtocole) {
        ResultSet childrenResultSet = null;
        try {
            PreparedStatement childrenStatement = connection.prepareStatement("SELECT DISTINCT(S.resultat_id) from Selection S JOIN Resultat R ON S.resultat_id=R.idResultat WHERE R.idProtocole=?; ");
            childrenStatement.setString(1, idProtocole);
            childrenResultSet = childrenStatement.executeQuery();
            if (childrenResultSet.next()) {
                int x = childrenResultSet.getInt(1);
                return x;
            }
        } catch (SQLException e) {
            afficherErreur("Erreur requête qui récupère le nombre total de sessions pour un protocole.");
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Récupère le nombre de sous-menus ouverts pour chaque résultat
     *
     * @param idResult Liste des identifiants des résultats
     * @return Un tableau contenant le nombre de sous-menus ouverts pour chaque résultat
     */
    public int[] getSousMenuCounts(ArrayList<Integer> idResult) {
        int[] sousMenuCounts = new int[idResult.size()];
        for (int i = 0; i < idResult.size(); i++) {
            int y = MaxOpenedMenu(idResult.get(i));
            sousMenuCounts[i] = (y != -1) ? y : 0;
            TotalMenu += y;
        }
        return sousMenuCounts;
    }

    /**
     * Récupère le nombre maximal de sous-menus ouverts pour un résultat donné
     *
     * @param resultatId L'identifiant du résultat
     * @return Le nombre maximal de sous-menus ouverts
     */
    public int MaxOpenedMenu(int resultatId) {
        ResultSet childrenResultSet = null;
        try {
            PreparedStatement childrenStatement = connection.prepareStatement("SELECT COUNT(idSelection) FROM Selection WHERE resultat_id= ?; ");
            childrenStatement.setInt(1, resultatId);
            childrenResultSet = childrenStatement.executeQuery();
            if (childrenResultSet.next()) {
                int x = childrenResultSet.getInt(1) - 1;
                return x;
            }
        } catch (SQLException e) {
            afficherErreur("Erreur requête pour récuperer le nombre de sous-menus ouverts.");
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Récupère la liste des identifiants de résultat pour un protocole donné
     *
     * @param idProtocol L'identifiant du protocole
     * @return La liste des identifiants de résultat
     */
    public ArrayList<Integer> getIdResult(String idProtocol) {
        ResultSet childrenResultSet = null;
        ArrayList<Integer> idRes = new ArrayList<>();
        try {
            PreparedStatement childrenStatement = connection.prepareStatement("Select distinct(idResultat) from Resultat where idProtocole=(?); ");
            childrenStatement.setString(1, idProtocol);
            childrenResultSet = childrenStatement.executeQuery();
            while (childrenResultSet.next()) {
                int idR = childrenResultSet.getInt("idResultat");
                idRes.add(idR);
            }
        } catch (SQLException e) {
            afficherErreur("Erreur requête pour récuperer la liste des id pour un protocole.");
            e.printStackTrace();
        }
        return idRes;
    }

    /**
     * Récupère le tableau contenant le nombre de sous-menus ouverts pour chaque résultat
     *
     * @return Le tableau contenant le nombre de sous-menus ouverts pour chaque résultat
     */
    public int[] getSousMenuCounts() {
        return sousMenuCounts;
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
