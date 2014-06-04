package fr.personnage;
import java.util.ArrayList;

import fr.capacite.*;

public class Guerrier extends Combattant {
	
	public Guerrier(Combattant guerrier) {
		super(guerrier);
	}

	public Guerrier(String nom, int force, int dexterite, int intelligence, int concentration, int vitalite, int experience,int type, ArrayList<Capacite> capacite) {
		super(nom, force, dexterite, intelligence, concentration, vitalite, experience, type, capacite);
	}

	public Guerrier(){
		super("Magicien_inconnu", 5, 5, 46, 45, 103, Combattant.MIN_XP, Combattant.GUERRIER,null);
		capaciteDefaut();
	}
	
	public void init(){
		do{
			super.init();
		}while(force < dexterite+10 && dexterite+10 < intelligence +10 && intelligence +10 < concentration);
	}
}