package fr.jeu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;

import fr.jeu.multiLocal.Serveur;
import fr.personnage.Combattant;

public class DuelLocal extends Duel {

	private File	dossier;
	private String	nomSauvegarde;
	private int		instance;

	public DuelLocal(File dossier, Combattant combattant, String nomSauvegarde) {
		this.dossier = dossier;
		this.combattant = new Combattant[2];
		this.nomSauvegarde = nomSauvegarde;
		PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:*.comb");
		try {
			// if (matcher.matches(dossier.toPath())) {
			if (!Serveur.dossierVide(dossier)) {
				this.rejoindreDuel(dossier, combattant);
				this.combattant[1] = combattant;
				instance = 1;
			}
			else {
				instance = 0;
				System.out.println("En attente d'un joueur....");
				new Sauvegarde<Combattant>().sauvegarder(combattant, dossier, combattant.getNom() + ".comb");
				this.combattant[0] = combattant;
				String saveOther = Serveur.nouveauFichier(dossier.toPath()); // On attends la création d'un combattant dans le dossier
				Thread.sleep(1000);
				this.combattant[1] = Sauvegarde.chargerCombattant(dossier, saveOther);
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public DuelLocal(DuelLocal d) {
		super(d);
		this.dossier = new File(d.dossier.getAbsolutePath());
		this.nomSauvegarde = new String(d.nomSauvegarde);
	}

	@Override
	public void demarrageDuel() {
		if (instance == 0)
			try {
				super.demarrageDuel();
				System.out.println("Je suis " + instance);
				this.combattant[joueur1].setJePeuxJouer(true);;
				this.combattant[joueur2].setJePeuxJouer(false);
				System.out.println("j1 if" + joueur1);
				new Sauvegarde<DuelLocal>().sauvegarder(this, dossier, nomSauvegarde);
			} catch (IOException e) {
				e.printStackTrace();
			}
		else {
			try {
				Serveur.surveillerDuel(dossier.toPath());
				/*
				 * Thread.sleep(2500); System.out.println("Je suis " + instance); System.out.println("j1 else "+joueur1);
				 */
				DuelLocal tmp = Sauvegarde.chargerDuelLocal(dossier, nomSauvegarde);
				this.combattant = tmp.getCombattant();
				this.joueur1 = tmp.joueur1;
				this.joueur2 = tmp.joueur2;
				
				 System.out.println("j12 else" + joueur1);
				 
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.combattant[joueur2].setJePeuxJouer(false);
		}
		System.out.println(this.combattant[0]);
		System.out.println(this.combattant[1]);
		System.out.println("j2" + joueur2 + "j1 : " + joueur1);
	}

	public void combat() {
		demarrageDuel();
		do {
			tourJInitiative();
		} while (!this.finCombat());
		int gagnant = gagnant();
		System.out.println("Le joueur " + combattant[gagnant].getNom() + " a gagné.");
		gestionFinCombat(gagnant);
	}

	/**
	 * @param joueur
	 *        true joueur qui à l'initiative false l'autre joueur
	 */
	public void tourJInitiative() {
		int otherJoueur = (instance == 1) ? 0 : 1;
		System.out.print(otherJoueur + "   " + instance);
		try {
			System.out.print(Sauvegarde.chargerDuelLocal(dossier, nomSauvegarde).getCombattant()[0].isJePeuxJouer());
			System.out.print(Sauvegarde.chargerDuelLocal(dossier, nomSauvegarde).getCombattant()[1].isJePeuxJouer());
			System.out.println(this.getCombattant()[0].isJePeuxJouer());
			System.out.println(this.getCombattant()[1].isJePeuxJouer() + "instance " + instance);
			if ((combattant[0].isJePeuxJouer() && instance == 0) || (combattant[1].isJePeuxJouer() && instance == 1)) {
				refreshCombattant(otherJoueur);
				tour(dossier, nomSauvegarde);
				combattant[otherJoueur].setJePeuxJouer(true);
				combattant[instance].setJePeuxJouer(false);
				new Sauvegarde<DuelLocal>().sauvegarder(this, dossier, nomSauvegarde);
			}
			else {
				System.out.println("En attente de la fin du tour de l'adversaire...");
				Serveur.surveillerDuel(dossier.toPath());
				refreshCombattant(instance);
				System.out.println(this.combattant[instance].isJePeuxJouer());
				tourJInitiative();
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void refreshCombattant(int i) {
		try {
			this.combattant[i] = Sauvegarde.chargerDuelLocal(dossier, nomSauvegarde).getCombattant()[i];
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Permet de rejoindre un duel en copiant le combattant dans le dossier passé en paramètre
	 * 
	 * @param dossier
	 * @param combattant
	 */
	public void rejoindreDuel(File dossier, Combattant combattant) {
		try {
			String[] s = dossier.list();
			for (int i = 0; i < s.length; i++) {
				if (s[i].endsWith(".comb")) {
					this.combattant[0] = Sauvegarde.chargerCombattant(dossier, s[i]);
					break;
				}
			}
			new Sauvegarde<>().sauvegarder(combattant, dossier, combattant.getNom() + ".comb");
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] a) {
		try {
			System.out.println(Sauvegarde.chargerDuelLocal(new File("C:/Users/Maxime/Downloads"), "duelTest"));
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
