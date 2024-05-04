package com.example.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Cette classe est un modele qui verifie si on arrive à la fin du jeu
 *
 * @version 1.0
 * @author Alexis, Djabrail, Mikhail
 * */
public class FinJeu {

    /**
     * constante qui contiens les 8 vecteurs directions des traits possible dans le jeu.
     * */
    private static Point[] listeDirection = new Point[]{
            new Point(-1f, -1f),
            new Point(-1f, 0f),
            new Point(-1f, 1f),
            new Point(0f, -1f),
            new Point(0f, 1f),
            new Point(1f, -1f),
            new Point(1f, 0f),
            new Point(1f, 1f)
    };

    /**
     * Cette methode verifie si il est encore possible de tracer un trait.
     * @param dicoPoint dictionnaire qui contiens pour chaque croix la liste des traits qui passe par ce point.
     * @param tailleTrait la taille que doivent faire les traits ou le nombre de croix qu'il faut pour pouvoir en tracer un
     * @param autoriserProlongementTrait boolean qui indique si la partie autorise ou non le prolongement de trait
     * @return un point qu'il est encore possible de jouer si la partie n'est pas fini, sinon null
     * */
    public static Point finJeu(HashMap<Point, List<Trait>> dicoPoint, int tailleTrait, boolean autoriserProlongementTrait){
        for (Point croix : dicoPoint.keySet()){
            for (Point direction : FinJeu.listeDirection){
                Point pointPossible = GestionTrait.isValide2(croix, direction,tailleTrait,autoriserProlongementTrait,dicoPoint);
                if (pointPossible != null){
                    return pointPossible;
                }
            }
        }
        return null;
    }

    /**
     * permet d'ajouter à une liste de trait des traits qui provienne d'une autre liste.
     * Attention le remplissage s'arrete des qu'on rencontre un trait à ajouter deja present dans la liste à remplir.
     * @param listeARemplir liste a remplir (qui n'est pas forcement vide)
     * @param elementAAjouter liste de trait à ajouter dans listeARemplir
     * @return false si on a rencontrer un trait commun au deux liste, true si le remplissage à fonctionné jusqu'au bout
     * */
    public static boolean remplirListe(List<Trait> listeARemplir, List<Trait> elementAAjouter){
        for (Trait trait : elementAAjouter){
            if (listeARemplir.contains(trait)){
                return false;
            }
            listeARemplir.add(trait);
        }
        return true;
    }
}
