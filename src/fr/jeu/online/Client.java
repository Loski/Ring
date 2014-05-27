package fr.jeu.online;

import java.io.*;
import java.net.*;
import java.util.Vector;

import fr.jeu.Sauvegarde;
import fr.personnage.*;

//Composante connexe + composante connexe forte
public class Client {

	private static Socket	socket;

	public static void main(String[] args) throws IOException,
			InterruptedException, ClassNotFoundException {
		socket = null;
		ObjectOutputStream out;
		try {
			socket = new Socket(InetAddress.getLocalHost(), 2501);
			//out = new ObjectOutputStream(socket.getOutputStream());
			/*Serveur.envoieCombattant(out);
			new Observation(socket);*/		    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

		    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

		    // Création de l'input stream
		    // Création de l'output stream
		    Vector vecRead = (Vector)ois.readObject();
		    System.out.println(vecRead);
		} catch (UnknownHostException e) {
			System.err.println("Impossible de se connecter à l'adresse " + socket.getInetAddress());
		} catch (IOException e) {
			System.err.println("Aucun serveur à l'écoute du port " + socket.getPort());
		}
		return;
	}
}

class Observation implements Runnable {

	private Socket	socket;

	Observation(Socket s) {
		this.socket = s;
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		ObjectInputStream in;
		System.out.println("aiie");
		try {
			System.out.println("aiie");
			in = new ObjectInputStream(socket.getInputStream());
			Combattant objetRecu;
			System.out.println("aaa");
			while (true) {
				System.out.println("aha hah");
				objetRecu = (Combattant) in.readObject();
				System.out.println(objetRecu);
				System.out.println("ahah");
				System.out.println("ahahah");
				Thread.sleep(2500);
			}
		} catch (IOException e) {
			System.err.println(1);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println(2);
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.err.println(3);
			e.printStackTrace();
		}
	}
}
