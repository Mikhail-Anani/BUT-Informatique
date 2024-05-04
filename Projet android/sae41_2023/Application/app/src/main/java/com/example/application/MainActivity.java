package com.example.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.preference.PreferenceManager;

/**
 * Cette classe est l'activité principale. elle nous amene sur la fenetre d'accueil
 *
 * @version 1.0
 * @author Alexis, Djabrail, Mikhail
 * */
public class MainActivity extends AppCompatActivity {
    /**
     * Taille de la croix de depart au lancement du jeu
     * */
    private int tailleCroix = 4;
    /**
     * Regle de prolongment des trait lors du jeu (active ou non)
     * */
    private boolean prolongementTrait = false;
    /**
     * Regle qui active ou non le mode triche
     * (qui permet au joueur de voir une position de croix possible a faire apparaitre en tracant un trait)
     * */
    private boolean modeTriche = false;

    /**
     * Cette methode est executee au lancement du jeu.
     * Elle amene sur la page d'accueil et recupere les preferences du joueur pour le jeu
     * @param savedInstanceState donnee sauvegarder
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.getPreference();
    }

    /**
     * Cette methode est executee a chaque fois que l'on retourne sur la page d'accueil
     * Elle  recupere les preferences du joueur pour le jeu.
     * Elle ajoute egalement les ecouteur d'evenement de la page
     * */
    @Override
    protected void onResume() {
        super.onResume();
        Button buttonPlay = findViewById(R.id.button_play);
        EventButtonPlay eventButtonPlay = new EventButtonPlay(this);
        buttonPlay.setOnClickListener(eventButtonPlay);
        this.getPreference();
    }

    /**
     * Cette methode est executee a chaque fois que la page n'est plus au premier plan
     * Elle retire les ecouteurs d'evenements de la page pour optimiser les performances
     * */
    @Override
    protected void onPause() {
        super.onPause();
        Button buttonPlay = findViewById(R.id.button_play);
        buttonPlay.setOnClickListener(null);
        this.getPreference();
    }

    /**
     * Cette methode est executee lorsque l'utilisateur clic sur le bouton avec les 3 petit points
     * Elle affiches les differents onglets accessible par le joueur (ici il n'y a que les options)
     * @param menu Le menu dans lequel les options seront affichées
     * @return true pour indiquer que le menu a été créé avec succès
     * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        this.getMenuInflater().inflate(R.menu.option, menu);
        return true;
    }

    /**
     * Cette methode est executee lorsque l'utilisateur clic sur un onglet du bouton avec les 3 petit points
     * La methode envoie le joueur sur la page / l'activité correspondante
     * @param item onglet selectionné par le joueur
     * @return true si le joueur à été envoyer sur la bonne page, false sinon
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.option){
            Intent intention = new Intent(this, ActiviteParametre.class);
            this.startActivity(intention);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Recupere les preferences du joueurs et les stocke dans les attributs de cet objet
     * */
    public void getPreference(){
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(this);
        boolean tailleCroix = preference.getBoolean("tailleCroix", false);
        if (tailleCroix){
            this.tailleCroix = 4;
        }
        else{
            this.tailleCroix = 3;
        }
        this.modeTriche = preference.getBoolean("triche", false);
        this.prolongementTrait = preference.getBoolean("prolongementTrait", true);
        Log.v("test preferences", tailleCroix+" "+this.prolongementTrait);
    }

    /**
     * Envoie le joueur vers la page / l'activité jeu, en prennant en compte ses préférences
     * */
    public void demarrerActiviteJeu(){
        Intent intension = new Intent(MainActivity.this, ActiviteJeu.class);
        intension.putExtra("tailleCroix", this.tailleCroix);
        intension.putExtra("prolongementTrait", this.prolongementTrait);
        intension.putExtra("triche", this.modeTriche);
        this.startActivity(intension);
    }
}