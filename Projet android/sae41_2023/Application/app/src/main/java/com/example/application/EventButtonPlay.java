package com.example.application;

import android.view.View;

/**
 *Cette classe permet de reagir au clic d'un bouton en lançant une nouvelle activité
 *
 * @version 1.0
 * @author Alexis, Djabrail, Mikhail
 */
public class EventButtonPlay implements View.OnClickListener{
    /**
     * L'activite qui contiens le bouton associer à cet écouteur d'évenement et qui permet de lancer l'activité jeu
     */
    private MainActivity mainActivity;

    /**
     * Constructeur de l'écouteur d'évenement
     * @param mainActivity l'activité qui contiens le bouton associer à cet écouteur d'évenement et qui permet de lancer l'activité jeu
     */
    public EventButtonPlay(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    /**
     * Lance l'activité jeu au clic sur le bouton
     * @param v bouton cliqué (se référer à la methode onClick de View.OnClickListener)
     */
    @Override
    public void onClick(View v){
        this.mainActivity.demarrerActiviteJeu();
    }
}
