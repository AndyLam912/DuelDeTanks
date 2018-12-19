package fenetresecondaire;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import javax.swing.event.EventListenerList;

import aaplication.App10DuelDeChars;
import interfaces.ChoixTerrainListener;
import zoneanimation.PanelAvecImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

/**
 * Cr�e la fen�tre contenant les choix de niveau que l'utilisateur peut
 * selectionner
 * 
 * @author Andy Lam
 *
 */
public class ChoixDeTerrain extends JFrame {

	private static final long serialVersionUID = 1L;
	private final String NOM_ARRIERE_PLAN = "BackGroundChoixNiv.jpg";
	private String[][] nomImageBoutons = { { "NiveauTerre.png", "NiveauTerreClaire.png" },
			{ "NiveauLune.png", "NiveauLuneClaire.png" }, { "NiveauMars.png", "NiveauMarsClaire.png" },
			{ "NiveauNeptune.png", "NiveauNeptuneClaire.png" }, { "BoutonRetour.png", "BoutonRetourClaire.png" } };
	private PanelAvecImage contentPane;
	private JButton btnRetourner;
	private JLabel lblVeuillerChoisirVotre;
	private JButton btnTerre;
	private JButton btnLune;
	private JLabel lblTerre;
	private JLabel lblLune;
	private JButton btnMars;
	private JButton btnNeptune;
	private JLabel lblMars;
	private JLabel lblNeptune;
	private JSeparator separator;
	private App10DuelDeChars app;
	private JLabel lblvalGravTerre;
	private JLabel lblChampGrav;
	private JLabel labelChampGrav2;
	private JLabel lblvalGravMars;
	private JLabel labelChampGrav1;
	private JLabel lblvalGravLune;
	private JLabel labelChampGrav3;
	private JLabel lblvalGravNept;
	private final EventListenerList OBJETS_ENREGISTRES = new EventListenerList();

	/**
	 * Cr�e la fen�tre du choix de terrain
	 */
	public ChoixDeTerrain() {

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 940, 469);
		setTitle("Choix d'un niveau");
		contentPane = new PanelAvecImage();
		contentPane.setNomImage(NOM_ARRIERE_PLAN);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnRetourner = new JButton("Retourner");
		btnRetourner.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				associerBoutonAvecImage(btnRetourner, nomImageBoutons[4][1]);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				associerBoutonAvecImage(btnRetourner, nomImageBoutons[4][0]);
			}
		});
		btnRetourner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				app.setVisible(true);
			}
		});
		btnRetourner.setBounds(10, 11, 137, 67);
		contentPane.add(btnRetourner);
		associerBoutonAvecImage(btnRetourner, nomImageBoutons[4][0]);

		lblVeuillerChoisirVotre = new JLabel("Veuillez choisir votre niveau");
		lblVeuillerChoisirVotre.setForeground(Color.BLACK);
		lblVeuillerChoisirVotre.setBackground(Color.LIGHT_GRAY);
		lblVeuillerChoisirVotre.setForeground(Color.WHITE);
		lblVeuillerChoisirVotre.setHorizontalAlignment(SwingConstants.CENTER);
		lblVeuillerChoisirVotre.setFont(new Font("Haettenschweiler", Font.ITALIC, 48));
		lblVeuillerChoisirVotre.setBounds(157, 22, 620, 56);
		contentPane.add(lblVeuillerChoisirVotre);

		btnTerre = new JButton("Terre");
		btnTerre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				associerBoutonAvecImage(btnTerre, nomImageBoutons[0][1]);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				associerBoutonAvecImage(btnTerre, nomImageBoutons[0][0]);
			}
		});
		btnTerre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				leverEvenChoixTerrain("Terre");
				app.initInterfaceJeu();
				dispose();

			}
		});
		btnTerre.setBounds(10, 111, 457, 110);
		contentPane.add(btnTerre);
		associerBoutonAvecImage(btnTerre, nomImageBoutons[0][0]);

		btnLune = new JButton("Lune");
		btnLune.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				associerBoutonAvecImage(btnLune, nomImageBoutons[1][1]);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				associerBoutonAvecImage(btnLune, nomImageBoutons[1][0]);
			}
		});

		btnLune.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				app.initInterfaceJeu();
				leverEvenChoixTerrain("Lune");
				dispose();
			}
		});
		btnLune.setBounds(475, 111, 439, 110);
		contentPane.add(btnLune);
		associerBoutonAvecImage(btnLune, nomImageBoutons[1][0]);

		btnMars = new JButton("Mars");
		btnMars.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				associerBoutonAvecImage(btnMars, nomImageBoutons[2][1]);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				associerBoutonAvecImage(btnMars, nomImageBoutons[2][0]);
			}
		});
		btnMars.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				app.initInterfaceJeu();
				leverEvenChoixTerrain("Mars");
				dispose();

			}
		});
		btnMars.setBounds(10, 277, 457, 110);
		contentPane.add(btnMars);
		associerBoutonAvecImage(btnMars, nomImageBoutons[2][0]);

		btnNeptune = new JButton("Neptune");
		btnNeptune.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				associerBoutonAvecImage(btnNeptune, nomImageBoutons[3][1]);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				associerBoutonAvecImage(btnNeptune, nomImageBoutons[3][0]);
			}
		});
		btnNeptune.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				app.initInterfaceJeu();
				leverEvenChoixTerrain("Neptune");
				dispose();
			}
		});
		btnNeptune.setBounds(475, 277, 439, 110);
		contentPane.add(btnNeptune);
		associerBoutonAvecImage(btnNeptune, nomImageBoutons[3][0]);

		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(471, 111, 39, 319);
		contentPane.add(separator);

		lblTerre = new JLabel("Terre");
		lblTerre.setForeground(Color.WHITE);
		lblTerre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTerre.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		lblTerre.setBounds(10, 232, 188, 36);
		contentPane.add(lblTerre);

		lblLune = new JLabel("Lune");
		lblLune.setForeground(Color.WHITE);
		lblLune.setHorizontalAlignment(SwingConstants.CENTER);
		lblLune.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		lblLune.setBounds(475, 230, 188, 36);
		contentPane.add(lblLune);

		lblMars = new JLabel("Mars");
		lblMars.setForeground(Color.WHITE);
		lblMars.setHorizontalAlignment(SwingConstants.CENTER);
		lblMars.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		lblMars.setBounds(10, 394, 188, 36);
		contentPane.add(lblMars);

		lblNeptune = new JLabel("Neptune");
		lblNeptune.setForeground(Color.WHITE);
		lblNeptune.setHorizontalAlignment(SwingConstants.CENTER);
		lblNeptune.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		lblNeptune.setBounds(475, 394, 188, 36);
		contentPane.add(lblNeptune);

		lblChampGrav = new JLabel("Champ gravitationnel : ");
		lblChampGrav.setForeground(Color.WHITE);
		lblChampGrav.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblChampGrav.setBounds(173, 232, 177, 34);
		contentPane.add(lblChampGrav);

		labelChampGrav2 = new JLabel("Champ gravitationnel : ");
		labelChampGrav2.setForeground(Color.WHITE);
		labelChampGrav2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelChampGrav2.setBounds(173, 403, 177, 27);
		contentPane.add(labelChampGrav2);

		labelChampGrav1 = new JLabel("Champ gravitationnel : ");
		labelChampGrav1.setForeground(Color.WHITE);
		labelChampGrav1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelChampGrav1.setBounds(627, 239, 177, 27);
		contentPane.add(labelChampGrav1);

		labelChampGrav3 = new JLabel("Champ gravitationnel : ");
		labelChampGrav3.setForeground(Color.WHITE);
		labelChampGrav3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelChampGrav3.setBounds(627, 403, 177, 27);
		contentPane.add(labelChampGrav3);

		lblvalGravTerre = new JLabel("9.8 N/kg");
		lblvalGravTerre.setForeground(Color.WHITE);
		lblvalGravTerre.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblvalGravTerre.setHorizontalAlignment(SwingConstants.CENTER);
		lblvalGravTerre.setBounds(318, 232, 119, 42);
		contentPane.add(lblvalGravTerre);

		lblvalGravLune = new JLabel("1.62 N/kg");
		lblvalGravLune.setForeground(Color.WHITE);
		lblvalGravLune.setHorizontalAlignment(SwingConstants.CENTER);
		lblvalGravLune.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblvalGravLune.setBounds(766, 230, 119, 36);
		contentPane.add(lblvalGravLune);

		lblvalGravMars = new JLabel("3.71 N/kg");
		lblvalGravMars.setForeground(Color.WHITE);
		lblvalGravMars.setHorizontalAlignment(SwingConstants.CENTER);
		lblvalGravMars.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblvalGravMars.setBounds(318, 394, 119, 36);
		contentPane.add(lblvalGravMars);

		lblvalGravNept = new JLabel("11.15 N/kg");
		lblvalGravNept.setForeground(Color.WHITE);
		lblvalGravNept.setHorizontalAlignment(SwingConstants.CENTER);
		lblvalGravNept.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblvalGravNept.setBounds(766, 394, 119, 36);
		contentPane.add(lblvalGravNept);
	}

	/**
	 * M�thode qui permet de fixer le lien entre cette classe et la fen�tre du menu
	 * principale
	 * 
	 * @param app
	 *            la fen�tre m�re de l'application
	 */
	public void setApp(App10DuelDeChars app) {
		this.app = app;
	}

	/**
	 * M�thode qui permet de lever le choix du terrain
	 * 
	 * @param choixTerrain
	 *            le choix du terrain choisi par l'utilisateur
	 */
	private void leverEvenChoixTerrain(String choixTerrain) {
		for (ChoixTerrainListener ecout : OBJETS_ENREGISTRES.getListeners(ChoixTerrainListener.class)) {
			ecout.terrainChange(choixTerrain);
		}
	}

	/**
	 * M�thode qui permet d'ajouter un objet lorsqu'il a y un �v�nement
	 * 
	 * @param objEcout
	 *            objet � ajouter dans le objEcout
	 */
	public void addChoixTerrainListener(ChoixTerrainListener objEcout) {
		OBJETS_ENREGISTRES.add(ChoixTerrainListener.class, objEcout);
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
