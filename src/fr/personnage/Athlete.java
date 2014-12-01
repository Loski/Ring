package fr.personnage;
/**
 * Athlete est la classe qui assure la gestion des caractéristiques d'un athlete
 * @author Maxime LAVASTE
 * @author Loïc LAFONTAINE
 */
import java.util.ArrayList;

import fr.capacite.*;
import fr.jeu.Sauvegarde;
import fr.jeu.SauvegardeCapacite;

public class Athlete extends Combattant {

	public Athlete(String nom, int force, int dexterite, int intelligence,
			int concentration, int vitalite, int experience,
			ArrayList<Capacite> capacite) {
		super(nom, force, dexterite, intelligence, concentration, vitalite, experience, ATHLETE, capacite);
	}

	public Athlete() {
		super("Athlete", 25, 25, 25, 26, 103, Combattant.MIN_XP, ATHLETE, null);
		capaciteDefaut();
	}

	public Athlete(Combattant athlete) {
		super(athlete);
	}
	
	/**
	 * Initialise des capacités par défauts
	 */
	public void capaciteDefaut() {
		this.capacite = new ArrayList<Capacite>();
		this.capacite.add(new SauvegardeCapacite<Sortilege>().chargerCapacite("Sortilege/Le souffle du grand ange", Capacite.SORTILEGE));
		this.capacite.add(new SauvegardeCapacite<Bouclier>().chargerCapacite("Bouclier/L'Egide", Capacite.BOUCLIER));
		this.capacite.add(new SauvegardeCapacite<Epee>().chargerCapacite("Epee/Excalibur", Capacite.EPEE));
	}

	public void init() {
		do {
			System.out.println("ath");
			super.init();
		} while (this.intelligence < 20 && this.force < 20 && this.dexterite < 20 && this.concentration < 20);
	}
	/**
	 * 
	 * @see fr.personnage.Combattant#addCaract()
	 */
	public void addCaract() {
		int hasard = Combattant.nbAleatoire();
		switch (hasard) {
			case FORCE:
				force++;
				break;
			case DEXTERITE:
				dexterite++;
				break;
			case INTELLIGENCE:
				intelligence++;
				break;
			case CONCENTRATION:
				concentration++;
		}
	}
	/**
	 * 
	 * @see fr.personnage.Combattant#supCaract()
	 */
	public void supCaract() {
		int hasard = Combattant.nbAleatoire();
		boolean enleverPointImpossible = false;
		switch (hasard) {
			case FORCE:
				if (force - 1 >= 20)
					force--;
				else
					enleverPointImpossible = true;
				break;
			case DEXTERITE:
				if (dexterite - 1 >= 20)
					dexterite--;
				else
					enleverPointImpossible = true;
				break;
			case INTELLIGENCE:
				if (intelligence - 1 >= 20)
					intelligence--;
				else
					enleverPointImpossible = true;
				break;
			case CONCENTRATION:
				if (concentration - 1 >= 20)
					concentration--;
				else
					enleverPointImpossible = true;
				break;
		}
		if (enleverPointImpossible)
			supCaract();
	}
}