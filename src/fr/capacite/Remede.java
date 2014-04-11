package fr.capacite;

import fr.personnage.Combattant;

public class Remede extends Capacite{

	private int facilite;
	private int efficacite;
	public static final String description = "Heal yourself";
	
	public Remede() {
		this.efficacite = this.facilite = 50;
	}
	public Remede(int efficacite, int facilite){
		this.efficacite = efficacite;
		this.facilite = facilite;
	}

	@Override
	public String toString(){
		return "[Remède : " + "efficacite = " + this.efficacite + " facilite = " + this.facilite+"]";
	}
	public boolean soin(Combattant combattant) {
		this.calculReussite(combattant.getDexterite(), this.facilite);
		this.calculImpact(combattant.getDexterite(), this.efficacite);
		combattant.setPointAction(-1);
		if(true)
			combattant.addVita(this.impact);
		return true;
	}
	

}
