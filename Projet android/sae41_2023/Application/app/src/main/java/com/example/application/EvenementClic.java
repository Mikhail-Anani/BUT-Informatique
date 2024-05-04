package com.example.application;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Cette permet de detecter les differents clics et d'executer des actions en fonction
 *
 * @version 1.0
 * @author Alexis, Djabrail, Mikhail
 */
public class EvenementClic extends GestureDetector.SimpleOnGestureListener {

    /**
     * Le model qui gere tous les calculs du jeu
     */
    private Model jeu;


    /**
     * constructeur qui permet de stocker l'interface graphique du jeu afin de le changer en fonction des types de clics
     * @param jeu Le modèle représentant le jeu associé à l'interface graphique
     */
    public EvenementClic(Model jeu){
        this.jeu = jeu;
    }


    /**
     * La methode s'execute lorsqu'on pose un doigt. La methode ne fait rien
     * @param e evenement (se referer à la methode onDown de GestureDetector.SimpleOnGestureListener)
     * @return false
     */
    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    /**
     * La methode s'execute lorsqu'on scroll rapidement. La methode ne fait rien
     * @param e1 evenement1 (se referer à la methode onDown de GestureDetector.SimpleOnGestureListener)
     * @param e2 evenement2 (se referer à la methode onDown de GestureDetector.SimpleOnGestureListener)
     * @param velocityX vitesse de scroll en absisse (se referer à la methode onDown de GestureDetector.SimpleOnGestureListener)
     * @param velocityY vitesse de scroll en ordonnee (se referer à la methode onDown de GestureDetector.SimpleOnGestureListener)
     * @return false
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    /**
     * La methode s'execute lorsqu'on clic (et pas lorsqu'on double clic). La methode ne fait rien
     * @param e evenement (se referer à la methode onDown de GestureDetector.SimpleOnGestureListener)
     * @return false
     */
    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    /**
     * La methode s'execute lorsqu'on  double clic. La methode recentre la grille du jeu sur la croix de depart
     * @param e evenement (se referer à la methode onDown de GestureDetector.SimpleOnGestureListener)
     * @return true
     */
    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.v("test","Long Press");
        this.jeu.recentrerMap();
        return true;
    }

    /**
     * La methode s'execute lorsqu'on laisse son doigt appuyé sur l'écran pendant un certain temps.
     * La methode ne fait rien
     * @param e evenement (se referer à la methode onDown de GestureDetector.SimpleOnGestureListener)
     */
    @Override
    public void onLongPress(MotionEvent e) {
    }
}
