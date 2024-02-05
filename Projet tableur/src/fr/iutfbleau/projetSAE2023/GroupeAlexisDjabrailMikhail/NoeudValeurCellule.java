package fr.iutfbleau.projetSAE2023.GroupeAlexisDjabrailMikhail;
/**
 * La classe NoeudValeurCellule étend la classe NoeudValeur
 * Elle represente une feuille de l'arbre qui conttient une cellule
 *
 * @version 1.0
 * @author Alexis, Djabrail, Mikhail
 */
public class NoeudValeurCellule extends NoeudValeur{
    /**
     * cellule stocker dans le noeud
     */
    private Cellule valeur;
    /**
     * generalement 1 ou -1,
     * cette valeur permet de savoir si on doit simplement calculer la valeur de la cellule ou son oppose
     */
    private int multiplication;

    /**
     * Constructeur de la classe qui creer un Noeud en y stockant une cellule
     *
     * @param valeur la cellule du noeud
     */
	public NoeudValeurCellule(Cellule valeur){
        this.valeur = valeur;
        this.multiplication = 1;
    }

    /**
     * Constructeur de la classe qui creer un Noeud en y stockant une cellule.
     * Il permet en plus de multiplier la valeur de la cellule par une autre valeur lorsqu'on fait getValue().
     * Cela permet par exemple de calculer la valeur negative de la cellule.
     *
     * @param valeur la cellule du noeud
     * @param multiplication la valeur par laquelle la cellule sera multiplier (generalement 1 ou -1)
     */
    public NoeudValeurCellule(Cellule valeur, int multiplication){
        this.valeur = valeur;
        this.multiplication = multiplication;
    }

    /**
     * Permet de recuperer la valeur du noeud (la valeur de la cellule contenus dans le noeud)
     *
     * @return la valeur sous forme de decimal
     * @throws NumberFormatException si la valeur ne peut pas etre convertis en decimal
     */
    @Override
    public double getValue(){
        return this.valeur.getDouble() * this.multiplication;
    }

    /**
     * Ajoute une dependance vers une cellule a la cellule contenu dans le noeud
     *
     * @param reference la reference a ajouter
     */
    @Override
    public void addDependance(Cellule reference){
        if (this.valeur != null){
            this.valeur.addDependance(reference);
        }
    }

    /**
     * Retire une dependance a la cellule contenu dans le noeud
     *
     * @param reference la reference a retirer
     */
    @Override
    public void removeDependance(Cellule reference){
        if (this.valeur != null){
            this.valeur.removeDependance(reference);
        }
    }
}