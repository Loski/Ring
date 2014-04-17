package fr.jeu;

import java.util.Scanner;

import fr.capacite.Action;
import fr.personnage.Combattant;

public class tour {

	private Combattant[] combattant;

	public tour() {
		combattant = new Combattant[2];
		for (int i = 0; i < 2; i++)
			combattant[i] = new Combattant();
	}

	public boolean debut() {
		if (combattant[0].isInitiative()) {
			this.jouer(0,1);
			this.jouer(1,0);

		} else {
			this.jouer(1,0);
			this.jouer(0,1);
		}
	}

	public void jouer(int joueurActuel, int cible) {
		Scanner sc = new Scanner("System.in");
		int choix = sc.nextInt(); // /choix de la capa
		switch (this.combattant[joueurActuel].capacite[choix].getType()) {
		case Action.ATTAQUE:
			this.combattant[joueurActuel].attaque(choix, combattant[cible]);
			break;
		case Action.PARADE:
			this.combattant[joueurActuel].parade(choix);
			break;
		case Action.SOIN:
			this.combattant[joueurActuel].soin(choix);
		}
		//Add un cas pour l'épée
	}

}
