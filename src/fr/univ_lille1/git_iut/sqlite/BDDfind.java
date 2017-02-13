package fr.univ_lille1.git_iut.sqlite;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class BDDfind implements ListSelectionListener{
	
	@SuppressWarnings("rawtypes")
	private JList jliste = new JList();
	private JPanel etiquette = new JPanel();
	private JFrame fenetre = new JFrame("Fenetre de recherche");
	private JLabel name,path,fecha,desc;
	@SuppressWarnings("unused")
	private BDDname bdDname;
	private BDD bdd = new BDD();

	/**
	 * Creer une fenetre de resultat de la recherche sur la description
	 * @param description a chercher
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BDDfind(String[] description) {
		
		List<FichierPly> liste = bdd.selection_tout_table("demo.sqlite");
		String listeNomString [] = new String[liste.size()];
		int cpt = 0;
		for(FichierPly ply : liste){
			String [] strings = ply.getDescription().split(",");
			for(int i = 0; i < description.length; i++) {
				for(int j = 0; j < strings.length; j++){
					if(description[i].equals(strings[j])) {
						listeNomString[cpt] = ply.getNom();
						cpt++;
					}
				}
			}
		}
		
		jliste = new JList(listeNomString);
		jliste.addListSelectionListener(this);
		fenetre.add(etiquette, BorderLayout.EAST);
		fenetre.add(jliste, BorderLayout.WEST);
		fenetre.pack();
		fenetre.setSize(400, 350);
		fenetre.setVisible(true);
		
		etiquette.setLayout(new GridLayout(4,2));
		
		JLabel nom = new JLabel("Nom :");
		//nom.setBounds(0, 10, 40, 15);
		etiquette.add(nom);
		
		name = new JLabel();
		//name.setBounds(64, 12, 305, 15);
		etiquette.add(name);
		
		JLabel chemin = new JLabel("Chemin :");
		//chemin.setBounds(12, 57, 70, 15);
		etiquette.add(chemin);
		
		path = new JLabel();
		//path.setBounds(94, 57, 275, 15);
		etiquette.add(path);
		
		JLabel date = new JLabel("Date :");
		//date.setBounds(12, 111, 51, 15);
		etiquette.add(date);
		
		fecha = new JLabel();
		//fecha.setBounds(75, 111, 294, 15);
		etiquette.add(fecha);
		
		JLabel descritpion = new JLabel("Description :");
		//descritpion.setBounds(12, 164, 90, 15);
		etiquette.add(descritpion);
		
		desc = new JLabel();
		//desc.setBounds(114, 164, 255, 15);
		etiquette.add(desc);
	}
	
	/**
	 * Fonction de gestion de l action du clic
	 */
	public void valueChanged(ListSelectionEvent arg0) {
		FichierPly file = bdd.selection_unique_table("demo.sqlite",(String)jliste.getSelectedValue());
		name.setText(file.getNom());
		path.setText(file.getChemin());
		fecha.setText(file.getDate());
		desc.setText(file.getDescription());
	}
	
	/**
	 * Affiche un message d erreur si probleme
	 */
	public static void message_erreur() {
		JOptionPane.showMessageDialog(null,"Le modele n'a pas pu etre trouve", "Erreur", JOptionPane.ERROR_MESSAGE);
	}
	
}
