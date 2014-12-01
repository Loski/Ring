package fr.capacite;

/**
 * Sortilege est la classe responsable de la gestion des sortilèges dans Ring
 * @author Maxime LAVASTE
 * @author Loïc LAFONTAINE
 */
import fr.jeu.*;
import fr.personnage.Combattant;

public class Sortilege extends Capacite {

	private int	efficacite;
	private int	facilite;

	// Il faut d�finir le type pour savoir si c'est un sortil�ge d�fensif, gu�risseur ou offensif
	public Sortilege() {
		super("Sortilege de feu", Capacite.ATTAQUE, Capacite.MAGIQUE, Capacite.SORTILEGE, " C'est un sort d'attaque");
		this.efficacite = this.facilite = 50;
	}

	public Sortilege(String nom, int type, int efficacite, int facilite,
			String description) {
		super(nom, type, Capacite.MAGIQUE, Capacite.SORTILEGE, description);
		this.efficacite = efficacite;
		this.facilite = facilite;
	}

	public Sortilege(Capacite c) {
		super(c);
		Sortilege s = (Sortilege) c;
		this.efficacite = s.efficacite;
		this.facilite = s.facilite;
	}

	/**
	 * Permet de créer un sortilège via la console puis le sauvegarder dans le dossier "Sauvegardes/Capacite/Sortilege"
	 */
	public static void creerSortilege() {
		Capacite a = new Sortilege();
		a.init();
		new SauvegardeCapacite<Sortilege>().sauvegarderCapacite((Sortilege) a);
	}

	/**
	 * Fonction qui créée des fichiers texte contenant des sortilèges dans le dossier "Sauvegardes/Capacite/Sortilege"
	 */
	public static void creationSortilege() {
		int nbSort = 13;
		Sortilege[] sort = new Sortilege[nbSort];
		sort[0] = new Sortilege("Sortilège de feu", Capacite.ATTAQUE, 60, 40, "Des flammes mordantes");
		sort[1] = new Sortilege("Avada Kedavra", Capacite.ATTAQUE, 80, 20, "Un sortilège mortel");
		sort[2] = new Sortilege("Sortilège de soin", Capacite.SOIN, 60, 40, "Un soin efficace");
		sort[3] = new Sortilege("Armure de glace", Capacite.PARADE, 80, 20, "Une couche de glace vous protèges");
		sort[4] = new Sortilege("Armure de feu", Capacite.PARADE, 60, 40, "Des flammes vous protègent de toutes attaques");
		sort[5] = new Sortilege("Armure de vent", Capacite.PARADE, 70, 30, "Des vents coupent toutes les attaques");
		sort[6] = new Sortilege("Sortilège de glace", Capacite.ATTAQUE, 60, 40, "Des glaces glaçantes");
		sort[7] = new Sortilege("Sortilège de vent", Capacite.ATTAQUE, 50, 50, "Des vents qui mordent vos enemis");
		sort[8] = new Sortilege("Sortilège gazeux", Capacite.ATTAQUE, 60, 40, "Du gaz qui vous brûlent");
		sort[9] = new Sortilege("FUS ROH DAH", Capacite.PARADE, 50, 50, "Un cri surpuissant qui permet de repousser les attaques ennemis");
		sort[10] = new Sortilege("Rasengan", Capacite.ATTAQUE, 75, 25, "Une attaque surpuissante");
		sort[11] = new Sortilege("Katon - Gôkakyû no Jutsu", Capacite.ATTAQUE, 65, 35, "Une boule de feu gigantesque");
		sort[12] = new Sortilege("Le souffle du grand ange", Capacite.SOIN, 80, 20, "Soigne les blessures les plus profondes");
		SauvegardeCapacite<Sortilege> saveE = new SauvegardeCapacite<Sortilege>();
		for (int i = 0; i < nbSort; i++)
			saveE.sauvegarderCapacite(sort[i]);
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
		return nom;
		//return "Sortilege " + nom + " [efficacite=" + efficacite + ", facilite=" + facilite + ", type=" + description + "]";
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