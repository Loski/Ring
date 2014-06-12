package fr.capacite;

import fr.jeu.Menu;
import fr.jeu.*;
import fr.personnage.Combattant;

public class Remede extends Capacite {

	private int	facilite;
	private int	efficacite;

	public Remede() {
		this.nom = new String("Rem�de");
		this.efficacite = this.facilite = 50;
		this.type = Capacite.SOIN;
		this.dommage = Capacite.MAGIQUE;
		this.description = "Heal yourself";
	}

	public Remede(String nom, String description, int efficacite, int facilite) {
		super(nom, Capacite.SOIN, Capacite.MAGIQUE, description);
		if ((efficacite + facilite) == 100 && efficacite >= 20 && facilite >= 20) {
			this.efficacite = efficacite;
			this.facilite = facilite;
		}
		this.description = "Heal yourself";
	}

	public Remede(Capacite c) {
		super(c);
		Remede r = (Remede) c;
		this.facilite = r.facilite;
		this.efficacite = r.efficacite;
	}

	public static void creerRemede() {
		Capacite r = new Remede();
		r.init();
		new SauvegardeCapacite<Remede>().sauvegarderCapacite((Remede) r);
	}

	public boolean calculReussite(Combattant combattant) {
		if (reussite == 0)
			return actionReussie(calculReussite(combattant.getDexterite(), this.facilite));
		else
			return actionReussie(reussite);
	}

	public int calculImpact(Combattant combattant, int type) {
		if (impact == 0)
			return calculImpact(combattant.getDexterite(), this.efficacite);
		return impact;
	}

	@Override
	public String toString() {
		return "Remede " + nom + "[facilite=" + facilite + ", efficacite=" + efficacite + "]";
	}

	public void init() {
		super.init();
		do {
			System.out.println("Choississez une valeur pour la valeur de la facilit� :");
			this.facilite = Menu.choix();
			System.out.println("Choississez une valeur pour la valeur de l'�fficacit� :");
			this.efficacite = Menu.choix();
		} while (!(this.efficacite + this.facilite == 100) || this.facilite < 20 || this.efficacite < 20);
	}
}