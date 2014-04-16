package fr.capacite;

import fr.personnage.Combattant;

public class SortilegeGuerisseur extends Magie {
	public static final String descrition ="C'est le remède des magiciens";
	public SortilegeGuerisseur(){
		this.efficacite = 50;
		this.facilite = 50;
	}
	public SortilegeGuerisseur(int nom, int efficacite, int facilite){
		this.efficacite = efficacite;
		this.facilite = facilite;
	}
	public void calculImpact(Combattant combattant) {
		super.calculImpact(combattant.getIntelligence(), this.efficacite); 
	}
	public void calculReussite(Combattant combattant){
		super.calculReussite(combattant.getConcentration(), this.facilite);
	}

}
