package fr.jeu;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import fr.personnage.*;

public class ImageAccueil extends JPanel{

	public ImageAccueil(){	
	}
	
	public void paintComponent(Graphics g){
		//g.drawImage(Toolkit.getDefaultToolkit().getImage("img/Ring.png"),this.getWidth()/2 -360,0,this);
		g.drawImage(Toolkit.getDefaultToolkit().getImage("img/Ring.png"),0,0,this.getWidth(),this.getHeight(),this);
		
	}
}
