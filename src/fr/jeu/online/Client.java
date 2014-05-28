package fr.jeu.online;

import java.net.*;
import java.io.*;

import fr.personnage.Combattant;

public class Client extends Socket {

	// flux
	private ObjectInputStream	in;
	private ObjectOutputStream	out;

	public Client() throws IOException, ClassNotFoundException {
		super("localhost", 2501);
		System.out.println("connexion �tablie");
		this.startClient();
	}

	/* lance le client */
	private void startClient() throws IOException, ClassNotFoundException {
		System.out.println("ouverture des flux en cours");
		this.out = new ObjectOutputStream(super.getOutputStream());
		this.in = new ObjectInputStream(super.getInputStream());
		System.out.println("ouverture des flux effectu�e" + "\n" + "le client est pr�t � recevoir les messages du serveur");
		while (true) {
			System.out.println("serveur dit : " + this.recevoir());
		}
	}

	/* re�oit les donn�es venant du serveur */
	private Combattant recevoir() throws IOException, ClassNotFoundException {
		return (Combattant)this.in.readObject();
	}

	/* lancement de l'application */
	public static void main(String[] args) {
		try {
			new Client();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}
}