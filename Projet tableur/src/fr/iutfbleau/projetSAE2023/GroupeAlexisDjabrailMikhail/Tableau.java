package fr.iutfbleau.projetSAE2023.GroupeAlexisDjabrailMikhail;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.Color;

/**
 * La classe Tableau permet de creer un tableau remplis de cellule.
 * Le tableau peut ensuite etre ajouter a une fenetre comme un JPanel.
 * Il contient une ligne avec le nom des colonnes de A a Z, une colonne avec le nom des lignes de 1 a +inf
 * Et l'interieur est remplis d'objet de type cellule visuellement comme des JLabels.
 *
 * @version 1.0
 * @author Alexis, Djabrail, Mikhail
 */
public class Tableau extends JPanel {

    /**
     * Dictionnaire d'association entre nom des cellules et les objets cellule du tableau
     */
    private DicoCellule dico;
    
    /**
     * Constructeur de la classe Tableau qui creer le visuel et les evenements associes a chaque cellules
     * 
     * @param nbCaseX nombre de cellule par lignes (de 0 a 26)
     * @param nbCaseY nombre de cellule par colonnes (nombre entier positif)*
     * @param barre objet barre dans lequelle les formules des cellules vont etre affiche
     */
    public Tableau(int nbCaseX, int nbCaseY, Barre barre) {
        if (nbCaseX>26){
            nbCaseX=26;
        }

        this.dico = new DicoCellule();
        // Initialisation de la grille
        this.setLayout(new GridLayout(nbCaseY+1, nbCaseX+1));

        // Ecouteur d'evenement
        EvenementsCellule evenement = new EvenementsCellule(barre);

        int x,y;
		for (y=0; y<=nbCaseY; y++){
			for (x=0; x<=nbCaseX; x++){
                char c = (char) ('A' + x-1);
                if (x==0 && y==0){
                    // Ajout d'un JLabel vide en haut à gauche
                    this.add(new JLabel("", SwingConstants.CENTER));
                }
                else if (y==0){
                    // Ajout des étiquettes pour les colonnes
                    this.add(new JLabel(String.valueOf(c), SwingConstants.CENTER));
                }
                else if (x==0){
                    // Ajout des étiquettes pour les lignes
                    this.add(new JLabel(String.valueOf(y), SwingConstants.CENTER));
                }
                else{
                    // Ajout des cellules éditables
                    String clefCellule = String.valueOf(c) + y;
                    Cellule celluleActuel = new Cellule(clefCellule, this.dico);
                    celluleActuel.addMouseListener(evenement);
                    this.dico.put(clefCellule, celluleActuel);
                    this.add(celluleActuel); 
                }
			}
		}
    }

    /**
     * Permet de recuperer un objet cellule du tableau en connaissant son nom
     * 
     * @param clefCellule nom de la cellule (nom colonne + nom ligne. ex: "A1" ou "D24")
     * @return l'objet de type Cellule correspondant ou null si le nom ne correspond a aucune cellule du tableau
     */
    public Cellule getCellule(String clefCellule){
		return this.dico.getCellule(clefCellule);
	}
}
