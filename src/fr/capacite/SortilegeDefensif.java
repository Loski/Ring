package fr.capacite;

import fr.personnage.Combattant;

public class SortilegeDefensif extends Magie {

	public void calculReussite(Combattant combattant) {
		super.calculReussite(combattant.getConcentration(), this.facilite);
	}
	public void calculImpact(Combattant combattant) {
		super.calculImpact(combattant.getIntelligence(), this.efficacite);
	}
}
