package fr.personnage;
import java.util.ArrayList;

import fr.capacite.*;

public class Magicien extends Combattant {

	public void init(){
		do{
			super.init();
		}while(this.intelligence < Math.max(this.force, this.dexterite) + 15 && this.concentration < Math.max(this.force, this.dexterite) + 15);
	} 
	public Magicien(String nom, int force, int dexterite, int intelligence, int concentration, int vitalite, int experience, ArrayList<Capacite> capacite) {
		super(nom, force, dexterite, intelligence, concentration, vitalite, experience, Combattant.MAGE, capacite);
	}
	
	public Magicien(Combattant m) {
		super(m);
	}
	
	public Magicien(){
		super("Magicien_inconnu", 5, 5, 46, 45, 200, 1, Combattant.MAGE,null);
	}
	public void initCapacite(){
		super.initCapacite();
		this.capacite.add(new Sortilege(Capacite.ATTAQUE));
		this.capacite.add(new Sortilege(Capacite.SOIN));	
	}
	
}