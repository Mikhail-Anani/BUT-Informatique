package fr.iutfbleau.projetSAE2023.GroupeAlexisDjabrailMikhail;

/**
 * La classe Noeud represente un Noeud dans un arbre.
 * Elle est abstraite pour permettre plusieurs type de noeud
 *
 * @version 1.0
 * @author Alexis, Djabrail, Mikhail
 */

public abstract class Noeud{
	 /**
     * Ajoute un noeud fils à ce noeud ou plus bas dans l'arborescence si ce noeud a deja 2 fils
     * Cette méthode est abstraite et doit être implémentée par les sous-classes
     *
     * @param fils Le noeud à ajouter comme fils
     * @return true si le noeud a été ajouté avec succès, false sinon
     */
	public abstract boolean add(Noeud fils);

    /**
     * Renvoie la valeur calculée de ce noeud
     * Cette méthode est abstraite et doit être implémentée par les sous-classes
     *
     * @return La valeur calculée de ce noeud
     */
     public abstract double getValue();

    /**
     * Ajoute une dépendance à ce noeud et dans les noeuds plus bas dans l'arborescence si c'est possible
     * Cette méthode est abstraite et doit être implémentée par les sous-classes
     *
     * @param origine La cellule à ajouter en tant que dépendance
     */
     public abstract void addDependance(Cellule origine);

    /**
     * Supprime une dépendance de ce noeud et au noeud plus bas dans l'arborescence
     * Cette méthode est abstraite et doit être implémentée par les sous-classes
     *
     * @param origine La cellule dont la dépendance doit être supprimée
     */
     public abstract void removeDependance(Cellule origine);
}