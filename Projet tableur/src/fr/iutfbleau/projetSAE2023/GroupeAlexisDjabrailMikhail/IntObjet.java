package fr.iutfbleau.projetSAE2023.GroupeAlexisDjabrailMikhail;

/**
 * La classe IntObjet sert de classe Integer mais avec cette classe,
 * on a la possibilite de modifier la valeur de l'entier dans une methode
 * et de recuperer l'entier ainsi modifie a l'exterieur de la methode.
 * Comme cela etait possible en C
 *
 * @version 1.0
 * @author Alexis, Djabrail, Mikhail
 */
public class IntObjet{

    /**
     * L'entier interne
     */
    private int valeur;

    /**
     * Constructeur de la classe IntObjet
     * Initialise un nouvel entier
     * @param valeur la valeur a mettre
     */
    public IntObjet(int valeur){
        this.valeur = valeur;
    }

    /**
     * Donne une nouvelle valeur a l'entier
     *
     * @param valeur Valeur prise par l'entier
     */
    public void setValeur(int valeur){
        this.valeur = valeur;
    }

    /**
     * Recupere la valeur de l'entier
     *
     * @return la valeur de l'entier
     */
    public int getValeur(){
        return this.valeur;
    }


}