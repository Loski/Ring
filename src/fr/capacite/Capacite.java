package fr.capacite;

import java.io.Serializable;

import fr.jeu.Menu;

@SuppressWarnings("serial")
public abstract class Capacite implements Action, Serializable {

	public static final int	MIN_CAPACITE	= 2;
	public static final int	MAX_CAPACITE	= 9;

	protected String		description;
	protected int			dommage;
	protected String		nom;
	protected int			type;

	public Capacite() {

	}

	public Capacite(Capacite c) {
		this.nom = new String(c.nom);
		this.type = c.type;
		this.dommage = c.dommage;
		this.description = new String(c.description);
	}

	public Capacite(String nom, int type, int dommage, String description) {
		this.nom = nom;
		this.type = type;
		this.dommage = dommage;
		this.description = description;
	}

	/**
	 * @param f
	 * @return renvoie si une action a r�ussie
	 */
	public static boolean actionReussie(final float reussite) {
		return (Math.random()) <= reussite;
	}
 
	public static int calculImpact(final int cara_Perso, final int cara_Capa) {
		return (cara_Capa * cara_Perso) / 100;
	}

	public static float calculReussite(final int cara_Perso, final int cara_Capa) {
		return ((float)(cara_Perso * cara_Capa) / 3000);
	}

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
	public void init(){
		System.out.println("Choississez un nom :");
		this.nom = Menu.choixString();
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
		return "description=" + description + ", dommage=" + dommage + ", nom=" + nom + ", type=" + type;
	}

}
