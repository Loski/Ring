package fr.personnage;

import java.util.ArrayList;

import fr.capacite.*;

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
		super("Gon", 5, 5, 46, 45, 103, Combattant.MIN_XP, Combattant.MAGE, null);
		capaciteDefaut();
	}

	public void addCaract(int hasard) {
		boolean nouveauPointImpossible = false;
		switch (hasard) {
			case FORCE:
				if (intelligence >= Math.max(force + 1, dexterite) + 15 && concentration >= Math.max(force + 1, dexterite) +15)
					force++;
				else
					nouveauPointImpossible = true;
				break;
			case DEXTERITE:
				if (intelligence >= Math.max(force, dexterite + 1) && concentration >= Math.max(force, dexterite + 1) +15)
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
			if (Math.random() > 0.50)
				concentration++;
			else
				intelligence++;
		}
	}
}