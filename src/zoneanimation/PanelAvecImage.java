package zoneanimation;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * Classe permettant de creer un panel avec une image en arriere-plan
 * @author Andy Lam
 *
 */
public class PanelAvecImage extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image image;
	private String nomImage;
	/**
	 * Creation d'un panel dans lequel on veut mettre une image
	 * 
	 */
	public PanelAvecImage() {
		
	}
	/**
	 * Methode qui permet de dessiner l'image de fond dans un panel
	 * @param g
	 *            qui est le contexte graphique
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
			URL img = getClass().getClassLoader().getResource(nomImage);
			if (img == null) {
				JOptionPane.showMessageDialog(null, "Fichier d'image introuvable!");
			
			} else {
				try {
					image = ImageIO.read(img);
					
			} catch (IOException e) {
					System.out.println("Erreur de lecture du fichier d'image");
				}
			}
			g2d.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
		}
	/**
	 * Methode permettant de fixer le nom de l'image a lire
	 * @param nomImage le nom de l'image a lire dans les ressources
	 */
	public void setNomImage(String nomImage) {
		this.nomImage = nomImage;
	}


}
