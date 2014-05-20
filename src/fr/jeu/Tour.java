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
	
	/**
	 * Gère le tour d'un joueur
	 * @param joueurActuel
	 * Joueur courant qui gère ses actions disponibles
	 * @param cible
	 * joueur cible de l'action du joueur lors d'une attaque
	 */
	public void jouer(int joueurActuel, int cible) {
		Scanner sc = new Scanner(System.in);
		int choix;
		combattant[joueurActuel].setPointAction(2);// On iniatialise le nombre d'action possible à 2
		combattant[joueurActuel].finProtection(); //On remet à 0 les protections du combattant courants à chaque début de tour
		while(combattant[joueurActuel].getPointAction() > 0 && combattant[cible].isEnVie() && !combattant[joueurActuel].isCapitule()){
			do{
				combattant[joueurActuel].capaciteDisponible();
				System.out.print(this.combattant[0].getNombreCapacite()+1+"\tAbandon");
				choix = sc.nextInt();
			}while(choix <= 0 || choix > this.combattant[0].getNombreCapacite() + 1);// +1 Abandon
		action(joueurActuel, cible, choix-1);
		}
	}
	
	/**
	 * 
	 * @param joueurActuel
	 * @param cible
	 * @param choix
	 */
	public void action(int joueurActuel, int cible, int choix){
		combattant[joueurActuel].enlevePointAction();
		if(choix == this.combattant[joueurActuel].getNombreCapacite())
			this.combattant[joueurActuel].setCapitule(true);
		else
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
				if(Epee.choixType())
					this.combattant[joueurActuel].attaque(choix, combattant[cible]);
				else
					this.combattant[joueurActuel].parade(choix);
				break;
			default:
				break;
			}
	}
}

