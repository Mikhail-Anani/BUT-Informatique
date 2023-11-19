package fr.iutfbleau.projetIHM2023FI2.GroupeAchJLMIK;


/**
 * Contrôleur de l'application de protocole
 * Gère les interactions entre le modèle et la vue de l'application
 *
 * 
 * @version 1.1
 * @author Mikhail Anani,Achok Samedi, Jean-Luc Nelet
 * 
 */
public class ProtocolController {
    private ProtocolModel model;
    private ProtocolView view;


      /**
     * Constructeur du contrôleur
     *
     * @param model Le modèle de l'application
     * @param view  La vue de l'application
     */
    public ProtocolController(ProtocolModel model, ProtocolView view) {
        this.model = model;
        this.view = view;

        view.addValidationListener(new ValidationListener(view, model));
    }


    
}