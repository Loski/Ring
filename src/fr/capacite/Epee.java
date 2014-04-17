package fr.capacite;

import fr.personnage.Combattant;

public class Epee extends Capacite{
	private int impactEpee;
	private int paradeEpee;
	private int maniabilite;

	public Epee(){
		super("unknow", Capacite.EPEE, Capacite.PHYSIQUE, "C'est une épée");
		this.impactEpee = 33;
		this.paradeEpee = 33;
		this.maniabilite = 34;
	}
	
	public Epee(int impact,int maniabilite, int parade, String nom, String description){
		super(nom, Capacite.EPEE, Capacite.PHYSIQUE, description);
		if(impactEpee + maniabilite + paradeEpee == 100  && parade >= 20 && impactEpee >=20 && maniabilite >= 20){
			this.impact = impact;
			this.maniabilite = maniabilite;
			this.paradeEpee = parade;
		}
	}
	
	public Epee(Epee sword){
		this.impactEpee = sword.impactEpee;
		this.maniabilite = sword.maniabilite;
		this.paradeEpee = sword.paradeEpee;
		this.nom = new String(sword.nom);
	}
	
/*
	public void calculAttaque(Combattant combattant){
		this.attaque =  (combattant.getForce() * this.impact) / 10_000; //p'tete 100
	}
	
	public void calculParade(Combattant combattant){
		this.parade = (combattant.getForce() * this.parade_arme) / 10_000; 
	}
	
	public void calcul(Combattant combattant){
		this.calculImpact(combattant.getForce(), this.impactEpee); 
	}
	

	public void calculImpact(Combattant combattant) {
		// TODO Auto-generated method stub
		
	}

	public void calculReussite(Combattant combattant) {
		super.calculReussite(combattant.getDexterite(), this.maniabilite);
	}

*/
}
