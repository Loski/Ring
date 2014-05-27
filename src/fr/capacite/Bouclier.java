package fr.capacite;

import fr.personnage.Combattant;

public class Bouclier extends Capacite {

	private int maniabilite;
	private int protection;

	public Bouclier() {
		this.nom = "Bouclier de fer";
		this.maniabilite = 50;
		this.protection = 50;
		this.description = "Bouclier";
	}

	public Bouclier(String nom, int type, int dommage, String description,
			int maniabilite, int protection) {
		super(nom, type, dommage, description);
		this.protection = protection;
		this.maniabilite = maniabilite;
	}

	public Bouclier(Bouclier b) {
		super(b.nom, b.type, b.dommage, b.description);
		this.protection = b.protection;
		this.maniabilite = b.maniabilite;
	}

	public Bouclier(Capacite c) {
		super(c);
		Bouclier b = (Bouclier) c;
		this.protection = b.protection;
		this.maniabilite = b.maniabilite;
	}

	@Override
	public int calculImpact(Combattant combattant, int type) {
		return calculImpact(combattant.getForce(), this.protection);
	}

	@Override
	public boolean calculReussite(Combattant combattant) {
		return actionReussie((calculReussite(combattant.getDexterite(),
				this.maniabilite)));
	}

	@Override
	public String toString() {
		return "Bouclier " + nom + "[maniabilite=" + maniabilite
				+ ", protection=" + protection + "]";
	}

}
