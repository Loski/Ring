package fr.capacite;

import fr.personnage.Combattant;

public abstract class Capacite implements Action{

	public static final int MIN_CAPACITE = 2;
	public static final int MAX_CAPACITE = 9;
	
	public String description;
	protected int reussite;
	protected int impact;
	protected int categorie;
	protected String nom;
	protected int type;

	
	public Capacite(){
		
	}
	public Capacite(String description, int reussite, int impact, int categorie, String nom, int type) {
		this.description = description;
		this.reussite = reussite;
		this.impact = impact;
		this.categorie = categorie;
		this.nom = nom;
		this.type = type;
	}
	public Capacite(String nom, int type, int categorie, String description){
		this.nom = nom;
		this.type = type;
		this.categorie = categorie;
		this.description = description;
	}
	
	public void calculImpact(int cara_Perso, int cara_Capa) {
		this.impact = (cara_Capa * cara_Perso) / 100;
	}	
	
	public void calculReussite(int cara_Perso, int cara_Capa) {
		this.reussite = (cara_Perso * cara_Capa) / 10_000;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getReussite() {
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
	public int getCategorie() {
		return categorie;
	}
	public void setCategorie(int categorie) {
		this.categorie = categorie;
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
	public void setType(int type){
		this.type = type;
	}
	
}
