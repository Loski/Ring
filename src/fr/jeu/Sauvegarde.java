package fr.jeu;

import java.io.*;

import sun.org.mozilla.javascript.internal.ast.ThrowStatement;
import fr.capacite.*;
import fr.personnage.*;

public class Sauvegarde<T> {

	/**
	 * * Sauvegarde une instance du type générique dans le File passé en paramètre.
	 * 
	 * @param d
	 *        Instance à sauvegarder
	 * @param dossier
	 *        Dossier où l'instance sera sauvegardé
	 * @param nom
	 *        Nomde la sauvegarde
	 */
	public void sauvegarder(T d, File dossier, String nom) {
		ObjectOutputStream oos = null;
		creerDossier(dossier);
		try {
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(dossier.getCanonicalPath() + "/" + nom)));
			oos.writeObject(d);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// oos.close();
	}

	/**
	 * Affiche tous les fichiers contenant une sauvegarde puis demande au joueur de selectionner une sauvegarde particuli�re
	 * 
	 * @param fichier
	 *        Chemin du répertoire où sont stockés les sauvegardes
	 * @return Le chemin de la sauvegarde selectionn�e
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static String selectSauvegarde(File fichier)
			throws ClassNotFoundException, IOException {
		String[] s = fichier.list();
		if (s.length == 0) {
			throw new IOException("Aucune sauvegarde dans le dossier " + fichier);
		}
		int choix;
		for (int i = 0; i < s.length; i++)
			System.out.println((i + 1) + "\t" + s[i]);
		do {
			choix = Menu.choix() - 1;
		} while (choix < 0 || choix > s.length);
		return s[choix];
	}

	/**
	 * Demande au joueur quelle sauvegarde il doit charger, puis r�cup�re les informations dans un fichier texte de ce combattant
	 * 
	 * @return Une instance d'un combattant charg� � partir d'un fichier texte.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Combattant chargerCombattant() throws IOException,
			ClassNotFoundException {
		File fichier = new File("Sauvegardes/Combattant/");
		String nomSauvegarde = selectSauvegarde(fichier);
		return chargerCombattant(new File(fichier.getCanonicalPath() + "/" + nomSauvegarde + ".comb"));
	}

	public static Combattant chargerCombattant(File fichier) {
		Combattant c = null;
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fichier)));
			c = new Combattant((Combattant) ois.readObject());
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return c;
	}

	public static Combattant chargerCombattant(File fichier, String nom) {
		return chargerCombattant(new File(fichier.toPath() + "/" + nom));
	}

	/**
	 * @param combattant
	 * @return
	 */
	public static Combattant typeCombattant(Combattant combattant) { // a appelé pour cq combattant à la fin du combatpour la gestion de perte de
																		// carac
		switch (combattant.getType()) { // a voir si un boolean dans la classe combattant pour voir s'il a déjà été sauver pour ne pas faire d'op
										// inutile
			case Combattant.ATHLETE:
				return new Athlete(combattant);
			case Combattant.GUERRIER:
				return new Guerrier(combattant);
			case Combattant.MAGE:
				return new Magicien(combattant);
			default:
				return combattant;
		}
	}

	/**
	 * Charge une instance d'un duel à partir d'un fichier
	 * 
	 * @param fichier
	 *        Chemin complet de la sauvegarde
	 * @return Une instance d'un duel
	 */
	public static Duel chargerDuel(File fichier) {
		ObjectInputStream ois = null;
		Duel d = null;
		try {
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fichier)));
			d = new Duel((Duel) ois.readObject());
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return d;
	}

	/**
	 * Demande � un joueur quelle sauvegarde il doit charger dans le dossier de sauvegarde courant de Ring, puis r�cup�re les informations dans un
	 * fichier texte
	 * 
	 * @return Une instance d'un duel charg� � partir d'un fichier texte. null si un problème a eu lieu
	 * @throws IOException
	 */
	public static Duel chargerDuel() throws ClassNotFoundException, IOException {
		File fichier = new File("Sauvegardes/Duel/");
		String nomSauvegarde = selectSauvegarde(fichier);
		if (nomSauvegarde == null)
			throw new IOException("Aucune sauvegarde présente dans le dossier " + fichier);
		else
			return chargerDuel(new File(fichier.getAbsolutePath() + "/" + nomSauvegarde));
	}

	/**
	 * Charge une instance d'un duel contenue dans un fichier
	 * 
	 * @param chemin
	 *        chemin du fichier
	 * @param nomSauvegarde
	 *        nom du fichier
	 * @return Une instance de la sauvegarde du duel
	 */
	public static Duel chargerDuel(String chemin, String nomSauvegarde) {
		return chargerDuel(new File(chemin + "/" + nomSauvegarde));
	}

	/**
	 * Charge une instance d'un duel contenue dans un fichier
	 * 
	 * @param dossier
	 *        chemin du fichier
	 * @param nomSauvegarde
	 *        nom du fichier
	 * @return Une instance de la sauvegarde du duel
	 */
	public static Duel chargerDuel(File dossier, String nomSauvegarde) {
		return chargerDuel(new File(dossier.toPath() + "/" + nomSauvegarde));
	}

	/*public static DuelLocal chargerDuelLocal(File fichier) {
		ObjectInputStream ois = null;
		DuelLocal d = null;
		try {
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fichier)));
			d = new DuelLocal((DuelLocal) ois.readObject());
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return d;
	}

	public static
			DuelLocal chargerDuelLocal(File fichier, String nomSauvegarde) {
		return chargerDuelLocal(new File(fichier.toPath() + "/" + nomSauvegarde));
	}
*/
	/**
	 * Supprime un duel
	 * 
	 * @param fichier
	 *        Chemin du duel
	 * @throws IOException
	 */
	public static void supprimerDuel(File fichier) throws IOException {
		if (fichier.exists() && fichier.canWrite())
			fichier.delete();
		else
			throw new IOException("Impossible de supprimer le dossier " + fichier);
	}

	/**
	 * Créer un dossier et ses parents si et seulement si le dossier n'éxiste pas, à partir du chemin du File passé en paramètre,
	 * 
	 * @param dossier
	 *        File contenant le chemin du dossier à créer
	 */
	public static void creerDossier(File dossier) {
		if (!dossier.exists())
			dossier.mkdirs();
	}

	public static void main(String[] arf) {
		Combattant a = new Athlete(), b;
		new Sauvegarde<Combattant>().sauvegarder(a, new File("Sauvegardes/Combattant/"), a.getNom());
		b = chargerCombattant(new File("Sauvegardes/Combattant/"), a.getNom());
		b = typeCombattant(b);
		System.out.println(b.getClass());
		return;
	}
}