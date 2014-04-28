package fr.capacite;

public abstract class Capacite implements Action{

	public static final int MIN_CAPACITE = 2;
	public static final int MAX_CAPACITE = 9;
	
	protected String description;
	protected int categorie;
	protected String nom;
	protected int type;

	
	public Capacite(){
		
	}
	
	public Capacite(String nom, int type, int categorie, String description){
		this.nom = nom;
		this.type = type;
		this.categorie = categorie;
		this.description = description;
	}
	

	public int calculImpact(int cara_Perso, int cara_Capa) {
		return (cara_Capa * cara_Perso) / 100;
	}	
	
	public int calculReussite(int cara_Perso, int cara_Capa) {
		return (cara_Perso * cara_Capa) / 10_000;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
