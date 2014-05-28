package fr.jeu.online;

import java.net.Socket;
import java.io.*;

public class Flux {

	private Socket				client;
	private ObjectOutputStream	out;
	private ObjectInputStream	in;

	public Flux() {
	}

	public Flux(Socket client) throws IOException {
		this.client = client;
		this.out = new ObjectOutputStream(this.client.getOutputStream());
		this.in = new ObjectInputStream(this.client.getInputStream());
	}

	/* getters */
	public ObjectInputStream getInputStream() {
		return this.in;
	}

	public ObjectOutputStream getOutputStream() {
		return this.out;
	}
}