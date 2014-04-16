package fr.capacite;

import fr.personnage.Combattant;

public class Remede extends Capacite {

	private int facilite;
	private int efficacite;
	public static final String description = "Heal yourself";

	public Remede() {
		this.efficacite = this.facilite = 50;
	}

	public Remede(int efficacite, int facilite) {
		this.efficacite = efficacite;
		this.facilite = facilite;
	}

	@Override
	public String toString() {
		return "[Remède : " + "efficacite = " + this.efficacite
				+ " facilite = " + this.facilite + "]";
	}

	public void calculReussite(Combattant combattant) {
		super.calculReussite(combattant.getDexterite(), this.facilite);
	}

	public void calculImpact(Combattant combattant) {
		super.calculImpact(combattant.getDexterite(), this.efficacite);
	}
}