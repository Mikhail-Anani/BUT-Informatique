package fr.iutfbleau.projetIHM2023FI2.GroupeAchJLMIK;
/**
 * Main de l'application Outil de test
 * Lance l'application.
 * 
 * @version 1.1
 * @author Mikhail Anani,Achok Samedi, Jean-Luc Nelet
 * 
 */
public class Main {
    /**
     * Point d'entrée principal de l'application
     *
     * @param args Les arguments de la ligne de commande
     */
    public static void main(String[] args) {
        ProtocolModel model = new ProtocolModel();
        ProtocolView view = new ProtocolView();
        ProtocolController controller = new ProtocolController(model, view);
    }
}