package fr.personnage;
import fr.capacite.*;;

public class Magicien extends Combattant {

	public void init(){
		do{
			super.init();
		}while(force >= dexterite+10 && dexterite+10 >= intelligence +10 && intelligence +10 >= concentration);
	}
	public Magicien(String nom, int force, int dexterite, int intelligence, int concentration, int vitalite, int experience, Capacite[] capacite) {
		super(nom, force, dexterite, intelligence, concentration, vitalite, experience, capacite);
	}

	public Magicien(){
		super();
	}
}