package fr.iutfbleau.projetSAE2023.GroupeAlexisDjabrailMikhail;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JTextField;

/**
 * La classe EvenementsBarre implémente l'interface ActionListener
 * pour gérer les événements d'action sur une barre de texte dans l'interface utilisateur.
 * Cette classe est conçue pour écouter et traiter les actions sur une barre de texte, telles que
 * l'entrée de l'utilisateur, et mettre à jour la cellule actuellement sélectionnée 
 *
 * @version 1.0
 * @author Alexis, Djabrail, Mikhail
 */
public class EvenementsBarre implements ActionListener {
    /**
     * La cellule actuellement sélectionnée dans l'interface utilisateur
     * Cette référence est utilisée pour mettre à jour le contenu de la cellule en fonction des actions de l'utilisateur
     */

    private Cellule celluleActuel = null;

    /**
     * Constructeur de la classe EvenementsBarre
     * Initialise un nouveau gestionnaire d'événements pour la barre
     */
    public EvenementsBarre(){
    }

    /**
     * Définit la cellule actuellement active pour cet écouteur d'événements
     * Lorsque l'utilisateur effectue des actions dans la barre de texte, cette cellule sera mise à jour
     *
     * @param celluleActuel La cellule à définir comme actuellement active
     */
    public void setCelluleActuel(Cellule celluleActuel){
        this.celluleActuel = celluleActuel;
    }

    /**
     * Méthode appelée lorsqu'une action est effectuée
     * Écoute les événements d'action sur la barre de texte, met à jour le contenu de la cellule
     * actuellement active et change sa couleur pour refléter la sélection
     *
     * @param e L'événement d'action qui se produit
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField barre = (JTextField) e.getSource();
        String nouveauContenu = barre.getText();
        if (this.celluleActuel != null){
            this.celluleActuel.setContenu(nouveauContenu); // modifie egalement la couleur de la cellule
            //this.celluleActuel.setBackground(EvenementsCellule.COULEUR_SELECTION);
        }
    }

}