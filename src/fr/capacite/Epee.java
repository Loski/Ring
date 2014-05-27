package fr.capacite;

import fr.jeu.Menu;
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
			this.maniabilite = maniabilite;
			this.paradeEpee = parade;
		}
	}
	
	public Epee(Epee sword){
		super(sword.nom, Capacite.EPEE, Capacite.PHYSIQUE, sword.description);
		this.impactEpee = sword.impactEpee;
		this.maniabilite = sword.maniabilite;
		this.paradeEpee = sword.paradeEpee;
	}
	
	public Epee(Capacite c){
		super(c);
		Epee e = (Epee) c;
		this.impactEpee = e.impactEpee;
		this.maniabilite =  e.maniabilite;
		this.paradeEpee = e.paradeEpee;
	}
	/**
	 * Demande au combattant courant de choisir le type d'action d'une épée
	 * 1 -> Attaque
	 * 2 -> Parade
	 * @return
	 * true si c'est une attaque
	 * false si c'est une parade
	 */
	public static boolean choixType(){
		int choix;
		do{
			System.out.println("1.\tAttaque\n2.\tParade");
			choix = Menu.choix();
		}while(choix <=0 || choix >2);
		return choix == Action.ATTAQUE; 
	}
	
	@Override
	public int calculImpact(Combattant combattant, int type) {
		if(type == Capacite.ATTAQUE)
			return calculImpact(combattant.getForce(), this.impactEpee);
		return calculImpact(combattant.getForce(), this.paradeEpee);
	}
	@Override
	public boolean calculReussite(Combattant combattant) {
		return actionReussie(calculReussite(combattant.getDexterite(), this.maniabilite));
	}

	@Override
	public String toString() {
		return "Epee " +nom+"[impactEpee=" + impactEpee + ", paradeEpee=" + paradeEpee
				+ ", maniabilite=" + maniabilite + "]";
	}

}
