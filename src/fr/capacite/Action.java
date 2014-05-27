package fr.capacite;

import fr.personnage.Combattant;

public interface Action {
	public static final int ATTAQUE = 1, PARADE = 2, SOIN = 3, EPEE = 4; //type
	public static final int PHYSIQUE = 1, MAGIQUE = 2; //dommage
	
	/**
	 * Calcule l'impact de la capacité courante
	 * @param combattant
	 * 	Le combattant courant
	 * @param type
	 * Envoie le type d'attaque (ATTAQUE/PARADE/SOIN), ce qui sert à différencier pour l'impact de l'épée en parade ou en attaque
	 * @return valeur de l'impact de la capacité qui lance cette fonction
	 */
	
	public abstract int calculImpact(Combattant combattant, int type);
	/**
	 * Calcule la probabilité de réussite de la capacité courante
	 * @param combattant
	 * 	Le combattant courrant
	 * @return valeur de la probabilité de réussite de cette capacité
	 */
	public abstract boolean calculReussite(Combattant combattant);
}
