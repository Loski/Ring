package fr.capacite;

import fr.personnage.Combattant;

public class Remede extends Capacite {

	private int facilite;
	private int efficacite;

	public Remede() {
		this.nom = new String("de génie");
		this.efficacite = this.facilite = 50;
		this.type = Capacite.SOIN;
		this.dommage = Capacite.MAGIQUE;
		this.description = "Heal yourself";
	}

	public Remede(String nom, String description, int efficacite, int facilite) {
		super(nom, Capacite.SOIN, Capacite.MAGIQUE, description);
		if ((efficacite + facilite) == 100 && efficacite >= 20 && facilite >= 20) {
			this.efficacite = efficacite;
			this.facilite = facilite;
		}
		this.description = "Heal yourself";
	}
	
	public Remede(Remede r){
		super(r.nom, r.type, r.dommage, r.description);
		this.facilite = r.facilite;
		this.efficacite =  r.efficacite;
	}
	public Remede(Capacite c){
		super(c);
		Remede r = (Remede) c;
		this.facilite = r.facilite;
		this.efficacite =  r.efficacite;
	}



	public boolean calculReussite(Combattant combattant) {
		return actionReussie(calculReussite(combattant.getDexterite(), this.facilite));
	}
	
	public int calculImpact(Combattant combattant, int type) {
		return calculImpact(combattant.getDexterite(), this.efficacite);
	}

	@Override
	public String toString() {
		return "Remede " +nom+"[facilite=" + facilite + ", efficacite=" + efficacite
				+ "]";
	}
}