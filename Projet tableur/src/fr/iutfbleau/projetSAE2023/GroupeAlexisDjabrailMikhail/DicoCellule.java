package fr.iutfbleau.projetSAE2023.GroupeAlexisDjabrailMikhail;

import java.util.*;

/**
 * La classe DicoCellule sert de dictionnaire pour stocker et gérer des associations
 * entre des clés de type String et des Cellules. Elle utilise un HashMap en interne pour gérer
 * ces associations. Cette classe peut être utilisée pour stocker des références à des cellules
 * et les récupérer facilement à l'aide de clés
 *
 * @version 1.0
 * @author Alexis, Djabrail, Mikhail
 */

public class DicoCellule{

	/**
     * Le dictionnaire interne utilisé pour stocker les associations entre les clés de type String
     * et les Cellules 
     */

    private HashMap<String,Cellule> dico;

	/**
     * Constructeur de la classe DicoCellule
     * Initialise un nouveau dictionnaire de cellules vide
     */

	public DicoCellule(){
		this.dico = new HashMap<>();
	}

	/**
     * Ajoute une association clé-Cellule dans le dictionnaire
     * Si une cellule est déjà associée à la clé, elle est remplacée par la nouvelle valeur
     *
     * @param clef La clé sous laquelle la cellule doit être stockée
     * @param valeur La cellule à stocker dans le dictionnaire
     * @return La cellule précédemment associée à la clé, ou null si aucune cellule n'était associée
     */

	public Cellule put(String clef, Cellule valeur){
		return this.dico.put(clef, valeur);
	}

	/**
     * Récupère une cellule du dictionnaire en utilisant sa clé
     * 
     * @param clefCellule La clé de la cellule à récupérer
     * @return La cellule associée à la clé spécifiée, ou null si aucune cellule n'est associée à cette clé
     */

	public Cellule getCellule(String clefCellule){
		return this.dico.get(clefCellule);
	}
}