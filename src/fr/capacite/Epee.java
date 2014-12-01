package fr.capacite;
/**
 * Epee est la classe responsable de la gestion des épées
 * @author Maxime LAVASTE
 * @author Loïc LAFONTAINE
 */
import fr.jeu.*;
import fr.personnage.Combattant;

public class Epee extends Capacite {

	private int	impactEpee;
	private int	paradeEpee;
	private int	maniabilite;

	public Epee() {
		super("unknow", Capacite.EPEE, Capacite.PHYSIQUE, Capacite.EPEE, "C'est une �p�e");
		this.impactEpee = 33;
		this.paradeEpee = 33;
		this.maniabilite = 34;
	}

	public Epee(int impact, int maniabilite, int parade, String nom,
			String description) throws Exception {
		super(nom, Capacite.EPEE, Capacite.PHYSIQUE, Capacite.EPEE, description);
		this.maniabilite = maniabilite;
		this.paradeEpee = parade;
		this.impactEpee = impact;
		if (!(this.maniabilite + this.impactEpee + this.paradeEpee == 100) || this.maniabilite < 20 || this.paradeEpee < 20 || this.impactEpee < 20)
			throw new Exception("Epee Invalide :" + this.getNom());
	}

	public Epee(Capacite c) {
		super(c);
		Epee e = (Epee) c;
		this.impactEpee = e.impactEpee;
		this.maniabilite = e.maniabilite;
		this.paradeEpee = e.paradeEpee;
	}

	/**
	 * Demande au combattant courant de choisir le type d'action d'une épee 1 Attaque 2 Parade
	 * 
	 * @return true si c'est une attaque false si c'est une parade
	 */
	public static boolean choixType() {
		int choix;
		do {
			System.out.println("1.\tAttaque\n2.\tParade");
			choix = Menu.choix();
		} while (choix <= 0 || choix > 2);
		return choix == Action.ATTAQUE;
	}

	@Override
	public int calculImpact(Combattant combattant, int type) {
		if (type == Capacite.ATTAQUE)
			return calculImpact(combattant.getForce(), this.impactEpee);
		return calculImpact(combattant.getForce(), this.paradeEpee);
	}

	@Override
	public boolean calculReussite(Combattant combattant) {
		if (reussite == 0)
			return actionReussie(calculReussite(combattant.getDexterite(), this.maniabilite));
		else
			return actionReussie(reussite);
	}
	/**
	 * Permet de créer une épée via la console puis la sauvegarder dans le dossier "Sauvegardes/Capacite/Epee"
	 */
	public static void creerEpee() {
		Capacite a = new Epee();
		a.init();
		new SauvegardeCapacite<Epee>().sauvegarderCapacite((Epee) a);
	}
	/**
	 * Fonction qui créée des fichiers texte contenant des épées dans le dossier "Sauvegardes/Capacite/Epee"
	 */
	public static void creationEpee() {
		try {
			int nbEpee = 9;
			Epee[] epee = new Epee[nbEpee];
			epee[0] = new Epee(33, 33, 34, "Epée de Lumière", "Une épée forgée à partir de la plus pure des lumières");
			epee[1] = new Epee(33, 33, 34, "Epée des Ténèbres", "Une épée forgée dans les profondeurs des ténèbres");
			epee[2] = new Epee(40, 20, 40, "Master Sword", "Légendaire épée portée par les élus des Dieux");
			epee[3] = new Epee(50, 30, 20, "Durandal", "Une épée digne du plus grand chevalier");
			epee[4] = new Epee(30, 20, 50, "Excalibur", "Une épée royale");
			epee[5] = new Epee(20, 60, 20, "Kunai", "Faible arme facile à manier");
			epee[6] = new Epee(30, 30, 40, "Black March", "Une arme trés féminine");
			epee[7] = new Epee(37, 23, 40, "Monado", "Une épée capable de percevoir l'avenir");
			epee[8] = new Epee(20, 40, 40, "Zangetsu", "L'arme d'un weak");
			SauvegardeCapacite<Epee> saveE = new SauvegardeCapacite<Epee>();
			for (int i = 0; i < nbEpee; i++)
				saveE.sauvegarderCapacite(epee[i]);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public String toString() {
		return nom;
		// return "Epee " + nom + "[impactEpee=" + impactEpee + ", paradeEpee=" + paradeEpee + ", maniabilite=" + maniabilite + "]";
	}

	public void init() {
		super.init();
		do {
			System.out.println("Choississez une valeur pour la valeur de l'attaque :");
			this.impactEpee = Menu.choix();
			System.out.println("Choississez une valeur pour la valeur de la parade :");
			this.paradeEpee = Menu.choix();
			System.out.println("Choississez une valeur pour la maniabilit� :");
			this.maniabilite = Menu.choix();
		} while (!(this.maniabilite + this.impactEpee + this.paradeEpee == 100) || this.maniabilite < 20 || this.paradeEpee < 20 || this.impactEpee < 20);
	}

	public static void main(String[] argc) {
		creerEpee();
	}
}
