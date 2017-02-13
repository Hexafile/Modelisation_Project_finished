package fr.univ_lille1.git_iut.sqlite;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

public class BDDname {
	
	private JFrame fenetre;
	
	/**
	 * affiche la Jframe avec les informations selon le modele demande
	 * @param a Nom du modele
	 * @param b Chemin du modele
	 * @param c Date du modele
	 * @param d Mots cles du modele
	 */
	public BDDname(String a, String b, String c, String d) {
		fenetre = new JFrame("BDD option --name");

		fenetre.setSize(400, 238);
		
		//Nous demandons maintenant a notre objet de se positionner au centre
		fenetre.setLocationRelativeTo(null);

		//fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.getContentPane().setLayout(null);
		
		JLabel nom = new JLabel("Nom :");
		nom.setBounds(12, 12, 40, 15);
		fenetre.getContentPane().add(nom);
		
		JLabel chemin = new JLabel("Chemin :");
		chemin.setBounds(12, 57, 70, 15);
		fenetre.getContentPane().add(chemin);
		
		JLabel date = new JLabel("Date :");
		date.setBounds(12, 111, 51, 15);
		fenetre.getContentPane().add(date);
		
		JLabel descritpion = new JLabel("Description :");
		descritpion.setBounds(12, 164, 90, 15);
		fenetre.getContentPane().add(descritpion);
		
		JLabel name = new JLabel(a);
		name.setBounds(64, 12, 305, 15);
		fenetre.getContentPane().add(name);
		
		JLabel path = new JLabel(b);
		path.setBounds(94, 57, 275, 15);
		fenetre.getContentPane().add(path);
		
		JLabel fecha = new JLabel(c);
		fecha.setBounds(75, 111, 294, 15);
		fenetre.getContentPane().add(fecha);
		
		JLabel desc = new JLabel(d);
		desc.setBounds(114, 164, 255, 15);
		fenetre.getContentPane().add(desc);
		
		//Et enfin, la rendre visible 
		fenetre.setAlwaysOnTop(true);
		fenetre.setVisible(true);
	}
	
	/**
	 * affiche un message de confirmation si le modele est bien dans la base de donnee
	 */
	public static void message_confirmation(String a) {
		JOptionPane.showMessageDialog(null, "Le modele " + a + " a bien ete modifie", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * affiche un message d'erreur si le modele n'existe pas dans la base de donnee
	 */
	public static void message_erreur(String a) {
		JOptionPane.showMessageDialog(null, "Le modele "+ a + " n'existe pas", "Erreur", JOptionPane.ERROR_MESSAGE);
	}
}
