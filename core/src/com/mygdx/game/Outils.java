package com.mygdx.game;
import com.badlogic.gdx.Gdx;

import static com.mygdx.game.GDXGame.*;

public class Outils {

    public static int randHeight() {
        return (int) (Math.random() * 400) + espacementTuyaux/2;
    }

    public static void deplacerTuyaux() {
        if(!play) return;
        positionXPremierTuyaux -= vitesseDeplacementTuyaux;
        positonXDeuxiemeTuyaux -= vitesseDeplacementTuyaux;
        positionXTroisiemeTuyaux -= vitesseDeplacementTuyaux;

        if(positionXPremierTuyaux <= resetPipe) {
            positionXPremierTuyaux = 550;
            hauteurPremierTuyauDuBas = Outils.randHeight();
        }

        if(positonXDeuxiemeTuyaux <= resetPipe) {
            positonXDeuxiemeTuyaux = 550;
            hauteurSecondTuyauDuBas = Outils.randHeight();
        }

        if(positionXTroisiemeTuyaux <= resetPipe) {
            positionXTroisiemeTuyaux = 550;
            hauteurTroisiemeTuyauDuBas = Outils.randHeight();
        }
    }

    public static void deplacerOiseau() {
        if(!play) return;
        vistesseOiseau++; // birdSpeed = birdSpeed + 1;
        hauteurOiseau -= vistesseOiseau /1.5;
    }

    public static void verifCollision() {
        //check pipe one
        if(positionXPremierTuyaux < 176 && positionXPremierTuyaux > 20) {
            if(hauteurOiseau < hauteurPremierTuyauDuBas || hauteurOiseau +54 > hauteurPremierTuyauDuBas + espacementTuyaux) {
                affichage = "lose";
            }
        }
        //check pipe two
        if(positonXDeuxiemeTuyaux < 176 && positonXDeuxiemeTuyaux > 20) {
            if(hauteurOiseau < hauteurSecondTuyauDuBas || hauteurOiseau +54 > hauteurSecondTuyauDuBas + espacementTuyaux) {
                affichage = "lose";
            }
        }
        //check pipe three
        if(positionXTroisiemeTuyaux < 176 && positionXTroisiemeTuyaux > 20) {
            if(hauteurOiseau < hauteurTroisiemeTuyauDuBas || hauteurOiseau +54 > hauteurTroisiemeTuyauDuBas + espacementTuyaux) {
                affichage = "lose";
            }
        }
        //check floor
        if(hauteurOiseau < 29) {
            affichage = "lose";
        }
    }

    public static void verifPasseTuyaux() {
        //check pipe one
        if(positionXPremierTuyaux < 20 && positionXPremierTuyaux >= 20- vitesseDeplacementTuyaux){
            ding.play(0.01f);
            score++;
        }
        if(positonXDeuxiemeTuyaux < 20 && positonXDeuxiemeTuyaux >= 20- vitesseDeplacementTuyaux){
            ding.play(0.01f);
            score++;
        }
        if(positionXTroisiemeTuyaux < 20 && positionXTroisiemeTuyaux >= 20- vitesseDeplacementTuyaux){
            ding.play(0.01f);
            score++;
        }
    }

    public static void setGame() {

        //SET PIPES POSITIONS
        positionXPremierTuyaux = 550;
        positonXDeuxiemeTuyaux = (Gdx.graphics.getWidth()+(Gdx.graphics.getWidth()+ ecartTuyaux)/3);
        positionXTroisiemeTuyaux = (int) (Gdx.graphics.getWidth()+(Gdx.graphics.getWidth()+ ecartTuyaux)/1.5);

        //SET RANDOM HEIGHTS
        hauteurPremierTuyauDuBas = Outils.randHeight();
        hauteurSecondTuyauDuBas = Outils.randHeight();
        hauteurTroisiemeTuyauDuBas = Outils.randHeight();

        //SET BIRD HEIGHT AND SPEED
        hauteurOiseau = 450;
        vistesseOiseau = 0;

        //SET PLAYING STATUS
        play = false;

        //SET MENU SELECTORS
        selectLose = 1;
        selectMenu = 2;

        //SET SCORE
        score = 0;

        affichage = "game";
    }

    public static void exit() {
        Gdx.app.exit();
    }

}