package interfaces;

import java.util.EventListener;
/**
 * interface pour les �couteurs personnalis�s de position et de temps
 * @author l�o-paul lapointe
 *
 */
public interface PositionsEtTempsListener extends EventListener {

	/**
	 * �coute la position en x et y du char
	 * @param x la position en x du char
	 * @param y la position en y du char
	 */
	public void changementPosChar(double x, double y);

	/**
	 * �coute la position en x et y du projectile
	 * @param x la position en x du projectile
	 * @param y la position en y du projectile
	 */
	public void changementPosProjectile(double x, double y);

	/**
	 * �coute le temps �coul� depuis le tir
	 * @param temps le temps �coul� depuis le tir
	 */
	public void changementTemps(double temps);
	
	/**
	 * �coute la vitesse du projectile
	 * @param vitesse la vitesse du projectile
	 */
	public void changementVitesseProjectile (double vitesse);
}
