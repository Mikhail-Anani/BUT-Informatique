package fr.iutfbleau.projetSAE2023.GroupeAlexisDjabrailMikhail;
/**
 * La classe NoeudValeurDouble étend la classe NoeudValeur
 * Elle represente une feuille de l'arbre qui conttient un reel
 *
 * @version 1.0
 * @author Alexis, Djabrail, Mikhail
 */
public class NoeudValeurDouble extends NoeudValeur{
    /**
     * Reel stocker dans le noeud
     */
    private double valeur;
    /**
     * Constructeur de la classe qui stooooocke un reel dans le noeud
     *
     * @param valeur reel a stocker
     */
	public NoeudValeurDouble(Double valeur){
        this.valeur = valeur;
    }

    /**
     * Recupere la valeur du reel du noeud
     *
     * @return valeur du noeud
     */
    @Override
    public double getValue(){
        return this.valeur;
    }

    /**
     * Ne fait rien
     *
     * @param origine ce que vous voulez car la methode ne fait rien
     */
    @Override
    public void addDependance(Cellule origine){
        // ne fait rien
    }

    /**
     * Ne fait rien
     *
     * @param origine ce que vous voulez car la methode ne fait rien
     */
    @Override
    public void removeDependance(Cellule origine){
        // ne fait rien
    }
}