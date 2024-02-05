package fr.iutfbleau.projetSAE2023.GroupeAlexisDjabrailMikhail;


/**
 * La classe Calcul permet d'effectuer des calculs et de faire des correspondance entre les caracteres et les operateurs.
 * Toutes les methodes de cette classe sont statiques
 *
 * @version 1.0
 * @author Alexis, Djabrail, Mikhail
 */
public class Calcul{
    
    /**
     * Permet de renvoyer l'operateur correspondant a une chaine de caractere
     *
     * @param operator La chaine de caractere a analyser
     * @return l'operateur correspondant ou null si la chaine ne correspond a aucun operateur
     */
    public static Operateur getOperator(String operator){
        switch(operator){
            case "+":
                return Operateur.ADDITION;
            case "-":
                return Operateur.SOUSTRACTION;
            case "*":
                return Operateur.MULTIPLICATION;
            case "/":
                return Operateur.DIVISION;
            default:
                return null;
        }
    }
    /**
     * Permet de renvoyer l'operateur correspondant a un caractere
     *
     * @param operator Le caractere a analyser
     * @return l'operateur correspondant ou null si le caractere ne correspond a aucun operateur
     */
    public static Operateur getOperator(char operator){
        switch(operator){
            case '+':
                return Operateur.ADDITION;
            case '-':
                return Operateur.SOUSTRACTION;
            case '*':
                return Operateur.MULTIPLICATION;
            case '/':
                return Operateur.DIVISION;
            default:
                return null;
        }
    }

    /**
     * Permet de calculer le resultat de deux nombre et d'un operateur:
     * resultat = v1 operateur v2 
     *
     * @param operator L'operateur qui va permette de faire la calcul
     * @param v1 Le premier nombre
     * @param v2 Le deuxieme nombre
     * @return le resultat des deux nombre par l'operateur
     * @throws ArithmeticException si l'operateur est invalide ou si il y a division par 0
     */
    public static double calcul(Operateur operator, double v1, double v2){
        switch(operator){
            case ADDITION:
                return v1 + v2;
            case SOUSTRACTION:
                return v1 - v2;
            case MULTIPLICATION:
                return v1 * v2;
            case DIVISION:
                if (v2 == 0){
                    throw new ArithmeticException("Erreur, Division par 0 : "+v1+operator+v2);
                }
                return v1 / v2;
            default:
                throw new ArithmeticException("Erreur, Operateur invalide : "+operator);
        }
    }
}