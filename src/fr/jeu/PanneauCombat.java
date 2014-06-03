package fr.jeu;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import fr.personnage.*;

public class PanneauCombat extends JPanel{

	//public static final int MAGE=0, GUERRIER=1, ATHLETE=2;
	
	private Image [] img;
	private Combattant[] combattant;

	
	public PanneauCombat(Combattant [] c){
		this.setBackground(Color.white);
		this.setLayout(new GridLayout(1,2));
		combattant = c;
		img = new Image[2];
		this.changeImage(c[0],0);
		this.changeImage(c[1],1);
		
		
		//imgJ1 = Toolkit.getDefaultToolkit().getImage("img/athlete.png");
		
	}
	
	public void changeImage(Combattant c, int i){
		if(c.getType()==Combattant.MAGE)
			img[i] = Toolkit.getDefaultToolkit().getImage("img/mage.png");
		else if(c.getType()==Combattant.GUERRIER)
			img[i] = Toolkit.getDefaultToolkit().getImage("img/guerrier.png");
		else
			img[i] = Toolkit.getDefaultToolkit().getImage("img/athlete.png");
		
	}
	
	/*public void setImageJ1(int i){
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
	}*/
	

	public void paintComponent(Graphics g){
		super.paintComponent(g);

		g.drawImage(Toolkit.getDefaultToolkit().getImage("img/battle.jpg"),0,0,this);
		g.drawImage(img[0],0,40,this);
		//g.drawImage(img[1],300,40,this);
	}
}
