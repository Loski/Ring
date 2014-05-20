package fr.jeu;

import java.util.Scanner;

import fr.capacite.Capacite;
import fr.capacite.Epee;
import fr.personnage.*;

public class Duel {
	private  Combattant[] combattant;
	private int joueur1 = 0, joueur2 = 1;

	
	public Duel(){
		this.combattant = new Combattant[2];
	}
	
	public Duel(Combattant[] combattant){
		this.combattant = new Combattant[2];
		this.combattant[0] = combattant[0];
		this.combattant[1] = combattant[1];
	}
	
	public void demarrageDuel(){
		for(int i = 0; i<2;i++){
			this.combattant[i].preparationCombattant();
		}
		this.initiative();
	}
	
	public boolean finCombat(){
		return this.combattant[0].isCapitule() || this.combattant[1].isCapitule() || !this.combattant[0].isEnVie() ||  !this.combattant[1].isEnVie(); 
	}
	
	/**
	 * 
	 * @param i
	 * Indice du combattant
	 */
	public void choixClasse(int i){
		Scanner sc = new Scanner(System.in);
		int choix;
		do{
			choix= sc.nextInt();
		}while(choix <= 0 || choix > 3);
		switch(choix){
		case 1:
			this.combattant[i] = new Athlete();
			break;
		case 2:
			this.combattant[i] = new Magicien();
			break;
		case 3:
			this.combattant[i] = new Guerrier();
			break;
		}
		this.combattant[i].init();
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
			switch (this.combattant[joueurActuel].getCapacite().get(choix).getType()) {
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
	
	public void initiative(){
		if(combattant[0].getExperience() > combattant[1].getExperience()){
			joueur1 = 0;
			joueur2 = 1;
		}
		else if(combattant[0].getExperience() < combattant[1].getExperience())
		{	
			joueur1 = 1;
			joueur2 = 0;
		}
		else{ 
			if((Math.random() * 100) > 50){
				joueur1 = 1;
				joueur2 =0;
			}
			else{
				joueur1 = 0;
				joueur2 =1;
			}	
		}
	}
		public void tour(){
			do{ 
				System.out.println(this.affJoueur(this.joueur1));
				this.jouer(joueur1, joueur2);
				System.out.println(this.affJoueur(joueur2));
				this.jouer(joueur2, joueur1 );
			}while(this.finCombat());
		}
	public static void main(String[] args) {
		Duel duel = new Duel();
		duel.combattant[0] = new Athlete();
		duel.combattant[1] = new Magicien();
		duel.combattant[0].initCapacite();		
		duel.combattant[1].initCapacite();
		Sauvegarde.sauvegarder(duel.combattant[0]);
		
		duel.demarrageDuel();
		
		duel.tour();
		System.out.println("Le joueur " + duel.combattant[duel.gagnant()].getNom()+ " a gagné.");
	}
	public void affichageJouer(){
		for(int i =0; i <2; i++){
			System.out.println(combattant[i]);
		}
	}
	public String affJoueur(int i){
		return combattant[i].getNom() + " à toi de joueur !";
	}
	public int gagnant(){
		if(combattant[0].isEnVie() && !combattant[0].isCapitule())
			return 0;
		else return 1;
	}
}
