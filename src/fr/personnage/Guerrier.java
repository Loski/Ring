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
		super();
		this.type = Combattant.GUERRIER;
	}
	
	public void init(){
		do{
			super.init();
		}while(force < dexterite+10 && dexterite+10 < intelligence +10 && intelligence +10 < concentration);
	}
	public void initCapacite(){
		super.initCapacite();
		this.capacite.add(new Epee());
		this.capacite.add(new Bouclier());
	}
}