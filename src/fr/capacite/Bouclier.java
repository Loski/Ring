package fr.capacite;

import fr.jeu.Menu;
import fr.jeu.*;
import fr.personnage.Combattant;

public class Bouclier extends Capacite {

	private int	maniabilite;
	private int	protection;

	public Bouclier() {
		this.nom = "Bouclier de fer";
		this.maniabilite = 50;
		this.protection = 50;
		this.type = Capacite.PARADE;
		this.dommage = Capacite.PHYSIQUE;
		this.description = "Bouclier";
	}

	public Bouclier(String nom, int type, int dommage, String description,
			int maniabilite, int protection) {
		super(nom, type, dommage, description);
		this.protection = protection;
		this.maniabilite = maniabilite;
	}

	public Bouclier(Capacite c) {
		super(c);
		Bouclier b = (Bouclier) c;
		this.protection = b.protection;
		this.maniabilite = b.maniabilite;
	}

	public static void creerBouclier() {
		Capacite a = new Bouclier();
		a.init();
		new SauvegardeCapacite<Bouclier>().sauvegarderCapacite((Bouclier) a); 
	}

	@Override
	public int calculImpact(Combattant combattant, int type) {
		if (impact == 0)
			return calculImpact(combattant.getForce(), this.protection);
		return impact;
	}

	@Override
	public boolean calculReussite(Combattant combattant) {
		if (reussite == 0)
			return actionReussie((calculReussite(combattant.getDexterite(), this.maniabilite)));
		else
			return actionReussie(reussite);
	}

	@Override
	public String toString() {
		return "Bouclier " + nom + "[maniabilite=" + maniabilite + ", protection=" + protection + "]";
	}

	public void init() {
		super.init();
		do {
			System.out.println("Choississez une valeur pour la protection :");
			this.protection = Menu.choix();
			System.out.println("Choississez une valeur pour la maniabilit� :");
			this.maniabilite = Menu.choix();
		} while (!(this.maniabilite + this.protection == 100) || this.maniabilite < 20 || this.protection < 20);
	}
}
