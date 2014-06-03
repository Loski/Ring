package fr.jeu;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.io.File;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import fr.personnage.*;

public class PanneauCaract extends JPanel{

	String nom;
	JLabel force;
	JLabel dex;
	JLabel intell;
	JLabel concentration;
	JLabel exp;
	JLabel vitalite;
	JPanel pan;
	
	public PanneauCaract(Combattant c){
		this.setBackground(Color.white);
		this.setLayout(new BorderLayout());
		pan = new JPanel();
		pan.setLayout(new GridLayout(15,0));
		nom = c.getNom();
		pan.setBorder(BorderFactory.createTitledBorder("Combattant"));
		vitalite = new JLabel("Vitalite :"+c.getVitalite() +" PV");
		pan.add(vitalite);
		force = new JLabel("Force : "+c.getForce());
		pan.add(force);
		dex = new JLabel("Dexterite : "+c.getDexterite());
		pan.add(dex);
		intell = new JLabel("Intelligence : "+c.getIntelligence());
		pan.add(intell);
		concentration = new JLabel("Concentration : "+c.getConcentration());
		pan.add(concentration);
		exp = new JLabel("EXP : "+c.getExperience());
		pan.add(exp);
		this.add(pan,BorderLayout.CENTER);

	}
	
	public void setNom(String s){
		nom=s;
		pan.setBorder(BorderFactory.createTitledBorder(nom));
	}
	
	public void setForce(String s){
		force.setText(s);
	}
	
	public void setIntelligence(String s){
		intell.setText(s);
	}
	
	public void setDexterite(String s){
		dex.setText(s);
	}
	
	public void setConcentration(String s){
		concentration.setText(s);
	}

	public void setCombattant(Combattant c){
		setForce("Force : "+c.getForce());
		setNom(c.getNom());
	}
}
