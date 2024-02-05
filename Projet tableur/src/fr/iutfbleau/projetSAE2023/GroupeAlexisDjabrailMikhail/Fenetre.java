package fr.iutfbleau.projetSAE2023.GroupeAlexisDjabrailMikhail;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;

/**
 * La classe Fenetre crée une interface utilisateur graphique pour une feuille de calcul
 * similaire à Excel. Elle utilise Swing pour créer une fenêtre contenant un tableau
 * La fenêtre est configurée pour occuper les deux tiers de l'écran de l'utilisateur
 *
 * @version 1.0
 * @author Alexis, Djabrail, Mikhail
 */

public class Fenetre {

    
    /**
     * Le tableau de cellules affiché dans cette fenêtre
     * Cet attribut représente la structure de données principale manipulée par l'utilisateur
     * dans l'interface graphique. Il est configuré selon les dimensions spécifiées lors de la création
     * de l'instance de Fenetre
     */

    private Tableau tableau;
    private Barre barre;

    /**
     * Constructeur de la classe Fenetre.
     * Crée et configure une fenêtre contenant un tableau de cellules.
     * La taille de la fenêtre est fixée à deux tiers de la taille de l'écran de l'utilisateur.
     *
     * @param nbCaseX Le nombre de colonnes du tableau.
     * @param nbCaseY Le nombre de lignes du tableau.
     */

    public Fenetre(int nbCaseX, int nbCaseY) {
        JFrame page = new JFrame();
        page.setTitle("Feuille Excel");
        page.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.barre = new Barre();
        this.tableau = new Tableau(nbCaseX, nbCaseY, this.barre);

        // récuperer la dimension de l'écran
        Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
        int longueur = tailleMoniteur.width * 2 / 3;
        int hauteur = tailleMoniteur.height * 2 / 3;
        // régler la taille de JFrame à 2/3 la taille de l'écran
        page.setSize(longueur, hauteur);

        page.setLocationRelativeTo(null);
        // ajouts à la fenetre
        page.add(this.barre, BorderLayout.NORTH);
        page.add(this.tableau, BorderLayout.CENTER);
        page.setVisible(true);

    }

    /**
     * Renvoie le tableau contenu dans cette fenêtre.
     *
     * @return Le tableau de la fenêtre.
     */

    public Tableau getTableau(){
        return this.tableau;
    }
}