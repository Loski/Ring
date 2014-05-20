package fr.personnage;

import java.util.ArrayList;

import fr.capacite.*;

public class Athlete extends Combattant {

	public Athlete(String nom, int force, int dexterite, int intelligence, int concentration, int vitalite, int experience, ArrayList<Capacite> capacite) {
		super(nom, force, dexterite, intelligence, concentration, vitalite, experience, capacite);
	}

	public Athlete() {
		super("Athlete inconnu", 25, 25, 25, 25, 200, 1, null);
	}
	public Athlete(Athlete athlete){
			super(athlete.nom, athlete.force, athlete.dexterite, athlete.intelligence, athlete.concentration,  athlete.vitalite, athlete.experience, athlete.capacite);
	}

	public void init(){
		do{
			super.init();
		}while(this.intelligence <= 20 && this.force <= 20 && this.dexterite <= 20 && this.concentration <= 20);
	}
	public void initCapacite(){
		super.initCapacite();
		this.capacite.add(new Epee());
		this.capacite.add(new Remede());
	}
}