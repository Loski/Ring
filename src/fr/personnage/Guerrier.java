package fr.personnage;

import java.util.ArrayList;

import fr.capacite.*;

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
		super("Link", 45 , 38, 15, 2, 103, Combattant.MIN_XP, Combattant.GUERRIER, null);
		capaciteDefaut();
	}

	public void init() {
		do {
			super.init();
		} while (force < dexterite + 10 && dexterite + 10 < intelligence + 10 && intelligence + 10 < concentration);
	}

	public void addCaract(int hasard) {
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
			force++;
	}
}