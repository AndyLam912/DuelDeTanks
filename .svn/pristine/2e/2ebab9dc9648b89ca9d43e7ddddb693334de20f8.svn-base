package interfaces;

import java.util.EventListener;
/**
 * Interface pour les écouteurs en lien avec les tours
 * @author léo-paul lapointe
 *
 */
public interface TourListener extends EventListener {

	/**
	 * écoute à quel char est le tour
	 * @param tourDuCharVert vrai si c'est le tour du char vert
	 */
	public void changementTour(boolean tourDuCharVert);
	
	/**
	 * écoute le niveau d'essence restant à un char durant son tour
	 * @param essence le niveau d'essence du char
	 */
	public void niveauEssence (int essence);
	
	/**
	 * écoute quand il faut activer et désactiver les entrées
	 */
	public void disableComposants();
	
	/**
	 * écoute quand il y a le dessin d'un nouveau terrain
	 * @param gravite la force de gravité du terrain
	 */
	public void premierPaint(double gravite);
}
