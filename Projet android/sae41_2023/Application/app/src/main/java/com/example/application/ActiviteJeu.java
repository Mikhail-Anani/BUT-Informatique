package com.example.application;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.List;

/**
 * Cette classe est l'activité jeu. c'est sur cette page que l'utilisateur pourras jouer ses meilleurs parties de morpions solitaire
 *
 * @version 1.0
 * @author Alexis, Djabrail, Mikhail
 * */
public class ActiviteJeu extends AppCompatActivity {

    /**
     * L'interface graphique du jeu
     * */
    private InterfaceGraphiqueJeu dessin;
    /**
     * Le modele du jeu qui fait tous les calculs
     * */
    private Model model;

    /**
     * Cette méthode s'execute lorsqu'on lance cette activité
     * Elle envoie sur la page du jeu, en le configurant avec les preferences du joueur
     * @param savedInstanceState preferences du joueur
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_jeu);

        this.dessin = findViewById(R.id.interfaceGraphiqueJeu);
        this.model = new Model();

        Intent intension = this.getIntent();
        if (intension != null){
            this.model.setTailleCroix(intension.getIntExtra("tailleCroix", 4));
            this.model.setProlongementTrait(intension.getBooleanExtra("prolongementTrait", true));
            this.model.setTriche(intension.getBooleanExtra("triche", false));
        }
        this.model.setInterfaceGraphiqueJeu(this.dessin);
        this.dessin.setModel(this.model);
    }

    /**
     * Cette methode est executee a chaque fois que l'on retourne sur la page du jeu
     * Elle ajoute les ecouteur d'evenement de la page
     * */
    @Override
    protected void onResume() {
        super.onResume();
        EvenementDoigt evenementDoigt = new EvenementDoigt(this.model);
        this.dessin.setOnTouchListener(evenementDoigt);
    }

    /**
     * Cette methode est executee a chaque fois que la page n'est plus au premier plan
     * Elle retire les ecouteurs d'evenements de la page afin d'optimiser les performances
     * */
    @Override
    protected void onPause() {
        super.onPause();
        this.dessin.setOnTouchListener(null);
    }

    /**
     * Cette méthode sauvegarde les donnes principales du jeu (coordonnees sur la carte, score, traits et points)
     * @param outState unite de stockages des  donnes principales du jeu
     * */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Point positionCarte = this.model.getPositionMap();
        outState.putFloat("x",positionCarte.getX());
        outState.putFloat("y",positionCarte.getY());
        outState.putInt("score",this.model.getScore());

        HashMap<Point, List<Trait>> dicoPoint = this.model.getDicoPoint();
        outState.putSerializable("dicoPoint", dicoPoint);
    }

    /**
     * Cette méthode recupere la sauvegarde des donnes principales du jeu (coordonnees sur la carte, score, traits et points)
     * et affiche le jeu en conséquence.
     * @param inState donnes principales du jeu
     * */
    @Override
    protected void onRestoreInstanceState(Bundle inState) {
        super.onSaveInstanceState(inState);
        Point positionCarte = new Point(inState.getFloat("x",0), inState.getFloat("y",0));
        HashMap<Point, List<Trait>> dicoPoint = (HashMap<Point, List<Trait>>) inState.getSerializable("dicoPoint");
        int score = inState.getInt("score", 0);

        this.model.setPositionMap(positionCarte);
        this.model.setDicoPoint(dicoPoint);
        this.model.setScore(score);
        this.model.setPartieEnCours(true);
    }
}