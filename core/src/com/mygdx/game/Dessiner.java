package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;

import static com.mygdx.game.GDXGame.*;

public class Dessiner {

    private static boolean draw = false;

    public static void background() {
        batch.begin();
        batch.draw(imageDeFond, 0, 0, 550, 900);
        batch.end();
    }

    public static void score() {
        batch.begin();
        //font.scaleToFitSquare(50,50,2);
        font.draw(batch, "" + score, 20, 880);
        batch.end();
    }

    public static void tuyaux() {
        batch.begin();
        batch.draw(tuyauDuBas, positionXPremierTuyaux, -900 + hauteurPremierTuyauDuBas, 80, 900);
        batch.draw(tuyauDuHaut, positionXPremierTuyaux, espacementTuyaux + hauteurPremierTuyauDuBas, 80, 900);

        batch.draw(tuyauDuBas, positonXDeuxiemeTuyaux, -900 + hauteurSecondTuyauDuBas, 80, 900);
        batch.draw(tuyauDuHaut, positonXDeuxiemeTuyaux, espacementTuyaux + hauteurSecondTuyauDuBas, 80, 900);

        batch.draw(tuyauDuBas, positionXTroisiemeTuyaux, -900 + hauteurTroisiemeTuyauDuBas, 80, 900);
        batch.draw(tuyauDuHaut, positionXTroisiemeTuyaux, espacementTuyaux + hauteurTroisiemeTuyauDuBas, 80, 900);
        batch.end();
    }

    public static void sol() {
        CustomRectangle sol = new CustomRectangle();
        sol.creerRectangle(0, 0, 550, 20, Color.ORANGE);
        sol.creerRectangle(0, 18, 550, 11, Color.DARK_GRAY);
        sol.creerRectangle(0, 20, 550, 7, Color.LIME);
    }

    public static void oiseau() {
        batch.begin();
        batch.draw(oiseau, 100, hauteurOiseau, 76, 54);
        batch.end();
    }

    public static void menu() {
        batch.begin();
        batch.draw(boutonJouer, 125, 650, 400, 150);
        batch.draw(boutonParametre, 125, 375, 400, 150);
        batch.draw(boutonQuitter, 125, 100, 400, 150);
        switch (selectMenu) {
            case 2:
                batch.draw(flecheMenu, 25, 650, 75, 150);
                break;
            case 1:
                batch.draw(flecheMenu, 25, 375, 75, 150);
                break;
            case 0:
                batch.draw(flecheMenu, 25, 100, 75, 150);
                break;
        }
        batch.end();
    }

    public static void settings() {
        stage.act(Gdx.graphics.getDeltaTime());

        batch.begin();
        batch.draw(boutonEcart, Gdx.graphics.getWidth() / 2 - 200, 650, 400, 150);
        batch.draw(boutonVitesse, Gdx.graphics.getWidth() / 2 - 200, 300, 400, 150);


        espacementTuyauxParametre.setSize(350, 100);
        espacementTuyauxParametre.setPosition(Gdx.graphics.getWidth() / 2 - 175, 520);
        espacementTuyauxParametre.setAlignment(Align.center);
        espacementTuyauxParametre.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());

        vitesseParametreText.setSize(350, 100);
        vitesseParametreText.setPosition(Gdx.graphics.getWidth() / 2 - 175, 170);
        vitesseParametreText.setAlignment(Align.center);
        vitesseParametreText.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());

        if (!espacementTuyauxParametre.getText().isEmpty() && Integer.parseInt(espacementTuyauxParametre.getText()) > 350) {
                espacementTuyauxParametre.setText("350");
        }

        if (!vitesseParametreText.getText().isEmpty() && Integer.parseInt(vitesseParametreText.getText()) > 5) {
                vitesseParametreText.setText("5");
        }

        stage.addActor(vitesseParametreText);
        stage.addActor(espacementTuyauxParametre);
        batch.end();
        stage.draw();
    }

    public static void mort() {
        batch.begin();
        batch.draw(messagePerdu, 0, 600, 550, 250);
        batch.draw(boutonRecommence, 125, 375, 400, 150);
        batch.draw(boutonMenu, 125, 100, 400, 150);
        font.draw(batch, "" + score, 388, 708);
        switch (selectLose) {
            case 1:
                batch.draw(flecheMenu, 25, 375, 75, 150);
                break;
            case 0:
                batch.draw(flecheMenu, 25, 100, 75, 150);
                break;
        }
        batch.end();
    }

}