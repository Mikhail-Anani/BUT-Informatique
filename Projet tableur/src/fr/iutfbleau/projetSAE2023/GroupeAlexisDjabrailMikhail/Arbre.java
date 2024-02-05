package fr.iutfbleau.projetSAE2023.GroupeAlexisDjabrailMikhail;

import java.util.*;

/**
 * La classe Arbre représente une structure d'arbre pour gérer des noeuds
 * Cette classe peut être utilisée pour construire et manipuler un arbre de noeuds 
 * qui peuvent dépendre de différentes cellules
 *
 * @version 1.0
 * @author Alexis, Djabrail, Mikhail
 */

public class Arbre{

    /**
     * Le noeud racine de l'arbre
     * Il s'agit du point de départ de l'arbre à partir duquel tous les autres noeuds
     * sont ajoutés
     */

    private Noeud racine = null;

    /**
     * Cette pile permet de savoir, a la creation de l'arbre, le noeud ou il faut ajouter des elements
     * Cela permet d'eviter de parcourir l'arbre en profondeur a chaque ajout de noeud
     * et de verifier rapidement si l'arbre est complet
     */

    private Deque<NoeudOperateur> noeudActuel;

    /**
     * Le dictionnaire de cellules
     * Ce dictionnaire permet de creer des noeuds contenant des cellule en conaissant simplement leurs noms
     */

    private DicoCellule dico;

    /**
     * Constructeur de la classe Arbre
     * Initialise un nouvel arbre avec un dictionnaire de cellules spécifié
     *
     * @param dico Le dictionnaire de cellules associé à cet arbre
     */

    public Arbre(DicoCellule dico){
        this.dico = dico;
        this.noeudActuel = new ArrayDeque<>();
    }

    /**
     * Renvoie le noeud racine de l'arbre
     *
     * @return Le noeud racine de l'arbre, (peut retourner null si l'arbre est vide)
     */

    public Noeud getRacine(){
        return this.racine;
    }


    /**
     * Ajoute un noeud avec une valeur specifique à l'arbre 
     * Si l'arbre est vide, définit ce noeud comme racine. Sinon, ajoute le noeud
     * au noeud racine
     *
     * @param element La valeur du noeud à ajouter sous forme de String (nom de cellule, entier ou operateur)
     * @return true si l'élément a été ajouté avec succès, false sinon
     */
    public boolean add(String element){
        Noeud noeudFils = this.createNoeud(element);
        if (this.racine == null){
            this.racine = noeudFils;
            if (noeudFils instanceof NoeudOperateur){
                this.noeudActuel.push((NoeudOperateur) noeudFils);
            }
            return true;
        }
        else{
            if (this.noeudActuel.isEmpty()){
                return false;
            }
            NoeudOperateur noeudPere = this.noeudActuel.pop();
            if (noeudPere.add(noeudFils)){
                if (noeudPere.getFils2() == null){
                    this.noeudActuel.push(noeudPere);
                }
                if (noeudFils instanceof NoeudOperateur){
                    this.noeudActuel.push((NoeudOperateur) noeudFils);
                }
                return true;
            }
            return false;
        }
    }


    /**
     * Renvoie la valeur calculée de l'arbre
     *
     * @return La valeur calculée de l'arbre
     * @throws NullPointerException Si certains noeud de l'arbre sont null
     * @throws ArithmeticException Si une erreur arithmétique se produit lors des calculs
     * @throws NumberFormatException Si dans une feuille de l'arbre,
     * une cellule contient une valeur inconvertissable en double
     */

    public double getValue(){
            return this.racine.getValue();
    }

    /**
     * Ajoute la cellule spécifiée en tant que dépendance à toutes les feuilles de l'arbre qui contienne une cellule
     *
     * @param origine La cellule à ajouter en tant que dépendance.
     */


    public void addDependance(Cellule origine){
        if (this.racine != null){
            this.racine.addDependance(origine);
        }
    }


    /**
     * Supprime la cellule spécifiée des dépendances de tous les feuilles de l'arbre qui contienne une cellule
     *
     * @param origine La cellule à supprimer des dépendances
     */

    public void removeDependance(Cellule origine){
        if (this.racine != null){
            this.racine.removeDependance(origine);
        }
    }

    /**
     * Crée un nouveau noeud avec la valeur spécifiée
     * Selon la nature de la valeur, crée différents types de noeuds
     * NoeudOperateur si la valeur est un operateur
     * NoeudValeurDouble si la valeur est un reel
     * NoeudValeurCellule si la valeur est un nom de cellule
     *
     * @param valeur La valeur du noeud à créer
     * @return Un nouveau noeud ou null si la création échoue
     */

    public Noeud createNoeud(String valeur){
        Operateur operator = Calcul.getOperator(valeur);
        if (operator != null){
            return new NoeudOperateur(operator);
        }
        try{
            double valeurDouble = Double.parseDouble(valeur);
            return new NoeudValeurDouble(valeurDouble);
        }
        catch(NumberFormatException e){
            char premierCaractere = valeur.charAt(0);
            if (Calcul.getOperator(premierCaractere) == Operateur.SOUSTRACTION){
                valeur = valeur.substring(1);
                Cellule reference = this.dico.getCellule(valeur);
                if (reference != null){
                    return new NoeudValeurCellule(reference, -1);
                }
                return null;
            }
            else{
                Cellule reference = this.dico.getCellule(valeur);
                if (reference != null){
                    return new NoeudValeurCellule(reference);
                }
                return null;
            }
        }
    }

    /**
     * Verifie si l'arbre est remplis. il ne faut pas qu'il y ait de Noeuds sans fils.
     * Chaque branche doit se terminer par des feuille
     *
     * @return true si l'arbre est complet ou vide, false sinon
     */

    public boolean isComplet(){
        if (this.noeudActuel.isEmpty()){
            return true;
        }
        NoeudOperateur noeudActuel = this.noeudActuel.pop();
        if (noeudActuel.getFils2() != null){
            return true;
        }
        return false;
    }
}