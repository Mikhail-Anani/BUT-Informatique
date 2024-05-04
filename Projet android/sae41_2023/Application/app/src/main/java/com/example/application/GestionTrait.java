package com.example.application;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Cette classe est un modele qui verifie si on un trait est valide ou non
 *
 * @version 1.0
 * @author Alexis, Djabrail, Mikhail
 * */
public class GestionTrait {

    /**
     * Cette methode verifie si un trait peut etre tracer ou non.
     * Si c'est le cas elle appelle la methode ajouterTrait() pour modifier les donnees de la partie (dicoPoint)
     * @param trait le trait à vérifié (avec des coordonnées exprimée en unite de grille)
     * @param dicoPoint le dictionnaire qui contiens toutes les croix et tous les traits de la carte
     * @param tailleTrait la taille que devrait faire le trait ou le nombre de croix déjà existante sur lequel il repose
     * @param autoriserProlongementTrait boolean qui indique si les prolongement de trait sont autorisé ou non.
     * @return les coordonnées du nouveaux point formée par le trait (en unite de grille) si il est valide.
     * Si le trait n'est pas valide return null
     * */
    public static Point isValide(Trait trait, HashMap<Point,List<Trait>> dicoPoint, int tailleTrait, boolean autoriserProlongementTrait){
        if (trait != null) {
            Log.v("test verification trait trace", trait.toString());
            if (trait.haveLongueur(tailleTrait)) {
                Point depart = trait.getDepart();
                int directionX = (int) (trait.getArrivee().getX() - depart.getX());
                int directionY = (int) (trait.getArrivee().getY() - depart.getY());
                Point vecteurDirection = new Point(directionX, directionY);
                vecteurDirection.direction();

                Point nouveauPoint = GestionTrait.isValide2(depart, vecteurDirection, tailleTrait, autoriserProlongementTrait, dicoPoint);
                Log.v("test verification trait trace", trait+"    "+vecteurDirection+"    "+nouveauPoint);
                if (nouveauPoint != null){
                    GestionTrait.ajouterTrait(trait, vecteurDirection, tailleTrait, dicoPoint);
                    return nouveauPoint;
                }
            }
        }
        return null;
    }

    /**
     * Cette methode ajoute un trait dans le dictionnaire. cela ajoute egalement la nouvelle croix du trait.
     * Attention a bien verifier le trait avant d'appeller cette methode.
     * @param trait le trait à ajouter dans le dictionnaire (en unite de grille)
     * @param vecteurDirection la direction du trait (distance qui separe chaque croisement du trait)
     * @param tailleTrait la taille que devrait faire le trait ou le nombre de croix déjà existante sur lequel il repose
     * @param dicoPoint le dictionnaire qui contient les donnees de la partie a modifier
     * */
    private static void ajouterTrait(Trait trait, Point vecteurDirection, int tailleTrait, HashMap<Point,List<Trait>> dicoPoint){
        int i;
        Point pointActuel = trait.getDepart().copie();
        for (i=0; i<=tailleTrait; i++){
            List<Trait> traitDupoint = dicoPoint.get(pointActuel);
            if (traitDupoint == null){
                traitDupoint = new ArrayList<>();
                traitDupoint.add(trait);
                Log.v("test verification trait trace", pointActuel.toString());
                dicoPoint.put(pointActuel.copie(), traitDupoint);
            }
            else{
                traitDupoint.add(trait);
            }
            pointActuel.addVecteur(vecteurDirection);
        }
    }

    /**
     * Verifie si un trait peut etre tracer ou non.
     * @param depart point de depart du trait a verifier
     * @param vecteurDirection vecteur direction du trait (distance qui separe chaque croisement du trait)
     * @param dicoPoint le dictionnaire qui contiens toutes les croix et tous les traits de la carte
     * @param tailleTrait la taille du trait
     * @param autoriserProlongementTrait boolean qui indique si les prolongement de trait sont autorisé ou non.
     * @return les coordonnées du nouveaux point formée par le trait (en unite de grille) si il est valide.
     * Si le trait n'est pas valide return null
     * */
    public static Point isValide2(Point depart, Point vecteurDirection, int tailleTrait, boolean autoriserProlongementTrait, HashMap<Point,List<Trait>> dicoPoint){
        List<Trait> listeTraitCroise = new ArrayList<>();
        Point nouveauPoint = null;
        int i;
        int compteurCroix = 0;
        int max = tailleTrait;
        Point pointActuel = depart.copie();

        if (autoriserProlongementTrait == false){
            Point directionOppose = vecteurDirection.copie();
            directionOppose.inverseVecteur();
            pointActuel.addVecteur(directionOppose);
            max += 2;
        }

        for (i=0; i<=max; i++){
            List<Trait> TraitDupoint = dicoPoint.get(pointActuel);
            if (TraitDupoint != null){
                if (FinJeu.remplirListe(listeTraitCroise, TraitDupoint) == false){
                    return null; // on est en train de prolonger un trait
                }
                if (autoriserProlongementTrait || (i!=0 && i!=max)) {
                    compteurCroix ++;
                }
            }
            else if (autoriserProlongementTrait || (i!=0 && i!=max)) {
                nouveauPoint = pointActuel.copie();
            }
            pointActuel.addVecteur(vecteurDirection);
        }

        if (compteurCroix == tailleTrait){
            return nouveauPoint;
        }
        return null;
    }
}
