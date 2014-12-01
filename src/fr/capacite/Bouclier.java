package fr.capacite;
/**
 * Bouclier est la classe responsable de la gestion des Boucliers dans Ring
 * @author Maxime LAVASTE
 * @author Loïc LAFONTAINE
 */
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

	public Bouclier(int maniabilite, int protection, String nom,
			String description) throws Exception {
		super(nom, Capacite.PARADE, Capacite.PHYSIQUE, Capacite.BOUCLIER, description);
		if (!(maniabilite + protection == 100) || maniabilite < 20 || protection < 20)
			throw new Exception("Bouclier invalide: " + this.nom);
		this.protection = protection;
		this.maniabilite = maniabilite;
	}

	public Bouclier(Capacite c) {
		super(c);
		Bouclier b = (Bouclier) c;
		this.protection = b.protection;
		this.maniabilite = b.maniabilite;
	}
	/**
	 * Fonction qui créée des fichiers texte contenant des boucliers dans le dossier "Sauvegardes/Capacite/Bouclier"
	 */
	public static void creationBouclier() {
		int nbBou = 7;
		Bouclier[] bouclier = new Bouclier[nbBou];
		try {
			bouclier[0] = new Bouclier(60, 40, "L'Egide", "Un bouclier divin capable de pétrifier");
			bouclier[1] = new Bouclier(30, 70, "Bouclier Hylien", "Le bouclier de toute une race");
			bouclier[2] = new Bouclier(20, 80, "Bouclier de gladiateur", "Un bouclier rouge du sang des vaincus");
			bouclier[3] = new Bouclier(70, 30, "Le bouclier d'Héraclès", "Le bouclier des héros");
			bouclier[4] = new Bouclier(50,50, "Bouclier de bois","Un bouclier basique");
			bouclier[5] = new Bouclier(40,60, "Bouclier d'acier", "Un bouclier d'acier");
			bouclier[6]= new Bouclier(20,80,"Bouclier du dragon", "Le bouclier le plus solide");
			SauvegardeCapacite<Bouclier> save = new SauvegardeCapacite<Bouclier>();
			for (int i = 0; i < nbBou; i++)
				save.sauvegarderCapacite(bouclier[i]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * Permet de créer un bouclier via la console
	 */
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
		return nom;
		//return "Bouclier " + nom + "[maniabilite=" + maniabilite + ", protection=" + protection + "]";
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
