package fr.jeu;
import java.io.*;
import java.util.*;
import fr.personnage.*;

public class Sauvegarde {

	public void sauvegarder(Combattant c){
		
		try
		{
			FileWriter fw = new FileWriter("Sauvegardes/"+c.getNom()+".save",false);
			BufferedWriter output = new BufferedWriter(fw);
			output.write(c.getNom()+"\n");
			output.write(c.getForce()+"\n");
			output.write(c.getDexterite()+"\n");
			output.write(c.getIntelligence()+"\n");
			output.write(c.getConcentration()+"\n");
			output.write(c.getVitalite()+"\n");
			output.write(c.getExperience()+"\n");
			output.write(c.getCapacite().length+"\n");
			for(int i=0; i<c.getCapacite().length;i++)
			{
				output.write(c.getCapacite()[i].getNom()+"\n");
				output.write(c.getCapacite()[i].getCategorie()+"\n");
				output.write(c.getCapacite()[i].getType()+"\n");
				output.write(c.getCapacite()[i].getDescription()+"\n");
			}
			output.flush();
			output.close();
			
		}catch(IOException ioe){};
	}
	
	public void charger(Combattant c){
		
		File fichier = new File("Sauvegardes/"+c.getNom()+".save");
		
			try
			{
				BufferedReader ficTexte = new BufferedReader(new FileReader(fichier));
			
			String ligne;
			//int taille=0; //variable temporaire pour la taille du tableau de capacité
			int i=1;
			do{
				ligne = ficTexte.readLine();
				
				switch(i){
					case 1:c.setNom(ligne);
							break;
					case 2:c.setForce(Integer.parseInt(ligne));
							break;
					case 3:c.setDexterite(Integer.parseInt(ligne));
							break;
					case 4:c.setIntelligence(Integer.parseInt(ligne));
							break;
					case 5:c.setConcentration(Integer.parseInt(ligne));
							break;
					case 6:c.setVitalite(Integer.parseInt(ligne));
							break;
					case 7:c.setExperience(Integer.parseInt(ligne));
							break;
					/*case 8:taille=Integer.parseInt(ligne);
							break;*/
				}
				
				i++;
				
			}while(ligne != null && i!=9);
			
			/*Il manque les capacités !!!
			 * 
			 * 
			*/
				
			
		}catch(IOException ie){};
}
	
}
