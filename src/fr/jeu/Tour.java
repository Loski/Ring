package fr.jeu;

import java.util.Scanner;

import fr.capacite.*;
import fr.personnage.*;

public class Tour {

	private Combattant[] combattant;

	public Tour() {
		combattant = new Combattant[2];
		for (int i = 0; i < 2; i++)
			combattant[i] = new Combattant();
	}

	public Tour(Combattant[] combattant){
		this.combattant[0] = combattant[0];
		this.combattant[1] = combattant[1];
	}

	public void jouer(int joueurActuel, int cible) {
		Scanner sc = new Scanner("System.in");
		int choix;
		do{
			choix = sc.nextInt();
		}while(choix > 0 && choix < this.combattant[0].getNombreCapacite() +1);
		action(joueurActuel, cible, choix);// /choix de la capa
	}
	
	public void action(int joueurActuel, int cible, int choix){
		switch (this.combattant[joueurActuel].getCapacite()[choix].getType()) {
		case Capacite.ATTAQUE:
			this.combattant[joueurActuel].attaque(choix, combattant[cible]);
			break;
		case Capacite.PARADE:
			this.combattant[joueurActuel].parade(choix);
			break;
		case Capacite.SOIN:
			this.combattant[joueurActuel].soin(choix);
			break;
		/*case ABANDON:
			this.combattant[0].isCapitule(true);
			break;*/
		}
	}
		// Add un cas pour l'épée
}

