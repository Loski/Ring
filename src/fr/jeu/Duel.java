package fr.jeu;

/**
 * Duel est la classe gérant un duel entre un personnage et une intelligence artificielle
 * @author Maxime LAVASTE
 * @author Loïc LAFONTAINE
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Scanner;

import fr.capacite.Capacite;
import fr.capacite.Epee;
import fr.personnage.*;

public class Duel implements Serializable {

	protected Combattant[]	combattant;
	protected int			joueur1, joueur2;

	public Duel() {
		this.combattant = new Combattant[2];
	}

	public Duel(Combattant[] combattant) {
		this.combattant = new Combattant[2];
		this.combattant[0] = combattant[0];
		this.combattant[1] = combattant[1];
	}

	public Duel(Duel d) {
		this.combattant = new Combattant[2];
		this.combattant[0] = new Combattant(d.getCombattant()[0]);
		this.combattant[1] = new Combattant(d.getCombattant()[1]);
		this.joueur1 = d.joueur1;
		this.joueur2 = d.joueur2;
	}

	/**
	 * Gère le démarrage d'un duel en initialisant les caractéristiques des combattants et l'initiative
	 */
	public void demarrageDuel() {
		for (int i = 0; i < 2; i++) {
			this.combattant[i].preparationCombattant();
		}
		this.initiative();
		combattant[joueur1].setJePeuxJouer(true);
	}

	/**
	 * Vérifie si le combat est terminé
	 * 
	 * @return Si le combat est terminé true, sinon else
	 */
	public boolean finCombat() {
		return this.combattant[0].isCapitule() || this.combattant[1].isCapitule() || !this.combattant[0].isEnVie() || !this.combattant[1].isEnVie();
	}

	/**
	 * Gère le tour d'un joueur
	 * 
	 * @param joueurActuel
	 *        Joueur courant qui gère ses actions disponibles
	 * @param cible
	 *        joueur cible de l'action du joueur lors d'une attaque
	 */
	public void jouer(int joueurActuel, int cible) {
		int choix, pointAction = 2;
		combattant[joueurActuel].preparationTour(combattant[cible]); // On remet � 0 les protections du combattant courants � chaque d�but de tour +//
																		// // // dégat
		System.out.println(combattant[cible].uhdCombattant());
		while (pointAction > 0 && !finCombat()) {
			do {
				combattant[joueurActuel].capaciteDisponible();
				System.out.println(this.combattant[joueurActuel].getCapacite().size() + 1 + "\tAbandon");
				choix = Menu.choix();
			} while (choix < 1 || choix > this.combattant[joueurActuel].getCapacite().size() + 1);// +1 Abandon
			action(joueurActuel, cible, choix - 1);
			pointAction--;
		}
	}

	/**
	 * @param joueurActuel
	 *        Combattant courant
	 * @param cible
	 *        Combattant visé par une attaque
	 * @param choix
	 *        choix de la capacités
	 */
	public void action(int joueurActuel, int cible, int choix) {
		if (choix == this.combattant[joueurActuel].getCapacite().size())
			this.combattant[joueurActuel].setCapitule(true);
		else
			switch (this.combattant[joueurActuel].getCapacite().get(choix).getType()) {
				case Capacite.ATTAQUE:
					System.out.println(this.combattant[joueurActuel].attaque(this.combattant[joueurActuel].getCapacite().get(choix), combattant[cible]));
					break;
				case Capacite.PARADE:
					System.out.println(this.combattant[joueurActuel].parade(this.combattant[joueurActuel].getCapacite().get(choix)));
					break;
				case Capacite.SOIN:
					System.out.println(this.combattant[joueurActuel].soin(this.combattant[joueurActuel].getCapacite().get(choix)));
					break;
				case Capacite.EPEE:
					if (Epee.choixType())
						System.out.println(this.combattant[joueurActuel].attaque(this.combattant[joueurActuel].getCapacite().get(choix), combattant[cible]));
					else
						System.out.println(this.combattant[joueurActuel].parade(this.combattant[joueurActuel].getCapacite().get(choix)));
					break;
				default:
					break;
			}
	}

	/**
	 * Gère l'initiative
	 */
	public void initiative() {
		if (combattant[0].getExperience() > combattant[1].getExperience()) {
			joueur1 = 0;
			joueur2 = 1;
		}
		else if (combattant[0].getExperience() < combattant[1].getExperience()) {
			joueur1 = 1;
			joueur2 = 0;
		}
		else {
			if ((Math.random()) > 0.50) {
				joueur1 = 1;
				joueur2 = 0;
			}
			else {
				joueur1 = 0;
				joueur2 = 1;
			}
		}
	}

	/**
	 * Gère le tour d'un joueur
	 */
	public void tour() {
		if (combattant[joueur1].isJePeuxJouer()) {
			System.out.println(this.affJoueur(this.joueur1));
			this.jouer(joueur1, joueur2);
			this.combattant[joueur1].setJePeuxJouer(false);
			this.combattant[joueur2].setJePeuxJouer(true);
		}
		else {
			System.out.println(this.affJoueur(joueur2));
			this.jouer(joueur2, joueur1);
			this.combattant[joueur1].setJePeuxJouer(true);
			this.combattant[joueur2].setJePeuxJouer(false);
		}
	}

	/**
	 * Gestion de tout le combat
	 */
	public void combat() {
		demarrageDuel();
		do {
			new Sauvegarde<Duel>().sauvegarder(this, new File("Sauvegardes/Duel/"), combattant[0].getNom() + "_vs_" + combattant[1].getNom());
			tour();
		} while (!this.finCombat());
		int gagnant = gagnant();
		int perdant = (gagnant == 1) ? 0 : 1;
		System.out.println("Le joueur " + combattant[gagnant].getNom() + " a gagné.");
		this.combattant[gagnant].gagner(combattant[perdant]);
		this.combattant[perdant].perdre();
		System.out.println(combattant[gagnant]);
	}

	/**
	 * @param i
	 *        indice du joueur courant
	 * @return Un message pour indiquer qui doit jouer
	 */
	public String affJoueur(int i) {
		return combattant[i].getNom() + " à toi de jouer !";
	}

	/*
	 * Retourne l'indice du combattant qui a gagné
	 */
	public int gagnant() {
		if (combattant[0].isEnVie() && !combattant[0].isCapitule())
			return 0;
		else
			return 1;
	}

	public Combattant[] getCombattant() {
		return combattant;
	}

	public void setCombattant(Combattant[] combattant) {
		this.combattant = combattant;
	}

	@Override
	public String toString() {
		return "Duel [combattant=" + Arrays.toString(combattant) + ", joueur1=" + joueur1 + ", joueur2=" + joueur2 + "]";
	}
}
