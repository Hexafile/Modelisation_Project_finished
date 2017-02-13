package fr.univ_lille1.git_iut.sqlite;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class BDDall{
	
	@SuppressWarnings("rawtypes")
	private JList jliste = new JList();
	private JLabel etiquette = new JLabel(" ");
	private JFrame fenetre = new JFrame("BDD option --all");
	
	/**
	 * creer une fentre pour afficher tous les fichiers de la base
	 * puis clique sur le nom pour avoir les infos
	 */
	
	
	public static JList<String> showAll(JList<String> j, JPanel panel){
		BDD bdd = new BDD();
		List<FichierPly> liste = bdd.selection_tout_table("demo.sqlite");
		String listeNomString [] = new String[liste.size()];
		int cpt = 0;
		for(FichierPly ply : liste){
			listeNomString[cpt] = ply.getNom();
			cpt++;
		}
		
		j = new JList<String>(listeNomString);
		panel.add(j, BorderLayout.CENTER);
		panel.setVisible(true);
		
		return j;
	}
	
	public static JList<String> showDescription(JList<String> j, JPanel panel, List<FichierPly> liste){
		BDD bdd = new BDD();
		String listeNomString [] = new String[liste.size()];
		int cpt = 0;
		for(FichierPly ply : liste){
			listeNomString[cpt] = ply.getNom();
			cpt++;
		}
		j = new JList<String>(listeNomString);
		panel.add(j, BorderLayout.CENTER);
		panel.setVisible(true);
		
		return j;
	}

	/**
	 * Affiche un message d erreur si probleme
	 */
	public static void message_erreur() {
		JOptionPane.showMessageDialog(null,"Les modeles n'ont pas pu etre trouve", "Erreur", JOptionPane.ERROR_MESSAGE);
	}
}
