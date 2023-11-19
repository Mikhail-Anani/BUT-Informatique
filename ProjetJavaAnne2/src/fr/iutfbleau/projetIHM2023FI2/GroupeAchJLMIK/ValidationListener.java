package fr.iutfbleau.projetIHM2023FI2.GroupeAchJLMIK;

import java.awt.event.*;


/**
 * ActionListener pour le bouton de validation dans l'interface utilisateur
 * Réagit aux événements de bouton de validation et traite les protocoles
 * 
 * @version 1.1
 * @author Mikhail Anani,Achok Samedi, Jean-Luc Nelet
 * 
 */
public class ValidationListener implements ActionListener {
    private ProtocolView view;
    private ProtocolModel model;

    /**
     * Constructeur de ValidationListener
     *
     * @param view   La vue de l'application
     * @param model  Le modèle de l'application
     */
    public ValidationListener(ProtocolView view, ProtocolModel model) {
        this.view = view;
        this.model = model;
    }

    /**
     * Réagit à un événement de bouton de validation
     *
     * @param e L'événement d'action déclenché par le bouton de validation
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object protocol = view.getProtocolInput();

        if (model.checkProtocol(protocol)) {
            view.showMessage("Protocole valide.");
            view.getFrame().dispose();
            openProtocolInfoWindow(protocol);
        } else {
            view.showMessage("Protocole invalide.");
        }
    }

    /**
     * Ouvre une fenêtre d'informations sur le protocole
     *
     * @param protocol Le nom du protocole pour lequel afficher les informations
     */
    private void openProtocolInfoWindow(Object protocol) {
        String description = model.getDescriptionForProtocol(protocol);
        int menuName = model.getMenuForProtocol(protocol);
        int idProtocole = model.getIdForProtocol(protocol);
        ProtocolInfoView infoView = new ProtocolInfoView(description, menuName, idProtocole);
    }
}
