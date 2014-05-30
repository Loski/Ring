package fr.jeu;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.io.File;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import fr.personnage.*;

public class FenRing extends JFrame {

	Container		contentpane;
	Combattant		com;
	JPanel			nord;
	JPanel			ouest;
	PanneauCombat	centre;
	ImageAccueil	ecranAccueil;
	JTextField		nom;
	JTextField		force;
	JTextField		dex;
	JTextField		intel;
	JTextField		concentration;
	JTextArea		infos;

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
	}

	public void initialise() {
		contentpane = this.getContentPane();
		// c.add(new JButton("Choisir un personnage"),BorderLayout.NORTH);
		contentpane.add(creerPanelNord(), BorderLayout.NORTH);
		ecranAccueil = new ImageAccueil();
		contentpane.add(ecranAccueil, BorderLayout.CENTER);
		com = new Athlete();
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

	public JPanel creerPanelJ1(Combattant c) {
		ouest = new JPanel();
		ouest.setLayout(new GridLayout(3, 2));
		ouest.add(new JLabel("Nom"));
		ouest.add(new JLabel(c.getNom()));
		ouest.add(new JLabel("Points de vie / Points de vie totale (Donc faut créer un attribut)"));
		ouest.add(new JLabel("" + c.getVitalite()));
		ouest.add(new JLabel("EXP"));
		ouest.add(new JLabel("" + c.getExperience()));
		return ouest;
	}

	public JPanel creerPerso() {
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
		ouest.add(nom);
		ouest.add(new JLabel("Force :"));
		force = new JTextField("" + com.getForce());
		ouest.add(force);
		ouest.add(new JLabel("Dexterite :"));
		ouest.add(new JTextField("" + com.getDexterite()));
		ouest.add(new JLabel("Intelligence :"));
		ouest.add(new JTextField("" + com.getIntelligence()));
		ouest.add(new JLabel("Concentration :"));
		ouest.add(new JTextField("" + com.getConcentration()));
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
		String[] invalide = { "/", "!", ":", "*", "?", "<", ">", "\"", "\\", "|" };
		String caractere;
		for (String i : invalide)
			if (c.getNom().contains(i)) {
				caractere = i;
				infos.setForeground(Color.red);
				infos.setText("Le nom du personnage comporte des caractères interdits : " + caractere);
				return false;
			}
		if (c instanceof Athlete) {
			if (c.getForce() < 20 || c.getDexterite() < 20 || c.getIntelligence() < 20 || c.getConcentration() < 20)
				return false;
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
		File fichier = new File("Sauvegardes/Combattant/" + c.getNom());
		if (fichier.exists()) {
			infos.setForeground(Color.red);
			infos.setText("Un personnage du même nom existe déjà");
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
				contentpane.add(FenRing.this.creerPerso(), BorderLayout.WEST);
				contentpane.remove(nord);
				centre = new PanneauCombat();
				contentpane.remove(ecranAccueil);
				contentpane.add(centre, BorderLayout.CENTER);
				FenRing.this.validate();
				centre.setImageJ1(PanneauCombat.ATHLETE);
			}
			else if (s.equals("Creer le personnage")) {
				boolean valide = true;
				int rep = JOptionPane.showConfirmDialog(null, "Êtes-vous sur de vouloir créer ce personnage ?", "Creation de personnage", JOptionPane.YES_NO_OPTION);
				com.setNom(nom.getText());
				try {
					com.setForce(Integer.parseInt(force.getText()));
					com.setDexterite(Integer.parseInt(dex.getText()));
					com.setIntelligence(Integer.parseInt(intel.getText()));
					com.setConcentration(Integer.parseInt(concentration.getText()));
				} catch (Exception ex) {
					valide = false;
				}
				if (!testPerso(com) && !valide)
					JOptionPane.showMessageDialog(null, "Impossible de créer le personnage, vérifier l'affichage pour connaitre le problème", "Erreur !!!", JOptionPane.ERROR_MESSAGE);
				else if (rep == JOptionPane.YES_OPTION) {
					contentpane.remove(ouest);
					// contentpane.add(FenRing.this.creerPanelJ1(com),BorderLayout.WEST);
					contentpane.add(creerPanelDuel(), BorderLayout.NORTH);
					Sauvegarde.sauvegarderCombattant(com);
					FenRing.this.repaint();
					FenRing.this.validate();
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
					centre = new PanneauCombat();
					contentpane.remove(ecranAccueil);
					contentpane.add(centre, BorderLayout.CENTER);
					FenRing.this.validate();
				}
			}
		}
	}

	class ChoixClasseListener implements ItemListener {

		public void itemStateChanged(ItemEvent i) {
			if (i.getItem().equals("Magicien")) {
				com = new Magicien();
				centre.setImageJ1(PanneauCombat.MAGE);
				centre.repaint();
			}
			else if (i.getItem().equals("Guerrier")) {
				com = new Guerrier();
				centre.setImageJ1(PanneauCombat.GUERRIER);
				centre.repaint();
			}
			else if (i.getItem().equals("Athlete")) {
				com = new Athlete();
				centre.setImageJ1(PanneauCombat.ATHLETE);
				centre.repaint();
			}
			else {
				if (i.getStateChange() == ItemEvent.SELECTED) {
					com = Sauvegarde.chargerCombattant((String) i.getItem());
					if (com.getType() == Combattant.ATHLETE)
						centre.setImageJ1(PanneauCombat.ATHLETE);
					else if (com.getType() == Combattant.MAGE)
						centre.setImageJ1(PanneauCombat.MAGE);
					else
						centre.setImageJ1(PanneauCombat.GUERRIER);
					centre.repaint();
				}
			}
		}
	}
}
