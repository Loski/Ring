package fr.capacite;

import fr.personnage.Combattant;

public class Epee extends Capacite{
	private int impactEpee;
	private int parade;
	private int parade_arme;
	private int attaque;
	private int maniabilite;
	
	
	public Epee(){
		this.nom = "unknow";
		this.impactEpee = 33;
		this.parade_arme = 33;
		this.maniabilite = 34;
		this.categorie = Action.PHYSIQUE; 
	}
	
	public Epee(int impact,int maniabilite, int parade, String nom){
		if(impact + maniabilite + parade == 100){
			this.impact = impact;
			this.maniabilite = maniabilite;
			this.parade_arme = parade;
			this.nom = nom;
			this.categorie = Action.PHYSIQUE;
		}
	}
	
	public Epee(Epee sword){
		this.impact = sword.impact;
		this.maniabilite = sword.maniabilite;
		this.parade_arme = sword.parade;
		this.categorie = sword.categorie;
		this.nom = new String(sword.nom);
	}
	

	public void calculAttaque(Combattant combattant){
		this.attaque =  (combattant.getForce() * this.impact) / 10_000; //p'tete 100
	}
	
	public void calculParade(Combattant combattant){
		this.parade = (combattant.getForce() * this.parade_arme) / 10_000; 
	}
	public void calcul(Combattant combattant){
		this.calculImpact(combattant.getForce(), this.impactEpee); 
		this.calculReussite(combattant.getDexterite(), this.maniabilite);
	}
	///Attaque
	public boolean attaque(Combattant combattant, Combattant cible){

		System.out.println(this.impact);
		if(true) // faire la fonction test
			cible.lowVita(this.impact);
		return false;
	}	
	/// Parade

	public int getImpact() {
		return impact;
	}
	

	public void setImpact(int impact) {
		this.impact = impact;
	}

	public int getManiabilite() {
		return maniabilite;
	}

	public void setManiabilite(int maniabilite) {
		this.maniabilite = maniabilite;
	}

	public int getParade() {
		return parade;
	}

	public void setParade(int parade) {
		this.parade = parade;
	}

	public int getParade_arme() {
		return parade_arme;
	}

	public void setParade_arme(int parade_arme) {
		this.parade_arme = parade_arme;
	}

	public int getAttaque() {
		return attaque;
	}

	public void setAttaque(int attaque) {
		this.attaque = attaque;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}


}
