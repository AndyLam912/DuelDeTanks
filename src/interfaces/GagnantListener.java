package interfaces;

import java.util.EventListener;

/**
 * interface pour l'ecouteur du gagnant
 * @author léo-paul lapointe
 *
 */
public interface GagnantListener extends EventListener{
	
	/**
	 * permet de savoir qui est le gagnant
	 * @param gagnant le gagnant entre les deux chars
	 */
	public void declarationGagnant(boolean gagnant);

}
