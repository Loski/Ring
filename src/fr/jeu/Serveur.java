package fr.jeu;

import java.io.File;
import java.io.IOException;
import java.util.EventListener;
import java.util.Scanner;
import java.nio.file.*;

import sun.nio.cs.ext.MacArabic;
import fr.jeu.*;
import fr.personnage.*;

public class Serveur {

	private File		fichier;
	private String		nomSauvegarde;
	DuelLocal			duel;

	public Serveur(String chemin, String nomSauvegarde, Combattant combattant) {
		this.fichier = new File(chemin);
		this.nomSauvegarde = nomSauvegarde;
		Sauvegarde.creerDossier(fichier);
		duel = new DuelLocal(this.fichier, combattant, nomSauvegarde);
	}

	public void init() {
		Scanner sc = new Scanner(System.in);
		System.out.println("File");
		this.fichier = new File(sc.next());
		System.out.println("nomSave");
		System.out.println("nomSave");
	}

	public static void main(String[] main) {
		//Serveur serv = new Serveur("C:/Users/Maxime/Downloads", "duelTest", new Guerrier());
		Serveur serv = new Serveur("C:/Users/Maxime/Downloads", "duelTest", new Athlete());
		serv.duel.combat();
		return;
	}

	public static boolean dossierVide(File dossier) {
		if (dossier.list().length == 0)
			return true; // -> créé un duel
		return false;// -> rejoins un duel
	}

	public static String nouveauFichier(Path path) {
		WatchKey key = null;
		WatchService watcher = null;
		try {
			watcher = FileSystems.getDefault().newWatchService();
			key = path.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);
		} catch (IOException x) {
			System.err.println(x);
		}
		while (true) {
			try {
				key = watcher.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (WatchEvent<?> event : key.pollEvents()) {
				WatchEvent.Kind<?> kind = event.kind();
				String fileName = event.context().toString();
				if (kind == StandardWatchEventKinds.OVERFLOW) {
					continue;
				}
				else if (StandardWatchEventKinds.ENTRY_CREATE.equals(kind))
					return fileName;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static boolean surveillerDuel(Path path) {
		WatchKey key = null;
		WatchService watcher = null;
		try {
			watcher = FileSystems.getDefault().newWatchService();
			key = path.register(watcher, StandardWatchEventKinds.ENTRY_MODIFY);
		} catch (IOException x) {
			System.err.println(x);
		}
		while (true) {
			try {
				key = watcher.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (WatchEvent<?> event : key.pollEvents()) {
				WatchEvent.Kind<?> kind = event.kind();
				if (kind == StandardWatchEventKinds.OVERFLOW) {
					continue;
				}
				else if (StandardWatchEventKinds.ENTRY_MODIFY.equals(kind))
					return true;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
}
