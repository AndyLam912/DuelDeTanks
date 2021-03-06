package aaplication;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import fenetresecondaire.APropos;
import fenetresecondaire.ChoixDeTerrain;
import fenetresecondaire.ConceptScientifique;
import fenetresecondaire.Instructions;
import fenetresecondaire.InterfaceBataille;
import interfaces.ChoixTerrainListener;
import zoneanimation.PanelAvecImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Classe qui permet de cr�er le menu du jeu et de ses composants et de le
 * d�marrer
 * 
 * @author Andy Lam
 *
 *
 */
public class App10DuelDeChars extends JFrame {

	private static final long serialVersionUID = 1L;
	private final String NOM_IMAGE_MENU = "MenuPrincipal.jpg";
	private String[][] nomImageBoutons = { { "BoutonJouer.png", "BoutonJouerClaire.png" },
			{ "BoutonInstructions.png", "BoutonInstructionsClaire.png" },
			{ "BoutonConceptScientifique.png", "BoutonConceptScientifiqueClaire.png" },
			{ "BoutonAPropos.png", "BoutonAProposClaire.png" } };

	private PanelAvecImage contentPane;
	private JButton btnJouer;
	private JButton btnInstructions;
	private JButton btnConceptsScientifiques;
	private JButton btnPropos;
	private JMenuBar menuBar;
	private JMenu mnMenu;
	private JMenuItem mntmQuitter;
	private static App10DuelDeChars frame = new App10DuelDeChars();
	private InterfaceBataille bataille;
	private ChoixDeTerrain fenChoixTerrain;
	private Instructions fenInstruction;
	private APropos fenAPropos;
	private ConceptScientifique fenConcepts;

	/**
	 * D�marrage de l'application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Cr�ation de l'application
	 */
	public App10DuelDeChars() {
		bataille = new InterfaceBataille();
		bataille.setFrame(this);

		fenChoixTerrain = new ChoixDeTerrain();
		fenChoixTerrain.setApp(this);

		fenInstruction = new Instructions();
		fenAPropos = new APropos();
		fenConcepts = new ConceptScientifique();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 973, 690);
		contentPane = new PanelAvecImage();
		contentPane.setNomImage(NOM_IMAGE_MENU);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		setTitle("Duel de chars");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);

		mntmQuitter = new JMenuItem("Quitter");
		mntmQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnMenu.add(mntmQuitter);

		btnJouer = new JButton("Jouer");
		btnJouer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				associerBoutonAvecImage(btnJouer, nomImageBoutons[0][1]);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				associerBoutonAvecImage(btnJouer, nomImageBoutons[0][0]);
			}
		});
		btnJouer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fenChoixTerrain.setLocationRelativeTo(null);
				fenChoixTerrain.setVisible(true);

				frame.dispose();
			}
		});
		btnJouer.setBounds(328, 204, 300, 74);
		contentPane.add(btnJouer);
		associerBoutonAvecImage(btnJouer, nomImageBoutons[0][0]);

		btnInstructions = new JButton("Instructions");
		btnInstructions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				associerBoutonAvecImage(btnInstructions, nomImageBoutons[1][1]);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				associerBoutonAvecImage(btnInstructions, nomImageBoutons[1][0]);
			}
		});
		btnInstructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instructionVisible();
			}
		});
		btnInstructions.setBounds(328, 288, 300, 74);
		contentPane.add(btnInstructions);
		associerBoutonAvecImage(btnInstructions, nomImageBoutons[1][0]);

		btnConceptsScientifiques = new JButton("Concepts Scientifiques");
		btnConceptsScientifiques.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				associerBoutonAvecImage(btnConceptsScientifiques, nomImageBoutons[2][1]);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				associerBoutonAvecImage(btnConceptsScientifiques, nomImageBoutons[2][0]);
			}
		});
		btnConceptsScientifiques.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conceptsVisible();
			}
		});
		btnConceptsScientifiques.setBounds(328, 373, 300, 74);
		contentPane.add(btnConceptsScientifiques);
		associerBoutonAvecImage(btnConceptsScientifiques, nomImageBoutons[2][0]);

		btnPropos = new JButton("\u00C0 propos");
		btnPropos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				associerBoutonAvecImage(btnPropos, nomImageBoutons[3][1]);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				associerBoutonAvecImage(btnPropos, nomImageBoutons[3][0]);
			}
		});
		btnPropos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aProposVisible();
			}
		});
		btnPropos.setBounds(328, 458, 300, 74);
		contentPane.add(btnPropos);
		associerBoutonAvecImage(btnPropos, nomImageBoutons[3][0]);

		fenChoixTerrain.addChoixTerrainListener(new ChoixTerrainListener() {
			public void terrainChange(String choixTerrain) {
				bataille.changeTerrain(choixTerrain);
			}
		});
	}

	/**
	 * m�thode qui permet d'afficher la fen�tre de jeu du champ de bataille
	 */
	public void initInterfaceJeu() {
		bataille.setLocationRelativeTo(null);
		bataille.setVisible(true);
		bataille.getFocus();
		this.setVisible(false);
	}

	/**
	 * Affiche la fen�tre des instructions
	 */
	public void instructionVisible() {
		fenInstruction.setLocationRelativeTo(null);
		fenInstruction.setVisible(true);
	}

	/**
	 * Affiche la fen�tre les informations � propos des auteurs
	 */
	public void aProposVisible() {
		fenAPropos.setLocationRelativeTo(null);
		fenAPropos.setVisible(true);
	}

	/**
	 * Affiche la fen�tre les concepts scientifiques utilis�s dans la cr�ation de ce
	 * programme
	 */
	public void conceptsVisible() {
		fenConcepts.setLocationRelativeTo(null);
		fenConcepts.setVisible(true);
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
