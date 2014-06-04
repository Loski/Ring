package fr.personnage;

import java.util.ArrayList;

import fr.capacite.*;

public class Athlete extends Combattant {

	public Athlete(String nom, int force, int dexterite, int intelligence,
			int concentration, int vitalite, int experience,
			ArrayList<Capacite> capacite) {
		super(nom, force, dexterite, intelligence, concentration, vitalite, experience, ATHLETE, capacite);
	}

	public Athlete() {
		super("Athlete_inconnu", 25, 25, 25, 26, 103, Combattant.MIN_XP, ATHLETE, null);
		capaciteDefaut();
	}

	public Athlete(Combattant athlete) {
		super(athlete);
	}

	public void init() {
		do {
			super.init();
		} while (this.intelligence < 20 && this.force < 20 && this.dexterite < 20 && this.concentration < 20);
	}
}