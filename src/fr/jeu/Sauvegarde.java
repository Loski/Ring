package fr.jeu;

import java.io.*;
import java.util.*;

import fr.capacite.Capacite;
import fr.personnage.*;

public class Sauvegarde {

	public static void sauvegarder(Combattant c) {

		ObjectOutputStream oos;
		try {
			/*
			 * File f = new File("Sauvegardes/"); f.mkdir(); FileWriter fw = new
			 * FileWriter("Sauvegardes/"+c.getNom()+".save",false);
			 * BufferedWriter output = new BufferedWriter(fw);
			 */
			oos = new ObjectOutputStream(new BufferedOutputStream(
					new FileOutputStream(new File("game.txt"))));
			oos.writeObject(c);
			oos.close();

		} catch (IOException ioe) {
			System.out.print("Probleme lors de la Sauvegarde du Combattant "
					+ c.getNom());
		}

	}

	public static Combattant charger(String chemin) {

		// File fichier = new File("Sauvegardes/" + c.getNom() + ".save");
		ObjectInputStream ois;
		Combattant c = null;
		try {
			ois = new ObjectInputStream(new BufferedInputStream(
					new FileInputStream(new File("game.txt"))));
			c = new Combattant((Combattant) ois.readObject());
			ois.close();
		} catch (Exception e) {
			System.out.print("Probleme lors du chargement du Combattant");
		}
		return c;
	}

	public static void main(String[] arf) {
		Combattant c = new Athlete(), a = null;
		c.initCapacite();
		sauvegarder(c);
		a = charger("");
		System.out.println(a);
	}
}