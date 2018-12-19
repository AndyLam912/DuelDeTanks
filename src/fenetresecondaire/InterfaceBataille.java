package fenetresecondaire;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import aaplication.App10DuelDeChars;
import interfaces.FlecheListener;
import interfaces.GagnantListener;
import interfaces.PositionsEtTempsListener;
import interfaces.TourListener;
import zoneanimation.Fleches;
import zoneanimation.Vent;
import zoneanimation.ZoneDAnimation;
import java.awt.SystemColor;
import javax.swing.UIManager;

/**
 * classe de d�marrage de l'application et interface
 * 
 * @author l�o-paul lapointe
 * @author Andy Lam
 *
 */
public class InterfaceBataille extends JFrame {

	private final int VITESSE_INIT_BOULET = 50;
	private final int MASSE_INIT_BOULET = 15;
	private final int CHARGE_GRENADE_INIT = 200;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JButton btnRinitialiser;
	private JMenu mnMenu;
	private JMenuItem mntmInstructions;
	private JMenuItem mntmPropos;
	private JMenuItem mntmConceptsScientifiques;
	private JMenuItem mntmMenuPrincipal;
	private JLabel lblAuTourDuVert;
	private JPanel panSortiesBoulet;
	private JLabel lblVitesseDuProjectileIm;
	private JLabel lblPositionDuProjectileIm;
	private JLabel lblPositionDuProjectile;
	private JPanel panSortiesChar;
	private JLabel lblPositionDuCharX;
	private JLabel lblPositionDuCharY;
	private JPanel panSortieTemps;
	private JLabel lblTempscoul;
	private JSeparator separator;
	private JSeparator separator_1;
	private JLabel lblNiveauDessence;
	private JProgressBar prgBarNiveauEssence;
	private JButton btnTirer;
	private JButton btnPlayPause;
	private JSeparator separator_2;
	private JButton btnPas;
	private JCheckBox chckbxAfficherLesVecteurs;
	private JPanel panGravit�;
	private JLabel lblMasseDuProjectile;
	private JSlider sliMasseBoulet;
	private JPanel panProjectile;
	private JLabel lblVitesseInitialeDu;
	private JSlider sliderVitesseProjectile;
	private JLabel lblChargeDuProjectile;
	private JRadioButton rdbPlus;
	private JRadioButton rdbMoins;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblChargeGnrPar;
	private JSpinner spnChampGrenade;
	private JCheckBox chckbxPrsenceDuVent;
	private JPanel panVent;
	private ZoneDAnimation zoneDAnimation;
	private FenGagnant fenGagnant;
	private JMenuItem mntmQuitter;
	private Vent composantVent;
	private JLabel lblPosCharX;
	private JLabel lblPosCharY;
	private JLabel lblPosProjectileX;
	private JLabel lblPosProjectileY;
	private JLabel lblDuelDeChars;
	private JLabel lblTemps;
	private Fleches fleches;
	private JLabel lblVitprojectile;
	private App10DuelDeChars frame;

	private boolean enPause = false;
	private boolean tourVert = true;
	private int[] donneeVert = { MASSE_INIT_BOULET, VITESSE_INIT_BOULET, CHARGE_GRENADE_INIT };
	private int[] donneeRouge = { MASSE_INIT_BOULET, VITESSE_INIT_BOULET, CHARGE_GRENADE_INIT };
	private boolean[] donneeBolVert = { false, true };
	private boolean[] donneeBolRouge = { false, true };
	private JPanel panel;
	private JRadioButton rdbtnObusExplosif;
	private JRadioButton rdbtnGrenadeElectrique;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JLabel lblControle;
	private JLabel lblAuTourDuRouge;
	private JLabel lblGravite;
	private JLabel lblMasse;
	private JLabel lblVitinit;
	private JLabel lblCharProj;
	private JLabel lblNKg;

	// l�o-paul
	/**
	 * Cr�e la fen�tre du jeu
	 */

	public InterfaceBataille() {

		setLocationRelativeTo(null);
		setTitle("Duel de chars");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1440, 810);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		zoneDAnimation = new ZoneDAnimation();

		btnRinitialiser = new JButton("R\u00E9initialiser");
		btnRinitialiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reinitialiser();
			}
		});

		mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);

		mntmInstructions = new JMenuItem("Instructions");
		mntmInstructions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.instructionVisible();
			}
		});
		mnMenu.add(mntmInstructions);

		mntmPropos = new JMenuItem("\u00C0 propos");
		mntmPropos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.aProposVisible();
			}
		});
		mnMenu.add(mntmPropos);

		mntmConceptsScientifiques = new JMenuItem("Concepts scientifiques");
		mntmConceptsScientifiques.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.conceptsVisible();
				;
			}
		});

		mnMenu.add(mntmConceptsScientifiques);

		mntmMenuPrincipal = new JMenuItem("Menu principal");
		mntmMenuPrincipal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(true);
				setVisible(false);
			}
		});

		mnMenu.add(mntmMenuPrincipal);

		mntmQuitter = new JMenuItem("Quitter");
		mntmQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnMenu.add(mntmQuitter);
		menuBar.add(btnRinitialiser);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.ORANGE);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		zoneDAnimation.addFlecheListener(new FlecheListener() {
			@Override
			public void flecheHaut(boolean allumer) {
				fleches.setAllumeHaut(allumer);
			}

			@Override
			public void flecheBas(boolean allumer) {
				fleches.setAllumeBas(allumer);
			}

			@Override
			public void flecheGauche(boolean allumer) {
				fleches.setAllumeGauche(allumer);
			}

			@Override
			public void flecheDroite(boolean allumer) {
				fleches.setAllumeDroite(allumer);
			}
		});
		zoneDAnimation.addCharListener(new PositionsEtTempsListener() {
			@Override
			public void changementPosChar(double x, double y) {
				lblPosCharX.setText(new DecimalFormat("##.##").format(x));
				lblPosCharY.setText(new DecimalFormat("##.##").format(y));
				prgBarNiveauEssence.setValue(zoneDAnimation.getBarreEssence());
			}

			@Override
			public void changementPosProjectile(double x, double y) {
				lblPosProjectileX.setText(new DecimalFormat("##.##").format(x));
				lblPosProjectileY.setText(new DecimalFormat("##.##").format(y));

			}

			@Override
			public void changementTemps(double temps) {
				lblTemps.setText(new DecimalFormat("##.##").format(temps));

			}

			@Override
			public void changementVitesseProjectile(double vitesse) {
				lblVitprojectile.setText(new DecimalFormat("##.##").format(vitesse));

			}
		});
		zoneDAnimation.addGagnantListener(new GagnantListener() {

			@Override
			public void declarationGagnant(boolean gagnant) {
				fenGagnant.couleurGagnant(gagnant);
				fenGagnant.setLocationRelativeTo(null);
				fenGagnant.setVisible(true);
			}

		});
		zoneDAnimation.addTourListener(new TourListener() {

			@Override
			public void niveauEssence(int essence) {
				prgBarNiveauEssence.setValue(essence);

			}

			@Override
			public void changementTour(boolean tourDuCharVert) {
				if (tourDuCharVert) {
					lblAuTourDuVert.setVisible(true);
					lblAuTourDuRouge.setVisible(false);
					tourVert = true;
					// remet les donn�es num�riques aux derni�re choisies par le char vert
					resetSliders(donneeVert[0], donneeVert[1]);
					spnChampGrenade.setValue(donneeVert[2]);

				} else {
					lblAuTourDuVert.setVisible(false);
					lblAuTourDuRouge.setVisible(true);
					tourVert = false;
					// remet les donn�es num�riques aux derni�re choisies par le char rouge
					resetSliders(donneeRouge[0], donneeRouge[1]);
					spnChampGrenade.setValue(donneeRouge[2]);

				}
				composantVent.ventAleatoire();
				zoneDAnimation.setVent(composantVent.getVent());
			}

			@Override
			public void disableComposants() {
				// r�active les composant
				enableDisable(true);
				// remet les donn�es booleen aux derni�re choisies par chaqun des chars
				if (tourVert) {
					resetRadio(donneeBolVert[0], donneeBolVert[1]);
				} else {
					resetRadio(donneeBolRouge[0], donneeBolRouge[1]);
				}
			}

			@Override
			public void premierPaint(double gravite) {
				lblGravite.setText(Double.toString(gravite));

			}
		});

		zoneDAnimation.setBounds(10, 11, 1150, 550);
		zoneDAnimation.setBackground(new Color(0, 191, 255));
		contentPane.add(zoneDAnimation);

		fenGagnant = new FenGagnant();
		fenGagnant.setApp(this);

		lblDuelDeChars = new JLabel("Duel de chars");
		lblDuelDeChars.setForeground(Color.WHITE);
		lblDuelDeChars.setFont(new Font("Tahoma", Font.BOLD, 20));
		zoneDAnimation.add(lblDuelDeChars);

		lblAuTourDuVert = new JLabel("Au tour du char vert");
		lblAuTourDuVert.setBackground(SystemColor.menu);
		lblAuTourDuVert.setBounds(10, 572, 250, 36);
		lblAuTourDuVert.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblAuTourDuVert.setForeground(Color.GREEN.darker());
		contentPane.add(lblAuTourDuVert);

		lblAuTourDuRouge = new JLabel("Au tour du char rouge");
		lblAuTourDuRouge.setBounds(10, 572, 270, 36);
		lblAuTourDuRouge.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblAuTourDuRouge.setForeground(Color.RED);
		lblAuTourDuRouge.setVisible(false);
		contentPane.add(lblAuTourDuRouge);

		panSortiesBoulet = new JPanel();
		panSortiesBoulet.setBackground(Color.PINK);
		panSortiesBoulet.setBorder(new LineBorder(new Color(0, 0, 0)));
		panSortiesBoulet.setBounds(10, 619, 250, 116);
		contentPane.add(panSortiesBoulet);
		panSortiesBoulet.setLayout(null);

		lblVitesseDuProjectileIm = new JLabel("Vitesse du projectile(m/s):");
		lblVitesseDuProjectileIm.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblVitesseDuProjectileIm.setBounds(10, 11, 164, 14);
		panSortiesBoulet.add(lblVitesseDuProjectileIm);

		lblPositionDuProjectileIm = new JLabel("Position du projectile en X(m):");
		lblPositionDuProjectileIm.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPositionDuProjectileIm.setBounds(10, 49, 190, 14);
		panSortiesBoulet.add(lblPositionDuProjectileIm);

		lblPositionDuProjectile = new JLabel("Position du projectile en Y(m):");
		lblPositionDuProjectile.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPositionDuProjectile.setBounds(10, 91, 190, 14);
		panSortiesBoulet.add(lblPositionDuProjectile);

		lblPosProjectileX = new JLabel("");
		lblPosProjectileX.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPosProjectileX.setBounds(185, 49, 51, 14);
		panSortiesBoulet.add(lblPosProjectileX);

		lblPosProjectileY = new JLabel("");
		lblPosProjectileY.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPosProjectileY.setBounds(185, 91, 51, 14);
		panSortiesBoulet.add(lblPosProjectileY);

		lblVitprojectile = new JLabel("");
		lblVitprojectile.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblVitprojectile.setBounds(182, 11, 51, 14);
		panSortiesBoulet.add(lblVitprojectile);

		panSortiesChar = new JPanel();
		panSortiesChar.setBackground(Color.PINK);
		panSortiesChar.setBorder(new LineBorder(new Color(0, 0, 0)));
		panSortiesChar.setBounds(267, 619, 215, 72);
		contentPane.add(panSortiesChar);
		panSortiesChar.setLayout(null);

		lblPositionDuCharX = new JLabel("Position du char en X(m):");
		lblPositionDuCharX.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPositionDuCharX.setBounds(10, 11, 155, 14);
		panSortiesChar.add(lblPositionDuCharX);

		lblPositionDuCharY = new JLabel("Position du char en Y(m):");
		lblPositionDuCharY.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPositionDuCharY.setBounds(10, 47, 155, 14);
		panSortiesChar.add(lblPositionDuCharY);

		lblPosCharX = new JLabel("");
		lblPosCharX.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPosCharX.setBounds(159, 11, 46, 14);
		panSortiesChar.add(lblPosCharX);

		lblPosCharY = new JLabel("");
		lblPosCharY.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPosCharY.setBounds(159, 47, 46, 14);
		panSortiesChar.add(lblPosCharY);

		panSortieTemps = new JPanel();
		panSortieTemps.setBackground(Color.PINK);
		panSortieTemps.setBorder(new LineBorder(new Color(0, 0, 0)));
		panSortieTemps.setBounds(267, 702, 215, 33);
		contentPane.add(panSortieTemps);
		panSortieTemps.setLayout(null);

		lblTempscoul = new JLabel("temps \u00E9coul\u00E9(s):");
		lblTempscoul.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTempscoul.setBounds(10, 11, 107, 14);
		panSortieTemps.add(lblTempscoul);

		lblTemps = new JLabel("");
		lblTemps.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTemps.setBounds(144, 11, 61, 14);
		panSortieTemps.add(lblTemps);

		separator = new JSeparator();
		separator.setBounds(494, 572, 2, 163);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(Color.BLACK);
		contentPane.add(separator);

		separator_1 = new JSeparator();
		separator_1.setBounds(820, 572, 2, 163);
		separator_1.setBackground(Color.BLACK);
		separator_1.setOrientation(SwingConstants.VERTICAL);
		contentPane.add(separator_1);

		lblNiveauDessence = new JLabel("Niveau d'essence");
		lblNiveauDessence.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNiveauDessence.setBounds(684, 572, 126, 14);
		contentPane.add(lblNiveauDessence);

		prgBarNiveauEssence = new JProgressBar();
		prgBarNiveauEssence.setOrientation(SwingConstants.VERTICAL);
		prgBarNiveauEssence.setBounds(707, 597, 79, 110);
		prgBarNiveauEssence.setValue(100);
		prgBarNiveauEssence.setForeground(Color.RED.darker());
		contentPane.add(prgBarNiveauEssence);

		btnTirer = new JButton("TIRER!");
		btnTirer.setBounds(832, 587, 168, 135);
		btnTirer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zoneDAnimation.tirer();
				prgBarNiveauEssence.setValue(zoneDAnimation.getBarreEssence());
				enableDisable(false);
				zoneDAnimation.requestFocus();
			}
		});
		contentPane.add(btnTirer);
		associerBoutonAvecImage(btnTirer, "Tirer.png");

		btnPlayPause = new JButton("Pause");
		btnPlayPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (enPause == false) {
					// met le pas d'euler a 0 pour stopper l'animation
					zoneDAnimation.setPasEuler(0);
					btnPlayPause.setText("Jouer");
					enPause = true;
					associerBoutonAvecImage(btnPlayPause, "Jouer.png");
				} else {
					// remet le pas d'euler a sa valeur de r�f�rence
					zoneDAnimation.setPasEuler(zoneDAnimation.getPAS_EULER_REFERENCE());
					btnPlayPause.setText("Pause");
					enPause = false;
					associerBoutonAvecImage(btnPlayPause, "Pause.png");
				}
			}
		});
		btnPlayPause.setBounds(1010, 587, 139, 63);
		contentPane.add(btnPlayPause);
		associerBoutonAvecImage(btnPlayPause, "Pause.png");

		separator_2 = new JSeparator();
		separator_2.setBounds(1159, 572, 8, 163);
		separator_2.setBackground(Color.BLACK);
		separator_2.setOrientation(SwingConstants.VERTICAL);
		contentPane.add(separator_2);

		btnPas = new JButton("Avancer d'un pas");
		btnPas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (enPause == false) {
					// met le pas d'euler a 0 pour stopper l'animation
					zoneDAnimation.setPasEuler(0);
					btnPlayPause.setText("Jouer");
					enPause = true;
					associerBoutonAvecImage(btnPlayPause, "Jouer.png");
				}
				zoneDAnimation.avancerUnPas(zoneDAnimation.getPAS_EULER_REFERENCE());
				zoneDAnimation.requestFocus();
			}
		});
		btnPas.setBounds(1010, 661, 139, 61);
		contentPane.add(btnPas);
		associerBoutonAvecImage(btnPas, "ProchainPas.png");
		chckbxAfficherLesVecteurs = new JCheckBox("Afficher les vecteurs de forces");
		chckbxAfficherLesVecteurs.setBackground(Color.PINK);
		chckbxAfficherLesVecteurs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (zoneDAnimation.isAfficherVecteurs() == false) {
					zoneDAnimation.setAfficherVecteurs(true);
				} else {
					zoneDAnimation.setAfficherVecteurs(false);
				}
				zoneDAnimation.requestFocus();
			}
		});
		chckbxAfficherLesVecteurs.setBounds(1170, 7, 210, 23);
		contentPane.add(chckbxAfficherLesVecteurs);

		panGravit� = new JPanel();
		panGravit�.setBackground(Color.PINK);
		panGravit�.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Champ gravitationnel", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panGravit�.setBounds(1170, 36, 244, 54);
		contentPane.add(panGravit�);
		panGravit�.setLayout(null);

		lblGravite = new JLabel("9.8");
		lblGravite.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblGravite.setBounds(97, 21, 54, 22);

		panGravit�.add(lblGravite);

		lblNKg = new JLabel("N/Kg");
		lblNKg.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNKg.setBounds(150, 27, 46, 14);
		panGravit�.add(lblNKg);

		panProjectile = new JPanel();
		panProjectile.setBackground(Color.PINK);
		panProjectile.setBorder(new LineBorder(new Color(0, 0, 0)));
		panProjectile.setBounds(1170, 184, 244, 301);
		contentPane.add(panProjectile);
		panProjectile.setLayout(null);

		lblVitesseInitialeDu = new JLabel("Vitesse initiale du projectile(m/s):");
		lblVitesseInitialeDu.setBounds(6, 95, 192, 14);
		panProjectile.add(lblVitesseInitialeDu);

		sliderVitesseProjectile = new JSlider();
		sliderVitesseProjectile.setBackground(Color.PINK);
		sliderVitesseProjectile.setMinorTickSpacing(10);
		sliderVitesseProjectile.setPaintLabels(true);
		sliderVitesseProjectile.setMajorTickSpacing(20);
		sliderVitesseProjectile.setPaintTicks(true);
		sliderVitesseProjectile.setMaximum(150);
		sliderVitesseProjectile.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				zoneDAnimation.setVitesseInitiale(sliderVitesseProjectile.getValue());
				// conserve les vitesses de projectiles des chars dans un tableau pour les
				// r�attribuer au bon char au d�but de son tour
				if (tourVert) {
					donneeVert[1] = sliderVitesseProjectile.getValue();
				} else {
					donneeRouge[1] = sliderVitesseProjectile.getValue();
				}
				lblVitinit.setText(Integer.toString(sliderVitesseProjectile.getValue()));
				zoneDAnimation.requestFocus();
			}
		});
		sliderVitesseProjectile.setBounds(6, 120, 224, 53);
		panProjectile.add(sliderVitesseProjectile);

		lblChargeDuProjectile = new JLabel("Charge du projectile(mC):");
		lblChargeDuProjectile.setBounds(6, 184, 168, 14);
		panProjectile.add(lblChargeDuProjectile);

		rdbPlus = new JRadioButton("+");
		rdbPlus.setBackground(Color.PINK);
		rdbPlus.setSelected(true);
		rdbPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zoneDAnimation.setProjectileChargePlus(true);
				// conserve la charge plus ou moins boolean des chars dans un tableau pour les
				// r�attribuer au bon char au d�but de son tour
				if (tourVert) {
					donneeBolVert[1] = true;
				} else {
					donneeBolRouge[1] = true;
				}
				lblCharProj.setText("1");
				zoneDAnimation.requestFocus();
			}
		});
		buttonGroup.add(rdbPlus);
		rdbPlus.setBounds(10, 205, 109, 23);
		panProjectile.add(rdbPlus);

		rdbMoins = new JRadioButton("-");
		rdbMoins.setBackground(Color.PINK);
		rdbMoins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zoneDAnimation.setProjectileChargePlus(false);
				// conserve la charge plus ou moins boolean des chars dans un tableau pour les
				// r�attribuer au bon char au d�but de son tour
				if (tourVert) {
					donneeBolVert[1] = false;
				} else {
					donneeBolRouge[1] = false;
				}
				lblCharProj.setText("-1");
				zoneDAnimation.requestFocus();
			}
		});
		buttonGroup.add(rdbMoins);
		rdbMoins.setBounds(121, 205, 109, 23);
		panProjectile.add(rdbMoins);

		lblChargeGnrPar = new JLabel("Charge g\u00E9n\u00E9r\u00E9 par la grenade(mC)");
		lblChargeGnrPar.setBounds(6, 239, 220, 14);
		panProjectile.add(lblChargeGnrPar);

		spnChampGrenade = new JSpinner();
		spnChampGrenade.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				zoneDAnimation.setChargeChamp((int) spnChampGrenade.getValue());
				// conserve le type de projectile boolean des chars dans un tableau pour les
				// r�attribuer au bon char au d�but de son tour
				if (tourVert) {
					donneeVert[2] = (int) spnChampGrenade.getValue();
				} else {
					donneeRouge[2] = (int) spnChampGrenade.getValue();
				}
				zoneDAnimation.requestFocus();
			}
		});
		spnChampGrenade.setModel(new SpinnerNumberModel(150, 50, 300, 1));
		spnChampGrenade.setBounds(10, 264, 224, 26);
		panProjectile.add(spnChampGrenade);

		sliMasseBoulet = new JSlider();
		sliMasseBoulet.setBackground(Color.PINK);
		sliMasseBoulet.setPaintLabels(true);
		sliMasseBoulet.setMajorTickSpacing(10);
		sliMasseBoulet.setPaintTicks(true);
		sliMasseBoulet.setMaximum(50);
		sliMasseBoulet.setMinimum(1);
		sliMasseBoulet.setBounds(6, 39, 224, 45);
		panProjectile.add(sliMasseBoulet);
		sliMasseBoulet.setValue(15);

		lblMasseDuProjectile = new JLabel("Masse du projectile(g):");
		lblMasseDuProjectile.setBounds(6, 11, 129, 14);
		panProjectile.add(lblMasseDuProjectile);

		lblMasse = new JLabel("");
		lblMasse.setBounds(184, 11, 46, 14);
		lblMasse.setText(Integer.toString(sliMasseBoulet.getValue()));
		panProjectile.add(lblMasse);

		lblVitinit = new JLabel("");
		lblVitinit.setBounds(205, 95, 25, 14);
		lblVitinit.setText(Integer.toString(sliderVitesseProjectile.getValue()));
		panProjectile.add(lblVitinit);

		lblCharProj = new JLabel("");
		lblCharProj.setBounds(180, 184, 46, 14);
		panProjectile.add(lblCharProj);
		sliMasseBoulet.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				zoneDAnimation.setMasseProjectile(sliMasseBoulet.getValue());
				// conserve les masses de projectiles des chars dans un tableau pour les
				// r�attribuer au bon char au d�but de son tour
				if (tourVert) {
					donneeVert[0] = sliMasseBoulet.getValue();
				} else {
					donneeRouge[0] = sliMasseBoulet.getValue();
				}
				lblMasse.setText(Integer.toString(sliMasseBoulet.getValue()));
				zoneDAnimation.requestFocus();
			}
		});

		panVent = new JPanel();
		panVent.setBackground(Color.PINK);
		panVent.setBorder(new LineBorder(new Color(0, 0, 0)));
		panVent.setBounds(1170, 496, 244, 239);
		contentPane.add(panVent);
		panVent.setLayout(null);

		composantVent = new Vent();
		composantVent.setBackground(new Color(255, 175, 175));
		composantVent.setBounds(29, 42, 186, 186);
		panVent.add(composantVent);

		chckbxPrsenceDuVent = new JCheckBox("Pr\u00E9sence du vent");
		chckbxPrsenceDuVent.setBackground(Color.PINK);
		chckbxPrsenceDuVent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// permet de fixer si le vent sera present ou pas avec une case a cocher
				if (zoneDAnimation.isVentActive()) {
					zoneDAnimation.setVentActive(false);
				} else {
					zoneDAnimation.setVentActive(true);
				}
				zoneDAnimation.requestFocus();
			}
		});
		chckbxPrsenceDuVent.setBounds(6, 7, 139, 23);
		panVent.add(chckbxPrsenceDuVent);

		fleches = new Fleches();
		fleches.setBackground(Color.ORANGE);
		fleches.setBounds(506, 597, 173, 110);
		contentPane.add(fleches);

		panel = new JPanel();
		panel.setBackground(Color.PINK);
		panel.setBorder(
				new TitledBorder(null, "Type de projectile", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(1170, 101, 244, 72);
		contentPane.add(panel);
		panel.setLayout(null);

		rdbtnObusExplosif = new JRadioButton("obus explosif");
		rdbtnObusExplosif.setBackground(Color.PINK);
		rdbtnObusExplosif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// fixe le type de projectile a l'obus explosif
				zoneDAnimation.setEstGrenadeElectrique(false);
				if (tourVert) {
					donneeBolVert[0] = false;
				} else {
					donneeBolRouge[0] = false;
				}
				zoneDAnimation.requestFocus();
			}
		});
		rdbtnObusExplosif.setSelected(true);
		buttonGroup_1.add(rdbtnObusExplosif);
		rdbtnObusExplosif.setBounds(6, 17, 149, 23);
		panel.add(rdbtnObusExplosif);

		rdbtnGrenadeElectrique = new JRadioButton("Grenade \u00E9lectrique");
		rdbtnGrenadeElectrique.setBackground(Color.PINK);
		rdbtnGrenadeElectrique.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// fixe le type de projectile a la grenade �lectrique
				zoneDAnimation.setEstGrenadeElectrique(true);
				if (tourVert) {
					donneeBolVert[0] = true;
				} else {
					donneeBolRouge[0] = true;
				}
				zoneDAnimation.requestFocus();
			}
		});
		buttonGroup_1.add(rdbtnGrenadeElectrique);
		rdbtnGrenadeElectrique.setBounds(6, 43, 164, 23);
		panel.add(rdbtnGrenadeElectrique);

		lblControle = new JLabel("Controle clavier \r\n");
		lblControle.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblControle.setBounds(536, 572, 111, 14);
		contentPane.add(lblControle);

		zoneDAnimation.setVent(composantVent.getVent());
	}

	// Andy Lam
	/**
	 * M�thode qui permet � cette fen�tre de recevoir le focus
	 */
	public void getFocus() {
		zoneDAnimation.requestFocusInWindow();

	}

	// Andy Lam
	/**
	 * M�thode qui permet de r�initialiser les parametres de l'interface de bataille
	 * et du terrain
	 */
	public void reinitialiser() {
		zoneDAnimation.reinitialiser();
		sliMasseBoulet.setValue(MASSE_INIT_BOULET);
		sliderVitesseProjectile.setValue(VITESSE_INIT_BOULET);
		spnChampGrenade.setValue(CHARGE_GRENADE_INIT);
		rdbtnObusExplosif.doClick();
		rdbPlus.doClick();
		composantVent.ventAleatoire();
		zoneDAnimation.setVent(composantVent.getVent());
		getFocus();
	}

	// Andy Lam
	/**
	 * M�thode qui permet de changer le terrain d�pandanment selon le choix du
	 * terrain
	 * 
	 * @param choixTerrain
	 *            le nom du choix du terrain choisi par l'utilisateur
	 */
	public void changeTerrain(String choixTerrain) {
		zoneDAnimation.reinitialiser();
		zoneDAnimation.changeTerrain(choixTerrain);
	}

	// Andy Lam
	/**
	 * M�thode qui retourne qui retourne la fen�tre m�re de cette fen�tre
	 * 
	 * @return la fen�tre m�re de cette fen�tre
	 */
	public App10DuelDeChars getFrame() {
		return frame;
	}
	/**
	 * Methode qui permet de fixer la fenetre m�re � cet objet
	 * @param frame la fen�tre m�re qu'on veut lier avec cet objet
	 */
	public void setFrame(App10DuelDeChars frame) {
		this.frame = frame;
	}

	// par l�o-paul lapointe
	/**
	 * permet de remettre les sliders aux derni�res valeurs enregistr�es par les
	 * utilisateurs � leur tour
	 * 
	 * @param masse
	 *            la masse du projectile
	 * @param vitesse
	 *            la vitesse du projectile
	 */
	public void resetSliders(int masse, int vitesse) {
		sliMasseBoulet.setValue(masse);
		sliderVitesseProjectile.setValue(vitesse);
	}

	// par l�o-paul lapointe
	/**
	 * permet de remettre les boutons radio aux derni�res valeurs enregistr�es par
	 * les utilisateurs � leur tour
	 * 
	 * @param grenade
	 *            vrai si le projectile est une grenade �lectrique
	 * @param plus
	 *            vrai si la charge du projectile est plus
	 */
	public void resetRadio(boolean grenade, boolean plus) {
		if (grenade) {
			rdbtnGrenadeElectrique.doClick();
		} else {
			rdbtnObusExplosif.doClick();
		}
		if (plus) {
			rdbPlus.doClick();
		} else {
			rdbMoins.doClick();
		}
		panel.repaint();
		panProjectile.repaint();
		composantVent.repaint();
	}

	// par l�o-paul lapointe
	/**
	 * permet d'activer et de d�sactiver les entr�es de l'application
	 * 
	 * @param enable
	 *            vrai si les entr�es sont activ�es
	 */
	public void enableDisable(boolean enable) {
		btnTirer.setEnabled(enable);
		sliMasseBoulet.setEnabled(enable);
		sliderVitesseProjectile.setEnabled(enable);
		rdbtnGrenadeElectrique.setEnabled(enable);
		rdbtnObusExplosif.setEnabled(enable);
		rdbPlus.setEnabled(enable);
		rdbMoins.setEnabled(enable);
		spnChampGrenade.setEnabled(enable);

	}

	/**
	 * @author Caroline Houle
	 * @param leBouton
	 *            bouton qu'on desire associer avec une image
	 * @param fichierImage
	 *            image qu'on desire ajouter sur un bouton
	 */
	public void associerBoutonAvecImage(JButton leBouton, String fichierImage) {
		Image imgLue = null;
		URL urlImage = getClass().getClassLoader().getResource(fichierImage);
		if (urlImage == null) {
			JOptionPane.showMessageDialog(null, "Fichier " + fichierImage + " introuvable");
		}
		try {
			imgLue = ImageIO.read(urlImage);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erreur pendant la lecture du fichier d'image");
		}
		// redimensionner l'image de la m�me grandeur que le bouton
		Image imgRedim = imgLue.getScaledInstance(leBouton.getWidth(), leBouton.getHeight(), Image.SCALE_SMOOTH);
		// au cas o� le fond de l�image serait transparent
		leBouton.setOpaque(false);
		leBouton.setContentAreaFilled(false);
		leBouton.setBorderPainted(false);
		// associer l'image au bouton
		leBouton.setText("");
		leBouton.setIcon(new ImageIcon(imgRedim));
		// on se d�barrasse des images interm�diaires
		imgLue.flush();
		imgRedim.flush();
	}
}
