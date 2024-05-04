package com.example.application;

import java.io.Serializable;

/**
 * La classe Trait permet de stocker les caracteristiques d'un segment. point de depart et d'arrivee
 *
 * @version 1.0
 * @author Alexis, Djabrail, Mikhail
 */
public class Trait implements Serializable {
    /**
     * Cette constante permet de serialiser l'objet point
     * */
    private static final long serialVersionUID = 1L;
    /**
     * Le point de depart du trait
     * */
    private Point depart;
    /**
     * Le point d'arrivee du trait
     * */
    private Point arrivee;

    /**
     * Ce constructeur definis un trait avec un point de depart mais un point d'arrivee null.
     * @param depart point de depart du trait
     * */
    public Trait(Point depart){
        this.depart = depart;
        this.arrivee = null;
    }
    /**
     * Ce constructeur definis un trait avec un point de depart et un point d'arrivee.
     * @param depart point de depart du trait
     * @param arrivee point d'arrivee du trait
     * */
    public Trait(Point depart, Point arrivee){
        this.depart = depart;
        this.arrivee = arrivee;
    }
    /**
     * Cette methode modifie le point d'arrivee du trait
     * @param arrivee nouveau point d'arrivee du trait
     * */
    public void setArrivee(Point arrivee){
        this.arrivee = arrivee;
    }

    /**
     * Cette methode recupere le point d'arrivee du trait
     * @return Le point d'arrivee du trait
     * */
    public Point getArrivee(){
        return this.arrivee;
    }

    /**
     * Cette methode recupere le point de depart du trait
     * @return Le point de départ du trait
     * */
    public Point getDepart(){
        return this.depart;
    }

    /**
     * Cette methode verifie si le trait est horizontal, vertical ou oblique à 45.
     * Elle verifie egalement si le trait à la bonne longueur.
     * Attention: la longueur calculé dans cette methode est la valeur la plus grande entre dx et dy (ce n'est pas fait avec pythagore).
     * Ex: un trait qui a pour longueur dx=3 et dy=4, auras une longueur de 4 et non de 5
     * @param longueur La longueur à vérifier
     * @return true si le trait fait la bonne longueur et qu'il est vertical, horizontale ou oblique à 45, false sinon.
     * */
    public boolean haveLongueur(int longueur){
        if (this.depart == null || this.arrivee == null){
            return false;
        }
        if (Math.abs(this.depart.getX()-this.arrivee.getX()) == longueur){
            if (Math.abs(this.depart.getY()-this.arrivee.getY()) == longueur || this.depart.getY()-this.arrivee.getY() == 0){
                return true;
            }
        }
        else if (this.depart.getX()-this.arrivee.getX() == 0 && Math.abs(this.depart.getY()-this.arrivee.getY()) == longueur) {
            return true;
        }
        return false;
    }

    /**
     * Renvoie les caracteristique du Trait sous la forme "5.0 11.7; 5.0 -4.7"
     * @return une chaine de caractere avec les caracteristiques du trait
     * */
    @Override
    public String toString(){
        StringBuilder texte = new StringBuilder();
        if (this.depart != null){
            texte.append(this.depart.toString());
        }
        else{
            texte.append("null");
        }
        texte.append("; ");
        if (this.arrivee != null){
            texte.append(this.arrivee.toString());
        }
        else{
            texte.append("null");
        }
        return texte.toString();
    }
}
