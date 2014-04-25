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
		this.combattant = combattant;
	}

	public void jouer(int joueurActuel, int cible) {
		Scanner sc = new Scanner(System.in);
		int choix;
		combattant[joueurActuel].setPointAction(2);  // On iniatialise le nombre d'action possible à 2
		while(combattant[joueurActuel].getPointAction() > 0 && combattant[cible].isEnVie()){
		do{
			combattant[joueurActuel].capaciteDisponible();
			System.out.print("Abandon    " + (this.combattant[0].getNombreCapacite()+1));
			choix = sc.nextInt();
		}while(choix <= 0 || choix > this.combattant[0].getNombreCapacite() + 1);
		System.out.print(true);// Abandon => 0
		action(joueurActuel, cible, choix-1);
		}
	}
	
	public void action(int joueurActuel, int cible, int choix){
		combattant[joueurActuel].enlevePointAction();
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
		case Capacite.EPEE:
			this.combattant[joueurActuel].attaque(choix, combattant[cible]);

		/*case ABANDON:
			this.combattant[0].isCapitule(true);
			break;*/
		}
	}
		// Add un cas pour l'épée
}

