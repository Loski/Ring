package fr.personnage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import fr.capacite.*;

public class Combattant {
	
	protected String nom;
	protected int force;
	protected int dexterite;
	protected int intelligence;
	protected int concentration;
	protected int vitalite;
	protected int experience;
	protected ArrayList<Capacite> capacite;
	protected int pointAction = 2;
	protected boolean capitule, enVie; 
	protected int nombreCapacite = 2;
	protected int protectionMagique;
	protected int protectionPhysique;
	public static final int MIN_XP = 1;
	public static final int MAX_XP = 20;
	public static final int MAX_CAPACITE = 9;

	public Combattant() {
		this.nom = new String("unknow");
		this.capacite = new ArrayList<Capacite>();
		this.capitule = false;
		this.enVie = true;
	}

	public Combattant(String nom, int force, int dexterite, int intelligence,
			int concentration, int vitalite, int experience, ArrayList<Capacite> capacite) {
			this.nom = nom;
			this.experience = Combattant.MIN_XP;
			this.experience = experience;
			this.force = force;
			this.dexterite = dexterite;
			this.intelligence = intelligence;
			this.concentration = concentration;
			this.vitalite = vitalite;
			this.capacite = capacite;
	}

	public Combattant(String nom, int force, int dexterite, int intelligence,
			int concentration, int vitalite, int experience) {
		this.nom = nom;
		if (experience > 20 || experience < 1)
			this.experience = 1;
		else
			this.experience = experience;
		if ((force + dexterite + intelligence + concentration) <= 100 + experience * 3) {
			this.force = force;
			this.dexterite = dexterite;
			this.intelligence = intelligence;
			this.concentration = concentration;
			this.vitalite = vitalite;
		}
		this.capacite = new ArrayList<Capacite>();
	}

	public Combattant(Combattant combattant) {
		this.nom = new String(combattant.nom);
		this.force = combattant.force;
		this.dexterite = combattant.dexterite;
		this.intelligence = combattant.intelligence;
		this.concentration = combattant.concentration;
		this.vitalite = combattant.vitalite;
		this.experience = combattant.experience;
		//this.capacite = new Capacite(combattant.capacite);
	}

	public String toString() {
		String s = "-";
		for (int i = 0; i < 50; i++)
			s += "-";
		s += "\nnom = " + this.nom + "\nforce = " + force + "\ndexterite = "
				+ dexterite + "\nintelligence = " + intelligence
				+ "\nconcentration = " + concentration + "\nvitalite = "
				+ vitalite + "\nexperience = " + experience;

		return s;
	}

	public void initCapacite() {
		this.capacite = new ArrayList<Capacite>();
	}
	
	public void capituler(){
		this.capitule = true;
	}
	public void capaciteDisponible() {
		for (int i = 0; i < this.nombreCapacite; i++)
			System.out.println(i+1 + "\t"+this.capacite.get(i).toString());
	}
	/**
	 * Permet de soigner le combattant courrant
	 * @param i
	 * indice de la capacité
	 * @message d'action
	 */
	public String soin(int i) {
		if(this.capacite.get(i).calculReussite(this)){
		this.addVita(this.capacite.get(i).calculImpact(this));
			return "Soin de :" + this.capacite.get(i).calculImpact(this); // 	On retourne un string pour l'aff graph.
		}
		else
			return "Echec du soin";
		}
	
	/**
	 * Permet d'enclencher une parade pour le combattant courrant
	 * @param i
	 * indice de la capacité
	 * @return message d'action
	 */
	public String parade(int i){
		if(this.capacite.get(i).calculReussite(this)){
			switch(this.capacite.get(i).getDommage()){
				case Capacite.MAGIQUE:
					this.protectionMagique +=  this.capacite.get(i).calculImpact(this); //+= s'il lance deux parades magiques dans le même tour
					break;
				case Capacite.PHYSIQUE:
					this.protectionPhysique += this.capacite.get(i).calculImpact(this);
					break;
			}
			return "Protection magique : " + protectionMagique+"\nProtection physique : " + protectionPhysique;
		}
		else
			return "Echec de la parade \nProtection magique : " + protectionMagique+"\nProtection physique : " + protectionPhysique;
		
	}
	
	/**
	 * Permet d'enclencher une attaque sur un combattant cible
	 * @param i
	 * indice de la capacité
	 * @param cible
	 * Cible de l'attaque
	 * @return message d'action
	 */
	public String attaque(int i, Combattant cible) {
		
		if(this.capacite.get(i).calculReussite(this)){
			int impact = this.capacite.get(i).calculImpact(this);
			switch(this.capacite.get(i).getDommage()){
			case Capacite.PHYSIQUE:
				if(this.capacite.get(i).getDommage() == Capacite.EPEE)
					impact -= cible.protectionPhysique;
				else
					impact -= cible.protectionPhysique/3;
				break;
			case Capacite.MAGIQUE:
				impact -= cible.protectionMagique;
				impact -= cible.protectionPhysique/3;  // La parade de l'épée?
				break;
			}
			if(impact>0)
				cible.lowVita(impact);
			else
				return "Les parades sont trop puissantes, echec !";
			return "Attaque de "+impact+" dommage";
		}
		else
			return "Echec critique";

		
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
	 * Soigne le personnage selon l'efficacité de la potion jusqu'à la santé maximale du combattant
	 * @param potion
	 * Efficacité du soin
	 */
	public void addVita(int potion) {
		int vitaMax = 200
				- (this.force + this.concentration + this.intelligence + this.dexterite)
				+ this.experience * 3;
		if (this.vitalite + potion >= vitaMax)
			this.vitalite = vitaMax;
		else
			this.vitalite += potion;
	}

	public void lowVita(int degat) {
		if (this.vitalite - degat <= 0)
			this.setEnVie(false);
		else
			this.vitalite -= degat;
	}

	public void init() {
		Scanner sc = new Scanner(System.in);
		this.experience = 1;
		do {
			System.out.println("Quelle est votre nom ?");
			this.nom = sc.nextLine();
			System.out.println("Dextérité?");
			this.dexterite = sc.nextInt();
			System.out.println("Force?");
			this.force = sc.nextInt();
			System.out.println("Intelligence?");
			this.intelligence = sc.nextInt();
			this.concentration = 100 + this.experience - (this.dexterite + this.force + this.intelligence);
			System.out.println("La concentration est égal à "+ this.concentration);
			}while ((this.dexterite + this.force + this.concentration + this.intelligence) > (100 + this.experience));
	}
	public void choixNouvelleCapacite(){
		this.capaciteDisponible();
		int choix;
		Scanner sc = new Scanner(System.in);
		if(this.capacite.size() == Combattant.MAX_CAPACITE){
		do{
			System.out.println("Capacité à remplacer");
			choix = sc.nextInt();
		}while(choix <= 0 && choix > this.capacite.size());
		
	}
		}
	public static void main(String[] Arg){
		Athlete a = new Athlete();
		a.initCapacite();
		System.out.println(a.capacite.size());
	}
	/**
	 * Initialise le début du combat pour le combattant courrant
	 */
	public void preparationCombattant(){ // futur changement de nom
		this.capitule = false;
		this.enVie = true;
		this.vitalite = 200 + this.experience * 3 - (this.force + this.dexterite + this.intelligence + this.concentration);
	}

	/**
	 * Met à 0 les protections du joueurs courants.
	 */
	public void finProtection(){
		this.protectionMagique = this.protectionPhysique = 0;
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

	public void setEnVie(boolean enVie) {
		this.enVie = enVie;
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

	public void setCapacite(ArrayList <Capacite> capacite) {
		this.capacite = capacite;
	}

	public void setPointAction(int i) {
		this.pointAction = i;
	}
	public void enlevePointAction(){
		if(this.pointAction > 0)
			this.pointAction--;
	}
	public int getPointAction() {
		return this.pointAction;
	}

	public boolean isCapitule() {
		return capitule;
	}

	public void setCapitule(boolean capitule) {
		this.capitule = capitule;
	}
	public int getNombreCapacite() {
		return nombreCapacite;
	}

	public void setNombreCapacite(int nombreCapacite) {
		this.nombreCapacite = nombreCapacite;
	}


}