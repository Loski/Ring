package fr.jeu;
import java.util.*;

import fr.personnage.*;

public class Menu {
		
	//Permet de garder la même entête et fin pour les différents menus
	private static String stringMenu(String s){
		return "------------------------------------MENU-------------------------------------\n"+s+"\n-----------------------------------------------------------------------------";
	}
	
	private static int choix(){
		
		int choix =-1;
		Scanner sc = new Scanner(System.in);

			System.out.print(":");
			try{
				choix = sc.nextInt();
			}catch(InputMismatchException e){System.out.println("ERREUR: Veuillez rentrer un entier\n");}

		return choix;
	}
	/**
	 * Affiche le menu principal du jeu Ring
	 */
	public static void afficherMenuPrincipal(){
		//Verif si duel en cours + demande si on veut le continuer
		
		int choix=-1;
		do{
			System.out.println(stringMenu("(1)Jouer contre l'ordinateur\n(2)Jouer contre un autre Joueur"));
			
			choix = choix();
			
		}while(choix<1||choix>2);
		
		if(choix==2)
			afficherMenu2Joueurs();
		
	}

	/**
	 * 
	 * @param i
	 *            Indice du combattant
	 */
	public static Combattant choixClasse() {
		Scanner sc = new Scanner(System.in);
		int choix;
		do {
			choix = sc.nextInt();
		} while (choix <= 0 || choix > 3);
		switch (choix) {
		case 1:
			return new Athlete();
		case 2:
			return new Magicien();
		case 3:
			return new Guerrier();
		default:
			return null;
		}

	}

	
	public static void afficherMenu2Joueurs(){
		int choix=-1;
		do{
		System.out.println(stringMenu("(1)Partie Locale (Les 2 joueurs jouent à tour de rôle sur la meme machine)\n(2)Partie en réseau (Les 2 joueurs jouent chacun sur une machine différente)"));
	
		choix = choix();
		}while(choix<1||choix>2);
	}
	
	public static void afficherMenuPersonnageLocal(){
		
		Combattant a1 = null;
		Combattant a2 = null;
		
		for(int i=1;i<=2;i++){
		
		int choix =-1;
		do{
			System.out.println(stringMenu("(1)Creer un personnage (Joueur "+i+")"+"\n(2)Charger un personnage (Joueur " +i+")"));
			
			choix = choix();
		}while(choix<1||choix>2);
		
		if(choix==1)
		{
			//Choix entre Athlete,magicien...
			do{
				System.out.println(stringMenu("(1)Creer un magicien..."));
				
				choix = choix();
			}while(choix<1||choix>2);
			a1 = new Athlete();

			
			
		}
		
		
		Combattant[] c = new Combattant[2];
		c[0]=a1;
		c[1]=a2;
		Duel d = new Duel(c);
		d.demarrageDuel();
		}
	}
	
	public static void afficherMenuPesonnageReseau(){
		/*Boucle "en attente du joueur 1" pour le joueur 2
		Boucle "en attentre du joueur 2" pour le joueur 1
		=>Fichier texte avec variable Joueur 1 = Pret/Pas Pret (idem pour J2)
		*/
	}
	
	public static void main(String[] args){
		afficherMenuPrincipal();
	return;	
	}
	
}
