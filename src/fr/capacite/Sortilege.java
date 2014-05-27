package fr.capacite;

import fr.personnage.Combattant;

public class Sortilege extends Capacite {
	private int	efficacite;
	private int	facilite;

	// Il faut définir le type pour savoir si c'est un sortilège défensif, guérisseur ou offensif

	public Sortilege() {
		super("Sortilege de feu", Capacite.ATTAQUE, Capacite.MAGIQUE,
				" C'est un sort d'attaque");
		this.efficacite = this.facilite = 50;
	}

	public Sortilege(int type) { // Dans le chargement d'un sortilège -> tester par rapport au type
		super("Sortilege de feu", type, Capacite.MAGIQUE, "");
		this.efficacite = this.facilite = 50;
		initDescription();
	}

	public Sortilege(String nom, int type, int efficacite, int facilite,
			String description) {
		super(nom, type, Capacite.MAGIQUE, description);
		if ((efficacite + facilite) == 100 && efficacite >= 20 && facilite >= 20) {
			this.efficacite = efficacite;
			this.facilite = facilite;
		}
	}

	public Sortilege(Capacite c) {
		super(c);
		Sortilege s = (Sortilege) c;
		this.efficacite = s.efficacite;
		this.facilite = s.facilite;
	}

	/**
	 * Initialise la description d'un sortilège en fonction de son type d'action
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
		return actionReussie(calculReussite(combattant.getConcentration(),
				this.facilite));
	}

	@Override
	public int calculImpact(Combattant combattant, int type) {
		return calculImpact(combattant.getIntelligence(), this.efficacite);
	}

	@Override
	public String toString() {
		return "Sortilege " + nom + " [efficacite=" + efficacite + ", facilite=" + facilite + ", type=" + description + "]";
	}
}