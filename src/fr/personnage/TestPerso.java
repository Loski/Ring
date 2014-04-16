package fr.personnage;

public class TestPerso {

	public static void main(String[] args) {
		Combattant mario = new Athlete();
		Combattant ichigo = new Athlete();
	//	mario.init();
		mario.initCapacite();
		//System.out.println(mario);
		mario.setVitalite(-150);
		//System.out.println(mario);
		mario.soin(0);
		System.out.println(mario);
		mario.capacite[1].attaque(mario, ichigo);
		//System.out.println(ichigo);
	}

}
