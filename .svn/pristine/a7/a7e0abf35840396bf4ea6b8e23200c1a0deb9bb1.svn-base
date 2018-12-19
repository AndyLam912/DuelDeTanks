package fenetresecondaire;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * fenetre qui indique qui est le gagnant de la partie
 * 
 * @author léo-paul lapointe
 *
 */
public class FenGagnant extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblVictoire;
	private JLabel lblPar;
	private JLabel lblCharGagnant;
	private JButton btnMenuPrincipal;
	private JButton btnRecommencer;
	private InterfaceBataille app;

	/**
	 * créé la fenetre
	 */
	public FenGagnant() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblVictoire = new JLabel("Victoire");
		lblVictoire.setHorizontalAlignment(SwingConstants.CENTER);
		lblVictoire.setFont(new Font("Dialog", Font.BOLD, 24));
		lblVictoire.setBounds(96, 0, 238, 54);
		contentPane.add(lblVictoire);

		lblPar = new JLabel("du");
		lblPar.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPar.setHorizontalAlignment(SwingConstants.CENTER);
		lblPar.setBounds(131, 44, 166, 54);
		contentPane.add(lblPar);

		lblCharGagnant = new JLabel("Char Vert");
		lblCharGagnant.setForeground(Color.GREEN);
		lblCharGagnant.setBackground(Color.WHITE);
		lblCharGagnant.setHorizontalAlignment(SwingConstants.CENTER);
		lblCharGagnant.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblCharGagnant.setBounds(96, 94, 238, 54);
		contentPane.add(lblCharGagnant);

		btnMenuPrincipal = new JButton("Menu Principal");
		btnMenuPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				app.getFrame().setVisible(true);
				setVisible(false);
				app.setVisible(false);

			}
		});
		btnMenuPrincipal.setBounds(24, 159, 187, 74);
		contentPane.add(btnMenuPrincipal);

		btnRecommencer = new JButton("Recommencer");
		btnRecommencer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				app.reinitialiser();
				setVisible(false);
			}
		});
		btnRecommencer.setBounds(237, 159, 187, 74);
		contentPane.add(btnRecommencer);
	}

	/**
	 * methode qui permet de changer le label
	 * 
	 * @param victoireVert
	 *            vrai si le char vert a gagné
	 */
	public void couleurGagnant(boolean victoireVert) {
		if (victoireVert) {
			lblCharGagnant.setForeground(Color.GREEN);
			lblCharGagnant.setText("Char Vert");
		} else {
			lblCharGagnant.setForeground(Color.RED);
			lblCharGagnant.setText("Char Rouge");
		}

	}

	/**
	 * Méthode qui permet d'établir le lien entre cette classe et le menu principale
	 * 
	 * @param app
	 */
	public void setApp(InterfaceBataille app) {
		this.app = app;
	}
}