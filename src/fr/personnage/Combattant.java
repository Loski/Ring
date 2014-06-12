package fr.personnage;

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

	public Combattant() {
		this.nom = new String("unknow");
		this.capitule = false;
		this.capaciteDefaut(); // Initialise 2 capacit�s
	}

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
	public void addCaract(){};
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

	public void capaciteDisponible() {
		for (int i = 0; i < this.capacite.size(); i++)
			System.out.println(i + 1 + "\t" + this.capacite.get(i).toString());
	}

	public void capaciteDefaut() {
		this.capacite = new ArrayList<Capacite>();
		capacite.add(new Epee());
		capacite.add(new Sortilege());
	}

	/**
	 * Permet de soigner le combattant courrant
	 * 
	 * @param i
	 *        indice de la capacit�
	 */
	public String soin(int i) {
		String s = "";
		if (this.capacite.get(i).calculReussite(this)) {
			this.addVita(this.capacite.get(i).calculImpact(this, Capacite.SOIN));
			s = "Soin de :" + this.capacite.get(i).calculImpact(this, Capacite.SOIN);
		}
		else
			s = "Echec du soin";
		return s + "   Vitalité actuelle : " + this.vitalite;
	}

	/**
	 * Permet d'enclencher une parade pour le combattant courrant
	 * 
	 * @param i
	 *        indice de la capacit�
	 * @return message d'action
	 */
	public String parade(int i) {
		if (this.capacite.get(i).calculReussite(this)) {
			switch (this.capacite.get(i).getDommage()) {
				case Capacite.MAGIQUE:
					this.protectionMagique += this.capacite.get(i).calculImpact(this, Capacite.PARADE);
					break;
				case Capacite.PHYSIQUE:
					this.protectionPhysique += this.capacite.get(i).calculImpact(this, Capacite.PARADE);
					break;
			}
			return "Protection magique : " + protectionMagique + "\nProtection physique : " + protectionPhysique;
		}
		else
			return "Echec de la parade \nProtection magique : " + protectionMagique + "\nProtection physique : " + protectionPhysique;
	}

	/**
	 * Permet d'enclencher une attaque sur un combattant cible
	 * 
	 * @param i
	 *        indice de la capacit�
	 * @param cible
	 *        Cible de l'attaque
	 * @return message d'action
	 */
	public String attaque(int i, Combattant cible) {
		if (this.capacite.get(i).calculReussite(this)) {
			int impact = this.capacite.get(i).calculImpact(this, Capacite.ATTAQUE);
			switch (this.capacite.get(i).getDommage()) {
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
				return this.capacite.get(i).getNom() + " inflige " + impact + " dommage à " + cible.getNom();
			}
		}
		else
			return "Echec de l'attaque";
	}

	public void addXP() {
		if (this.experience < Combattant.MAX_XP)
			this.experience++;
	}

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

	public void preparationTour(Combattant combattant) {
		this.finValeurAction();
		this.lowVita(combattant.getDegat());
	}

	public static void main(String[] argc) {
		Combattant[] combat = new Combattant[2];
		combat[0] = new Athlete();
		combat[0].init();
		combat[0].initCapacite();
		combat[1] = new Athlete(combat[0]);
		System.out.println(combat[1]);
		System.out.println(combat[0]);
		return;
	}

	/**
	 * Initialise le début du combat pour le combattant courant
	 */
	public void preparationCombattant() {
		this.capitule = false;
		this.vitalite = 200 + (this.experience * 3) - (this.force + this.dexterite + this.intelligence + this.concentration);
	}

	/**
	 * Met � 0 les protections du joueurs courants, et ses dégâts.
	 */
	public void finValeurAction() {
		this.protectionMagique = this.protectionPhysique = this.degat = 0;
	}

	public void gagner(Combattant perdant) {
		this.addXP();
		this.choixNouvelleCapacite(perdant);
	}

	public void perdre() {
		this.lowXP();
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