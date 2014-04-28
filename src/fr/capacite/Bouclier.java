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

	public Bouclier(String nom, int type, int categorie, String description, int maniabilite, int protection) {
		super(nom, type, categorie, description);
		this.protection = protection;
		this.maniabilite = maniabilite;
	}

	@Override
	public int calculImpact(Combattant combattant) {
		return super.calculImpact(combattant.getForce(), this.protection);
	}

	@Override
	public int calculReussite(Combattant combattant) {
		return super.calculReussite(combattant.getDexterite(), this.maniabilite);
	}

}
