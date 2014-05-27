package fr.personnage;
import java.util.ArrayList;

import fr.capacite.*;

public class Magicien extends Combattant {

	public void init(){
		int max;
		do{
			super.init();
			if(this.force>this.dexterite)
				max = force;
			else
				max = dexterite;
		}while(intelligence < max && concentration < max);
	}
	public Magicien(String nom, int force, int dexterite, int intelligence, int concentration, int vitalite, int experience, ArrayList<Capacite> capacite) {
		super(nom, force, dexterite, intelligence, concentration, vitalite, experience, capacite);
	}
	public Magicien(){
		super("Magicien_inconnu", 5, 5, 46, 45, 200, 1, null);
	}
	public void initCapacite(){
		super.initCapacite();
		this.capacite.add(new Sortilege(Capacite.ATTAQUE));
		this.capacite.add(new Sortilege(Capacite.SOIN));
		
	}
	
}