package fr.capacite;

import fr.jeu.Menu;
import fr.jeu.Sauvegarde;
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
	public static void creerBouclier(){
		Capacite a = new Bouclier();
		a.init();
		new Sauvegarde<Bouclier>().sauvegarderCapacite((Bouclier) a);
	}
	@Override
	public int calculImpact(Combattant combattant, int type) {
		return calculImpact(combattant.getForce(), this.protection);
	}

	@Override
	public boolean calculReussite(Combattant combattant) {
		return actionReussie((calculReussite(combattant.getDexterite(), this.maniabilite)));
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
			System.out.println("Choississez une valeur pour la maniabilité :");
			this.maniabilite = Menu.choix();
		} while (!(this.maniabilite + this.protection == 100) || this.maniabilite < 20 || this.protection < 20);
	}
}
