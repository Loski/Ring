package fr.capacite;

import fr.personnage.Combattant;

public class Remede extends Capacite {

	private int facilite;
	private int efficacite;

	public Remede() {
		this.efficacite = this.facilite = 50;
		this.description = "Heal yourself";
	}

	public Remede(String nom, String description, int efficacite, int facilite) {
		super(nom, Capacite.SOIN, Capacite.MAGIE, description);
		if ((efficacite + facilite) == 100 && efficacite >= 20 && facilite >= 20) {
			this.efficacite = efficacite;
			this.facilite = facilite;
		}
		this.description = "Heal yourself";
	}

	@Override
	public String toString() {
		return "[Remède : " + "efficacite = " + this.efficacite
				+ " facilite = " + this.facilite + "]";
	}

	public int calculReussite(Combattant combattant) {
		return super.calculReussite(combattant.getDexterite(), this.facilite);
	}
	
	public int calculImpact(Combattant combattant) {
		return super.calculImpact(combattant.getDexterite(), this.efficacite);
	}
}