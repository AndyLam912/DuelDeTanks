package fenetresecondaire;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Classe qui permet de cr�e la fen�tre contenant les concepts scientifiques
 * utilis�s pour la cr�ation de cette application
 * 
 * @author Andy Lam
 *
 */
public class ConceptScientifique extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Cr�e la fen�tre contenant les concepts scientifiques utilis�s durant la
	 * cr�ation de cette application
	 */
	public ConceptScientifique() {
		setTitle("Les Concepts Scientifiques");
		setLocationRelativeTo(null);
		setBounds(100, 100, 1020, 659);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.ORANGE);
		
		ImageAvecDefilement panConcepts = new ImageAvecDefilement();
		panConcepts.setBounds(35, 11, 941, 541);
		contentPane.add(panConcepts);
		
		//Pour modifier la largeur du cadre 
		panConcepts.setLargeurCadre(10);
		//Pour charger l'image pre-fabriquee
		panConcepts.setFichierImage("ConceptScientifique.jpg");
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnQuitter.setBounds(831, 573, 145, 23);
		contentPane.add(btnQuitter);
		
		
		
	}

}
