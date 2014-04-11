package fr.capacite;

import fr.personnage.Combattant;

public abstract class Capacite /*implements Action*/{
	public static final int MIN_CAPACITE = 2;
	public static final int MAX_CAPACITE = 9;
	
	public String description;
	protected int reussite;
	protected int impact;
	protected int efficacite;
	protected int facilite;
	protected int maniabilite;
	protected int categorie;
	
	public void calculImpact(int cara_Perso, int cara_Capa) {
		this.impact = (cara_Capa * cara_Perso) / 100;
	}	
	public void calculReussite(int cara_Perso, int cara_Capa) {
		this.reussite = (cara_Perso * cara_Capa) / 10_000;
	}
	
	public boolean soin(Combattant combattant){
			return false;
	}
	public boolean attaque(Combattant combattant, Combattant cible) {
		return false;
	}
	
	public boolean parade(Combattant combattant) {
		return false;
	}
	
}
