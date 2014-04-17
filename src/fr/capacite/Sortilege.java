package fr.capacite;

import fr.personnage.Combattant;

public abstract class Sortilege extends Capacite {
	private int efficacite;
	private int facilite;
	// Il faut définir le type pour savoir si c'est un sortilège défensif, guérisseur ou agressif
	
	public Sortilege(){
		super("unknow", Capacite.ATTAQUE, Capacite.MAGIE, "uknow");
		this.efficacite = this.facilite = 50;
	}
	
	public Sortilege(String nom, int type, int efficacite, int facilite, String description){
		super(nom, type, Capacite.MAGIE, description);
		if((efficacite + facilite) == 100 && efficacite >= 20 && facilite >= 20){
			this.efficacite = efficacite;
			this.facilite = facilite;
		}
	}
	
	public void initDescription(){
		if(this.type == Capacite.ATTAQUE)
			this.description = " c'est une a";
		else if(this.type == Capacite.PARADE)
			this.description = "c'esunt une parade";
		else
			this.description = "heal";
	}
	
	public void calculReussite(Combattant combattant) {
		super.calculReussite(combattant.getConcentration(), this.facilite);
	}

	public void calculImpact(Combattant combattant) {
		super.calculImpact(combattant.getIntelligence(), this.efficacite);
		if(this.type == Capacite.ATTAQUE){
			// On vire des dégats selon le type et l'impact de la parade de l'autre joueur 
		}
	}
	
}
