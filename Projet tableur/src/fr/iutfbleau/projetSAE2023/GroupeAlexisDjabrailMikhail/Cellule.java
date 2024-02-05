package fr.iutfbleau.projetSAE2023.GroupeAlexisDjabrailMikhail;

import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import java.awt.Color;


/**
 * La classe Cellule permet de stocker et faire toutes les operations qu'une cellule a besoins.
 * Une cellule est considerer comme l'extension d'un JLabel. elle peut donc afficher une valeur.
 *
 * @version 1.0
 * @author Alexis, Djabrail, Mikhail
 */
public class Cellule extends JLabel{

	/**
	 * Message affiche dans la cellule en cas de reference circulaire
	 */
	private final static String PROBLEM_CIRCULAIRE = "BOUCLE :";
	/**
	 * Message affiche dans la cellule si la formule est valide mais que le calcul est impossible
	 */
	private final static String PROBLEM_CALCUL = "CALCUL IMPOSSIBLE";
	/**
	 * Message affiche dans la cellule si la formule est invalide
	 */
	private final static String PROBLEM_FORMULE = "FORMULE INVALIDE";
	/**
	 * Couleur de la cellule si la formule est valide mais impossible a calculer
	 */
	private final static Color COULEUR_CALCUL = new Color(244,143,177);
	/**
	 * Couleur de la cellule si la formule est invalide
	 */
	private final static Color COULEUR_FORMULE = new Color(229,115,115);
	/**
	 * Couleur par default de la cellule
	 */
	private final static Color COULEUR_DEFAULT = Color.WHITE;


	/**
	 * Couleur de la formule
	 */
	private Color couleur;
	/**
	 * Dictionnaire qui permet de faire des correspondance entre les noms des cellules et les objets cellules
	 */
	private DicoCellule dico;
	/**
	 * Nom de la cellule
	 */
	private String nom;
	/**
	 * Valeur de la cellule (reultat de sa formule)
	 */
	private String valeur;
	/**
	 * Contenu de la cellule (ce qui a ete ecrit dans la barre de saisis: ca peut etre une formule)
	 */
	private String contenu;
	/**
	 * Formule de la cellule stocker sous forme d'arbre
	 */
	private Arbre formule;
	/**
	 * Liste des cellules qui dependent directement de celle ci
	 */
	private List<Cellule> listDependance;

	/**
	 * indique si le contenu de la cellule represente une formule ou non
	 */
	private boolean isFormule;
	/**
	 * indique si la formule contient une erreur de syntaxe ou non
	 */
	private boolean isFormuleError;
	/**
	 * Contient toutes les boucle (reference circulaire) dans laquelle est impliquee la cellule
	 */
	private List<Boucle> listeBoucle;
	/**
	 * indique si la formule de la cellule est calculable ou non
	 */
	private boolean isCalculImpossible;



	/**
	 * constructeur de la cellule
	 * initialise le style et les donnees interne necessaire a son fonctionement
	 * 
	 * @param nom nom de la cellule
	 * @param dico dictionnaire qui permet de faire les correspondance entre les noms des cellules et l'objet qui correspond
	 */
	public Cellule(String nom, DicoCellule dico){
		this.nom = nom;
		this.dico = dico;
		this.listDependance = new ArrayList<>();
		this.listeBoucle = new ArrayList<>();
		this.isFormule = false;
		this.contenu = "";
		this.couleur = Cellule.COULEUR_DEFAULT;
		this.setValeur();
		this.setOpaque(true);
		this.setBackground(this.couleur);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

	/**
	 * Permet de modifier le contenus d'une cellule
	 * cela met a jour les references, et le resultat afficher, et la couleur de la cellule
	 * 
	 * @param contenu nouveau contenu de la cellule
	 */
	public void setContenu(String contenu){
		this.removeAllDependance();
		this.removeAllBoucle();
		this.contenu = contenu;
		this.setAttributIsFormule();
		if (this.isFormule){
			this.createFormule();
		}
		this.addAllDependance();
		this.setListeBoucle(this, new ArrayList<Cellule>());
		this.updateValeur(this, new ArrayList<Cellule>());
	}

	/**
	 * Permet de recuperer le contenu de la cellule
	 * 
	 * @return le contenu de la cellule (et pas le resultat)
	 */
	public String getContenu(){
		return this.contenu;
	}

	/**
	 * Permet de recuperer le resultat d'une formule
	 * 
	 * @return le resultat de la cellule (et pas le contenu)
	 */
	public String getValeur(){
		return this.valeur;
	}

	/**
	 * Permet de recuperer le nom de la cellule
	 * 
	 * @return le nom de la cellule (ex : "A1")
	 */
	public String getNom(){
		return this.nom;
	}

	/**
	 * Permet de recuperer la couleur de la cellule
	 * 
	 * @return la couleur de la cellule
	 */
	public Color getColor(){
		return this.couleur;
	}

	/**
	 * Permet de recuperer la liste des boucles (probleme circulaire) dans laquelle est implique une cellule
	 * 
	 * @return liste des boucles
	 */
	public List<Boucle> getListeBoucle(){
		return this.listeBoucle;
	}

	/************************************PARTIE PLUS COMPLEXE (Methodes utilisees que depuis une autre cellule ou un noeud)******************************/

	
	/**
	 * setValeur() permet de mettre a jour le resultat d'une formule,
	 * non pas en recalculant la valeur de l'arbre, mais en verifiant avec les attribut boolean si il n'y a pas d'erreur
	 * Cela met a jour la couleur et le texte (resultat) de la cellule
	 */
	private void setValeur(){
		this.couleur = Cellule.COULEUR_DEFAULT;
		if (this.isFormule){
			if (this.formule == null || this.isFormuleError){
				this.valeur = Cellule.PROBLEM_FORMULE;
				this.couleur = Cellule.COULEUR_FORMULE;
			}
			else if (this.listeBoucle.size() > 0){
				StringBuilder valeurBoucle = new StringBuilder(Cellule.PROBLEM_CIRCULAIRE);
				for (Boucle boucle : this.listeBoucle){
					valeurBoucle.append(" ");
					valeurBoucle.append(boucle.getNumBoucle());
				}
				this.valeur = valeurBoucle.toString();
				this.couleur = Boucle.superposeColor(this.listeBoucle);
			}
			else if (this.isCalculImpossible){
				this.valeur = Cellule.PROBLEM_CALCUL;
				this.couleur = Cellule.COULEUR_CALCUL;
			}
		}
		else{
			this.valeur = this.contenu;
		}
		this.setText(this.valeur);
		this.setBackground(this.couleur);
	}

	/**
	 * getDouble permet de recuperer le resultat d'une formule sous forme de decimal (double)
	 * 
	 * @return le resultat sous forme de double
	 * @throws NumberFormatException en cas de probleme de convertion du resultat en double
	 */
	public double getDouble(){
		return Double.parseDouble(this.valeur);
	}

	/**
	 * updateValeur permet de lire le resultat de la formule de l'arbre et de mettre a jour la valeur de la cellule
	 * et les differents attributs boolean qui indique si il y a une erreur.
	 * Cette methode est recursive et ne met pas a jour seulement la valeur de cette cellule
	 * mais aussi de celle qui dependent directement ou indirectement de cette cellule
	 * 
	 * @param origine la cellule source. La premiere cellule sur laquelle ont applique cette methode
	 * @param referenceAEviter liste vide au debut, qui vas ce remplir au fur et a mesure de la recursion
	 * par les cellules deja impliquee dans des boucles de references.
	 * Ce parametre permet d'eviter d'etre bloquer en visitant une boucle qui depend de l'origine
	 * 
	 */
	protected void updateValeur(Cellule origine, List<Cellule> referenceAEviter){
		try{
			if (this.listeBoucle.size() == 0){
				this.isCalculImpossible = false;
				this.isFormuleError = false;
				this.valeur = ""+this.formule.getValue(); // cette ligne peut renvoyer des exceptions
			}
		}
		catch (NullPointerException e){
			this.isFormuleError = true;
		}
		catch (ArithmeticException e){
			this.isCalculImpossible = true;
		}
		catch (NumberFormatException e){
			this.isCalculImpossible = true;
		}
		this.setValeur();
		for (Cellule dependance : this.listDependance){
			if (referenceAEviter.contains(dependance) == false){
				if (dependance.getListeBoucle().size() > 0){
					referenceAEviter.add(dependance);
				}
				if (dependance != origine){
					dependance.updateValeur(origine, referenceAEviter);
				}
			}
		}
	}

	/**
	 * createFormule permet de creer l'arbre qui contiendra la formule.
	 * Si la formule est incorrect et que l'arbre ne peut pas entierement se construire, il sera null
	 * 
	 */
	private void createFormule(){
		List<String> args = Argument.splitFormule(this.contenu);
		boolean flag = true;
		int i, taille = args.size();
		this.formule = new Arbre(this.dico);

		for (i=0; i<taille && flag; i++){
			flag = this.formule.add(args.get(i));
		}
		if (flag==false){
			this.formule = null;
		}
		else if (this.formule.isComplet() == false){
			this.formule = null;
		}
	}

	/**
	 * setAttributIsFormule permet de verifier si la formule est syntaxiquement correcte.
	 * Le resultat de cette verification est stocke dans l'attribut boolean "isFormule"
	 */
	private void setAttributIsFormule(){
		String premierArgument = Argument.getFirstArgument(this.contenu, new IntObjet(0));
		if (premierArgument == null){
			this.isFormule = false;
		}
		if (Calcul.getOperator(premierArgument) != null || this.dico.getCellule(premierArgument) != null){
			this.isFormule = true;
		}
		else{
			try {
            	Double.parseDouble(premierArgument);
	            this.isFormule = true;
	        }
	        catch (NumberFormatException e) {
				char premierCaractere = premierArgument.charAt(0);
            	if (Calcul.getOperator(premierCaractere) == Operateur.SOUSTRACTION){
                	premierArgument = premierArgument.substring(1);
					if (this.dico.getCellule(premierArgument) != null){
						this.isFormule = true;
					}
					else{
						this.isFormule = false;
					}
				}
				else{
					this.isFormule = false;
				}
	        }
		}
	}

	/**
	 * permet de verifier recursivement les dependance entre les cellules et de verifier
	 * si il y a un probleme de reference circulaire
	 * Le resultat de cette verification est stocker dans
	 * les attributs listeBoucle des cellules impliquees dans une boucle de reference circulaire
	 * 
	 * @param origine cellule dont on veut verifier les references circulaire
	 * @param referenceAEviter liste vide au debut, qui vas ce remplir au fur et a mesure de la recursion
	 * par les cellules deja impliquee dans des boucles de references.
	 * Ce parametre permet d'eviter d'etre bloquer en visitant une boucle qui depend de l'origine
	 */
	private List<Boucle> setListeBoucle(Cellule origine, List<Cellule> referenceAEviter){
		List<Boucle> resultat = new ArrayList<>();
		for (Cellule dependance : this.listDependance){
			if (dependance == origine){
				Boucle problemCirculaire = new Boucle();
				problemCirculaire.addCellule(this);
				this.listeBoucle.add(problemCirculaire);
				resultat.add(problemCirculaire);
			}
			if (referenceAEviter.contains(dependance) == false){
				if (dependance.getListeBoucle().size() > 0){
					referenceAEviter.add(dependance);
				}
				if (dependance != origine){
					List<Boucle> listeSousBoucle = dependance.setListeBoucle(origine, referenceAEviter);
					for (Boucle sousBoucle : listeSousBoucle){
						sousBoucle.addCellule(this);
						this.listeBoucle.add(sousBoucle);
						resultat.add(sousBoucle);
					}
				}
			}
		}
		return resultat;
	}


	/**
	 * permet de supprimer toutes les boucles  de reference circulaire qui implique cette cellule.
	 * Cela supprime les boucles a la fois dans cette cellule mais aussi dans les cellules impliquee dans ces boucles
	 */
	private void removeAllBoucle(){
		int i, taille=this.listeBoucle.size();
		while (this.listeBoucle.size()>0){
			this.listeBoucle.get(0).destroy();// l'element 0 est retire de la liste listeBoucle
		}
	}

	/**
	 * permet de supprimer une boucle de reference circulaire a la cellule
	 * @param ancienne boucle a supprimer
	 * @return true si la boucle a ete retire, false sinon
	 */
	public boolean removeBoucle(Boucle ancienne){
		return this.listeBoucle.remove(ancienne);
	}

	/**
	 * permet d'ajouter aux cellules mentionnees dans la formule des reference vers cette cellule
	 */
	private void addAllDependance(){
		if (this.isFormule && this.formule != null){
			this.formule.addDependance(this);
		}
	}

	/**
	 * permet de supprimer aux cellules mentionnees dans la formule les reference vers cette cellule
	 */
	private void removeAllDependance(){
		if (this.isFormule && this.formule != null){
			this.formule.removeDependance(this);
		}
	}

	/**
	 * permet d'ajouter une cellule dans la liste de dependance de cette cellule.
	 * dans l'attribut "listeDependance"
	 * 
	 * @param nouvelle cellule a ajouter a la liste de dependance
	 * @return true si l'ajout a fonctionne false sinon
	 */
	protected boolean addDependance(Cellule nouvelle){
		for (Cellule dependance : this.listDependance){
			if (nouvelle == dependance){
				return true;
			}
		}
		return this.listDependance.add(nouvelle);
	}

	/**
	 * permet de supprimer une cellule a la liste de dependance de cette cellule.
	 * dans l'attribut "listeDependance"
	 * 
	 * @param ancienne cellule a supprimer de la liste de dependance
	 * @return true si la suppression a fonctionne false sinon
	 */
	protected boolean removeDependance(Cellule ancienne){
		return this.listDependance.remove(ancienne);
	}
}