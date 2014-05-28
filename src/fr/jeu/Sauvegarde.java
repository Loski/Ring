package fr.jeu;

import java.io.*;

import fr.personnage.*;

public class Sauvegarde {

	/**
	 * Sauvegarde une instance de Combattant dans le dossier Sauvegardes/Combattant/nomDuCombattant Remplace le fichier, si un fichier ayant le même
	 * nom existe
	 * 
	 * @param c
	 *        Le combattant à sauvegarder
	 */
	public static void sauvegarderCombattant(Combattant c) {
		ObjectOutputStream oos;
		try {
			File fichier = new File("Sauvegardes/Duel");
			if (!fichier.exists())
				fichier.mkdir();
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("Sauvegardes/Combattant/" + c.getNom()))));
			oos.writeObject(c);
			oos.close();
		} catch (IOException ioe) {
			System.err.println("Probleme lors de la Sauvegarde du Combattant " + c.getNom() + ioe.getMessage());
		}
	}

	/**
	 * Sauvegarde une instance d'un duel dans le dossier Sauvegardes/Duel/nomduCombattant1_vs_nomduCombattant2. Remplace le fichier, si un fichier
	 * ayant le même nom existe.
	 * 
	 * @param d
	 *        Le duel à sauvegarder
	 */
	public static void sauvegarderDuel(Duel d) {
		ObjectOutputStream oos;

		try {
			File fichier = new File("Sauvegardes/Duel");
			if (!fichier.exists())
				fichier.mkdir();
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("Sauvegardes/Duel/" + d.getCombattant()[0].getNom() + "_vs_" + d.getCombattant()[1].getNom()))));
			oos.writeObject(d);
			oos.close();
		} catch (IOException ioe) {
			System.err.println("Probleme lors de la Sauvegarde du Duel" + ioe.toString());
		}
	}

	/**
	 * Affiche tous les fichiers contenant une sauvegarde puis demande au joueur de selectionner une sauvegarde particulière
	 * 
	 * @param chemin
	 *        Chemin du répertoire où sont stockés les sauvegardes
	 * @return Le chemin de la sauvegarde selectionnée
	 */
	public static String selectSauvegarde(String chemin) {
		File fichier = new File(chemin);
		String[] s = fichier.list();
		int choix;
		for (int i = 0; i < s.length; i++)
			System.out.println((i + 1) + "\t" + s[i]);
		do {
			choix = Menu.choix() - 1;
		} while (choix < 0 || choix > s.length);
		return s[choix];
	}

	/**
	 * Demande au joueur quelle sauvegarde il doit charger, puis récupère les informations dans un fichier texte de ce combattant
	 * 
	 * @return Une instance d'un combattant chargé à partir d'un fichier texte.
	 */
	public static Combattant chargerCombattant() {
		String nomSauvegarde = selectSauvegarde("Sauvegardes/Combattant/");
		ObjectInputStream ois;
		Combattant c = null;
		try {
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("Sauvegardes/Combattant/" + nomSauvegarde))));
			c = new Combattant((Combattant) ois.readObject());
			ois.close();
		} catch (Exception e) {
			System.err.print("Probleme lors du chargement du Combattant" + nomSauvegarde);
		}
		return c;
	}
	
	public static Combattant chargerCombattant(String chemin) {
		ObjectInputStream ois;
		Combattant c = null;
		try {
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("Sauvegardes/Combattant/"+chemin))));
			c = new Combattant((Combattant) ois.readObject());
			ois.close();
		} catch (Exception e) {
			System.err.print("Probleme lors du chargement du Combattant" + chemin);
		}
		return c;
	}

	/**
	 * Demande à un joueur quelle sauvegarde il doit charger, puis récupère les informations dans un fichier texte
	 * 
	 * @return  Une instance d'un duel chargé à partir d'un fichier texte.
	 */
	public static Duel chargerDuel() {
		String nomSauvegarde = selectSauvegarde("Sauvegardes/Duel/");
		ObjectInputStream ois;
		Duel d = null;
		try {
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("Sauvegardes/Duel/" + nomSauvegarde))));
			d = new Duel((Duel) ois.readObject());
			ois.close();
		} catch (Exception e) {
			System.out.print("Probleme lors du chargement du Duel");
		}
		return d;
	}

	public static void main(String[] arf) {
		Combattant c = new Athlete(), a = null;
		c.initCapacite();
		sauvegarderCombattant(c);
		a = chargerCombattant("Athlete_inconnu");
		System.out.println(a);
		return;
	}
}