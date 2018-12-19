package fenetresecondaire;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

/**
 * fenetre qui affiche le menu a propos
 * 
 * @author Léo-paul
 *
 */
public class APropos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblDuelDeChar;
	private JLabel lblUneApplicationCre;
	private JLabel lblDansLeCadre;
	private JLabel lblEnScienceInfo;
	private JLabel lblAuCollgeDe;
	private JLabel lblSessionHiver;
	private JLabel lblGroupe;
	private JLabel lblEnScienceInformatique;

	/**
	 * créé la fenetre À propos
	 */
	public APropos() {
		setLocationRelativeTo(null);
		setBounds(100, 100, 450, 285);
		setTitle("À propos de l'application");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblDuelDeChar = new JLabel("\t\t\t\t\t\t\t\t\tDuel de char\r\n\t\t\t\t");
		lblDuelDeChar.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblDuelDeChar.setBounds(111, 11, 234, 42);
		contentPane.add(lblDuelDeChar);

		lblUneApplicationCre = new JLabel("Une application cr\u00E9\u00E9e par Andy Lam et L\u00E9o-Paul Lapointe");
		lblUneApplicationCre.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUneApplicationCre.setBounds(10, 64, 414, 29);
		contentPane.add(lblUneApplicationCre);

		lblDansLeCadre = new JLabel("dans le cadre du cour ");
		lblDansLeCadre.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDansLeCadre.setBounds(129, 91, 295, 35);
		contentPane.add(lblDansLeCadre);

		lblEnScienceInfo = new JLabel("d'int\u00E9gration des apprentissages ");
		lblEnScienceInfo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEnScienceInfo.setBounds(93, 122, 414, 29);
		contentPane.add(lblEnScienceInfo);

		lblAuCollgeDe = new JLabel("Au Coll\u00E8ge de Maisonneuve");
		lblAuCollgeDe.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAuCollgeDe.setBounds(93, 189, 313, 29);
		contentPane.add(lblAuCollgeDe);

		lblSessionHiver = new JLabel("Session Hiver 2018");
		lblSessionHiver.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSessionHiver.setBounds(258, 211, 251, 35);
		contentPane.add(lblSessionHiver);

		lblGroupe = new JLabel("Groupe 01");
		lblGroupe.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGroupe.setBounds(10, 214, 214, 29);
		contentPane.add(lblGroupe);

		lblEnScienceInformatique = new JLabel("en science informatique et math\u00E9matique");
		lblEnScienceInformatique.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEnScienceInformatique.setBounds(60, 148, 320, 35);
		contentPane.add(lblEnScienceInformatique);
	}
}
