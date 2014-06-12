package fr.jeu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;

import fr.personnage.Combattant;

public class DuelLocal extends Duel {

	private File	dossier;
	private String	nomSauvegarde;
	private int		instance;

	public DuelLocal(File dossier, Combattant combattant, String nomSauvegarde) {
		this.dossier = dossier;
		this.combattant = new Combattant[2];
		this.nomSauvegarde = nomSauvegarde;
		Sauvegarde.creerDossier(dossier);
		PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:*.comb");
		// if (matcher.matches(dossier.toPath())) {
		if (!Serveur.dossierVide(dossier)) { // Personne qui rejoinds le duel
			this.rejoindreDuel(dossier, combattant);
			this.combattant[1] = combattant;
			instance = 1;
		}
		else { // personne qui créé le duel
			instance = 0;
			System.out.println("En attente d'un joueur....");
			new Sauvegarde<Combattant>().sauvegarder(combattant, dossier, combattant.getNom() + ".comb");
			this.combattant[0] = combattant;
			String saveOther = Serveur.nouveauFichier(dossier.toPath()); // On attends la création d'un combattant dans le dossier
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.combattant[1] = Sauvegarde.chargerCombattant(dossier, saveOther);
		}
	}

	public DuelLocal(DuelLocal d) {
		super(d);
		this.dossier = new File(d.dossier.getAbsolutePath());
		this.nomSauvegarde = new String(d.nomSauvegarde);
	}

	@Override
	/**
	 * La première instance du duel est chargé de gérer le démarrage du duel, puis le sauvegarde
	 * La seconde attends que le duel soit sauvegardé, puis charge les combattants, et les variables gérant l'initiative.
	 */
	public void demarrageDuel() {
		if (instance == 0) {
			super.demarrageDuel();
			new Sauvegarde<Duel>().sauvegarder(this, dossier, nomSauvegarde);
		}
		else {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Duel tmp = Sauvegarde.chargerDuel(dossier, nomSauvegarde);
			this.combattant = tmp.getCombattant();
			this.joueur1 = tmp.joueur1;
			this.joueur2 = tmp.joueur2;
		}
		System.out.println(this.combattant[0]);
		System.out.println(this.combattant[1]);
	}

	public void tourOnline(int instance, int other) {
		if (combattant[joueur1].isJePeuxJouer()) {
			System.out.println(this.affJoueur(this.joueur1));
			this.jouer(joueur1, joueur2);
		}
		else {
			System.out.println(this.affJoueur(joueur2));
			this.jouer(joueur2, joueur1);
		}
		this.combattant[other].setJePeuxJouer(true);
		this.combattant[instance].setJePeuxJouer(false);
	}

	public void combat() {
		demarrageDuel();
		do {
			tour();
		} while (!this.finCombat());
		System.out.println("fin");
		int gagnant = gagnant();
		int perdant = (gagnant == 1) ? 0 : 1;
		System.out.println("Le joueur " + combattant[gagnant].getNom() + " a gagné.");
		if (gagnant == instance)
			gestionFinCombatGagnant(gagnant, perdant);
		else if (perdant == instance)
			gestionFinCombatPerdant(perdant, gagnant);
		try {
			Sauvegarde.supprimerDuel(new File(dossier.toPath() + "/" + nomSauvegarde));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param joueur
	 *        true joueur qui à l'initiative false l'autre joueur
	 */
	public void tour() {
		int otherJoueur = (instance == 1) ? 0 : 1;
		if ((combattant[0].isJePeuxJouer() && instance == 0) || (combattant[1].isJePeuxJouer() && instance == 1)) {
			refreshCombattant(otherJoueur);
			tourOnline(instance, otherJoueur);
			new Sauvegarde<Duel>().sauvegarder(this, dossier, nomSauvegarde);
		}
		else {
			System.out.println("En attente de la fin du tour de l'adversaire...");
			try {
				Thread.sleep(2500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			refreshCombattant(); //on rafréchit tous pour voir si l'autre personnage n'a pas abandonner
			if (!finCombat())
				tour();
		}
	}

	public void refreshCombattant(int i) {
		this.combattant[i] = Sauvegarde.chargerDuel(dossier, nomSauvegarde).getCombattant()[i];
	}

	public void refreshCombattant() {
		this.combattant = Sauvegarde.chargerDuel(dossier, nomSauvegarde).getCombattant();
	}

	/**
	 * Permet de rejoindre un duel en copiant le combattant dans le dossier passé en paramètre
	 * 
	 * @param dossier
	 * @param combattant
	 */
	public void rejoindreDuel(File dossier, Combattant combattant) {
		String[] s = dossier.list();
		for (int i = 0; i < s.length; i++) {
			if (s[i].endsWith(".comb")) {
				this.combattant[0] = Sauvegarde.chargerCombattant(dossier, s[i]);
				break;
			}
		}
		new Sauvegarde<>().sauvegarder(combattant, dossier, combattant.getNom() + ".comb");
	}

	public static void main(String[] a) {
		System.out.println(Sauvegarde.chargerDuel(new File("C:/Users/Maxime/Downloads"), "duelTest"));
	}
}
