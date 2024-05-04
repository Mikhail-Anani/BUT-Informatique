package com.example.application;

import android.os.Handler;
import android.util.Log;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Model {

    /////////////////////////////////////////////////////////////////// Attributs ///////////////////////////////////////////////////////////////////////////////
    /**
     * tailles des cases en pixel
     */
    private final int espaceCroix = 100;
    /**
     * point qui indique à quel coordonnees le joueur se situe sur la grille. (en pixel)
     * Ce point correspond au coin supérieur droit de l'ecran
     */
    private Point emplacementCarte;
    /**
     * liste des differents traits dessinee par le joueur.
     * C'est cette liste qui est utilisé pour dessiner les traits sur la grille
     */
    private List<Trait> listeTrait;
    /**
     * coordonnees du centre de la carte (en pixel)
     */
    private Point centreCarte;
    /**
     * ce dictionnaire contiens les donnees suivante:
     * clef = les croix du jeu
     * valeurs = pour chaqu'une des croix, liste des traits qui passe par cette croix
     */
    HashMap<Point,List<Trait>> dicoPoint;
    /**
     * Le trait qui est en train d'etre tracer (mais l'utilisateur n'a pas encore leve son doigt)
     */
    private Trait traitActuel;
    /**
     * Si le mode triche est actif, l'utilisateur peut voir quel point il a la possibilite de faire apparaitre
     */
    private Point prochainPoint;
    /**
     * Score = le nombre de traits traces
     */
    private int score;
    /**
     * Variable boolean qui permet au constructeur de savoir si on commence une nouvelle partie ou si on est en train de reprendre une partie qui avait deja commence.
     */
    private boolean partieEnCours = false;
    /**
     * Taille de la croix de depart.
     * la valeur indique le nombre de croix de chaques lignes.
     * (pour connaitre le nombre totale de croix au depart faire 12*(tailleCroix-1))
     */
    private int tailleCroix = 4;
    /**
     * Taille des traits ou nombre de croix qu'il faut pour tracer un trait
     */
    private int tailleTrait = 4;
    /**
     * Indique si la regle de prolongement de trait est active ou non
     */
    private boolean prolongementTrait = false;
    /**
     * Indique si le mode triche est actif ou non
     */
    private boolean modeTriche = false;
    /**
     * L'interface graphique du jeu
     */
    private InterfaceGraphiqueJeu interfaceGraphiqueJeu;

    /**
     * Constructeur qui initialise toutes les variables du jeu,
     * ou reprends celle qui existe si le joueur est en pleine partie
     */
    public Model(){
        this.centreCarte = new Point(0,0);
        if (!partieEnCours){
            this.emplacementCarte = null;
            this.listeTrait = new ArrayList<>();
            this.dicoPoint = new HashMap<>();
            this.score = 0;
        }
        else{
            this.remplirListe();
        }
    }

    /////////////////////////////////////////////////////////////////// Deplacement de la carte ///////////////////////////////////////////////////////////////////////////////

    /**
     * se deplace sur la grille par la translation d'un vecteur (en pixel)
     * @param vecteur vecteur translation
     */
    public void moveMap(Point vecteur){
        this.emplacementCarte.addVecteur(vecteur);
        this.interfaceGraphiqueJeu.invalidate();
    }

    /**
     * se deplace sur la grille à un point donner (en pixel)
     * @param position nouvel position
     */
    public void setPositionMap(Point position){
        this.emplacementCarte = position;
        this.interfaceGraphiqueJeu.invalidate();
    }

    /**
     * recentre la grille sur la croix centrale de depart. (de maniere animer)
     * Si vous n'aimer pas l'animation enlevez tout le bloc try et gardez ce qu'il y a dans le catch.
     */
    public void recentrerMap(){
        int frame = 30;
        int tempsFrame = 10;
        Point centre = new Point(this.interfaceGraphiqueJeu.getWidth()/2f+this.centreCarte.getX(), this.interfaceGraphiqueJeu.getHeight()/2f+this.centreCarte.getY());
        try{
            Handler handler = new Handler();
            Point vecteurDeplacement = this.emplacementCarte.getVecteur(centre);
            vecteurDeplacement.diviseVecteur(frame);
            int i;
            for (i=0; i<frame; i++){
                final int delay = i * tempsFrame;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        moveMap(vecteurDeplacement);
                    }
                }, delay);
            }
        }
        catch (NullPointerException e) {
            this.emplacementCarte = centre;
            this.interfaceGraphiqueJeu.invalidate();
        }
    }

    /////////////////////////////////////////////////////////////////// SET ///////////////////////////////////////////////////////////////////////////////

    /**
     * definis l'interface graphique du jeu (view) qui correspond à ce model
     * @param interfaceGraphiqueJeu nouvel interface graphique
     */
    public void setInterfaceGraphiqueJeu (InterfaceGraphiqueJeu interfaceGraphiqueJeu){
        this.interfaceGraphiqueJeu = interfaceGraphiqueJeu;
    }

    /**
     * modifie la variable partieEnCours
     * @param partieEnCours nouvelle valeur
     */
    public void setPartieEnCours(boolean partieEnCours){
        this.partieEnCours = partieEnCours;
    }

    /**
     * modifie la taille de la croix de depart
     * @param tailleCroix nouvelle taille
     */
    public void setTailleCroix(int tailleCroix){
        this.tailleCroix = tailleCroix;
    }

    /**
     * modifie la regle qui indique si on a le droit au prolongement de trait
     * @param prolongementTrait nouvel valeur
     */
    public void setProlongementTrait(boolean prolongementTrait){
        this.prolongementTrait = prolongementTrait;
    }

    /**
     * Active ou non le mode triche
     * @param triche nouvelle valeur
     */
    public void setTriche(boolean triche){
        this.modeTriche = triche;
    }

    /**
     * modifie le score
     * @param score nouveau score
     */
    public void setScore(int score){
        Log.v("test score", score+"");
        this.score = score;
    }

    /**
     * change le dictionnaire et la liste qui contiennent tous les traits et les croix de la partie
     * @param dicoPoint nouveau dictionnaire
     */
    public void setDicoPoint(HashMap<Point, List<Trait>> dicoPoint){
        this.dicoPoint = dicoPoint;
        Log.v("test dico", this.dicoPoint.toString());
        this.remplirListe();
    }

    /////////////////////////////////////////////////////////////////// GET ///////////////////////////////////////////////////////////////////////////////

    /**
     * recupere le dictionnaire qui contient toutes les croix et les traits qui passe par chaqu'une d'elles
     * @return le dictionnaire de croix et de traits
     */
    public HashMap<Point, List<Trait>> getDicoPoint(){
        return this.dicoPoint;
    }

    /**
     * recupere la liste des differents traits du jeu
     * @return Une liste contenant les traits du jeu
     */
    public List<Trait> getListeTrait(){
        return this.listeTrait;
    }

    /**
     * recupere le score
     * @return le score
     */
    public int getScore(){
        return this.score;
    }

    /**
     * recupere la position sur la grille (en pixel)
     * @return la position
     */
    public Point getPositionMap(){
        return this.emplacementCarte;
    }

    /**
     * recupere la taille d'une case de la grille (en pixel)
     * @return la taille d'une case de la grille (en pixel)
     */
    public int getEspaceCroix(){
        return this.espaceCroix;
    }

    /**
     * recupere le trait qui est en train d'etre tracé
     * @return le trait qui est en train d'etre tracé
     */
    public Trait getTraitActuel(){
        return this.traitActuel;
    }

    /**
     * recupere la position du point que le joueur peut faire apparaitre en tracant un trait au bon endroit
     * @return le point qui pourras etre trace prochainement
     */
    public Point getProchainPoint(){
        return this.prochainPoint;
    }

    /////////////////////////////////////////////////////////////////// TRAIT EN COURS DE TRACAGE ///////////////////////////////////////////////////////////////////////////////

    /**
     * fixe le point de depart du trait qui est en train d'etre tracer (le doigt vient d'etre poser)
     * @param depart position ou l'utilisateur a pose son doigt (en pixel par rapport à l'ecran)
     * cette position est ensuite convertis en unite de grille.
     */
    public void tracerDebutTrait(Point depart){
        Point departCroisement = this.getCroisementPlusProche(depart);
        this.traitActuel = new Trait(departCroisement);
    }

    /**
     * fixe le point d'arrivee du trait qui est en train d'etre tracer (le doigt est toujours pose sur l'ecran)
     * @param arrivee position du doigt de l'utilisateur (en pixel par rapport à l'ecran)
     * cette position est ensuite convertis en unite de grille.
     */
    public void tracerFinTrait(Point arrivee){
        Point arriveeCroisement = this.getCroisementPlusProche(arrivee);
        if (this.traitActuel != null){
            this.traitActuel.setArrivee(arriveeCroisement);
            Log.v("test tracer trait", this.traitActuel.toString());
        }
        this.interfaceGraphiqueJeu.invalidate();
    }

    /**
     * lorsque l'utilisateur leve son doigt cette methode est appellee.
     * Elle verifie si le trait est valide.modifie la liste des traits
     * Si c'est le cas, le trait est ajouter au dictionnaire et a la liste des traits.
     * Ensuite elle verifie si la partie est terminee cela envoie un popup "felicitation !"
     * Le popup est donc envoye a chaque fois que l'utilisateur construit un nouveau trait
     */
    public void validerTrait(){
        Point nouvelCroix = GestionTrait.isValide(this.traitActuel, this.dicoPoint, this.tailleTrait, this.prolongementTrait);
        if (nouvelCroix != null){
            this.score ++;
            this.listeTrait.add(this.traitActuel);
        }
        this.traitActuel = null;
        Point croixPossible = FinJeu.finJeu(this.dicoPoint, this.tailleTrait, this.prolongementTrait);
        if (croixPossible == null){
            Toast.makeText(this.interfaceGraphiqueJeu.getContext(), "Félicitation !", Toast.LENGTH_SHORT).show();
        }
        else if (modeTriche){
            this.prochainPoint = croixPossible;
        }
        this.interfaceGraphiqueJeu.invalidate();
    }

    /**
     * lorsque l'utilisateur met plusieurs doigt sur l'ecran cette methode est appellee.
     * Elle retire simplement l'affichage du trait en cours du dessin en lui attribuant la valeur null
     */
    public void annulerTrait(){
        this.traitActuel = null;
    }

    /////////////////////////////////////////////////////////////////// CALCUL DE COORDONNEES ///////////////////////////////////////////////////////////////////////////////

    /**
     * Cette methode permet de convertir une position en pixel par rapport à l'ecran en unite de grille.
     * @param point point en pixel par rapport à l'écran
     * @return point le plus proche en unite de grille
     */
    private Point getCroisementPlusProche(Point point){

        float coordonneeX = point.getX()-this.emplacementCarte.getX();
        float coordonneeY = point.getY()-this.emplacementCarte.getY();

        float xFinal = (float) ((int)coordonneeX / this.espaceCroix);
        float yFinal = (float) ((int)coordonneeY / this.espaceCroix);

        float resteX = coordonneeX % this.espaceCroix;
        float resteY = coordonneeY % this.espaceCroix;

        if (Math.abs(resteX) > this.espaceCroix/2) {
            if (resteX > 0){
                xFinal += 1f;
            }
            else{
                xFinal -= 1f;
            }
        }
        if (Math.abs(resteY) > this.espaceCroix/2) {
            if (resteY > 0){
                yFinal += 1f;
            }
            else{
                yFinal -= 1f;
            }
        }
        return new Point(xFinal, yFinal);
    }

    /**
     * Cette methode permet de convertir une coordonnes en unite de grille en pixel par rapport à l'ecran
     * @param point point en unite de grille
     * @return point en pixel par rapport à l'écran
     */
    public Point getCoordoneeInScreen(Point point){
        float x = point.getX()*this.espaceCroix+this.emplacementCarte.getX();
        float y = point.getY()*this.espaceCroix+this.emplacementCarte.getY();
        return new Point(x, y);
    }

    /////////////////////////////////////////////////////////////////// CROIX DE DEPART ///////////////////////////////////////////////////////////////////////////////

    /**
     * Ajoute des croix au dictionnaire (this.dicoCroix).
     * Cette methpode creer des croix qui se positionne en forme de croix autour du point (0,0).
     * La taille de la croix formée dépend de l'attribut tailleCroix
     * Cette methode ajuste egalement le centre de la grille (this.centreCarte) d'un demi carreau en fonction de si le nombre de croix par ligne est paire ou impaire.
     *
     */
    public void createCroix(){
        if (this.tailleCroix<2){
            this.tailleCroix = 2;
        }
        float px, py;
        if (this.tailleCroix%2 == 0){
            this.centreCarte = new Point(this.espaceCroix/2f, this.espaceCroix/2f);
            px = 1f-(float)(3*this.tailleCroix/2);
            py = -(float)(this.tailleCroix/2);
        }
        else{
            px = 1f-(float)(3*this.tailleCroix/2);
            py = -(float)(this.tailleCroix/2);
        }

        float x,y1=0, y2=0;
        for (x=0;x<=(3*(this.tailleCroix-1)); x++){
            if (x==0 || x==(3*(this.tailleCroix-1))){
                for(y2=0; y2<this.tailleCroix; y2++){
                    this.ajouterCroix(x+px,y2+py);
                }
                y2 --;
            }
            else if (x==(this.tailleCroix-1)){
                for(;y1>-this.tailleCroix;y1--, y2++){
                    this.ajouterCroix(x+px,y1+py);
                    this.ajouterCroix(x+px,y2+py);
                }
                y1++;
                y2--;
            }
            else if (x==(this.tailleCroix-1)*2){
                for(;y1<=0;y1++, y2--){
                    this.ajouterCroix(x+px,y1+py);
                    this.ajouterCroix(x+px,y2+py);
                }
                y1--;
                y2++;
            }
            else{
                this.ajouterCroix(x+px,y1+py);
                this.ajouterCroix(x+px,y2+py);
            }
        }
        for (Point croix : this.dicoPoint.keySet()){
            Log.v("test croix debut", croix.toString());
        }
    }

    /**
     * Cette methode permet d'ajouter une croix au dictionaire de croix (this.dicoPoint)
     * @param x absisse de la nouvelle croix en unite de grille
     * @param y ordonnee de la nouvelle croix en unite de grille
     */
    private void ajouterCroix(float x, float y){
        Point nouvelCroix = new Point(x,y);
        this.dicoPoint.put(nouvelCroix, new ArrayList<>());
    }

    /////////////////////////////////////////////////////////////////// UTILE ///////////////////////////////////////////////////////////////////////////////

    /**
     * Permet de mettre à jour la liste des traits (this.listeTrait)
     * Cette methode parcours le dictionnaire (this.dicoCroix) et ajoute les differents traits rencontré dans la liste des traits.
     */
    private void remplirListe(){
        this.listeTrait = new ArrayList<>();
        for (Point croix : this.dicoPoint.keySet()) {
            List<Trait> listeTrait = this.dicoPoint.get(croix);
            this.ajouterListeTrait(listeTrait);
        }
    }

    /**
     * Cette methode ajoute une liste de traits à la liste des traits de l'objet (this.listeTrait)
     * Bien sur la methode n'ajoute pas les traits déjà présent dans la liste
     * @param listeTrait liste des traits à ajouter
     */
    private void ajouterListeTrait(List<Trait> listeTrait){
        for (Trait trait : listeTrait){
            if (!this.listeTrait.contains(trait)){
                this.listeTrait.add(trait);
            }
        }
    }
}
