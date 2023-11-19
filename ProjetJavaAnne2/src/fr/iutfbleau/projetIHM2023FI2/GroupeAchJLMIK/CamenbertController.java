package fr.iutfbleau.projetIHM2023FI2.GroupeAchJLMIK;

import java.awt.event.*;
import java.util.ArrayList;
/**
 * Contrôleur de l'application de protocole
 * Gère les interactions entre le modèle et la vue de l'application
 * 
 * @version 1.1
 * @author Mikhail Anani,Achok Samedi, Jean-Luc Nelet
 *
 */
public class CamenbertController {
    private CamenbertModel model;
    private CamenbertView view;


      /**
     * Constructeur du contrôleur
     *
     * @param model Le modèle de l'application
     * @param view  La vue de l'application
     */
    public CamenbertController(CamenbertModel model, CamenbertView view) {
        this.model = model;
        this.view = view;

        view.addValidationListener(new ValidationListener2(view, model));
    }

}
