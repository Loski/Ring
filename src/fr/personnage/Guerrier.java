package fr.personnage;
import fr.capacite.*;

public class Guerrier extends Combattant {
	public Guerrier(Guerrier guerrier) {
		super(guerrier);
		// TODO Auto-generated constructor stub
	}

	public Guerrier(String nom, int force, int dexterite, int intelligence, int concentration, int vitalite, int experience, Capacite[] capacite) {
		super(nom, force, dexterite, intelligence, concentration, vitalite, experience, capacite);
	}

	public Guerrier(){
		super();
	}
	
	public void init(){
		do{
			super.init();
		}while(force >= dexterite+10 && dexterite+10 >= intelligence +10 && intelligence +10 >= concentration);
	}
	public void initCapacite(){
		super.initCapacite();
		capacite[0] = new Epee();
		capacite[1] = new Bouclier();
	}
}