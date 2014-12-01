package fr.personnage;
/**
 * Magicien est la classe qui assure la gestion des caractéristiques d'un magicien
 * @author Maxime LAVASTE
 * @author Loïc LAFONTAINE
 */
import java.util.ArrayList;

import fr.capacite.*;
import fr.jeu.SauvegardeCapacite;

public class Magicien extends Combattant {

	public void init() {
		do {
			super.init();
		} while (this.intelligence < Math.max(this.force, this.dexterite) + 15 && this.concentration < Math.max(this.force, this.dexterite) + 15);
	}

	public Magicien(String nom, int force, int dexterite, int intelligence,
			int concentration, int vitalite, int experience,
			ArrayList<Capacite> capacite) {
		super(nom, force, dexterite, intelligence, concentration, vitalite, experience, Combattant.MAGE, capacite);
	}

	public Magicien(Combattant m) {
		super(m);
	}

	public Magicien() {
		super("Loic", 5, 5, 46, 45, 103, Combattant.MIN_XP, Combattant.MAGE, null);
		capaciteDefaut();
	}

	/**
	 * Initialise des capacités par défauts
	 */
	public void capaciteDefaut() {
		this.capacite = new ArrayList<Capacite>();
		this.capacite.add(SauvegardeCapacite.chargerCapacite("Sortilege/Le souffle du grand ange", Capacite.SORTILEGE));
		this.capacite.add(SauvegardeCapacite.chargerCapacite("Sortilege/FUS ROH DAH", Capacite.SORTILEGE));
		this.capacite.add(SauvegardeCapacite.chargerCapacite("Sortilege/Avada Kedavra", Capacite.SORTILEGE));
		this.capacite.add(SauvegardeCapacite.chargerCapacite("Sortilege/Armure de feu", Capacite.SORTILEGE));
	}

	/**
	 * 
	 * @see fr.personnage.Combattant#addCaract()
	 */
	public void addCaract() {
		int hasard = Combattant.nbAleatoire();
		boolean nouveauPointImpossible = false;
		switch (hasard) {
			case FORCE:
				if (intelligence >= Math.max(force + 1, dexterite) + 15 && concentration >= Math.max(force + 1, dexterite) + 15)
					force++;
				else
					nouveauPointImpossible = true;
				break;
			case DEXTERITE:
				if (intelligence >= Math.max(force, dexterite + 1) && concentration >= Math.max(force, dexterite + 1) + 15)
					dexterite++;
				else
					nouveauPointImpossible = true;
				break;
			case INTELLIGENCE:
				intelligence++;
				break;
			case CONCENTRATION:
				concentration++;
		}
		if (nouveauPointImpossible) {
			addCaract();
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
				if (force > 0)
					force--;
				else
					enleverPointImpossible = true;
				break;
			case DEXTERITE:
				if (dexterite > 0)
					dexterite--;
				else
					enleverPointImpossible = true;
				break;
			case INTELLIGENCE:
				if (intelligence - 1 >= Math.max(force, dexterite) + 15)
					intelligence--;
				else
					enleverPointImpossible = true;
				break;
			case CONCENTRATION:
				if (concentration - 1 >= Math.max(force, dexterite) + 15)
					concentration--;
				else
					enleverPointImpossible = true;
				break;
		}
		if (enleverPointImpossible)
			supCaract();
	}
}