package fr.personnage;

import fr.capacite.*;

import java.util.*;

public class Combattant {
	protected String nom;
	protected int force;
	protected int dexterite;
	protected int intelligence;
	protected int concentration;
	protected int vitalite;
	protected int experience;
	protected Capacite[] capacite;
	protected boolean initiative;
	protected int pointAction = 2;
	
	public static final int MIN_XP = 1;
	public static final int MAX_XP = 20;
	
	public Combattant(){
		this.nom = new String("unknow");
		this.capacite = new Capacite[9];
	}

	public Combattant(String nom, int force, int dexterite, int intelligence, int concentration, int vitalite, int experience, Capacite[] capacite) {
		this.nom = nom;
		if(experience >20 || experience < 1)
			this.experience = 1;
		else
			this.experience = experience;
		if((force + dexterite + intelligence + concentration) <= 100 + experience*3)
		{
			this.force = force;
			this.dexterite = dexterite;
			this.intelligence = intelligence;
			this.concentration = concentration;
			this.vitalite = vitalite;
		}		
		this.capacite = capacite;
	}
	public Combattant(String nom, int force, int dexterite, int intelligence, int concentration, int vitalite, int experience) {
		this.nom = nom;
		if(experience >20 || experience < 1)
			this.experience = 1;
		else
			this.experience = experience;
		if((force + dexterite + intelligence + concentration) <= 100 + experience*3)
		{
			this.force = force;
			this.dexterite = dexterite;
			this.intelligence = intelligence;
			this.concentration = concentration;
			this.vitalite = vitalite;
		}
		 this.capacite = new Capacite[9];
	}
	
	public Combattant(Combattant combattant){
		this.nom = new String(combattant.nom);
		this.force = combattant.force;
		this.dexterite = combattant.dexterite;
		this.intelligence = combattant.intelligence;
		this.concentration = combattant.concentration;
		this.vitalite = combattant.vitalite;
		this.experience = combattant.experience;
		//this.capacite = new Capacite(combattant.capacite);
	}
  @Override
	public String toString() {
	  String s= "-";
	  
	  for(int i=0; i<50; i++)
		  s+="-";
	  
	  s+= "\nnom = " + this.nom + "\nforce = "  + force + "\ndexterite = "
				+ dexterite + "\nintelligence = " + intelligence + "\nconcentration = "
				+ concentration + "\nvitalite = " + vitalite + "\nexperience = "
				+ experience + "\ncapacite = " + Arrays.toString(capacite);
	  
	  
	  
	  return s;
	  
	  
	/*return "Combattant [nom = " + nom + ", force = "  + force + ", dexterite = "
			+ dexterite + ", intelligence = " + intelligence + ", concentration = "
			+ concentration + ", vitalite = " + vitalite + ", experience = "
			+ experience + ", capacite = " + Arrays.toString(capacite) + "]";*/
	
	/*---------------------------------------
	| 				Mario					|
	|---------------------------------------|
	capacite
	|*/
}
  public void initCapacite(){
	  this.capacite = new Capacite[9];
	  this.capacite[0] = new Remede();
	  this.capacite[1] = new Epee();
  }
  public void capaciteDisponible(){
	  for(int i = 0; i < this.capacite.length; i++)
		  System.out.println(this.capacite[i].toString());
  }

	public void addXP(){
		if(this.experience < Combattant.MAX_XP)
			this.experience++;
	}
	public void lowXP(){
		if(this.experience > Combattant.MIN_XP)
			this.experience--;
	}
	public void addVita(int potion){
		int vitaMax = 200 - (this.force + this.concentration + this.intelligence + this.dexterite)+ this.experience *3;
		if(this.vitalite + potion >= vitaMax)
			this.vitalite = vitaMax;
		else
			this.vitalite += potion;
	}
	public void lowVita(int degat){
		if(this.vitalite - degat <= 0)
			this.vitalite = 0;
		else
			this.vitalite-= degat;
	}
	public void init(){
		Scanner sc = new Scanner(System.in);
		this.experience = 1;
		do{
			System.out.println("Quelle est votre nom ?");
			this.nom = sc.nextLine();
			System.out.println("Dext�rit�?");
			this.dexterite = sc.nextInt();
			System.out.println("Force?");
			this.force = sc.nextInt();
			System.out.println("Intelligence?");
			this.intelligence = sc.nextInt();
			System.out.println("Concentration?");
			this.concentration = sc.nextInt();
		}while((this.dexterite + this.force + this.concentration + this.intelligence) >= (100 + this.experience));  // v�rifier la condi j'sais plus si c'est >= ou >
		this.vitalite = 200 + this.experience * 3 -(this.force + this.dexterite + this.intelligence + this.concentration);
		
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Combattant other = (Combattant) obj;
		if (!Arrays.equals(capacite, other.capacite))
			return false;
		if (concentration != other.concentration)
			return false;
		if (dexterite != other.dexterite)
			return false;
		if (experience != other.experience)
			return false;
		if (force != other.force)
			return false;
		if (intelligence != other.intelligence)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (vitalite != other.vitalite)
			return false;
		return true;
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
	
	public int getExperience() {
		return experience;
	}
	
	public void setExperience(int experience) {
		this.experience = experience;
	}
	
	public Capacite[] getCapacite() {
		return capacite;
	}
	
	public void setCapacite(Capacite[] capacite) {
		this.capacite = capacite;
	}

	public void setPointAction(int i) {
		this.pointAction = i;
	}
	public int getPointAction() {
		return this.pointAction;
	}

}