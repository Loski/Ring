package fr.jeu;
/**
 * SauvegardeCapacite sert à pouvoir sauvegarder l'état d'une capacité, puis de pouvoir la charger
 * @author Maxime LAVASTE
 * @author Loïc LAFONTAINE
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import fr.capacite.*;

public class SauvegardeCapacite<T extends Capacite> extends Sauvegarde {

	public SauvegardeCapacite() {
	}

	public static Capacite chargerCapacite(String chemin, int capacite) {
		ObjectInputStream ois;
		File fichier = new File("Sauvegardes/Capacite/" + chemin);
		try { 
			/*String nomCapacite = Sauvegarde.selectSauvegarde(fichier);
			if (nomCapacite == null)
				return null;*/
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fichier.getCanonicalPath())));
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
			System.err.print("Probleme lors du chargement de la capacit� " + e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void sauvegarderCapacite(T capacite) {
		ObjectOutputStream oos;
		File fichier = new File("Sauvegardes/Capacite/" + capacite.getClass().getSimpleName());
		creerDossier(fichier);
			try {
				oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fichier.getCanonicalPath() + "/" + capacite.getNom())));
				oos.writeObject(capacite);
				oos.close();
			} catch (IOException ioe) {
				System.err.println("Probleme lors de la sauvegarde d'une capacit�" + capacite.getNom() + ioe.getMessage());
			}
	}
}
