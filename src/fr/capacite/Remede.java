package fr.capacite;
/**
 * Remede est la classe responsable de la gestion des Remèdes dans Ring
 * @author Maxime LAVASTE
 * @author Loïc LAFONTAINE
 */
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

	public Remede(String nom, int efficacite, int facilite, String description)
			throws Exception {
		super(nom, Capacite.SOIN, Capacite.MAGIQUE,Capacite.REMEDE,description);
		if ((efficacite + facilite) == 100 && efficacite >= 20 && facilite >= 20) {
			this.efficacite = efficacite;
			this.facilite = facilite;
		} 
		else
			throw new Exception("Remède incorrecte: " + this.nom);
		this.description = "Heal yourself";
	}

	public Remede(Capacite c) {
		super(c);
		Remede r = (Remede) c;
		this.facilite = r.facilite;
		this.efficacite = r.efficacite;
	}
	/**
	 * Fonction qui créée des fichiers texte contenant des remèdes dans le dossier "Sauvegardes/Capacite/Remede"
	 */
	public static void creationRemede() {
		int nbRemde = 6;
		Remede[] remede = new Remede[nbRemde];
		try {
			remede[0] = new Remede("Potion de soin", 50, 50, "Un remède basique");
			remede[1] = new Remede("Grande potion de soin", 70, 30, "Un remède qui vous veux du bien!");
			remede[2] = new Remede("Fée", 80, 20, "Une petite fée en fiole");
			remede[3] = new Remede("Adréaline", 20, 80, "Un soin rapide");
			remede[4] = new Remede("Médicament", 30, 70, "Médicaments");
			remede[5] = new Remede("Kit de soin", 40, 60, "Une trouse de soin");
			SauvegardeCapacite<Remede> save = new SauvegardeCapacite<Remede>();
			for (int i = 0; i < nbRemde; i++)
				save.sauvegarderCapacite(remede[i]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Permet de créer un remède via la console puis le sauvegarder dans le dossier "Sauvegardes/Capacite/Remede"
	 */
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
		return nom;
		//return "Remede " + nom + "[facilite=" + facilite + ", efficacite=" + efficacite + "]";
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