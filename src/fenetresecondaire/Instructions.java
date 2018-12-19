package fenetresecondaire;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * menu d�roulant qui affiche les instructions
 * @author L�o-paul
 *
 */
public class Instructions extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Constructeur: cr�e une fen�tre qui inclut une instance d'image avec d�filement
	 */
	public Instructions() {
		setTitle("D\u00E9monstration : composant pouvant servir \u00E0 afficher de l'aide");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1020, 659);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageAvecDefilement panConcepts = new ImageAvecDefilement();
		
		
		panConcepts.setBounds(35, 11, 941, 541);
		contentPane.add(panConcepts);
		
		//Pour fixer la couleur du cadre
		panConcepts.setBackground(Color.WHITE);
		//Pour modifier la largeur du cadre 
		panConcepts.setLargeurCadre(10);
		//Pour charger l'image pre-fabriquee
		panConcepts.setFichierImage("instructions.jpg");
		
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnQuitter.setBounds(831, 573, 145, 23);
		contentPane.add(btnQuitter);
		
	
	}//fin constructeur
}//fin classe
