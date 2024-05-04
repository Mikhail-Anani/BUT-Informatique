package com.example.application;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * La classe InterfaceGraphiqueJeu est une classe qui affichage le jeu. Elle utilise les donnees du model
 *
 * @version 1.0
 * @author Alexis, Djabrail, Mikhail
 */
public class InterfaceGraphiqueJeu extends View {
    /**
     * couleur du fond du jeu
     */
    private final int COULEUR_FOND = ContextCompat.getColor(this.getContext(), R.color.white);
    /**
     * couleur de la grille
     */
    private final int COULEUR_GRILLE = ContextCompat.getColor(this.getContext(), R.color.gris);
    /**
     * couleur du trait en cours de tracage
     */
    private final int COULEUR_TRAI_NON_VALIDE = ContextCompat.getColor(this.getContext(), R.color.grenat_transparent);
    /**
     * couleur des traits
     */
    private final int COULEUR_TRAIT = ContextCompat.getColor(this.getContext(), R.color.rouge);
    /**
     * couleur des croix
     */
    private final int COULEUR_CROIX = ContextCompat.getColor(this.getContext(), R.color.black);
    /**
     * couleur du score affiche
     */
    private final int COULEUR_SCORE = ContextCompat.getColor(this.getContext(), R.color.blue);
    /**
     * couleur de la croix que le joueur peut faire apparaitre avec le mode triche
     */
    private final int COULEUR_TRICHE = ContextCompat.getColor(this.getContext(), R.color.vert);
    /**
     * pinceau qui dessine tout le jeu
     */
    private Paint pinceau;
    /**
     * Model du jeu qui contient toutes les donnes necessaire a l'affichage
     */
    private Model model;

    /**
     * Constructeur qui initialise toutes les variables du jeu,
     * ou reprends celle qui existe si le joueur est en pleine partie
     * @param context se refererer au constructeur de View
     * @param attrs se refererer au constructeur de View
     */
    public InterfaceGraphiqueJeu(Context context, AttributeSet attrs){
        super(context, attrs);
        this.pinceau = new Paint();
        this.pinceau.setAntiAlias(true);
        this.pinceau.setStrokeWidth(20);
        this.pinceau.setTextSize(100);
        this.pinceau.setFakeBoldText(true);
    }

    /**
     * Attribut un model à l'interface graphique afin de savoir ce qu'il faut afficher
     * @param model le model du jeu a afficher
     */
    public void setModel(Model model){
        this.model = model;
    }

    /**
     * Cette methode permet de dessiner le jeu. On y dessine :
     * un fond blanc, une grille, la liste des traits (this.listeTrait), le trait en cours de tracage (this.traitActuel),
     * les croix qui se trouve dans le dictionnaire (this.dicoPoint), et le score (this.score)
     * @param canvas le suport sur lequel on dessine le jeu (se referer au onDraw de la classe View)
     */
    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Point tailleEcran = new Point(canvas.getWidth(), canvas.getHeight());
        if (this.model != null){

            Point emplacementCarte = this.model.getPositionMap();
            int espaceCroix = this.model.getEspaceCroix();
            int score = this.model.getScore();
            List<Trait> listeTrait = this.model.getListeTrait();
            Trait traitActuel = this.model.getTraitActuel();
            HashMap<Point, List<Trait>> dicoPoint = this.model.getDicoPoint();
            Point pointTriche = this.model.getProchainPoint();


            if (emplacementCarte == null){
                this.model.createCroix();
                this.model.validerTrait();
                this.model.recentrerMap();
                return;
            }

            this.pinceau.setColor(this.COULEUR_FOND);
            canvas.drawRect(0,0, tailleEcran.getX(), tailleEcran.getY(), this.pinceau);

            this.pinceau.setColor(this.COULEUR_GRILLE);
            float x,y;
            for (x=emplacementCarte.getX()%espaceCroix; x<=tailleEcran.getX(); x+=espaceCroix){
                canvas.drawLine(x,0, x, tailleEcran.getY(), this.pinceau);
            }
            for (y=emplacementCarte.getY()%espaceCroix; y<=tailleEcran.getY(); y+=espaceCroix){
                canvas.drawLine(0,y, tailleEcran.getX(), y, this.pinceau);
            }

            this.pinceau.setColor(this.COULEUR_TRAIT);
            for (Trait trait : listeTrait){
                if (trait != null){
                    Point depart = this.model.getCoordoneeInScreen(trait.getDepart());
                    Point arrivee = this.model.getCoordoneeInScreen(trait.getArrivee());
                    if (depart != null && arrivee != null) {
                        canvas.drawLine(depart.getX(), depart.getY(), arrivee.getX(), arrivee.getY(), this.pinceau);
                    }
                }
            }
            this.pinceau.setColor(this.COULEUR_TRAI_NON_VALIDE);
            if (traitActuel != null){
                Point depart = traitActuel.getDepart();
                Point arrivee = traitActuel.getArrivee();
                if (depart != null && arrivee != null){
                    depart = this.model.getCoordoneeInScreen(depart);
                    arrivee = this.model.getCoordoneeInScreen(arrivee);
                    canvas.drawLine(depart.getX(), depart.getY(), arrivee.getX(), arrivee.getY(), this.pinceau);
                }
            }

            this.pinceau.setColor(this.COULEUR_CROIX);
            for (Point croix : dicoPoint.keySet()){
                Point coordonneeCroix = this.model.getCoordoneeInScreen(croix);
                canvas.drawLine(coordonneeCroix.getX()-20, coordonneeCroix.getY(), coordonneeCroix.getX()+20, coordonneeCroix.getY(), this.pinceau);
                canvas.drawLine(coordonneeCroix.getX(), coordonneeCroix.getY()-20, coordonneeCroix.getX(), coordonneeCroix.getY()+20, this.pinceau);
            }

            this.pinceau.setColor(this.COULEUR_TRICHE);
            if (pointTriche != null){
                Point coordonneeCroix = this.model.getCoordoneeInScreen(pointTriche);
                canvas.drawLine(coordonneeCroix.getX()-20, coordonneeCroix.getY(), coordonneeCroix.getX()+20, coordonneeCroix.getY(), this.pinceau);
                canvas.drawLine(coordonneeCroix.getX(), coordonneeCroix.getY()-20, coordonneeCroix.getX(), coordonneeCroix.getY()+20, this.pinceau);
            }

            this.pinceau.setColor(this.COULEUR_SCORE);
            canvas.drawText(score+"", 50, 150, this.pinceau);
        }
    }
}
