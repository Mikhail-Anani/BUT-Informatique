package fr.iutfbleau.projetIHM2023FI2.GroupeAchJLMIK;

/**
 * Contrôleur pour la deuxième vue du camembert
 * Il connecte le modèle (`LeDeuxiemeCamembertModel`) et la vue (`LeDeuxiemeCamembertView`)
 * 
 * @version 1.1
 * @author Mikhail Anani,Achok Samedi, Jean-Luc Nelet
 * 
 */
public class LeDeuxiemeCamembertController {
    private LeDeuxiemeCamembertModel model;
    private LeDeuxiemeCamembertView view;

    /**
     * Constructeur de la classe LeDeuxiemeCamembertController
     *
     * @param model Le modèle associé au contrôleur
     * @param view  La vue associée au contrôleur
     */
    public LeDeuxiemeCamembertController(LeDeuxiemeCamembertModel model, LeDeuxiemeCamembertView view) {
        this.model = model;
        this.view = view;
    }

}
