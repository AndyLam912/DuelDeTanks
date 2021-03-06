 package zoneanimation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

/**
 * cr�� et dessine des fl�ches directionnelles qui s'illuminent � l'auppuis de
 * ces touches
 * 
 * @author L�o-paul Lapointe
 *
 */
public class Fleches extends JPanel {
	private int tailleCases = 50;
	private boolean allumeHaut = false;
	private boolean allumeBas = false;
	private boolean allumeGauche = false;
	private boolean allumeDroite = false;

	/**
	 * cr��e les fl�ches
	 */
	public Fleches() {
		
	}

	private static final long serialVersionUID = 1L;

	/**
	 * cr��e les formes qui composent les fl�ches et les dessinent
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		Rectangle2D caseGauche = new Rectangle2D.Double(0, getHeight() - tailleCases, tailleCases, tailleCases);
		Rectangle2D caseBas = new Rectangle2D.Double(60, getHeight() - tailleCases, tailleCases, tailleCases);
		Rectangle2D caseDroite = new Rectangle2D.Double(120, getHeight() - tailleCases, tailleCases, tailleCases);
		Rectangle2D caseHaut = new Rectangle2D.Double(60, getHeight() - tailleCases - 60, tailleCases, tailleCases);
		Path2D fleche1 = new Path2D.Double();
		Path2D fleche2 = new Path2D.Double();
		Path2D fleche3 = new Path2D.Double();
		Path2D fleche4 = new Path2D.Double();
		fleche1.moveTo(30, getHeight() - 20);
		fleche1.lineTo(20, getHeight() - 25);
		fleche1.lineTo(30, getHeight() - 30);
		fleche2.moveTo(80, getHeight() - 30);
		fleche2.lineTo(85, getHeight() - 20);
		fleche2.lineTo(90, getHeight() - 30);
		fleche3.moveTo(140, getHeight() - 20);
		fleche3.lineTo(150, getHeight() - 25);
		fleche3.lineTo(140, getHeight() - 30);
		fleche4.moveTo(80, getHeight() - 80);
		fleche4.lineTo(85, getHeight() - 90);
		fleche4.lineTo(90, getHeight() - 80);
		if (allumeHaut ) {
			g2d.setColor(Color.YELLOW);
		}else {
			g2d.setColor(Color.GRAY);
		}
		g2d.fill(caseHaut);
		if (allumeBas ) {
			g2d.setColor(Color.YELLOW);
		}else {
			g2d.setColor(Color.GRAY);
		}
		g2d.fill(caseBas);
		if (allumeGauche ) {
			g2d.setColor(Color.YELLOW);
		}else {
			g2d.setColor(Color.GRAY);
		}
		g2d.fill(caseGauche);
		if (allumeDroite ) {
			g2d.setColor(Color.YELLOW);
		}else {
			g2d.setColor(Color.GRAY);
		}
		g2d.fill(caseDroite);
		
		g2d.setColor(Color.BLACK);
		g2d.draw(fleche1);
		g2d.draw(fleche2);
		g2d.draw(fleche3);
		g2d.draw(fleche4);

	}
	
	/**
	 * permet d'allumer ou d'�teindre la fleche du haut 
	 * @param allumeHaut si elle est �teinte ou allum�e
	 */
	public void setAllumeHaut(boolean allumeHaut) {
		this.allumeHaut = allumeHaut;
		repaint();
	}

	/**
	 * permet d'allumer ou d'�teindre la fleche du bas 
	 * @param allumeBas si elle est �teinte ou allum�e
	 */
	public void setAllumeBas(boolean allumeBas) {
		this.allumeBas = allumeBas;
		repaint();
	}

	/**
	 * permet d'allumer ou d'�teindre la fleche de gauche
	 * @param allumeGauche si elle est �teinte ou allum�e
	 */
	public void setAllumeGauche(boolean allumeGauche) {
		this.allumeGauche = allumeGauche;
		repaint();
	}

	/**
	 * permet d'allumer ou d'�teindre la fleche de droite
	 * @param allumeDroite si elle est �teinte ou allum�e
	 */
	public void setAllumeDroite(boolean allumeDroite) {
		this.allumeDroite = allumeDroite;
		repaint();
	}

}
