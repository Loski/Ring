package fr.jeu;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import sun.audio.*;
import fr.personnage.*;

public class FenRing extends JFrame {

	Container		contentpane;
	Combattant[]	combattant;
	JPanel			nord;
	JPanel			ouest; 
	PanneauCombat	centre;
	PanneauCaract	caracteristique;
	ImageAccueil	ecranAccueil;
	JTextField		nom;
	JTextField		force;
	JTextField		dex;
	JTextField		intel;
	JTextField		concentration;
	JTextArea		infos;
	AudioStream		son;

	public FenRing() {
		super("Ring");
		this.initialise();
		Toolkit t1 = Toolkit.getDefaultToolkit();
		Dimension d = t1.getScreenSize();
		// this.setBounds(0,0,d.width,d.height);
		this.setBounds(0, 0, 800, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(null); // Centre la fenetre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		try {
			son = new AudioStream(new FileInputStream("sound/Ring.mid"));
			AudioPlayer.player.start(son);
		} catch (Exception e) {
		}
	}

	public void initialise() {
		combattant = new Combattant[2];
		combattant[0] = new Athlete();
		combattant[1] = new Athlete();
		contentpane = this.getContentPane();
		// c.add(new JButton("Choisir un personnage"),BorderLayout.NORTH);
		contentpane.add(creerPanelNord(), BorderLayout.NORTH);
		ecranAccueil = new ImageAccueil();
		contentpane.add(ecranAccueil, BorderLayout.CENTER);
	}

	public JPanel creerPanelNord() {
		nord = new JPanel();
		nord.setLayout(new FlowLayout());
		JButton ia = new JButton("Joueur vs IA");
		ia.addActionListener(new BoutonListener());
		nord.add(ia);
		nord.add(new JButton("Joueur vs Joueur"));
		return nord;
	}

	public JPanel creerChoixPerso() {
		nord = new JPanel();
		nord.setLayout(new FlowLayout());
		JButton creer = new JButton("Creer un Personnage");
		JButton charger = new JButton("Charger un Personnage");
		creer.addActionListener(new BoutonListener());
		charger.addActionListener(new BoutonListener());
		nord.add(creer);
		nord.add(charger);
		return nord;
	}

	/*
	 * public JPanel creerPanelJ1(Combattant c){ ouest = new JPanel(); ouest.setLayout(new GridLayout(3,2)); ouest.add(new JLabel("Nom"));
	 * ouest.add(new JLabel(c.getNom())); ouest.add(new JLabel("Points de vie / Points de vie totale (Donc faut créer un attribut)")); ouest.add(new
	 * JLabel(""+c.getVitalite())); ouest.add(new JLabel("EXP")); ouest.add(new JLabel(""+c.getExperience())); return ouest; }
	 */
	public JPanel creerPerso(Combattant c) {
		infos = new JTextArea("");
		infos.setEditable(false);
		JScrollPane jsp = new JScrollPane();
		jsp.add(infos);
		contentpane.add(infos, BorderLayout.SOUTH);
		ouest = new JPanel();
		ouest.setLayout(new GridLayout(17, 0));
		ouest.add(new JLabel("Classe :"));
		String[] classe = { "Athlete", "Guerrier", "Magicien" };
		JComboBox combo = new JComboBox(classe);
		combo.addItemListener(new ChoixClasseListener());
		ouest.add(combo);
		ouest.add(new JLabel("Nom :"));
		nom = new JTextField("Combattant");
		nom.addMouseListener(new VerifPersoListener());
		ouest.add(nom);
		ouest.add(new JLabel("Force :"));
		force = new JTextField("" + c.getForce());
		force.addMouseListener(new VerifPersoListener());
		ouest.add(force);
		ouest.add(new JLabel("Dexterite :"));
		dex = new JTextField("" + c.getDexterite());
		dex.addMouseListener(new VerifPersoListener());
		ouest.add(dex);
		ouest.add(new JLabel("Intelligence :"));
		intel = new JTextField("" + c.getIntelligence());
		intel.addMouseListener(new VerifPersoListener());
		ouest.add(intel);
		ouest.add(new JLabel("Concentration :"));
		concentration = new JTextField("" + c.getConcentration());
		concentration.addMouseListener(new VerifPersoListener());
		ouest.add(concentration);
		ouest.add(new JLabel("Capacite n°1 :"));
		String[] capacite = { "Capacite ???" };
		JComboBox capa1 = new JComboBox(capacite);
		ouest.add(capa1);
		ouest.add(new JLabel("Capacite n°2 :"));
		JComboBox capa2 = new JComboBox(capacite);
		ouest.add(capa2);
		/*
		 * ouest.add(new JLabel("Vitalite :")); ouest.add(new JSlider());
		 */
		JButton perso = new JButton("Creer le personnage");
		perso.addActionListener(new BoutonListener());
		ouest.add(perso);
		return ouest;
	}

	public JPanel creerPanelDuel() {
		JPanel duel = new JPanel();
		duel.setLayout(new GridLayout(0, 1));
		String[] epee = { "1", "2", "3", "Lister toutes les épées !!!" };
		String[] sortOff = { "Lister tous les sorts !!!" };
		String[] sortDef = { "Lister tous les sorts !!!" };
		String[] sortSoin = { "Lister tous les soins !!!" };
		String[] remede = { "Lister tous les remedes !!!" };
		String[] bouclier = { "Lister tous les boucliers !!!" };
		JMenuBar barre = new JMenuBar();
		JMenu attaquer = new JMenu("Attaquer");
		JMenu parer = new JMenu("Parer");
		JMenu soin = new JMenu("Se Soigner");
		JMenu capituler = new JMenu("Capituler");
		attaquer.add(new JComboBox(epee));
		parer.add(new JComboBox(bouclier));
		attaquer.add(new JComboBox(sortOff));
		parer.add(new JComboBox(sortDef));
		soin.add(new JComboBox(sortSoin));
		soin.add(new JComboBox(remede));
		barre.add(attaquer);
		barre.add(parer);
		barre.add(soin);
		barre.add(capituler);
		duel.add(barre);
		return duel;
	}

	public boolean testPerso(Combattant c) {
		try {
			combattant[0].setForce(Integer.parseInt(force.getText()));
			combattant[0].setDexterite(Integer.parseInt(dex.getText()));
			combattant[0].setIntelligence(Integer.parseInt(intel.getText()));
			combattant[0].setConcentration(Integer.parseInt(concentration.getText()));
		} catch (Exception ex) {
		}
		combattant[0].setNom(nom.getText());
		caracteristique.setNom(c.getNom());
		caracteristique.setForce("Force : " + c.getForce());
		caracteristique.setDexterite("Dexterite : " + c.getDexterite());
		caracteristique.setIntelligence("Intelligence : " + c.getIntelligence());
		caracteristique.setConcentration("Concentration : " + c.getConcentration());
		File fichier = new File("Sauvegardes/Combattant/" + c.getNom());
		if (fichier.exists()) {
			infos.setForeground(Color.red);
			infos.setText("Un personnage du même nom existe déjà");
			nom.setBackground(Color.red);
			return false;
		}
		String[] invalide = { "/", "!", ":", "*", "?", "<", ">", "\"", "\\", "|" };
		String caractere;
		for (String i : invalide)
			if (c.getNom().contains(i)) {
				caractere = i;
				infos.setForeground(Color.red);
				infos.setText("Le nom du personnage comporte des caractères interdits : " + caractere);
				nom.setBackground(Color.red);
				return false;
			}
		if (c instanceof Athlete) {
			if (c.getForce() < 20) {
				infos.setForeground(Color.red);
				infos.setText("La Force d'un Athlete doit être supérieur à 20 : " + c.getForce() + " < 20");
				force.setBackground(Color.red);
				return false;
			}
			if (c.getDexterite() < 20) {
				infos.setForeground(Color.red);
				infos.setText("La Dextérité d'un Athlete doit être supérieur à 20 : " + c.getDexterite() + " < 20");
				dex.setBackground(Color.red);
				return false;
			}
			if (c.getIntelligence() < 20) {
				infos.setForeground(Color.red);
				infos.setText("L'Intelligence d'un Athlete doit être supérieur à 20 : " + c.getIntelligence() + " < 20");
				intel.setBackground(Color.red);
				return false;
			}
			if (c.getConcentration() < 20) {
				infos.setForeground(Color.red);
				infos.setText("La Concentration d'un Athlete doit être supérieur à 20 : " + c.getConcentration() + " < 20");
				concentration.setBackground(Color.red);
				return false;
			}
		}
		if (c instanceof Guerrier) {
			if (c.getForce() < c.getDexterite() + 10)
				return false;
			if (c.getDexterite() < c.getIntelligence() + 10)
				return false;
			if (c.getIntelligence() < c.getConcentration())
				return false;
		}
		if (c instanceof Magicien) {
			if (c.getIntelligence() < Math.max(c.getForce(), c.getDexterite()) + 15)
				return false;
			if (c.getConcentration() < Math.max(c.getForce(), c.getDexterite()) + 15)
				return false;
		}
		int total = c.getDexterite() + c.getForce() + c.getConcentration() + c.getIntelligence();
		// 101 = 100 + experience => creation de perso donc exp =1 dans tous les cas
		if (total > 101) {
			infos.setForeground(Color.red);
			infos.setText("Force\tDexterite\tIntelligence\tConcentration\n" + c.getForce() + "\t" + c.getDexterite() + "\t" + c.getIntelligence() + "\t" + c.getConcentration() + "\t= " + total + " > 101");
			concentration.setBackground(Color.red);
			force.setBackground(Color.red);
			intel.setBackground(Color.red);
			dex.setBackground(Color.red);
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		new FenRing();
		return;
	}

	class BoutonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String s = e.getActionCommand();
			if (s.equals("Joueur vs IA")) {
				contentpane.remove(nord);
				contentpane.add(FenRing.this.creerChoixPerso(), BorderLayout.NORTH);
				FenRing.this.validate();
			}
			else if (s.equals("Creer un Personnage")) {
				contentpane.add(FenRing.this.creerPerso(combattant[0]), BorderLayout.WEST);
				contentpane.remove(nord);
				centre = new PanneauCombat(combattant);
				caracteristique = new PanneauCaract(combattant[0]);
				contentpane.remove(ecranAccueil);
				JPanel panelPerso = new JPanel();
				panelPerso.setLayout(new GridLayout(2, 0));
				panelPerso.add(centre);
				panelPerso.add(caracteristique);
				contentpane.add(panelPerso, BorderLayout.CENTER);
				FenRing.this.validate();
			}
			else if (s.equals("Creer le personnage")) {
				boolean valide = true;
				int rep = JOptionPane.showConfirmDialog(null, "Êtes-vous sur de vouloir créer ce personnage ?", "Creation de personnage", JOptionPane.YES_NO_OPTION);
				combattant[0].setNom(nom.getText());
				try {
					combattant[0].setForce(Integer.parseInt(force.getText()));
					combattant[0].setDexterite(Integer.parseInt(dex.getText()));
					combattant[0].setIntelligence(Integer.parseInt(intel.getText()));
					combattant[0].setConcentration(Integer.parseInt(concentration.getText()));
				} catch (Exception ex) {
					valide = false;
				}
				if (!testPerso(combattant[0]) || !valide)
					JOptionPane.showMessageDialog(null, "Impossible de créer le personnage, vérifier l'affichage pour connaitre le problème", "Erreur !!!", JOptionPane.ERROR_MESSAGE);
				else if (rep == JOptionPane.YES_OPTION) {
					contentpane.remove(ouest);
					// contentpane.add(FenRing.this.creerPanelJ1(com),BorderLayout.WEST);
					contentpane.add(creerPanelDuel(), BorderLayout.NORTH);
					Sauvegarde.sauvegarderCombattant(combattant[0]);
					FenRing.this.repaint();
					FenRing.this.validate();
					try {
						AudioPlayer.player.stop(son);
						AudioPlayer.player.start(new FileInputStream("sound/Battle.mid"));
					} catch (Exception ex) {
					}
				}
			}
			else if (s.equals("Charger un Personnage")) {
				File f = new File("Sauvegardes/Combattant/");
				String[] liste = f.list();
				if (liste.length == 0)
					JOptionPane.showMessageDialog(null, "Il n'y pas de sauvegardes de Combattant", "Erreur !!!", JOptionPane.ERROR_MESSAGE);
				else {
					contentpane.remove(nord);
					JComboBox combo = new JComboBox(liste);
					combo.setSelectedIndex(-1);
					combo.addItemListener(new ChoixClasseListener());
					contentpane.add(combo, BorderLayout.NORTH);
					FenRing.this.validate();
					centre = new PanneauCombat(combattant);
					contentpane.remove(ecranAccueil);
					contentpane.add(centre, BorderLayout.CENTER);
					contentpane.add(new JButton("Charger"), BorderLayout.SOUTH);
					caracteristique = new PanneauCaract(combattant[0]);
					contentpane.add(caracteristique, BorderLayout.AFTER_LINE_ENDS);
					FenRing.this.validate();
				}
			}
		}
	}

	class ChoixClasseListener implements ItemListener {

		public void itemStateChanged(ItemEvent i) {
			if (i.getItem().equals("Magicien")) {
				combattant[0] = new Magicien();
				centre.changeImage(combattant[0], 0);
				centre.repaint();
			}
			else if (i.getItem().equals("Guerrier")) {
				combattant[0] = new Guerrier();
				centre.changeImage(combattant[0], 0);
				centre.repaint();
			}
			else if (i.getItem().equals("Athlete")) {
				combattant[0] = new Athlete();
				centre.changeImage(combattant[0], 0);
				centre.repaint();
			}
			else {
				if (i.getStateChange() == ItemEvent.SELECTED) {
					combattant[0] = Sauvegarde.chargerCombattant((String) i.getItem());
					if (combattant[0].getType() == Combattant.ATHLETE)
						centre.changeImage(combattant[0], 0);
					else if (combattant[0].getType() == Combattant.MAGE)
						centre.changeImage(combattant[0], 0);
					else
						centre.changeImage(combattant[0], 0);
					centre.repaint();
					caracteristique.setCombattant(combattant[0]);
				}
			}
		}
	}

	class VerifPersoListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			infos.setText("");
			nom.setBackground(Color.white);
			force.setBackground(Color.white);
			dex.setBackground(Color.white);
			intel.setBackground(Color.white);
			concentration.setBackground(Color.white);
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			testPerso(combattant[0]);
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
		}
	}
}
