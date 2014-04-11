package fr.capacite;

import fr.personnage.Combattant;

public interface Action {
	public abstract void soin(Combattant combattant);
	public abstract void attaque(Combattant combattant);
	public abstract void parade(Combattant combattant);
	public static final int ATTAQUE = 1, PARADE = 2, SOIN = 3;
}
