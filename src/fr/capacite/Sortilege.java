package fr.capacite;

import fr.jeu.Menu;
import fr.jeu.*;
import fr.personnage.Combattant;

public class Sortilege extends Capacite {

	private int	efficacite;
	private int	facilite;

	// Il faut d�finir le type pour savoir si c'est un sortil�ge d�fensif, gu�risseur ou offensif
	public Sortilege() {
		super("Sortilege de feu", Capacite.ATTAQUE, Capacite.MAGIQUE, " C'est un sort d'attaque");
		this.efficacite = this.facilite = 50;
	}

	public Sortilege(String nom, int type, int efficacite, int facilite,
			String description) {
		super(nom, type, Capacite.MAGIQUE, description);
		this.efficacite = efficacite;
		this.facilite = facilite;
	}

	public Sortilege(Capacite c) {
		super(c);
		Sortilege s = (Sortilege) c;
		this.efficacite = s.efficacite;
		this.facilite = s.facilite;
	}

	public static void creerSortilege() {
		Capacite a = new Sortilege();
		a.init();
		new SauvegardeCapacite<Sortilege>().sauvegarderCapacite((Sortilege) a);
	}

	/**
	 * Initialise la description d'un sortil�ge en fonction de son type d'action
	 */ 
	public void initDescription() {
		if (this.type == Capacite.ATTAQUE)
			this.description = "Attaque";
		else if (this.type == Capacite.PARADE)
			this.description = "Parade";
		else
			this.description = "heal";
	}

	@Override
	public boolean calculReussite(Combattant combattant) {
		if (reussite == 0)
			return actionReussie(calculReussite(combattant.getConcentration(), this.facilite));
		else
			return actionReussie(reussite);
	}

	@Override
	public int calculImpact(Combattant combattant, int type) {
		if (impact == 0)
			return calculImpact(combattant.getIntelligence(), this.efficacite);
		return impact;
	}

	@Override
	public String toString() {
		return "Sortilege " + nom + " [efficacite=" + efficacite + ", facilite=" + facilite + ", type=" + description + "]";
	}

	public void init() {
		super.init();
		do {
			System.out.println("Choississez une valeur pour la valeur de la facilit� :");
			this.facilite = Menu.choix();
			System.out.println("Choississez une valeur pour la valeur de l'�fficacit� :");
			this.efficacite = Menu.choix();
			System.out.println("Choissisez la fonctionnalit� du sortil�ge :\n1.\tAttaque\n2.\tParade\n3.\tSoin");
			this.type = Menu.choix();
		} while (!(this.efficacite + this.facilite == 100) || this.facilite < 20 || this.efficacite < 20 && (this.type < 0 || this.type > 3));
	}
}