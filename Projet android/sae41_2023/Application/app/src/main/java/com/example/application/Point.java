package com.example.application;

import java.io.Serializable;
import java.util.Objects;

/**
 * La classe Point permet de representer un point (x,y) ou un vecteur (dx, dy)
 * On y retrouve des methodes de calcule de coordonnees.
 * Cette classe implemente Serializable
 *
 * @version 1.0
 * @author Alexis, Djabrail, Mikhail
 */
public class Point implements Serializable {

    /**
    * Cette constante permet de serialiser l'objet point
    * */
    private static final long serialVersionUID = 1L;
    /**
     * x represente l'absisse:
     * Ca peut representer une coordonnee si l'objet est utilise comme un point
     * Ou une distance si l'objet est utilise comme un vecteur
     * */
    private float x;
    /**
     * y represente l'ordonee:
     * Ca peut representer une coordonnee si l'objet est utilise comme un point
     * Ou une distance si l'objet est utilise comme un vecteur
     * */
    private float y;

    /**
     * Le constructeur permet de definir x, et y.
     * @param x l'absisse
     * @param y l'ordonee
     * */
    public Point(float x, float y){
        this.x = x;
        this.y = y;
    }

    /**
     * setCoordonne permet de remplacer les ancienne coordonees x, et y par des nouvelles.
     * @param x nouvel absisse
     * @param y nouvel ordonee
     * */
    public void setCoordoonee(float x, float y){
        this.x = x;
        this.y = y;
    }

    /**
     * Permet de recuperer l'absisse x de l'objet
     * @return la valeur de l'absisse x
     * */
    public float getX(){
        return this.x;
    }
    /**
     * Permet de recuperer l'ordonnee y de l'objet
     * @return la valeur de l'absisse x
     * */
    public float getY(){
        return this.y;
    }

    /**
     * Permet de recuperer le vecteur qui separe deux points.
     * Cet objet est considerer comme le point de depart
     * Le point passer en argument est considerer comme le point d'arriver
     * @param destination le deuxieme point
     * @return le vecteur qui separe les deux points.
     * */
    public Point getVecteur(Point destination){
        return new Point(destination.getX()-this.x, destination.getY()-this.y);
    }

    /**
     * Permet de modifier ce vecteur pour reduire sa norme
     * @param diviseur le nombre par lequel ont veut diviser la norme du vecteur
     * */
    public void diviseVecteur(float diviseur){
        this.x /= diviseur;
        this.y /= diviseur;
    }

    /**
     * change un vecteur de sens en conservant sa direction et sa norme
     * */
    public void inverseVecteur(){
        this.x = -this.x;
        this.y = -this.y;
    }

    /**
     * change un vecteur en lui attribuant sa direction elementaire (N,NE,E,SE,S,SO,O,NO).
     * La nouvelle abscicces et ordonnee du vecteur seras donc soit -1 soit 0 ou soit 1.
     * */
    public void direction(){
        if (this.x > 0) {
            this.x = 1;
        } else if (this.x < 0) {
            this.x = -1;
        }
        if (this.y > 0) {
            this.y = 1;
        } else if (this.y < 0) {
            this.y = -1;
        }
    }

    /**
     * Permet de modifier la position du point en effectuant une translation avec un vecteur
     * @param vecteur le vecteur translation
     * */
    public void addVecteur(Point vecteur){
        this.x += vecteur.getX();
        this.y += vecteur.getY();
    }

    /**
     * Permet de copier ce point ou vecteur sans que les modifications apportee sur l'un impacte l'autre
     * @return le point copier
     * */
    public Point copie(){
        return new Point(this.getX(), this.getY());
    }

    /**
     * Permet d'effectuer la moyenne de deux vecteur.
     * @param vecteur1 Le premier vecteur
     * @param vecteur2 Le deuxième vecteur
     * @return le vecteur moyen
     * */
    public static Point moyenneVecteur(Point vecteur1, Point vecteur2) {
        float moyenneX = (vecteur1.getX() + vecteur2.getX()) / 2;
        float moyenneY = (vecteur1.getY() + vecteur2.getY()) / 2;
        return new Point(moyenneX, moyenneY);
    }

    /**
     * Affiche les coordonnes d'un point ou vecteur sous la forme "19.66 42.0"
     * @return la chaine de caractere formee
     * */
    @Override
    public String toString(){
        return this.x+" "+this.y;
    }

    /**
     * Verifie si deux Point ou vecteur on les memes coordonnees
     * @param o l'objet à comparer avec cet objet
     * @return true si ils ont les memes coordonnees, false sinon
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || this.getClass() != o.getClass()){
            return false;
        }
        Point point = (Point) o;
        return this.x == point.getX() && this.y == point.getY();
    }

    /**
     * permet de renvoyer un code unique pour chaque points de coordonnes differentes
     * @return le code unique (sous forme d'entier)
     * */
    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }
}
