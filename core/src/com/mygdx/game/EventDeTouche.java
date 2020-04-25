package com.mygdx.game;

import static com.mygdx.game.GDXGame.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class EventDeTouche {

    public static void saute() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if(!play) play = true; //On attend que le joueur appui une fois sur espace pour que les tuyaux commencent à avancer etc..
            vistesseOiseau = -jumpHeight;
            saut.play(0.01f);
        }
    }

    public static void menu() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            if (affichage.equalsIgnoreCase("settings")) {
                if (Integer.parseInt(espacementTuyauxParametre.getText()) < 220) {
                    espacementTuyauxParametre.setText("220");
                }
                if (Integer.parseInt(vitesseParametreText.getText()) < 1) {
                    vitesseParametreText.setText("1");
                }
                espacementTuyaux = Integer.parseInt(espacementTuyauxParametre.getText());
                vitesseDeplacementTuyaux = Integer.parseInt(vitesseParametreText.getText());
            }
            affichage = "menu";
        }
    }

    public static void menuSwitch() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            if (selectMenu > 0) {
                selectMenu--;
            } else {
                selectMenu = 2;
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if (selectMenu < 2) {
                selectMenu++;
            } else {
                selectMenu = 0;
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            switch (selectMenu) {
                case 2:
                    Outils.setGame();
                    break;
                case 1:
                    affichage = "settings";
                    break;
                case 0:
                    Outils.exit();
                    break;
            }
        }
    }

    public static void loseSwitch() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) || Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if (selectLose == 0) {
                selectLose = 1;
            } else {
                selectLose = 0;
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            switch (selectLose) {
                case 1:
                    Outils.setGame();
                    break;
                case 0:
                    affichage = "menu";
                    break;
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            affichage = "menu";
        }
    }

}