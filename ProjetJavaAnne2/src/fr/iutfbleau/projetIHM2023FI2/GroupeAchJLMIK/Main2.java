package fr.iutfbleau.projetIHM2023FI2.GroupeAchJLMIK;

/**
 * Main de l'application de vérfication pour le developpeur
 * Lance l'application.
 * 
 * @version 1.1
 * @author Mikhail Anani,Achok Samedi, Jean-Luc Nelet
 * 
 */
public class Main2 {
    public static void main(String[] args) {
        CamenbertModel model = new CamenbertModel();
        CamenbertView view = new CamenbertView();
        CamenbertController controller = new CamenbertController(model, view);
    }
}
