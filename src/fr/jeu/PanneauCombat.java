package fr.jeu;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import fr.personnage.*;

public class PanneauCombat extends JPanel{

	public static final int MAGE=0, GUERRIER=1, ATHLETE=2;
	
	private Image imgJ1;
	private Image imgJ2;

	
	public PanneauCombat(){
		this.setBackground(Color.white);
		this.setLayout(new GridLayout(1,2));
		
		//imgJ1 = Toolkit.getDefaultToolkit().getImage("img/athlete.png");
		
	}
	
	public void setImageJ1(int i){
		if(i==MAGE)
			imgJ1 = Toolkit.getDefaultToolkit().getImage("img/mage.png");
		else if(i==GUERRIER)
			imgJ1 = Toolkit.getDefaultToolkit().getImage("img/guerrier.png");
		else
			imgJ1 = Toolkit.getDefaultToolkit().getImage("img/athlete.png");
	}
	
	public void setImageJ2(int i){
		if(i==MAGE)
			imgJ2 = Toolkit.getDefaultToolkit().getImage("img/mage.png");
	}
	

	public void paintComponent(Graphics g){
		super.paintComponent(g);

		
		g.drawImage(imgJ1,0,0,this);
	}
}
