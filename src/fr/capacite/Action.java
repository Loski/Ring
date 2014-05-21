package fr.capacite;

import fr.personnage.Combattant;

public interface Action {
	public static final int ATTAQUE = 1, PARADE = 2, SOIN = 3, EPEE = 4; //type
	public static final int PHYSIQUE = 1, MAGIQUE = 2; //dommage
	
	/**
	 * Calcule l'impact de la capacit� courante
	 * @param combattant
	 * 	Le combattant courrant
	 * @return valeur de l'impact de cette capacit�
	 */
	
	public abstract int calculImpact(Combattant combattant, int type);
	/**
	 * Calcule la r�ussite de la capacit� courante
	 * @param combattant
	 * 	Le combattant courrant
	 * @return valeur de la r�ussite de cette capacit�
	 */
	public abstract boolean calculReussite(Combattant combattant);
}
