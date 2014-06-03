package fr.jeu;

import java.io.*;

import fr.capacite.Bouclier;
import fr.capacite.Capacite;
import fr.capacite.Epee;
import fr.capacite.Remede;
import fr.capacite.Sortilege;
import fr.personnage.*;

public class Sauvegarde <T extends Capacite>{

	/**
	 * Sauvegarde une instance de Combattant dans le dossier Sauvegardes/Combattant/nomDuCombattant Remplace le fichier, si un fichier ayant le m�me
	 * nom existe
	 * 
	 * @param c
	 *        Le combattant � sauvegarder
	 */
	public static void sauvegarderCombattant(Combattant c) {
		ObjectOutputStream oos;
		try {
			File fichier = new File("Sauvegardes/Combattant/");
			if (!fichier.exists())
				fichier.mkdirs();
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("Sauvegardes/Combattant/" + c.getNom()))));
			oos.writeObject(c);
			oos.close();
		} catch (IOException ioe) {
			System.err.println("Probleme lors de la Sauvegarde du Combattant " + c.getNom() + ioe.getMessage());
		}
	}

	/**
	 * Sauvegarde une instance d'un duel dans le dossier Sauvegardes/Duel/nomduCombattant1_vs_nomduCombattant2. Remplace le fichier, si un fichier
	 * ayant le m�me nom existe.
	 * 
	 * @param d
	 *        Le duel � sauvegarder
	 */
	public static void sauvegarderDuel(Duel d) {
		ObjectOutputStream oos;
		try {
			File fichier = new File("Sauvegardes/Duel");
			if (!fichier.exists())
				fichier.mkdirs();
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("Sauvegardes/Duel/" + d.getCombattant()[0].getNom() + "_vs_" + d.getCombattant()[1].getNom()))));
			oos.writeObject(d);
			oos.close();
		} catch (IOException ioe) {
			System.err.println("Probleme lors de la Sauvegarde du Duel" + ioe.toString());
		}
	}



	/**
	 * Affiche tous les fichiers contenant une sauvegarde puis demande au joueur de selectionner une sauvegarde particuli�re
	 * 
	 * @param chemin
	 *        Chemin du r�pertoire o� sont stock�s les sauvegardes
	 * @return Le chemin de la sauvegarde selectionn�e
	 */
	public static String selectSauvegarde(String chemin) {
		File fichier = new File(chemin);
		String[] s = fichier.list();
		if(s.length==0){
			System.out.println("Aucune sauvegarde dans le dossier " + chemin);
			return null;
		}
		int choix;
		for (int i = 0; i < s.length; i++)
			System.out.println((i + 1) + "\t" + s[i]);
		do {
			choix = Menu.choix() - 1;
		} while (choix < 0 || choix > s.length);
		System.out.println(s[choix]);
		return s[choix];
	}

	/**
	 * Demande au joueur quelle sauvegarde il doit charger, puis r�cup�re les informations dans un fichier texte de ce combattant
	 * 
	 * @return Une instance d'un combattant charg� � partir d'un fichier texte.
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
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("Sauvegardes/Combattant/" + chemin))));
			c = new Combattant((Combattant) ois.readObject());
			ois.close();
		} catch (Exception e) {
			System.err.print("Probleme lors du chargement du Combattant" + chemin);
		}
		return c;
	}

	/**
	 * Demande � un joueur quelle sauvegarde il doit charger, puis r�cup�re les informations dans un fichier texte
	 * 
	 * @return Une instance d'un duel charg� � partir d'un fichier texte.
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
	/**
	 * Sauvegarde une capacit� dans le dossier Sauvegardes/Capacite/nomDeLaClasse/nomDeLaCapacit�
	 * @param capacite
	 * Instance d'une classe h�rit� de capacit�, qui sera sauvegard�e
	 */
	public void sauvegarderCapacite(T capacite) {
		ObjectOutputStream oos;
		try {
			File fichier = new File("Sauvegardes/Capacite/" + capacite.getClass().getSimpleName());
			if (!fichier.exists())
				fichier.mkdirs(); 
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("Sauvegardes/Capacite/" + capacite.getClass().getSimpleName() +"/"+ capacite.getNom()))));
			oos.writeObject(capacite);
			oos.close();
		} catch (IOException ioe) {
			System.err.println("Probleme lors de la sauvegarde d'une capacit�" + capacite.getNom() + ioe.getMessage());
		}
	}
	

	public static Capacite chargerCapacite(String chemin, int capacite) {
		ObjectInputStream ois;
		String nomCapacite = Sauvegarde.selectSauvegarde("Sauvegardes/Capacite/"+chemin);
		try {
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("Sauvegardes/Capacite/"+chemin+"/"+nomCapacite))));

			switch (capacite) {
				case Capacite.EPEE:
					return new Epee((Epee) ois.readObject());
				case Capacite.REMEDE:
					return new Remede((Remede) ois.readObject());
				case Capacite.BOUCLIER:
					return new Bouclier((Bouclier) ois.readObject());
				case Capacite.SORTILEGE:
					return new Sortilege((Sortilege) ois.readObject());
			}
		} catch (IOException e) {
			System.err.print("Probleme lors du chargement de la capacite " + e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] arf) {
		/*Capacite a = new Sortilege();
		a.init();
		new Sauvegarde<Sortilege>().sauvegarderCapacite((Sortilege) a);
		Capacite b = Sauvegarde.chargerCapacite("Sortilege",Capacite.SORTILEGE);*/
		/*Bouclier a = new Bouclier();
		Capacite b = new Bouclier(a);
		System.out.println(b);*/
		return;
	}
}