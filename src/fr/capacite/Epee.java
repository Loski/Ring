package fr.capacite;

import fr.personnage.Combattant;

public class Epee extends Capacite{
	private int impactEpee;
	private int paradeEpee;
	private int maniabilite;

	public Epee(){
		super("unknow", Capacite.EPEE, Capacite.PHYSIQUE, "C'est une �p�e");
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

	@Override
	public void calculImpact(Combattant combattant) {
		System.out.print("epee");
		super.calculImpact(combattant.getForce(), this.impactEpee);
	}

	public void calculReussite(Combattant combattant) {
		super.calculReussite(combattant.getDexterite(), this.maniabilite);
	}

}
