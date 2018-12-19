package interfaces;

import java.util.EventListener;

/**
 * interface pour les �couteurs des fleches
 * @author l�o-paul Lapointe
 *
 */
public interface FlecheListener extends EventListener{
	
	/**
	 * �coute si oui ou non la fleche du haut est appuy�
	 * @param allumer
	 */
	public void flecheHaut(boolean allumer);
	
	/**
	 * �coute si oui ou non la fleche du bas est appuy�
	 * @param allumer
	 */
	public void flecheBas(boolean allumer);

	/**
	 * �coute si oui ou non la fleche de gauche est appuy�
	 * @param allumer
	 */
	public void flecheGauche(boolean allumer);

	/**
	 * �coute si oui ou non la fleche de droite est appuy�
	 * @param allumer
	 */
	public void flecheDroite(boolean allumer);

}
