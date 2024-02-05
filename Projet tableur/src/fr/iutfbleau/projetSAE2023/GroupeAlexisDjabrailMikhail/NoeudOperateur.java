package fr.iutfbleau.projetSAE2023.GroupeAlexisDjabrailMikhail;

/**
 * La classe NoeudOperateur permet de stocker un operateur dans un arbre dont le but est de stocker une formule mathematique
 * elle contient deux fils qui seront les deux valeurs sur lequel l'operateur seras applique
 *
 * @version 1.0
 * @author Alexis, Djabrail, Mikhail
 */
public class NoeudOperateur extends Noeud{
    /**
     * l'operateur stocker dans le noeud
     */
	private Operateur valeur;
    /**
     * le premier fils sur lequel l'operateur seras applique
     */
	private Noeud fils1 = null;
    /**
     * le deuxieme fils sur lequel l'operateur seras applique
     */
	private Noeud fils2 = null;

    /**
     * Constructeur du noeud qui stocke dans le noeud l'operateur
     * 
     * @param valeur operateur a stocker
     */
	public NoeudOperateur(Operateur valeur){
        this.valeur = valeur;
    }

    /**
     * Permet de calculer le resultat des deux fils du noeud par l'operateur.
     * resultat = fils1 operateur fils2
     * 
     * @return le resultat du calcul
     * @throws NullPointerException si il manque au moins un fils a ce noeud
     * @throws ArithmeticException si l'operateur est invalide ou si il y a division par 0
     * @throws NumberFormatException si la valeur des fils ne peut pas etre convertis en double
     */
    @Override
    public double getValue(){
        return Calcul.calcul(this.valeur, this.fils1.getValue(), this.fils2.getValue());
    }

    /**
     * Permet de recupereer le premier fils
     * 
     * @return le noeud du premier fils, il peut etre null
     */
    public Noeud getFils1(){
        return this.fils1;
    }

    /**
     * Permet de recupereer le deuxieme fils
     * 
     * @return le noeud du deuxieme fils, il peut etre null
     */
    public Noeud getFils2(){
        return this.fils2;
    }

    /**
     * Permet d'ajouter une reference vers une cellule a une autre cellule stocke potentiellement plus bas dans l'arborescence
     * 
     * @param origine la reference a ajouter
     */
    @Override
    public void addDependance(Cellule origine){
        if (this.fils1 != null){
            this.fils1.addDependance(origine);
        }
        if (this.fils2 != null){
            this.fils2.addDependance(origine);
        }
    }

    /**
     * Permet de retirer une reference vers une cellule aux cellule stocke plus bas dans l'arborescence
     * 
     * @param origine la reference a retirer
     */
    @Override
    public void removeDependance(Cellule origine){
        if (this.fils1 != null){
            this.fils1.removeDependance(origine);
        }
        if (this.fils2 != null){
            this.fils2.removeDependance(origine);
        }
    }

    /**
     * permet d'ajouter un noeud a celui ci
     * 
     * @param fils le noeud a ajouter
     * @return true si le noeud a correctement ete ajoute, false sinon
     */
    @Override
    public boolean add(Noeud fils){
    	if (this.fils1 == null){
            this.fils1 = fils;
            return true;
        }
        if (this.fils2 == null){
            this.fils2 = fils;
            return true;
        }
        return false;
    }
}