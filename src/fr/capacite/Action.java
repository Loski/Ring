package fr.capacite;

import fr.personnage.Combattant;

public interface Action {
	public static final int ATTAQUE = 1, PARADE = 2, SOIN = 3, EPEE = 4;
	public static final int PHYSIQUE = 1, MAGIE = 2;
	public abstract void calculImpact(Combattant combattant);
	public abstract void calculReussite(Combattant combattant);
}
