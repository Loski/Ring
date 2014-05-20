package fr.capacite;
import fr.capacite.*;
public abstract class Capacite implements Action{

	public static final int MIN_CAPACITE = 2;
	public static final int MAX_CAPACITE = 9;
	
	protected String description;
	protected int dommage;
	protected String nom;
	protected int type;

	
	public Capacite(){
		
	}
	
	public Capacite(String nom, int type, int dommage, String description){
		this.nom = nom;
		this.type = type;
		this.dommage = dommage;
		this.description = description;
	}
	
	public boolean attaqueReussie(int reussite){
		return Math.random() * 100 <= reussite;
	}
	public int calculImpact(int cara_Perso, int cara_Capa) {
		return (cara_Capa * cara_Perso) / 100;
	}	
	
	public int calculReussite(int cara_Perso, int cara_Capa) {
		return (cara_Perso * cara_Capa) / 10_000;
	}
	public static Capacite nouvelleCapacite(int i, Capacite c){
		if(c instanceof Bouclier)
			return new Bouclier(c);
		else if(c instanceof Epee)
			return new Epee(c);
		else if(c instanceof Sortilege)
			return new Sortilege(c);
		else 
			return new Remede(c);
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
	public void setType(int type){
		this.type = type;
	}
	
}
