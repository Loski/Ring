package fr.jeu.online;

import java.io.*;
import java.net.*;
import java.util.Vector;

import fr.personnage.Athlete;
import fr.personnage.Combattant;

public class Serveur {

	private boolean	enVie	= true;

	public static void main(String[] args) throws IOException,
			InterruptedException {
		ServerSocket socketServeur = new ServerSocket(2501);
		Socket socket = new Socket(InetAddress.getLocalHost(), 2501);
		System.out.println("Le serveur est à l'écoute du port " + socketServeur.getLocalPort());
		/*ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		envoieCombattant(oos);
		ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));*/
		Thread t = new Thread(new AccepterJoueurs(socketServeur));
		t.start();
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		// Création de l'input stream
		oos.writeObject(new Vector<Object>(12));

		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
	}

	synchronized static void envoieCombattant(ObjectOutputStream out) {
		try {
			Combattant combat = new Athlete();
			out.writeObject(combat);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isEnVie() {
		return enVie;
	}

	public void setEnVie(boolean enVie) {
		this.enVie = enVie;
	}
}

class AccepterJoueurs implements Runnable {

	private ServerSocket	socketserver;
	private Socket			socket;

	public AccepterJoueurs(ServerSocket s) {
		this.socketserver = s;
	}

	public void run() {
		try {
			while (true) {
				System.out.println("En attente de joueur...");
				socket = socketserver.accept();
				System.out.println("Serveur a accepte connexion: " + socket);// Un client se connecte on l'accepte
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
