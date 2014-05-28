package fr.jeu.online;

import java.io.*;
import java.net.*;
import java.util.*;

import fr.personnage.Athlete;

public class Serveur extends ServerSocket {
 
	// liste des clients
	private HashMap<Socket, Flux> clients ;
 
	public Serveur() throws IOException {
 
		super(2501) ;
 		System.out.println("serveur démarré") ;
		this.startServeur() ;
	}
 
	private void startServeur() throws IOException {
 
		// instance de la des clients
		this.clients=new HashMap<Socket, Flux>() ;
 
		System.out.println("liste des clients créée" +"\n" +"attente de connexion d'un client") ;
 		Socket dernierClient=this.accepterConnexion() ;
		System.out.println("client connecté" +"\n" +"le serveur est prêt à envoyer des messages au client") ;
 
		// debug - envoi constant de messages au client
		Scanner sc=new Scanner(System.in) ;
		while (true) {
			
 			this.envoyer(dernierClient, new Athlete()) ;
		}
	}
 
	/* écoute des connexions entrantes */
	private Socket accepterConnexion() throws IOException {
 
		Socket client=super.accept() ;
 		Flux f=new Flux(client) ;
 		this.clients.put(client, f) ;
		return client ;
	}
 
	private void envoyer(Socket client, Object o) throws IOException {
		ObjectOutputStream out=this.clients.get(client).getOutputStream() ;
		out.writeObject(o) ;
		out.flush() ;
		out=null ;
	}
 
	/* lancement de l'application */
	public static void main(String[] args) {
 
		try {
 
			new Serveur() ;
 
		} catch (IOException ioe) {
			ioe.printStackTrace() ;
		}
	}
}