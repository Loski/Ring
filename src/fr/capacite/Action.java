package fr.capacite;

import fr.personnage.Combattant;

public interface Action {
	public static final int ATTAQUE = 1, PARADE = 2, SOIN = 3, EPEE = 4; //type
	public static final int PHYSIQUE = 1, MAGIQUE = 2; //dommage
	
	/**
	 * Calcule l'impact de la capacité courante
	 * @param combattant
	 * 	Le combattant courrant
	 * @return valeur de l'impact de cette capacité
	 */
	
	public abstract int calculImpact(Combattant combattant, int type);
	/**
	 * Calcule la réussite de la capacité courante
	 * @param combattant
	 * 	Le combattant courrant
	 * @return valeur de la réussite de cette capacité
	 */
	public abstract boolean calculReussite(Combattant combattant);
}
