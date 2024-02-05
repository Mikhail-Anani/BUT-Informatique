package fr.iutfbleau.projetSAE2023.GroupeAlexisDjabrailMikhail;
/**
 * La classe abstraite NoeudValeur étend la classe Noeud
 * et représente un type de noeud spécialisé.
 * Ce type de noeud est cense etre une feuille et ne dois pas avoir de fils
 *
 * @version 1.0
 * @author Alexis, Djabrail, Mikhail
 */
public abstract class NoeudValeur extends Noeud{
    
 /**
     * Empêche l'ajout de noeuds fils à ce type de noeud
     * Étant donné que les instances de NoeudValeur ne doivent pas avoir de fils,
     * cette méthode est surchargée pour retourner toujours false
     *
     * @param fils Le noeud à ajouter comme fils 
     * @return false toujours, indiquant que l'ajout n'est pas possible
     */

    @Override
    public boolean add(Noeud fils){
    	return false;
    }


    /**
     * Renvoie la valeur de ce noeud
     * Cette méthode est abstraite et doit être implémentée par les sous-classes pour
     * retourner la valeur spécifique contenue dans le noeud
     *
     * @return La valeur calculée de ce noeud
     */
    @Override
    public abstract double getValue();

    /**
     * Ajoute une dépendance à la cellule de ce noeud
     * Cette méthode est abstraite et doit être implémentée par les sous-classes
     *
     * @param origine La cellule à ajouter en tant que dépendance
     */
    @Override
    public abstract void addDependance(Cellule origine);


    /**
     * Supprime une dépendance a la cellule contenu dans ce noeud
     * Cette méthode est abstraite et doit être implémentée par les sous-classes
     *
     * @param origine La reference a supprimée
     */
    @Override
    public abstract void removeDependance(Cellule origine);
}