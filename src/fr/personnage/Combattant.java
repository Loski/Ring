package fr.personnage;

/**
 * Combattant est la classe qui gère les actions des combattants
 * @author Maxime LAVASTE
 * @author Loïc LAFONTAINE
 */
import java.io.Serializable;
import java.util.*;

import fr.capacite.*;
import fr.jeu.Menu;

public class Combattant implements Serializable {

	public static final int			GUERRIER	= 0, MAGE = 1, ATHLETE = 2;
	public static final int			FORCE		= 1, INTELLIGENCE = 2,
			DEXTERITE = 3, CONCENTRATION = 4;
	protected String				nom;
	protected int					force;
	protected int					dexterite;
	protected int					intelligence;
	protected int					concentration;
	protected int					vitalite;
	protected int					experience;
	protected int					protectionMagique;
	protected int					protectionPhysique;
	protected int					type;
	protected int					degat;
	/**
	 * Un combattant poss�de un tableau de Capacit�
	 * 
	 * @see fr.capacite.Capacite
	 */
	protected ArrayList<Capacite>	capacite;
	protected boolean				capitule;
	protected boolean				jePeuxJouer;
	public static final int			MIN_XP		= 1;
	public static final int			MAX_XP		= 20;

	public Combattant(String nom, int force, int dexterite, int intelligence,
			int concentration, int vitalite, int experience, int type,
			ArrayList<Capacite> capacite) {
		this.nom = nom;
		this.experience = experience;
		this.force = force;
		this.dexterite = dexterite;
		this.intelligence = intelligence;
		this.concentration = concentration;
		this.vitalite = vitalite;
		this.capacite = capacite;
		this.type = type;
	}

	public Combattant(Combattant combattant) {
		this.nom = new String(combattant.nom);
		this.force = combattant.force;
		this.dexterite = combattant.dexterite;
		this.intelligence = combattant.intelligence;
		this.concentration = combattant.concentration;
		this.vitalite = combattant.vitalite;
		this.experience = combattant.experience;
		this.capacite = new ArrayList<Capacite>(combattant.capacite);
		this.type = combattant.type;
		this.degat = combattant.degat;
		this.jePeuxJouer = combattant.jePeuxJouer;
	}

	/**
	 * Initialise un combattant selon le paramètre passé
	 * 
	 * @param choix
	 *        Un nombre
	 * @return Le combattant initialisé
	 */
	public static Combattant combattantBase(int choix) {
		switch (choix) {
			case Combattant.ATHLETE:
				return new Athlete();
			case Combattant.GUERRIER:
				return new Guerrier();
			case Combattant.MAGE:
				return new Magicien();
			default:
				return new Athlete();
		}
	}

	/**
	 * @return Nombre aléatoire entre 1 et 4, pour l'ajout/perte caractéristiques
	 */
	public static int nbAleatoire() {
		return (int) (1 + Math.random() * (4 - 1));
	}

	public String toString() {
		String s = "-";
		for (int i = 0; i < 50; i++)
			s += "-";
		s += "\nnom = " + this.nom + "\nforce = " + force + "\ndexterite = " + dexterite + "\nintelligence = " + intelligence + "\nconcentration = " + concentration + "\nvitalite = " + vitalite + "\nprotection magique = " + protectionMagique + "\nprotectionPhysique = " + protectionPhysique + "\nCapacit� : " + capaciteString() + "\nlevel  = " + experience + "\n Je peux jouer : " + this.jePeuxJouer;
		return s;
	}

	public String uhdCombattant() {
		String s = "-";
		for (int i = 0; i < 50; i++)
			s += "-";
		s += "\nnom = " + this.nom + "\nvitalite = " + vitalite + "\nlevel  = " + experience;
		return s;
	}

	public void initCapacite() {
		this.capacite = new ArrayList<Capacite>();
		for (int i = 0; i < 2; i++)
			capacite.add(Menu.choisirCapacite());
	}

	public void capituler() {
		this.capitule = true;
	}

	public String capaciteString() {
		String s = "";
		for (Capacite a : capacite)
			s += a.toString();
		return s;
	}

	/**
	 * Affiche les capacités disponibles du combattants
	 */
	public void capaciteDisponible() {
		for (int i = 0; i < this.capacite.size(); i++)
			System.out.println(i + 1 + "\t" + this.capacite.get(i).toString());
	}

	/**
	 * Permet de soigner le combattant courrant
	 * 
	 * @param c
	 *       	capacité utilisée pour le soin
	 */
	public String soin(Capacite c) {
		String s = "";
		if (c.calculReussite(this)) {
			this.addVita(c.calculImpact(this, Capacite.SOIN));
			s =c.getNom()+":\nSoin de :" + c.calculImpact(this, Capacite.SOIN);
		}
		else
			s = "Echec du soin";
		return s + "   Vitalité actuelle : " + this.vitalite;
	}

	/**
	 * Permet d'enclencher une parade pour le combattant courrant
	 * 
	 * @param c
	 *        capacité utilisée pour la parade
	 * @return message d'action
	 */
	public String parade(Capacite c) {
		if (c.calculReussite(this)) {
			switch (c.getDommage()) {
				case Capacite.MAGIQUE:
					this.protectionMagique += c.calculImpact(this, Capacite.PARADE);
					break;
				case Capacite.PHYSIQUE:
					this.protectionPhysique += c.calculImpact(this, Capacite.PARADE);
					break;
			}
			return c.getNom() + ":\nProtection magique : " + protectionMagique + "\nProtection physique : " + protectionPhysique;
		}
		else
			return "Echec de la parade \nProtection magique : " + protectionMagique + "\nProtection physique : " + protectionPhysique;
	}

	/**
	 * Permet d'enclencher une attaque sur un combattant cible
	 * 
	 * @param c
	 *        capacité utilisée pour l'attaque
	 * @param cible
	 *        Cible de l'attaque
	 * @return message d'action
	 */
	public String attaque(Capacite c, Combattant cible) {
		if (c.calculReussite(this)) {
			int impact = c.calculImpact(this, Capacite.ATTAQUE);
			switch (c.getDommage()) {
				case Capacite.PHYSIQUE:
					impact -= cible.protectionPhysique;
					impact -= cible.protectionMagique / 3;
					break;
				case Capacite.MAGIQUE:
					impact -= cible.protectionMagique;
					impact -= cible.protectionPhysique / 3;
					break;
			}
			if (impact <= 0)
				return "Les parades sont trop puissantes...";
			else {
				this.degat += impact;
				return this.nom + " attaque avec " + c.getNom() + " inflige " + impact + " dommages à " + cible.getNom();
			}
		}
		else
			return this.nom + " attaque avec " + c.getNom() + ": Echec de l'attaque";
	}

	/**
	 * Ajoute un point d'expérience si possible
	 */
	public void addXP() {
		if (this.experience < Combattant.MAX_XP)
			this.experience++;
	}

	/**
	 * Enlève un point d'expérience si possible
	 */
	public void lowXP() {
		if (this.experience > Combattant.MIN_XP)
			this.experience--;
	}

	/**
	 * Soigne le personnage selon l'efficacit� de la potion avec pour limite la sant� maximale du combattant
	 * 
	 * @param potion
	 *        Efficacit� du soin
	 */
	public void addVita(int potion) {
		int vitaMax = 200 - (this.force + this.concentration + this.intelligence + this.dexterite) + this.experience * 3;
		if (this.vitalite + potion >= vitaMax)
			this.vitalite = vitaMax;
		else
			this.vitalite += potion;
	}

	/**
	 * Baisse la vitalit� du combattant cible selon la valeur de d�g�t. Si elle est inf�rieur ou �gal � 0, le boolean enVie du combattant cible est
	 * mis � false
	 * 
	 * @param degat
	 *        intensit� des d�g�ts
	 */
	public void lowVita(int degat) {
		if (this.vitalite - degat < 0)
			this.vitalite = 0;
		else
			this.vitalite -= degat;
	}

	public void init() {
		Scanner sc = new Scanner(System.in);
		this.experience = 1;
		do {
			System.out.println("Quelle est votre nom ?");
			this.nom = sc.next();
			System.out.println("Dext�rit�?");
			this.dexterite = Menu.choix();
			System.out.println("Force?");
			this.force = Menu.choix();
			System.out.println("Intelligence?");
			this.intelligence = Menu.choix();
			System.out.println("Concentration?");
			this.concentration = Menu.choix();
		} while ((this.dexterite + this.force + this.concentration + this.intelligence) > (100 + this.experience));
	}

	public void choixNouvelleCapacite(Combattant perdant) {
		this.capaciteDisponible();
		int choix, choixCapa;
		System.out.println("Voulez vous une nouvelle capacit�?");
		if (Menu.choix() == 1) {
			do {
				System.out.println("0 pour retour");
				perdant.capaciteDisponible();
				choixCapa = Menu.choix() - 1;
			} while (choixCapa < 0 || choixCapa > perdant.capacite.size() + 1);
			if (choixCapa == 0)
				return;
			if (this.capacite.size() == Capacite.MAX_CAPACITE) {
				do {
					System.out.println("Capacit� � remplacer");
					choix = Menu.choix() - 1;
				} while (choix <= -1 && choix > this.capacite.size() + 1);
				this.capacite.set(choix, Capacite.nouvelleCapacite(perdant.capacite.get(choixCapa)));
			}
			else
				this.capacite.add(Capacite.nouvelleCapacite(perdant.capacite.get(choixCapa)));
		}
	}

	/**
	 * Prépare le tour du joueur courant.
	 * 
	 * @param combattant
	 *        Le combattant adverse pour avoir ses dégats.
	 */
	public void preparationTour(Combattant combattant) {
		this.finValeurAction();
		this.lowVita(combattant.getDegat());
	}

	/**
	 * Initialise le début du combat pour le combattant courant.
	 */
	public void preparationCombattant() {
		this.capitule = false;
		this.vitalite = 200 + (this.experience * 3) - (this.force + this.dexterite + this.intelligence + this.concentration);
	}

	/**
	 * Met à 0 les protections du joueurs courants, et ses dégâts.
	 */
	public void finValeurAction() {
		this.protectionMagique = this.protectionPhysique = this.degat = 0;
	}

	/**
	 * Gestion des conséquences pour le gagnant.
	 */
	public void gagner(Combattant perdant) {
		this.addXP();
		this.choixNouvelleCapacite(perdant);
		if ((force + dexterite + intelligence + concentration + 1 <= 100 + experience))
			this.addCaract();
	}

	/**
	 * Ajoute une caractéristique au hasard en respectant les contraintes du personnage
	 */
	public void addCaract() {
	}

	/**
	 * Supprime une caractéristique au hasard en respectant les contraintes du personnage
	 */
	public void supCaract() {
	}

	/**
	 * Gestion des conséquences pour le vaincu.
	 */
	public void perdre() {
		this.lowXP();
		if (!(force + dexterite + intelligence + concentration <= 100 + experience))
			this.supCaract();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getForce() {
		return force;
	}

	public void setForce(int force) {
		this.force = force;
	}

	public int getDexterite() {
		return dexterite;
	}

	public void setDexterite(int dexterite) {
		this.dexterite = dexterite;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getConcentration() {
		return concentration;
	}

	public void setConcentration(int concentration) {
		this.concentration = concentration;
	}

	public int getVitalite() {
		return vitalite;
	}

	public void setVitalite(int vitalite) {
		this.vitalite = vitalite;
	}

	public boolean isEnVie() {
		return this.vitalite > 0;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public ArrayList<Capacite> getCapacite() {
		return capacite;
	}

	public void setCapacite(ArrayList<Capacite> capacite) {
		this.capacite = capacite;
	}

	public boolean isCapitule() {
		return capitule;
	}

	public void setCapitule(boolean capitule) {
		this.capitule = capitule;
	}

	public int getType() {
		return type;
	}

	public int getDegat() {
		return degat;
	}

	public void setDegat(int degat) {
		this.degat = degat;
	}

	public boolean isJePeuxJouer() {
		return jePeuxJouer;
	}

	public void setJePeuxJouer(boolean jePeuxJouer) {
		this.jePeuxJouer = jePeuxJouer;
	}

	public int getProtectionMagique() {
		return protectionMagique;
	}

	public int getProtectionPhysique() {
		return protectionPhysique;
	}
}