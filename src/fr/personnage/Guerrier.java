package fr.personnage;
/**
 * Guerrier est la classe qui assure la gestion des caractéristiques d'un guerrier
 * @author Maxime LAVASTE
 * @author Loïc LAFONTAINE
 */
import java.util.ArrayList;

import fr.capacite.*;
import fr.jeu.SauvegardeCapacite;

public class Guerrier extends Combattant {

	public Guerrier(Combattant guerrier) {
		super(guerrier);
	}

	public Guerrier(String nom, int force, int dexterite, int intelligence,
			int concentration, int vitalite, int experience, int type,
			ArrayList<Capacite> capacite) {
		super(nom, force, dexterite, intelligence, concentration, vitalite, experience, type, capacite);
	}

	public Guerrier() {
		super("Maxime", 45, 38, 15, 2, 103, Combattant.MIN_XP, Combattant.GUERRIER, null);
		capaciteDefaut();
	}
	/**
	 * Initialise des capacités par défauts
	 */
	public void capaciteDefaut() {
		this.capacite = new ArrayList<Capacite>();
		this.capacite.add(SauvegardeCapacite.chargerCapacite("Epee/Master Sword", Capacite.EPEE));
		this.capacite.add(SauvegardeCapacite.chargerCapacite("Epee/Black March", Capacite.EPEE));
		this.capacite.add(SauvegardeCapacite.chargerCapacite("Bouclier/Bouclier Hylien", Capacite.BOUCLIER));
		this.capacite.add(SauvegardeCapacite.chargerCapacite("Bouclier/Bouclier du dragon", Capacite.BOUCLIER));
		this.capacite.add(SauvegardeCapacite.chargerCapacite("Remede/Fée", Capacite.REMEDE));
	}

	public void init() {
		do {
			super.init();
		} while (force < dexterite + 10 && dexterite + 10 < intelligence + 10 && intelligence + 10 < concentration);
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
				force++;
				break;
			case DEXTERITE:
				if (force >= dexterite + 11)
					dexterite++;
				else
					nouveauPointImpossible = true;
				break;
			case INTELLIGENCE:
				if (dexterite + 10 >= intelligence + 11)
					intelligence++;
				else
					nouveauPointImpossible = true;
				break;
			case CONCENTRATION:
				if (intelligence + 10 >= concentration + 1)
					concentration++;
				else
					nouveauPointImpossible = true;
		}
		if (nouveauPointImpossible)
			addCaract();
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
				if (force - 1 >= dexterite + 10)
					force--;
				else
					enleverPointImpossible = true;
				break;
			case DEXTERITE:
				if (dexterite + 9 >= intelligence + 10)
					dexterite--;
				else
					enleverPointImpossible = true;
				break;
			case INTELLIGENCE:
				if (intelligence + 10 >= concentration + 1)
					intelligence--;
				else
					enleverPointImpossible = true;
				break;
			case CONCENTRATION:
				if (concentration > 0)
					concentration--;
				else
					enleverPointImpossible = false;
		}
		if (enleverPointImpossible)
			supCaract();
	}
}