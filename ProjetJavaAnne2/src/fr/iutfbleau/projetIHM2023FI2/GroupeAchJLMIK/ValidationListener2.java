package fr.iutfbleau.projetIHM2023FI2.GroupeAchJLMIK;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * ActionListener pour le bouton de validation dans l'interface utilisateur.
 * Réagit aux événements de bouton de validation et traite les protocoles.
 *
 * @version 1.1
 * @author Mikhail Anani,Achok Samedi, Jean-Luc Nelet
 * 
 */
public class ValidationListener2 implements ActionListener {
    private CamenbertView view;
    private CamenbertModel model;
    private String idProt;
    private ArrayList<String> actions=new ArrayList<String>();
    private String BonneAction;


     /**
     * Constructeur de ValidationListener
     *
     * @param view   La vue de l'application
     * @param model  Le modèle de l'application
     */
    public ValidationListener2(CamenbertView view, CamenbertModel model) {
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
        String protocol = view.getProtocolInput();

        if (model.checkProtocol(protocol)) {
            view.showMessage("Protocole valide.");
            this.idProt=model.getIdProtocol(protocol);
            openCamenbertInfoWindow(protocol);
            view.getFrame().dispose();
        } else {
            view.showMessage("Protocole invalide.");
        }
    }
    /**
     * Ouvre une fenêtre d'informations sur le protocole
     *
     * @param protocol Le nom du protocole pour lequel afficher les informations
     */
    private void openCamenbertInfoWindow(String protocol) {
        ArrayList<Double> proportion=model.getActions(idProt,this.actions);
        for(String e: this.actions){
        }
        BonneAction=model.getBonneAction(idProt);
        CamenbertInfoView1 infoView = new CamenbertInfoView1(proportion,this.actions,this.BonneAction,idProt);
    }
    
}
