package fr.iutfbleau.projetSAE2023.GroupeAlexisDjabrailMikhail;

import java.util.*;
import java.awt.Color;

/**
 * La classe Boucle permet de stocker une les differentes cellules appartenant a une meme boucle de references circulaire.
 *
 * @version 1.0
 * @author Alexis, Djabrail, Mikhail
 */
public class Boucle{
    /**
     * Entier qui s'incremente a chaque creation de boucle afin que chaque boucle ait un numero unique
     */
    private static int numBoucleGeneral = 0;

    /**
     * Numero idenfiant la boucle. unique pour chaque boucle
     */
    private int numBoucle;
    /**
     * Couleur de la boucle / des cellules appartenant a la boucle (pour mieux les identifier).
     */
    private Color couleur;
    /**
     * Liste des cellules appartenant a la boucle
     */
    private List<Cellule> listeCelluleInBoucle;


    /**
     * Constructeur qui attribut un identifiant unique et une couleur aleatoire a la boucle.
     * La liste des cellules appartenant a la boucle est initialement vide
     */
    public Boucle(){
        Boucle.numBoucleGeneral ++;
        this.numBoucle = Boucle.numBoucleGeneral;
        this.couleur = new Color((int)(Math.random()*150+100), (int)(Math.random()*150+100), (int)(Math.random()*150+100));
        this.listeCelluleInBoucle = new ArrayList<>();
    }

    /**
     * Permet d'ajouter une cellule a la boucle
     * @param cellule cellule a ajouter
     *@return si la cellule peut etre ajoute
     */
    public boolean addCellule(Cellule cellule){
        return this.listeCelluleInBoucle.add(cellule);
    }

    /**
     * Permet de detruire la boucle en supprimant a toutes les cellules de la boucle la reference vers cette boucle
     */
    public void destroy(){
        int i, taille = this.listeCelluleInBoucle.size();
        for (i=0; i<taille; i++){
            this.listeCelluleInBoucle.get(i).removeBoucle(this);
        }
    }

    /**
     * Permet de recuperer l'identifiant unique de la boucle
     * @return l'identifiant
     */
    public int getNumBoucle(){
        return this.numBoucle;
    }

    /**
     * Permet de recuperer la couleur de la boucle
     * @return la couleur de la boucle
     */
    public Color getCouleur(){
        return this.couleur;
    }

    /**
     * Cette methode permet de creer une nouvelle couleur qui est un mixe des couleurs des boucles donnees en argument
     * Cette methode peut etre utilise lorsqu'une cellule appartient a plusieurs boucles
     * Ainsi elle garderas une nuance de couleur de chaqu'une des boucle auxquelle elle appartient  
     * @param listeBoucle la liste des boucles dont on veut mixer les couleurs
     * @return la couleur creer
     */
    public static Color superposeColor(List<Boucle> listeBoucle){
        if (listeBoucle.size() < 0 || listeBoucle == null){
            throw new IllegalArgumentException("l'argument listeBoucle est vide ou null");
        }
        double rouge = 0.0;
        double vert = 0.0;
        double bleu = 0.0;
        double ratio = (1.0 / (double)listeBoucle.size());

        for (Boucle boucle : listeBoucle){
            Color couleur = boucle.getCouleur();
            rouge += ((double)couleur.getRed() * ratio);
            vert += ((double)couleur.getGreen() * ratio);
            bleu += ((double)couleur.getBlue() * ratio);
        }

        return new Color((int)rouge, (int)vert, (int)bleu);
    }

    @Override
    public String toString(){
        StringBuilder resultat = new StringBuilder();
        for (Cellule cellule : this.listeCelluleInBoucle){
            resultat.append(cellule.getNom());
            resultat.append(" ");
        }
        return resultat.toString();
    }
}