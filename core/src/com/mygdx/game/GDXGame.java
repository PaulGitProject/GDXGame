package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class GDXGame extends ApplicationAdapter {
	static SpriteBatch batch;
	static Stage stage;

	/**
	 * Tout ce qu'il y à entre ici et le @Override sont des variables "vides".
	 * Elles seront déclarées (donc auront des valeurs) dans la fonction public void create(){} en dessous du @Override
	 */

	//SPRITES
	static Sprite oiseau;
	static Sprite tuyauDuHaut;
	static Sprite tuyauDuBas;
	static Sprite boutonJouer;
	static Sprite boutonParametre; //bouton "Paramètre" pour régler la vitesse etc si l'on souhaite
  	static Sprite boutonQuitter; //boutton "Quitter" ferme le jeux
  	static Sprite boutonRecommence; //bouton "Recommencer" pour relancer une partie lorsque l'on est mort
	static Sprite boutonMenu; //bouton "Menu" pour retourner au menu
	static Sprite boutonVitesse;
	static Sprite boutonEcart;
	static Sprite flecheMenu; //flèche de sélection dans les menus
	static Sprite messagePerdu;

	//TEXTS
	static BitmapFont font;
	static FreeTypeFontGenerator generator;
	static FreeTypeFontGenerator.FreeTypeFontParameter parameter;

	//TEXTURES
	static Texture imageDeFond; //le background du jeu

	//SOUNDS
	static Sound saut;
	static Sound ding;

	//PIPES SETTINGS
	static int ecartTuyaux = 300; //min : windowWidth + pipeWidth = 630 ; Range of the total pipe swipe before reset
	static int espacementTuyaux = 200; //Espace entre le tuyau du haut et du bas
	static int vitesseDeplacementTuyaux = 3; //De combien de pixel les tuyaux se déplacent chaque tick
	static int positionXPremierTuyaux, positonXDeuxiemeTuyaux, positionXTroisiemeTuyaux; //pipes's X positions
	static int resetPipe; //reset position (relative to range)
	static int hauteurPremierTuyauDuBas, hauteurSecondTuyauDuBas, hauteurTroisiemeTuyauDuBas;

	//BIRD SETTINGS
	static int jumpHeight = 18; //bird's upward acceleration when pressing jump button
	static int hauteurOiseau; //bird's Y position
	static int vistesseOiseau; //bird's acceleration

	//MENUS AND DISPLAYING SETTINGS
	static String affichage = "menu"; //menu displayed [menu, lose, settings, game]
	static int selectMenu = 2; //menu arrow position (basic menu)
	static int selectLose = 1; //menu arrow position (losing screen)
	static boolean play = false; //waiting for player to press jump once before starting the game (on game screen)

	//GAME SETTINGS
	static int score = 0; //player's score

	static TextField vitesseParametreText;
	static TextField espacementTuyauxParametre;

	@Override
	public void create() {
		batch = new SpriteBatch();
		stage = new Stage();

		resetPipe = -ecartTuyaux;

		//TEXTS
		font = new BitmapFont();

		generator = new FreeTypeFontGenerator(Gdx.files.internal("Roboto-Black.ttf"));
		parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

		parameter.size = 70;
		font = generator.generateFont(parameter);
		vitesseParametreText = new TextField(String.valueOf(vitesseDeplacementTuyaux), new Skin(Gdx.files.internal("uiskin.json")));
		espacementTuyauxParametre = new TextField(String.valueOf(espacementTuyaux), new Skin(Gdx.files.internal("uiskin.json")));

		Gdx.input.setInputProcessor(stage);

		//SPRITES
		oiseau = new Sprite(new Texture("flappy.png"));



		boutonVitesse = new Sprite(new Texture("VITESSE.png"));
		boutonEcart = new Sprite(new Texture("ECART.png"));


		tuyauDuHaut = new Sprite(new Texture("top-pipe.png"));
		tuyauDuBas = new Sprite(new Texture("bottom-pipe.png"));
		boutonJouer = new Sprite(new Texture("play.png"));
		boutonParametre = new Sprite(new Texture("settings.png"));
		boutonQuitter = new Sprite(new Texture("exit.png"));
		boutonRecommence = new Sprite(new Texture("retry.png"));
		boutonMenu = new Sprite(new Texture("menu.png"));
		flecheMenu = new Sprite(new Texture("arrow.png"));
		messagePerdu = new Sprite(new Texture("lose.png"));



		//TEXTURES
		imageDeFond = new Texture("bg.png");

		//SOUNDS
		saut = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.mp3"));
		ding = Gdx.audio.newSound(Gdx.files.internal("sfx_point.wav"));
	}

	@Override
	public void render() {
		Dessiner.background();
		if(affichage.equals("game")) {

			EventDeTouche.menu();

			//On vérifie si l'oiseau n'entre pas en collision avec les 3 paires de tuyaux.
			Outils.verifCollision();
			//On vérifie si l'oiseau passe une paire de tuyaux pour son score
			Outils.verifPasseTuyaux();

			//Ici on applique les backgrounds, tuyaux, et l'oiseau etc..
			Dessiner.tuyaux();
			Dessiner.oiseau();
			Dessiner.sol();
			Dessiner.score(); //Permet d'afficher le score en haut à gauche

			//On déplace les tuyaux sur l'axe X
			Outils.deplacerTuyaux();

			//Lorsque l'on saute on reset la vitesse de chute de l'oiseau et on joue un son
			EventDeTouche.saute();

			//Ici se calcul comment l'oiseau "tombe" en fonction de sa vitesse
			Outils.deplacerOiseau(); //Permet d'animer l'oiseau

		}
		if(affichage.equals("menu")) {
			Dessiner.menu();
			EventDeTouche.menuSwitch();
		}
		if(affichage.equals("lose")) {
		    EventDeTouche.loseSwitch();
		    Dessiner.mort();
        }
		if(affichage.equals("settings")) {
			EventDeTouche.menu();
			Dessiner.settings();
		}
	}
}