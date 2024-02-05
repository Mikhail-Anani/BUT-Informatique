package fr.iutfbleau.projetSAE2023.GroupeAlexisDjabrailMikhail;

import javax.swing.*;
import java.awt.*;

/**
 * La classe Barre représente une barre d'interface utilisateur dans une application
 * graphique Swing, spécifiquement conçue pour afficher et modifier des formules liées à des cellules
 * Elle se compose d'un champ de texte pour saisir ou afficher des formules et d'une légende pour indiquer
 * à quelle cellule la formule est associée
 *
 * @version 1.0
 * @author Alexis, Djabrail, Mikhail
 */
public class Barre extends JPanel {

   /**
     * Le champ de texte pour saisir ou afficher les formules
     * Permet à l'utilisateur d'entrer ou de visualiser des formules associées à une cellule spécifique
     */
    private JTextField champsFormule;

    /**
     * La légende indiquant à quelle cellule la formule affichée dans le champ de texte est associée
     */
    private JLabel legende;

    /**
     * Gestionnaire d'événements pour la barre
     * Traite les actions réalisées dans le champ de texte, comme la saisie de formules
     */
    private EvenementsBarre evenement;

    /**
     * Constante définissant le début du message affiché dans la légende
     */
    private final static String DEBUT_MESSAGE = "Formule";

    /**
     * Constante définissant la fin du message affiché dans la légende
     */
    private final static String FIN_MESSAGE = " : ";


    /**
     * Constructeur de la classe Barre
     * Initialise une nouvelle barre avec un champ de texte et une légende
     */
    public Barre() {
        this.legende = new JLabel(Barre.DEBUT_MESSAGE + Barre.FIN_MESSAGE);
        this.champsFormule = new JTextField();

        JPanel conteneurDuChamps = new JPanel(new BorderLayout());
        conteneurDuChamps.add(this.champsFormule, BorderLayout.CENTER);

        this.setLayout(new BorderLayout());
        this.add(this.legende, BorderLayout.WEST);
        this.add(conteneurDuChamps, BorderLayout.CENTER);

        this.evenement = new EvenementsBarre();
        this.champsFormule.addActionListener(evenement);
    }


    /**
     * Définit la cellule actuellement sélectionnée dans la barre
     * Met à jour le champ de texte et la légende pour refléter le contenu et le nom de la cellule
     *
     * @param cellule La cellule à afficher et modifier dans cette barre
     */
    public void setCellule(Cellule cellule){
        this.champsFormule.setText(cellule.getContenu());
        this.evenement.setCelluleActuel(cellule);
        this.legende.setText(Barre.DEBUT_MESSAGE + " de " + cellule.getNom() + Barre.FIN_MESSAGE);
    }
}
