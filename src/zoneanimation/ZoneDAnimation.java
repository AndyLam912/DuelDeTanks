package zoneanimation;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;

import interfaces.FlecheListener;
import interfaces.GagnantListener;
import interfaces.PositionsEtTempsListener;
import interfaces.TourListener;
import objetsdessinables.ChampElectrique;
import objetsdessinables.CharDassaut;
import objetsdessinables.Projectile;
import objetsdessinables.Terrain;
import objetsdessinables.Vecteur;
import objetsdessinables.VecteurGraphique;

/**
 * classe qui permet le dessin de plusieurs �l�ments graphiques ainsi que leurs
 * animation
 * 
 * @author l�o-paul lapointe
 * @author Andy Lam
 *
 */
public class ZoneDAnimation extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;

	private final double POS_X_VERT_INIT = 100.0;
	private final double POS_X_ROUGE_INIT = 900.0;
	private final Double TIC_ANGLE_CANON = 0.1;
	private final long TEMPS_SLEEP = 3;

	private final double PAS_EULER_REFERENCE = 0.01;
	private final double TEMPS_MAX_CHAMP = 3.0;
	private final double TEMPS_MAX_OBUS = 50.0;
	private final int HAUTEUR_LEGENDE = 10;
	private final int NB_BOND_MAX = 10;
	private final int CHARGE_GRENADE_INIT = 2000;
	private final int BARRE_ESSENCE_INITIAL = 100;
	private final EventListenerList OBJETS_ENREGISTRES = new EventListenerList();
	private final double RAYON_EXPLOSION = 40;

	private AffineTransform mat;
	private double tailleDuTerrain = 1000.0;
	private double pasEuler = PAS_EULER_REFERENCE;
	private double temps;
	private double gravite;
	private double bondLegende = 10.0;
	private int chargeChamp = CHARGE_GRENADE_INIT;
	private int masseProjectileVert = 15;
	private double vitesseInitialeVert = 50;
	private int masseProjectileRouge = 15;
	private double vitesseInitialeRouge = 50;

	private Color couleurProjectile;

	private double posVertX = POS_X_VERT_INIT;
	private double posRougeX = POS_X_ROUGE_INIT;
	private int barreEssence = BARRE_ESSENCE_INITIAL;
	private int nbPVVert = 3;
	private int nbPVRouge = 3;
	private String nomNiveau;

	private ChampElectrique champ;
	private ModeleAffichage modele;
	private CharDassaut charVert;
	private CharDassaut charRouge;
	private Terrain terrain;
	private Projectile projectile;
	private Vecteur vent;

	private boolean premiereFois = true;
	private boolean explosionChamp = false;
	private boolean tourDuCharVert = true;
	private boolean animEnCours = false;
	private boolean reinitialiser = false;
	private boolean afficherVecteurs = false;
	private boolean ventActive = false;
	private boolean projectileChargePlus = true;
	private boolean estGrenadeElectrique = false;
	private boolean gagnantVert;
	private Image imgArrierePlan;
	private boolean texture = false;
	private boolean background = false;
	private Image imgTerrain;
	private String fichierSons[] = { "Cannon.wav", "Explosion.wav" };
	private ArrayList<AudioClip> listSons;

	;

	// Andy Lam
	/**
	 * Cr�ation de la zone d'animation
	 */
	public ZoneDAnimation() {
		// Permet d'initialiser le terrain. La taille du terrain sera changer lorsque
		// l'utilisateur choisirera un niveau.
		terrain = new Terrain(100, 100);
		lireLesSons();
		champ = new ChampElectrique(0, 0, chargeChamp);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				deplacement(arg0);
				leverEvenPosChar();
				angleCanonFleches(arg0);
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				eteindreFleches(arg0);
			}
		});

	}

	// Andy Lam
	/**
	 * M�thode qui permet de dessiner toute la zone d'animation
	 * 
	 * @param g
	 *            qui est le contexte graphique
	 * 
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setRenderingHints(rh);
		modele = new ModeleAffichage(getWidth(), getHeight(), tailleDuTerrain);

		if (premiereFois || reinitialiser) {

			if (premiereFois) {
				terrain = new Terrain((int) modele.getLargUnitesReelles(), (int) modele.getHautUnitesReelles());
				changeTerrain(nomNiveau);

			}

			charVert = new CharDassaut("tank_vr.png", posVertX,
					terrain.getPointsTerrain((int) (posVertX / terrain.getDx())),
					terrain.angleSurUnPoint((int) posVertX, (int) posVertX + 2), nbPVVert, true);
			charRouge = new CharDassaut("tank_rg.png", posRougeX,
					terrain.getPointsTerrain((int) (posRougeX / terrain.getDx())),
					terrain.angleSurUnPoint((int) posRougeX, (int) posRougeX + 2), nbPVRouge, false);

			reinitialiser = false;
			premiereFois = false;
		}

		mat = modele.getMatMC();
		if (background) {
			g2d.drawImage(imgArrierePlan, 0, 0, (int) modele.getLargPixels(), (int) modele.getHautPixels(), null);
		}
		if (explosionChamp) {
			// Test pour voir le dessin du champ electrique

			champ.dessiner(g2d, mat);
		}
		Graphics2D g2dTerrain = (Graphics2D) g2d.create();

		terrain.dessiner(g2dTerrain, mat);
		if (texture) {
			g2dTerrain.setClip(terrain.getFormeTerrain());
			g2dTerrain.drawImage(imgTerrain, 0, 0, (int) modele.getLargPixels(), (int) modele.getHautPixels(), null);
		}
		g2dTerrain.dispose();
		charVert.dessiner(g2d, mat);
		charRouge.dessiner(g2d, mat);
		if (animEnCours) {
			projectile.setCouleurProjectile(couleurProjectile);
			projectile.dessiner(g2d, mat);

			affichageVecteurs(g2d);
		}
		// Creation de l'echelle
		g2d.setColor(Color.WHITE);
		while ((tailleDuTerrain / bondLegende) > NB_BOND_MAX) {
			bondLegende += 10.0;
		}
		for (int i = 0; i < (int) Math.round(tailleDuTerrain / bondLegende); i++) {
			g2d.drawString((int) (i * bondLegende) + "m", (float) (Math.round(i * bondLegende) * mat.getScaleX()),
					(getHeight() + Math.round(mat.getScaleX() - HAUTEUR_LEGENDE)));
			Rectangle2D.Double graduation = new Rectangle2D.Double((Math.round(i * bondLegende) * mat.getScaleX()),
					(getHeight() + Math.round(mat.getScaleX() - 3 * HAUTEUR_LEGENDE)), 3, HAUTEUR_LEGENDE);
			g2d.fill(graduation);
		}
		g2d.drawLine(0, (int) (getHeight() + Math.round(mat.getScaleX() - (2.5 * HAUTEUR_LEGENDE))), getWidth(),
				(int) (getHeight() + Math.round(mat.getScaleX() - (2.5 * HAUTEUR_LEGENDE))));

	}

	// par l�o-paul lapointe
	/**
	 * dessine les vecteurs de forces sur le boulet
	 * 
	 * @param g2d
	 *            qui est le contexte graphique
	 */
	public void affichageVecteurs(Graphics2D g2d) {
		if (afficherVecteurs) {
			// cr��e et affiche les vecteurs de forces
			VecteurGraphique vecteurGravite = new VecteurGraphique(0, gravite * projectile.getMasseProjectile(),
					projectile.getX() * modele.getPixelsParUniteX(), projectile.getY() * modele.getPixelsParUniteY());
			g2d.setColor(Color.BLUE);
			vecteurGravite.dessiner(g2d, mat);
			if (ventActive) {
				VecteurGraphique vecteurVent = new VecteurGraphique(vent.getX(), vent.getY(),
						projectile.getX() * modele.getPixelsParUniteX(),
						projectile.getY() * modele.getPixelsParUniteY());
				g2d.setColor(Color.RED);
				vecteurVent.dessiner(g2d, mat);
			}
			if (explosionChamp) {
				// dessin du vecteur, on prend la force �lectrique divis�e par 5 pour plus de
				// lisibilit�
				VecteurGraphique vecteurChamp = new VecteurGraphique(champ.getForceElectrique(projectile).getX() / 5,
						champ.getForceElectrique(projectile).getY() / 5,
						projectile.getX() * modele.getPixelsParUniteX(),
						projectile.getY() * modele.getPixelsParUniteY());
				System.out.println(champ.getForceElectrique(projectile).getX());
				g2d.setColor(Color.YELLOW);
				vecteurChamp.dessiner(g2d, mat);
			}
		}
	}

	// Andy Lam
	/**
	 * Methode qui permet de deplacer le char selon la touche appuyee
	 * 
	 * @param arg0
	 *            Le code de la touche de clavier qui a ete appuyee
	 */
	public void deplacement(KeyEvent arg0) {
		if (barreEssence > 0) {

			switch (arg0.getKeyCode()) {
			case (KeyEvent.VK_LEFT):
				leverEventFlecheGauche();
				deplacementGauche();
				break;
			case (KeyEvent.VK_RIGHT):
				leverEventFlecheDroite();
				deplacementDroite();
				break;
			}

		}

	}

	// par l�o-paul lapointe
	/**
	 * permet de d�placer le canon avec les fl�ches du clavier
	 * 
	 * @param arg0
	 *            touche enfonc�e
	 */
	public void angleCanonFleches(KeyEvent arg0) {
		switch (arg0.getKeyCode()) {
		case (KeyEvent.VK_UP):
			leverEventFlecheHaut();
			monterCanon();
			break;
		case (KeyEvent.VK_DOWN):
			leverEventFlecheBas();
			descendreCanon();
			break;
		}

	}

	// par l�o-paul lapointe
	/**
	 * permet d'�teingre les fleches directionnelles quand une touche est relach�e
	 * 
	 * @param arg0
	 *            touche relach�e
	 */
	public void eteindreFleches(KeyEvent arg0) {
		switch (arg0.getKeyCode()) {
		case (KeyEvent.VK_UP):
			leverEventFlecheHautEteindre();
			break;
		case (KeyEvent.VK_DOWN):
			leverEventFlecheBasEteindre();
			break;
		case (KeyEvent.VK_LEFT):
			leverEventFlecheGaucheEteindre();
			break;
		case (KeyEvent.VK_RIGHT):
			leverEventFlecheDroiteEteindre();
			break;
		}
	}

	// par l�o-paul lapointe
	/**
	 * permet d'�lever le canon du char
	 */
	public void monterCanon() {
		if (tourDuCharVert) {
			if (charVert.getAngleCanon() > -3.2 && charVert.getAngleCanon() < 0.15) {
				charVert.setAngleCanon(charVert.getAngleCanon() - TIC_ANGLE_CANON);

			}
		} else {
			if (charRouge.getAngleCanon() > -3.2 && charRouge.getAngleCanon() < 0.15) {
				charRouge.setAngleCanon(charRouge.getAngleCanon() - TIC_ANGLE_CANON);

			}
		}
		repaint();
	}

	// par l�o-paul lapointe
	/**
	 * permet de descendre le canon du char
	 */
	public void descendreCanon() {
		if (tourDuCharVert) {
			if (charVert.getAngleCanon() > -3.25 && charVert.getAngleCanon() < 0.1) {
				charVert.setAngleCanon(charVert.getAngleCanon() + TIC_ANGLE_CANON);

			}
		} else {
			if (charRouge.getAngleCanon() > -3.25 && charRouge.getAngleCanon() < 0.1) {
				charRouge.setAngleCanon(charRouge.getAngleCanon() + TIC_ANGLE_CANON);

			}
		}
		repaint();
	}

	// Andy Lam
	/**
	 * Methode qui deplace le char selectionnee a droite
	 */
	public void deplacementDroite() {
		if (tourDuCharVert) {
			double nouvPosXDroite = charVert.getX() + (terrain.getDx() / 2);
			if (nouvPosXDroite < tailleDuTerrain - terrain.getDx()
					&& nouvPosXDroite < charRouge.getX() - charVert.getLargeurImgScaled()) {
				charVert.changerPosition(nouvPosXDroite,
						terrain.getPointsTerrain((int) nouvPosXDroite / terrain.getDx()),
						terrain.angleSurUnPoint(((int) charVert.getX()) - (terrain.getDx() / 2), (int) nouvPosXDroite));
			}
		} else {
			double nouvPosXDroite = charRouge.getX() + (terrain.getDx() / 2);
			if (nouvPosXDroite < tailleDuTerrain - terrain.getDx()) {
				charRouge.changerPosition(nouvPosXDroite,
						terrain.getPointsTerrain((int) nouvPosXDroite / terrain.getDx()), terrain.angleSurUnPoint(
								((int) charRouge.getX()) - (terrain.getDx() / 2), (int) nouvPosXDroite));
			}
		}
		barreEssence--;
		repaint();
	}

	// Andy Lam
	/**
	 * Methode qui permet de deplacer le char selectionne vers la gauche
	 */
	public void deplacementGauche() {

		if (tourDuCharVert) {
			double nouvPosXGauche = charVert.getX() - (terrain.getDx() / 2);
			if (nouvPosXGauche > terrain.getDx()) {
				charVert.changerPosition(nouvPosXGauche,
						terrain.getPointsTerrain((int) nouvPosXGauche / terrain.getDx()),
						terrain.angleSurUnPoint((int) charVert.getX() + (terrain.getDx() / 2), (int) nouvPosXGauche));

			}
		} else {
			double nouvPosXGauche = charRouge.getX() - (terrain.getDx() / 2);
			if (nouvPosXGauche > terrain.getDx() && nouvPosXGauche > charVert.getX() + charVert.getLargeurImgScaled()) {
				charRouge.changerPosition(nouvPosXGauche,
						terrain.getPointsTerrain((int) nouvPosXGauche / terrain.getDx()),
						terrain.angleSurUnPoint((int) charRouge.getX() + (terrain.getDx() / 2), (int) nouvPosXGauche));

			}
		}
		barreEssence--;
		repaint();
	}

	// par l�o-paul lapointe
	/**
	 * permet de d�buter l'animation de tir et le calcul de la trajectoire
	 */
	public void tirer() {
		this.temps = 0;
		if (tourDuCharVert) {
			projectile = new Projectile(charVert.getX(), charVert.getY(), charVert.getAngleCanon(),
					charVert.getAngleTerrain(), vitesseInitialeVert, masseProjectileVert, gravite * masseProjectileVert,
					charVert.getLONGUEUR_CANON(), charVert.getLARGEUR_CANON(), charVert.getHauteurImgScaled(),
					estGrenadeElectrique, projectileChargePlus);
			projectile.creerAireProjectile(mat);
		} else {
			projectile = new Projectile(charRouge.getX(), charRouge.getY(), charRouge.getAngleCanon(),
					charRouge.getAngleTerrain(), vitesseInitialeRouge, masseProjectileRouge,
					gravite * masseProjectileRouge, charRouge.getLONGUEUR_CANON(), charRouge.getLARGEUR_CANON(),
					charRouge.getHauteurImgScaled(), estGrenadeElectrique, projectileChargePlus);
			projectile.creerAireProjectile(mat);
		}
		listSons.get(0).play();
		demarrer();
	}

	// Andy Lam
	/**
	 * Methode permettant de verifier si une collision a lieu et d'exploser le
	 * projectile
	 */
	public void collisionProjectile() {

		if (animEnCours) {
			Area tempTerrain = new Area(terrain.getAireTerrain());
			Area tempProjectile = new Area(projectile.getAireProjectile());
			Area tempProjectile2 = new Area(projectile.getAireProjectile());
			Area tempChar;
			if (tourDuCharVert) {
				tempChar = new Area(charRouge.getAireChar(mat));
			} else {
				tempChar = new Area(charVert.getAireChar(mat));
			}
			tempProjectile2.intersect(tempChar);
			tempProjectile.intersect(tempTerrain);
			if (!tempProjectile.isEmpty() || !tempProjectile2.isEmpty()) {
				if (!projectile.isGrenadeElectrique()) {
					explosionObus();
				} else {
					explosionChamp();

				}
			} else if (TEMPS_MAX_CHAMP < temps && projectile.isGrenadeElectrique()) {
				explosionChamp();

			} else if (TEMPS_MAX_OBUS < temps && !projectile.isGrenadeElectrique()) {
				explosionObus();

			} else if (!projectileDansTerrain()) {
				if (projectile.isGrenadeElectrique()) {
					explosionChamp();
				} else {
					explosionObus();
				}
			}
		}
	}

	// Andy Lam
	/**
	 * M�thode qui permet de voir si le projectile est visible dans la zone d'animation ou en haut de la zone d'animation.
	 * 
	 * 
	 * @return vrai si le projectile est encore visible dans la zone d'animation ou haut de celui-ci
	 */
	public boolean projectileDansTerrain() {
		double posX = projectile.getX();
		double posY = projectile.getY();
		return (posX > 0 && posX < modele.getLargUnitesReelles() && posY < modele.getHautUnitesReelles());
	}

	// Andy Lam
	/**
	 * Methode qui permet de faire tous les effets lorsque l'obus explose
	 */
	public void explosionObus() {
		terrain.explosion(projectile.getX(), projectile.getY() * modele.getPixelsParUniteY(), RAYON_EXPLOSION,
				modele.getPixelsParUniteY());
		toucheChar();
		barreEssence = BARRE_ESSENCE_INITIAL;

		tourDuCharVert = !tourDuCharVert;
		double posXGaucheRouge = charRouge.getX() - (terrain.getDx() / 2);
		double posXGaucheVert = charVert.getX() - (terrain.getDx() / 2);

		// Permet de changer la position du char si l'obus a d�former le sol sous un des
		// chars
		charRouge.changerPosition(charRouge.getX(), terrain.getPointsTerrain((int) charRouge.getX() / terrain.getDx()),
				terrain.angleSurUnPoint((int) charRouge.getX() + (terrain.getDx() / 2), (int) posXGaucheRouge));
		charVert.changerPosition(charVert.getX(), terrain.getPointsTerrain((int) charVert.getX() / terrain.getDx()),
				terrain.angleSurUnPoint((int) charVert.getX() + (terrain.getDx() / 2), (int) posXGaucheVert));

		explosionChamp = false;
		listSons.get(1).play();
		arreter();
		repaint();
		verifierGagnant();
	}

	// Andy Lam
	/**
	 * Methode qui permet de faire tous les effets lorsqu'une grenade �lectrique
	 * explose
	 */
	public void explosionChamp() {
		champ.setPosition(projectile.getX(), projectile.getY());
		tourDuCharVert = !tourDuCharVert;
		explosionChamp = true;
		barreEssence = BARRE_ESSENCE_INITIAL;
		champ.setCharge(chargeChamp);
		arreter();
		repaint();
	}

	// par l�o-paul lapointe
	/**
	 * permet de savoir si un char est touch� par une explosion
	 */
	public void toucheChar() {
		if (Math.sqrt(Math.pow(Math.abs(charVert.getX() - projectile.getX()), 2)
				+ Math.pow(Math.abs(charVert.getY() - projectile.getY()), 2)) <= 50) {
			charVert.touche();
		}
		if (Math.sqrt(Math.pow(Math.abs(charRouge.getX() - projectile.getX()), 2)
				+ Math.pow(Math.abs(charRouge.getY() - projectile.getY()), 2)) <= 50) {
			charRouge.touche();

		}

	}

	// par l�o-paul lapointe
	/**
	 * permet de verifier si il y a un gagnant et de lever l'�v�nement qui affichera
	 * la fenetre du gagnant
	 */
	public void verifierGagnant() {
		if (charVert.getCompteurTouche() == nbPVVert) {
			gagnantVert = false;
			leverEventGagnant();
		} else if (charRouge.getCompteurTouche() == nbPVRouge) {
			gagnantVert = true;
			leverEventGagnant();
		}
	}

	// par l�o-paul lapointe
	/**
	 * animation du projectile
	 */
	@Override
	public void run() {
		while (animEnCours) {
			avancerUnPas(pasEuler);
			try {
				Thread.sleep(TEMPS_SLEEP);
			} catch (InterruptedException e) {
				System.out.println("Processus interrompu!");
			}
		}

	}

	// par l�o-paul lapointe
	/**
	 * permet d'avancer d'un pas d'euler
	 * 
	 * @param pasEuler
	 *            le pas
	 */
	public void avancerUnPas(double pasEuler) {
		if (ventActive) {
			projectile.accelerationVent(vent, champ.getForceElectrique(projectile), explosionChamp);
		} else {
			projectile.acceleration(champ.getForceElectrique(projectile), explosionChamp);
		}
		projectile.vitesse(pasEuler);
		projectile.position(pasEuler);
		temps = temps + pasEuler;
		leverEvenPosProjectile();
		leverEventVitesseProjectile();
		collisionProjectile();
		leverEvenTemps();
		repaint();
	}

	// par l�o-paul lapointe
	/**
	 * cr�� un nouveau thread et lance le run
	 */
	public void demarrer() {
		if (animEnCours == false) {
			Thread processusAnim = new Thread(this);
			animEnCours = true;
			processusAnim.start();

		}
	}

	// par l�o-paul lapointe
	/**
	 * fait mourrir le thread
	 */
	public void arreter() {
		animEnCours = false;
		leverEventChangementTour();
		leverEventDisableComposants();
		leverEventEssence();
	}

	// par l�o-paul lapointe
	/**
	 * permet de changer la masse du projectile
	 * 
	 * @param masse
	 *            la nouvelle masse du projectile
	 */
	public void setMasseProjectile(int masse) {
		if (tourDuCharVert) {
			this.masseProjectileVert = masse;
		} else {
			this.masseProjectileRouge = masse;
		}
	}

	// par l�o-paul lapointe
	/**
	 * permet de modifier la vitesse initiale du projectile
	 * 
	 * @param vitesse
	 *            la nouvelle vitesse initiale du projectile
	 */
	public void setVitesseInitiale(double vitesse) {
		if (tourDuCharVert) {
			this.vitesseInitialeVert = vitesse;
		} else {
			this.vitesseInitialeRouge = vitesse;
		}
	}

	// par l�o-paul lapointe
	/**
	 * permet de modifier l'angle du canon des chars
	 * 
	 * @param angle
	 *            le nouvel angle en radian
	 */
	public void setAngleCanonSlider(double angle) {
		if (tourDuCharVert) {
			charVert.setAngleCanon(angle);
			repaint();
		}
	}

	// par l�o-paul lapointe
	/**
	 * Methode qui retourne la taille reelle du terrain en metre
	 * 
	 * @return La taille reelle du terrain
	 */
	public double getTailleDuTerrain() {
		return tailleDuTerrain;
	}

	// par l�o-paul lapointe
	/**
	 * ajoute l'�couteur des d�placements du char et du projectile
	 * 
	 * @param objEcout
	 */
	public void addCharListener(PositionsEtTempsListener objEcout) {
		OBJETS_ENREGISTRES.add(PositionsEtTempsListener.class, objEcout);
	}

	// par l�o-paul lapointe
	/**
	 * ajoute les �couteurs qui change la couleur des fleches
	 * 
	 * @param objEcout
	 */
	public void addFlecheListener(FlecheListener objEcout) {
		OBJETS_ENREGISTRES.add(FlecheListener.class, objEcout);
	}

	// par l�o-paul lapointe
	/**
	 * ajoute l'�couteur qui voit si il y a un gagnant
	 * 
	 * @param objEcout
	 */
	public void addGagnantListener(GagnantListener objEcout) {
		OBJETS_ENREGISTRES.add(GagnantListener.class, objEcout);
	}

	// par l�o-paul lapointe
	/**
	 * ajoute les ecouteurs qui sont reli�s aux tours
	 * 
	 * @param objEcout
	 */
	public void addTourListener(TourListener objEcout) {
		OBJETS_ENREGISTRES.add(TourListener.class, objEcout);
	}

	// par l�o-paul lapointe
	/**
	 * permet de lever le niveau d'essence
	 */
	private void leverEventEssence() {
		for (TourListener ecout : OBJETS_ENREGISTRES.getListeners(TourListener.class)) {
			ecout.niveauEssence(barreEssence);
		}
	}

	// par l�o-paul lapointe
	/**
	 * permet de lever quand il y a un changement de tour
	 */
	private void leverEventChangementTour() {
		for (TourListener ecout : OBJETS_ENREGISTRES.getListeners(TourListener.class)) {
			ecout.changementTour(tourDuCharVert);
		}
	}

	// par l�o-paul lapointe
	/**
	 * permet de lever quand il faut activer les composants d'entr�e
	 */
	private void leverEventDisableComposants() {
		for (TourListener ecout : OBJETS_ENREGISTRES.getListeners(TourListener.class)) {
			ecout.disableComposants();
		}
	}

	// par l�o-paul lapointe
	/**
	 * permet de lever quand un nouveau terrain est dessin�
	 */
	private void leverEventPremierPaint() {
		for (TourListener ecout : OBJETS_ENREGISTRES.getListeners(TourListener.class)) {
			ecout.premierPaint(gravite);
		}
	}

	// par l�o-paul lapointe
	/**
	 * permet de lever la position du char en x et y
	 */
	private void leverEvenPosChar() {
		for (PositionsEtTempsListener ecout : OBJETS_ENREGISTRES.getListeners(PositionsEtTempsListener.class)) {
			if (tourDuCharVert) {
				ecout.changementPosChar(charVert.getX(), modele.getHautUnitesReelles() - charVert.getY());
			} else {
				ecout.changementPosChar(charRouge.getX(), modele.getHautUnitesReelles() - charRouge.getY());
			}
		}
	}

	// par l�o-paul lapointe
	/**
	 * permet de lever la position du projectile en x et y
	 */
	private void leverEvenPosProjectile() {
		for (PositionsEtTempsListener ecout : OBJETS_ENREGISTRES.getListeners(PositionsEtTempsListener.class)) {
			ecout.changementPosProjectile(projectile.getX(), modele.getHautUnitesReelles() - projectile.getY());
		}
	}

	// par l�o-paul lapointe
	/**
	 * l�ve le temps �coul� depuis le d�but d'un tir
	 */
	private void leverEvenTemps() {
		for (PositionsEtTempsListener ecout : OBJETS_ENREGISTRES.getListeners(PositionsEtTempsListener.class)) {
			ecout.changementTemps(temps);
		}
	}

	// par l�o-paul lapointe
	/**
	 * l�ve la vitesse du projectile
	 */
	private void leverEventVitesseProjectile() {
		for (PositionsEtTempsListener ecout : OBJETS_ENREGISTRES.getListeners(PositionsEtTempsListener.class)) {
			ecout.changementVitesseProjectile(projectile.getVitesse());
		}
	}

	// par l�o-paul lapointe
	/**
	 * leve si il y a un gagnant
	 */
	private void leverEventGagnant() {
		for (GagnantListener ecout : OBJETS_ENREGISTRES.getListeners(GagnantListener.class)) {
			ecout.declarationGagnant(gagnantVert);
		}
	}

	// par l�o-paul lapointe
	/**
	 * l�ve que la fleche du haut est press�e
	 */
	private void leverEventFlecheHaut() {
		for (FlecheListener ecout : OBJETS_ENREGISTRES.getListeners(FlecheListener.class)) {
			ecout.flecheHaut(true);
		}
	}

	// par l�o-paul lapointe
	/**
	 * l�ve que la fleche du bas est press�e
	 */
	private void leverEventFlecheBas() {
		for (FlecheListener ecout : OBJETS_ENREGISTRES.getListeners(FlecheListener.class)) {
			ecout.flecheBas(true);
		}
	}

	// par l�o-paul lapointe
	/**
	 * l�ve que la fleche de gauche est press�e
	 */
	private void leverEventFlecheGauche() {
		for (FlecheListener ecout : OBJETS_ENREGISTRES.getListeners(FlecheListener.class)) {
			ecout.flecheGauche(true);
		}
	}

	// par l�o-paul lapointe
	/**
	 * l�ve que la fleche de droite est press�e
	 */
	private void leverEventFlecheDroite() {
		for (FlecheListener ecout : OBJETS_ENREGISTRES.getListeners(FlecheListener.class)) {
			ecout.flecheDroite(true);
		}
	}

	// par l�o-paul lapointe
	/**
	 * l�ve que la fleche du haut est relach�e
	 */
	private void leverEventFlecheHautEteindre() {
		for (FlecheListener ecout : OBJETS_ENREGISTRES.getListeners(FlecheListener.class)) {
			ecout.flecheHaut(false);
		}
	}

	// par l�o-paul lapointe
	/**
	 * l�ve que la fleche du bas est relach�e
	 */
	private void leverEventFlecheBasEteindre() {
		for (FlecheListener ecout : OBJETS_ENREGISTRES.getListeners(FlecheListener.class)) {
			ecout.flecheBas(false);
		}
	}

	// par l�o-paul lapointe
	/**
	 * l�ve que la fleche de gauche est relach�e
	 */
	private void leverEventFlecheGaucheEteindre() {
		for (FlecheListener ecout : OBJETS_ENREGISTRES.getListeners(FlecheListener.class)) {
			ecout.flecheGauche(false);
		}
	}

	// par l�o-paul lapointe
	/**
	 * l�ve que la fleche de droite est relach�e
	 */
	private void leverEventFlecheDroiteEteindre() {
		for (FlecheListener ecout : OBJETS_ENREGISTRES.getListeners(FlecheListener.class)) {
			ecout.flecheDroite(false);
		}
	}

	// par l�o-paul lapointe
	/**
	 * permet de modifier le pas d'euler
	 * 
	 * @param pasEuler
	 *            le nouveau pas
	 */
	public void setPasEuler(double pasEuler) {
		this.pasEuler = pasEuler;
	}

	// par l�o-paul lapointe
	/**
	 * retourne le pas d'euler de r�f�rence
	 * 
	 * @return le pas d'euler de r�f�rence
	 */
	public double getPAS_EULER_REFERENCE() {
		return PAS_EULER_REFERENCE;
	}

	// Andy Lam
	/**
	 * Methode qui permet de fixer la force du vent
	 * 
	 * @param vent
	 *            la force du vent
	 */
	public void setVent(Vecteur vent) {
		this.vent = vent;
	}

	// Andy Lam
	/**
	 * m�thode qui permet de reinitialiser tous les composants de la zone
	 * d'animation � ses valeurs initiales et les redessine
	 */
	public void reinitialiser() {
		if (!reinitialiser) {
			reinitialiser = true;
			premiereFois = true;
			tourDuCharVert = true;
			posRougeX = POS_X_ROUGE_INIT;
			posVertX = POS_X_VERT_INIT;
			barreEssence = BARRE_ESSENCE_INITIAL;
			explosionChamp = false;
			arreter();

			repaint();
		}
	}

	// par l�o-paul lapointe
	/**
	 * permet de modifier l'affichage ou non des vecteurs de forces
	 * 
	 * @param afficherVecteurs
	 *            la nouvelle valeur boolean
	 */
	public void setAfficherVecteurs(boolean afficherVecteurs) {
		this.afficherVecteurs = afficherVecteurs;
	}

	// par l�o-paul lapointe
	/**
	 * retourne si oui ou non les vecteurs seont affich�s
	 * 
	 * @return oui ou non les vecteurs seont affich�s
	 */
	public boolean isAfficherVecteurs() {
		return afficherVecteurs;
	}

	// par l�o-paul lapointe
	/**
	 * M�thode qui permet de regarder si le vent va affect� le projectile
	 * 
	 * @return vrai si le projectile va �tre affect�
	 */
	public boolean isVentActive() {
		return ventActive;
	}

	// par l�o-paul lapointe
	/**
	 * retoure si oui ou non le projectile est une grenade �lectrique
	 * 
	 * @return si oui ou non le projectile est une grenade �lectrique
	 */
	public boolean isGrenageElectrique() {
		return projectile.isGrenadeElectrique();
	}

	// par l�o-paul lapointe
	/**
	 * permet de choisir si le vent est actif ou pas
	 * 
	 * @param ventActive
	 *            vrai si on veut que le projectile soit affect� par le vent
	 */
	public void setVentActive(boolean ventActive) {
		this.ventActive = ventActive;
	}

	// par l�o-paul lapointe
	/**
	 * permet de modifier la charge du projectile entre plus ou moins
	 * 
	 * @param projectileChargePlus
	 *            si c'est une charge plus
	 */
	public void setProjectileChargePlus(boolean projectileChargePlus) {
		this.projectileChargePlus = projectileChargePlus;
	}

	// Andy Lam
	/**
	 * M�thode qui permet de changer le terrain
	 */
	public void changeTerrain(String choixTerrain) {
		nomNiveau = choixTerrain;
		terrain.changePointsTerrain(choixTerrain);
		String arrierePlan = "";
		String textureTerrain = "";
		switch (choixTerrain) {
		case "Terre":
			arrierePlan = "TerreBackGround.jpg";
			textureTerrain = "TerreTerrain.jpg";
			couleurProjectile = Color.BLACK;
			gravite = 9.8;
			break;
		case "Lune":
			arrierePlan = "LuneBackGround.jpg";
			textureTerrain = "LuneTerrain.jpg";
			couleurProjectile = Color.RED;
			gravite = 1.62;
			break;
		case "Mars":
			arrierePlan = "MarsBackGround.jpg";
			textureTerrain = "MarsTerrain.jpg";
			couleurProjectile = Color.BLACK;
			gravite = 3.71;
			break;
		case "Neptune":
			arrierePlan = "NeptuneBackGround.jpg";
			textureTerrain = "NeptuneTerrain.jpg";
			couleurProjectile = Color.RED;
			gravite = 11.15;
			break;
		}
		leverEventPremierPaint();
		URL fich = getClass().getClassLoader().getResource(arrierePlan);
		if (fich == null) {
			JOptionPane.showMessageDialog(null, "Fichier d'image introuvable!");
			background = false;
		} else {
			try {
				imgArrierePlan = ImageIO.read(fich);
				background = true;

			} catch (IOException e) {
				System.out.println("Erreur de lecture du fichier d'image");
			}
		}
		URL img = getClass().getClassLoader().getResource(textureTerrain);
		if (img == null) {
			JOptionPane.showMessageDialog(null, "Fichier d'image introuvable!");
			texture = false;
		} else {
			try {
				imgTerrain = ImageIO.read(img);
				texture = true;

			} catch (IOException e) {
				System.out.println("Erreur de lecture du fichier d'image");
			}
		}
		repaint();
	}

	// par l�o-paul lapointe
	/**
	 * permet de d�cider si le projectile est une grenade �lectrique ou pas
	 * 
	 * @param estGrenadeElectrique
	 */
	public void setEstGrenadeElectrique(boolean estGrenadeElectrique) {
		this.estGrenadeElectrique = estGrenadeElectrique;
	}

	// par l�o-paul lapointe
	/**
	 * M�thode qui retourne la valeur de la barre d'essence
	 * 
	 * @return la valeur de la barre d'essence
	 */
	public int getBarreEssence() {
		return barreEssence;
	}

	// par l�o-paul lapointe
	/**
	 * permet de modifier le niveau d'essence dans un char
	 * 
	 * @param barreEssence
	 *            ne nouveau niveau d'essence
	 */
	public void setBarreEssence(int barreEssence) {
		this.barreEssence = barreEssence;
	}

	// par l�o-paul lapointe
	/**
	 * permet de modifier la charge du champ g�n�r� par la grenade
	 * 
	 * @param chargeChamp
	 *            la nouvelle charge
	 */
	public void setChargeChamp(int chargeChamp) {
		this.chargeChamp = chargeChamp;
	}

	// Andy Lam
	/**
	 * M�thode qui permet de retourner le champ gravitationnel de la zone
	 * d'animation
	 * 
	 * @return le champ gravitationnel en N/kg
	 */
	public double getGravite() {
		return gravite;
	}

	// Andy Lam
	/**
	 * Methode qui permet de retourner la charge de la grenade �lectrique par d�faut
	 * 
	 * @return la charge de la grenade �lectrique par d�faut
	 */
	public double getCHARGE_GRENADE_INIT() {
		return CHARGE_GRENADE_INIT;
	}

	// Caroline Houle
	/*
	 * methode privee pour lire les sons une fois au debut
	 */
	private void lireLesSons() {
		listSons = new ArrayList<AudioClip>();
		int nbSons = fichierSons.length;
		URL url;
		AudioClip clip;

		for (int k = 0; k < nbSons; k++) {
			url = getClass().getClassLoader().getResource(fichierSons[k]);
			clip = Applet.newAudioClip(url);
			listSons.add(clip);
		}
	}
}
