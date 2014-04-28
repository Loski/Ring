package fr.capacite;

import fr.personnage.Combattant;

public class Sortilege extends Capacite {
	private int efficacite;
	private int facilite;
	// Il faut définir le type pour savoir si c'est un sortilège défensif, guérisseur ou agressif
	
	public Sortilege(){
		super("Sortilege de feu", Capacite.ATTAQUE, Capacite.MAGIE," C'est un sort d'attaque");
		this.efficacite = this.facilite = 50;
	}
	
	public Sortilege(String nom, int type, int efficacite, int facilite, String description){
		super(nom, type, Capacite.MAGIE, description);
		if((efficacite + facilite) == 100 && efficacite >= 20 && facilite >= 20){
			this.efficacite = efficacite;
			this.facilite = facilite;
		}
	}
	
	/* Pour différencier le type de sortilège */
	public void initDescription(){
		if(this.type == Capacite.ATTAQUE)
			this.description = " c'est une a";
		else if(this.type == Capacite.PARADE)
			this.description = "c'esunt une parade";
		else
			this.description = "heal";
	}
	
	public int calculReussite(Combattant combattant) {
		return super.calculReussite(combattant.getConcentration(), this.facilite);
	}

	public int calculImpact(Combattant combattant) {
		return super.calculImpact(combattant.getIntelligence(), this.efficacite);
	}
}