package fr.iutfbleau.projetIHM2023FI2.GroupeAchJLMIK;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 * Modèle de l'application de protocole
 *
 * @version 1.1
 * @author Mikhail Anani,Achok Samedi, Jean-Luc Nelet
 * 
 */
public class ProtocolModel {
    private Connection connection;

    /**
     * Constructeur de ProtocolModel
     */
    public ProtocolModel() {
        try {
            // Connexion à la base de données
            connection = DriverManager.getConnection(
                    "jdbc:mariadb://dwarves.iut-fbleau.fr/nelet", "nelet", "Zigouigoui123"
            );
        } catch (SQLException e) {
            afficherErreur("Erreur lors de la connection de la base de donnée.");
            e.printStackTrace();
        }
    }

    /**
     * Vérifie si un protocole donné existe dans la base de données
     *
     * @param protocol Le nom du protocole à vérifier
     * @return true si le protocole existe, sinon false
     */
    public boolean checkProtocol(Object protocol) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM Protocole2 WHERE reference = (?)");
            preparedStatement.setObject(1, protocol);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            afficherErreur("Erreur requête de validation.");
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Obtient la description associée à un protocole donné
     *
     * @param protocol Le nom du protocole pour lequel obtenir la description
     * @return La description du protocole, ou une chaîne vide si aucune description n'est trouvée
     */
    public String getDescriptionForProtocol(Object protocol) {
        try {
            PreparedStatement descStatement = connection.prepareStatement("SELECT description FROM Protocole2 WHERE reference = (?)");
            descStatement.setObject(1, protocol);
            ResultSet descResultSet = descStatement.executeQuery();
            if (descResultSet.next()) {
                return descResultSet.getString("description");
            }

        } catch (SQLException e) {
            afficherErreur("Erreur requête pour obtenir la description.");
            e.printStackTrace();
        }

        return "";
    }

    /**
     * Obtient le nom du menu associé à un protocole donné
     *
     * @param protocol Le nom du protocole pour lequel obtenir le nom du menu
     * @return Le nom du menu associé au protocole, ou une chaîne vide si aucun menu n'est trouvé
     */
    public String getMenuName(int protocol) {
        try {
            PreparedStatement menuStatement = connection.prepareStatement("SELECT Nom FROM Menu WHERE IdOption = ?");
            menuStatement.setInt(1, protocol);

            ResultSet menuResultSet = menuStatement.executeQuery();

            if (menuResultSet.next()) {
                return menuResultSet.getString("Nom");
            }
        } catch (SQLException e) {
            afficherErreur("Erreur requête pour obtenir le nom.");
            e.printStackTrace();
        }

        return "";
    }

    /**
     * Obtient l'identifiant associé à un protocole donné
     *
     * @param protocol Le nom du protocole pour lequel obtenir l'identifiant
     * @return L'identifiant du protocole, ou -1 si aucun identifiant n'est trouvé
     */
    public int getIdForProtocol(Object protocol) {
        try {
            PreparedStatement idStatement = connection.prepareStatement("SELECT idProtocole FROM Protocole2 WHERE reference = ?");
            idStatement.setObject(1, protocol);

            ResultSet idResultSet = idStatement.executeQuery();

            if (idResultSet.next()) {
                return idResultSet.getInt("idProtocole");
            }
        } catch (SQLException e) {
            afficherErreur("Erreur pour l'obtention de l'ID du Protocole.");
            e.printStackTrace();
        }

        // Si l'ID n'est pas trouvé, on renvoie -1.
        return -1;
    }

    /**
     * Obtient l'identifiant du menu associé à un protocole donné
     *
     * @param protocol Le nom du protocole pour lequel obtenir l'identifiant du menu
     * @return L'identifiant du menu associé au protocole, ou -1 si aucun menu n'est trouvé
     */
    public int getMenuForProtocol(Object protocol) {
        try {
            PreparedStatement menuStatement = connection.prepareStatement("SELECT menu FROM Protocole2 WHERE idProtocole = ?");
            menuStatement.setObject(1, protocol);

            ResultSet menuResultSet = menuStatement.executeQuery();

            if (menuResultSet.next()) {
                return menuResultSet.getInt("menu");
            }
        } catch (SQLException e) {
            afficherErreur("Erreur pour sélectionner le menu.");
            e.printStackTrace();
        }

        // Si l'ID n'est pas trouvé, on renvoie -1
        return -1;
    }

    /**
     * Méthode pour enregistrer les informations dans la base de données
     *
     * @param idOption   L'identifiant de l'option.
     * @param idResultat L'identifiant du résultat.
     */
    public void saveToDatabase(int idOption, int idResultat) {
        try {

            // Requête pour insérer les données dans la table Résultat
            String query = "INSERT INTO Selection (option_id,resultat_id) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idOption);
            preparedStatement.setInt(2, idResultat);

            // Exécute la requête d'insertion
            preparedStatement.executeUpdate();

            
        } catch (SQLException e) {
            afficherErreur("Erreur lors de l'insertion dans la table Selection.");
            e.printStackTrace();
        }
    }

    /**
     * Obtient les enfants d'un menu à partir de l'identifiant du parent
     *
     * @param idParent L'identifiant du parent
     * @return ResultSet contenant les enfants du menu
     */
    public ResultSet getMenuChildren(int idParent) {
        ResultSet childrenResultSet = null;
        try {
            PreparedStatement childrenStatement = connection.prepareStatement("SELECT * FROM Menu WHERE IdParent = ?");
            childrenStatement.setInt(1, idParent);
            childrenResultSet = childrenStatement.executeQuery();
        } catch (SQLException e) {
            afficherErreur("Erreur lors de la requête pour obtenir les enfants.");
            e.printStackTrace();
        }
        return childrenResultSet;
    }

    /**
     * Obtient l'identifiant d'une option à partir de son nom
     *
     * @param nom Le nom de l'option
     * @return L'identifiant de l'option, ou -1 si aucun identifiant n'est trouvé
     */
    public int getOptionId(String nom) {
        try {
            PreparedStatement menuStatement = connection.prepareStatement("SELECT IdOption FROM Menu WHERE Nom = ?");
            menuStatement.setObject(1, nom);

            ResultSet menuResultSet = menuStatement.executeQuery();

            if (menuResultSet.next()) {
                return menuResultSet.getInt("IdOption");
            }
        } catch (SQLException e) {
            afficherErreur("Erreur de la requête pour obtenir l'IdOption.");
            e.printStackTrace();
        }

        // Si l'ID n'est pas trouvé, on renvoie -1
        return -1;
    }

    /**
     * Insère un nouvel enregistrement dans la table Résultat et retourne son identifiant généré
     *
     * @param idProtocol L'identifiant du protocole associé au résultat
     * @return L'identifiant généré du résultat, ou -1 en cas d'échec
     */
    public int insertIntoResultat(int idProtocol) {
        try {

            String query = "INSERT INTO Resultat (idProtocole) VALUES (?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, idProtocol);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    // Récupérer l'ID auto-incrémenté généré
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
           afficherErreur("Erreur pour l'insertion des valeurs dans la table Resultat.");
            e.printStackTrace();
        }

        return -1; // Retourne -1 en cas d'échec
    }


    /**
     * Retire la dernière ligne ajoutée à la table Resultat
     *Retire les dernieres ligne ajoutée à la table Selection
     * @param idResultat L'identifiant du résultat à supprimer
     */
    public void removeFromResultat(int idResultat) {
        Connection connection = null;
        PreparedStatement deleteSelectionStatement = null;
        PreparedStatement deleteResultatStatement = null;

        try {
            // Établir la connexion à la base de données
            connection = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/nelet", "nelet", "Zigouigoui123");

            // Démarrer une transaction
            connection.setAutoCommit(false);

            // Préparer la requête de suppression pour la table Selection
            String deleteSelectionQuery = "DELETE FROM Selection WHERE resultat_id = ?";
            deleteSelectionStatement = connection.prepareStatement(deleteSelectionQuery);
            deleteSelectionStatement.setInt(1, idResultat);
            deleteSelectionStatement.executeUpdate();

            // Préparer la requête de suppression pour la table Resultat
            String deleteResultatQuery = "DELETE FROM Resultat WHERE IdResultat = ?";
            deleteResultatStatement = connection.prepareStatement(deleteResultatQuery);
            deleteResultatStatement.setInt(1, idResultat);
            deleteResultatStatement.executeUpdate();

            // Valider la transaction
            connection.commit();
        } catch (SQLException e) {
            // En cas d'erreur, annuler la transaction
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }

            e.printStackTrace();
        } finally {
            // Rétablir le mode auto-commit et fermer les ressources
            try {
                if (deleteSelectionStatement != null) {
                    deleteSelectionStatement.close();
                }
                if (deleteResultatStatement != null) {
                    deleteResultatStatement.close();
                }
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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
