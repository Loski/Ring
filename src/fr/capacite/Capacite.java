package fr.capacite;
/**
 * Capacité est une classe abstraite
 * @author Maxime LAVASTE
 * @author Loïc LAFONTAINE
 */
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import fr.jeu.Menu;
import fr.jeu.SauvegardeCapacite;

public abstract class Capacite implements Action, Serializable {

	public static final int	MIN_CAPACITE	= 2;
	public static final int	MAX_CAPACITE	= 9;
	protected String		description;
	protected int			dommage;
	protected String		nom;
	protected int			type;
	protected float			reussite;
	protected int			impact;
	protected int			capacite;

	public Capacite() {
	}

	public Capacite(Capacite c) {
		this.nom = new String(c.nom);
		this.type = c.type;
		this.dommage = c.dommage;
		this.description = new String(c.description);
		this.capacite = c.capacite;
		this.reussite = 0;
		this.impact = 0;
	}

	public Capacite(String nom, int type, int dommage,int capacite, String description) {
		this.nom = nom;
		this.type = type;
		this.dommage = dommage;
		this.capacite = capacite;
		this.description = description;
		this.impact = 0;
		this.reussite = 0;
	}
	/**
	 * Stocke toutes les capacités sauvegardées dans une ArrayList
	 * @return
	 * L'ArrayList contenant toutes les capacités
	 */
	public static ArrayList<Capacite> capaciteDisponible() {
		ArrayList<Capacite> capa = new ArrayList<Capacite>();
		String epee[] = new File("Sauvegardes/Capacite/Epee/").list();
		String sort[] = new File("Sauvegardes/Capacite/Sortilege/").list();
		String remede[] = new File("Sauvegardes/Capacite/Remede/").list();
		String bouclier[] = new File("Sauvegardes/Capacite/Bouclier/").list();
		for (String s : epee) {
			capa.add(SauvegardeCapacite.chargerCapacite("Epee/" + s, Capacite.EPEE));
		}
		for (String s : sort) {
			capa.add(SauvegardeCapacite.chargerCapacite("Sortilege/" + s, Capacite.SORTILEGE));
		}
		for (String s : remede) {
			capa.add(SauvegardeCapacite.chargerCapacite("Remede/" + s, Capacite.REMEDE));
		}
		for (String s : bouclier) {
			capa.add(SauvegardeCapacite.chargerCapacite("Bouclier/" + s, Capacite.BOUCLIER));
		}
		return capa;
	}
	/**
	 * Créé toutes les capacités initiales du jeu
	 */
	public static void creerListeCapacite() {
		Epee.creationEpee();
		Bouclier.creationBouclier();
		Remede.creationRemede();
		Sortilege.creationSortilege();	
	}
	 
	/**
	 * Calcule l'impact d'une capacité
	 * La caractéristique du personnage
	 * @param cara_Capa
	 * La caractéristique de la capacité
	 * @return
	 * L'impact de cette capacité
	 */
	public int calculImpact(final int cara_Perso, final int cara_Capa) {
		this.impact = (cara_Capa * cara_Perso) / 100;
		return impact;
	}
	/**
	 * Calcule la réussite d'une capacité
	 * @param cara_Perso
	 * La caractéristique du personnage
	 * @param cara_Capa
	 * La caractéristique de la capacité
	 * @return
	 * Le pourcentage de réussite de cette capacité
	 */
	public float calculReussite(final int cara_Perso, final int cara_Capa) {
		this.reussite = ((float) (cara_Perso * cara_Capa) / 3000);
		return reussite;
	}

	/**
	 * @param reussite
	 * La réussite d'une capacité
	 * @return renvoie si une action a r�ussie
	 */
	public static boolean actionReussie(final float reussite) {
		return (Math.random()) <= reussite;
	}
	/**
	 * Copie une capacité
	 * @param c
	 * Capacité à copier
	 * @return
	 * La capacité copié
	 */
	public static Capacite nouvelleCapacite(Capacite c) {
		if (c instanceof Bouclier)
			return new Bouclier(c);
		else if (c instanceof Epee)
			return new Epee(c);
		else if (c instanceof Sortilege)
			return new Sortilege(c);
		else
			return new Remede(c);
	}

	public void init() {
		System.out.println("Choississez un nom :");
		this.nom = Menu.choixString();
		System.out.println("Description");
		this.description = Menu.choixString();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDommage() {
		return dommage;
	}

	public void setDommage(int dommage) {
		this.dommage = dommage;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return nom;
		//return "description=" + description + ", dommage=" + dommage + ", nom=" + nom + ", type=" + type;
	}

	public float getReussite() {
		return reussite;
	}
 
	public void setReussite(int reussite) {
		this.reussite = reussite;
	}

	public int getImpact() {
		return impact;
	}

	public void setImpact(int impact) {
		this.impact = impact;
	}

	
	public int getCapacite() {
		return capacite;
	}

	
	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

}
