package fr.jeu;

import java.util.Scanner;

import fr.personnage.*;

public class Duel {
	private  Combattant[] combattant;
	
	public Duel(){
		this.combattant = new Combattant[2];
	}
	
	public void demarrageDuel(){
		for(int i = 0; i<2;i++){
			this.combattant[i].initVita();
		}
	}
	
	public boolean finCombat(){
		return !this.combattant[0].isCapitule() && !this.combattant[1].isCapitule() && this.combattant[0].isEnVie() &&  this.combattant[1].isEnVie(); 
	}
	
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
	public static void main(String[] args) {
		Duel duel = new Duel();
		duel.combattant[0] = new Athlete();
		duel.combattant[1] = new Athlete();
		duel.combattant[0].initCapacite();		
		duel.combattant[1].initCapacite();
		
		do{ 
			Tour tour =  new Tour(duel.combattant);
			tour.jouer(0, 1);
			System.out.print(duel.combattant[1]);
		}while(duel.finCombat());
	}
	
}
