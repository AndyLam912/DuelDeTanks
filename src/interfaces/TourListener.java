package interfaces;

import java.util.EventListener;
/**
 * Interface pour les �couteurs en lien avec les tours
 * @author l�o-paul lapointe
 *
 */
public interface TourListener extends EventListener {

	/**
	 * �coute � quel char est le tour
	 * @param tourDuCharVert vrai si c'est le tour du char vert
	 */
	public void changementTour(boolean tourDuCharVert);
	
	/**
	 * �coute le niveau d'essence restant � un char durant son tour
	 * @param essence le niveau d'essence du char
	 */
	public void niveauEssence (int essence);
	
	/**
	 * �coute quand il faut activer et d�sactiver les entr�es
	 */
	public void disableComposants();
	
	/**
	 * �coute quand il y a le dessin d'un nouveau terrain
	 * @param gravite la force de gravit� du terrain
	 */
	public void premierPaint(double gravite);
}
