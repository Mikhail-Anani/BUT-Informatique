package com.example.application;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Cette permet de detecter les differents geste tactile et d'executer des actions en fonction
 *
 * @version 1.0
 * @author Alexis, Djabrail, Mikhail
 */
public class EvenementDoigt implements View.OnTouchListener{

    /**
     * Le model qui gere tout le jeu
     */
    private Model jeu;
    /**
     * variable qui connais le nombre de doigt posé sur l'ecran
     */
    private int nbDoigts;
    /**
     * position du premier doigt (en pixel par rapport à l'écran)
     */
    private Point position1;
    /**
     * position du deuxieme doigt (en pixel par rapport à l'écran)
     */
    private Point position2;
    /**
     * classe qui permet de détecter les clics
     */
    private GestureDetector gestureDetector;
    /**
     * classe qui permet d'executer d'autres actions en cas de clics
     */
    private EvenementClic listener;

    /**
     * constructeur qui permet de stocker l'interface graphique du jeu afin de le changer en fonction des gestes tactiles
     * @param jeu Le modèle représentant le jeu associé à l'interface graphique
     */
    public EvenementDoigt(Model jeu){
        this.jeu = jeu;
        this.listener = new EvenementClic(this.jeu);
        this.gestureDetector= new GestureDetector(this.listener);
    }

    /**
     * methode qui permet de: se deplacer sur la grille, la recentrer ou tracer un trait. en fonction du geste tactile
     * @param v view sur laquel le geste est effectuer (se référer à la methode onTouch de View.OnTouchListener)
     * @param event evenement (se référer à la methode onTouch de View.OnTouchListener)
     * @return true
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (this.gestureDetector.onTouchEvent(event)){
            return true;
        }
        int index = event.getActionIndex();
        int action = event.getActionMasked();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                this.updatePosition(event);
                this.jeu.tracerDebutTrait(this.position1);
                this.nbDoigts = 1;
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                this.jeu.annulerTrait();
                this.nbDoigts++;
                this.updatePosition(event);
                break;

            case MotionEvent.ACTION_MOVE:
                if (this.nbDoigts == 1 && this.jeu.getTraitActuel() != null){
                    this.jeu.tracerFinTrait(new Point(event.getX(0), event.getY(0)));
                }
                else if (this.nbDoigts == 2) {
                    Point vecteurDoigt1 = new Point(event.getX(0) - position1.getX(), event.getY(0) - position1.getY());
                    Point vecteurDoigt2 = new Point(event.getX(1) - position2.getX(), event.getY(1) - position2.getY());
                    this.updatePosition(event);
                    this.jeu.moveMap(Point.moyenneVecteur(vecteurDoigt1, vecteurDoigt2));
                    Log.v("test doigt", "1:"+vecteurDoigt1.toString()+"   2:"+vecteurDoigt2.toString());
                }
                break;

            case MotionEvent.ACTION_POINTER_UP:
                this.nbDoigts--;
                break;

            case MotionEvent.ACTION_UP:
                if (this.jeu.getTraitActuel() != null){
                    this.jeu.validerTrait();
                }
                break;
        }
        return true;
    }

    /**
     * methode qui met a jour les positions des deux doigts (this.position1 et this.position2)
     * @param event evenement qui permet de lire les positions des doigts (se référer à la methode onTouch de View.OnTouchListener)
     */
    private void updatePosition(MotionEvent event){
            this.position1 = new Point(event.getX(0), event.getY(0));
            if (this.nbDoigts > 1){
                this.position2 = new Point(event.getX(1), event.getY(1));
            }
    }
}
