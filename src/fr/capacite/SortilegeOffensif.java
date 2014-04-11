package fr.capacite;

import fr.personnage.Combattant;

public class SortilegeOffensif extends Magie {

	public void attaque(Combattant combattant, Combattant cible) {
		this.calculReussite(combattant.getConcentration(), this.efficacite);
		this.calculImpact(combattant.getIntelligence(), this.facilite);
		if(true){
			cible.lowVita(this.impact);
		}
	}

}
