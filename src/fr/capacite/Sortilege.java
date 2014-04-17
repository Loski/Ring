package fr.capacite;

import fr.personnage.Combattant;

public abstract class Sortilege extends Capacite {
	private int efficacite;
	private int facilite;
	private final int categorie = Action.MAGIE;
	
	public Sortilege(){
		this.efficacite = this.facilite = 50;
		this.type = Action.SOIN;
	}
	public Sortilege(String nom, int type, int efficacite, int facilite){
		super(nom, type);
		if((efficacite + facilite) == 100){
			this.nom = nom;
			this.efficacite = efficacite;
			this.facilite = facilite;
			this.type = type;
		}
	}
	
	public void calculReussite(Combattant combattant) {
		super.calculReussite(combattant.getConcentration(), this.facilite);
	}

	public void calculImpact(Combattant combattant) {
		super.calculImpact(combattant.getIntelligence(), this.efficacite);
	}
}
