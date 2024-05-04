package com.example.application;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.MenuItem;

/**
 * Cette classe est l'activité parametre. c'est sur cette page que l'utilisateur pourras configurer ses préférences de l'application
 *
 * @version 1.0
 * @author Alexis, Djabrail, Mikhail
 * */

public class ActiviteParametre extends PreferenceActivity {

    /**
     * Cette méthode s'execute lorsqu'on lance cette activité
     * Elle envoie sur la page des parametres en fixant les options tel qu'elle était la dernière fois que le joueur était sur cette page
     * @param savedInstanceState se referer au onCreate de  PreferenceActivity
     * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.addPreferencesFromResource(R.xml.preference);

        PreferenceManager.setDefaultValues(this, R.xml.preference, false);
    }
}