package fr.capacite;

import fr.personnage.Combattant;

public abstract class Magie extends Capacite {
	public Magie(){
		this.efficacite = this.facilite = 50;
	}
	public Magie(int efficacite, int facilite){
		if((efficacite + facilite) == 100){
			this.efficacite = efficacite;
			this.facilite = facilite;
		}
	}
	

}
