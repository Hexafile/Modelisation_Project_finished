package fr.univ_lille1.git_iut.sqlite;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class BDDdelete {

	private JFrame fenetre;
	private BDD bdd = new BDD();

	/**
	 * Suppression d une valeur
	 * @param nom du fichier a supprimer
	 */
	public BDDdelete(String a, String b, String c, String d) {
	    final String a2 = a;
		try {
			fenetre = new JFrame("Fenetre de suppression de ply");

			fenetre.setSize(400, 300);

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

			JButton btnConfirmer = new JButton("Confirmer");
			btnConfirmer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						System.out.println("fichier a supprimer :" + a2);
						bdd.suppression_table("demo.sqlite", a2);
						message_confirmation(a2);
						fenetre.dispose();
					} catch (Exception e2) {
						message_erreur(a2);
						e2.printStackTrace();
					}	
				}	
			});	
			btnConfirmer.setBounds(140, 203, 117, 25);
			fenetre.getContentPane().add(btnConfirmer);

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

		} catch (Exception e) {
			message_erreur(a);
		}
	}

	/**
	 * Affiche un message de confirmation de suprression
	 * @param nom du fichier a supprimer
	 */
	public static void message_confirmation(String a) {
		JOptionPane.showMessageDialog(null, "Le modele " + a + " a bien ete supprimer", "Confirmation", JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * Message d erreur sur la suppression
	 * @param nom du fichier a supprimer
	 */
	public static void message_erreur(String a) {
		JOptionPane.showMessageDialog(null, "Une erreur est survenue lors de la suppression du modele " + a + "! Recommencez ou annuler.", "Erreur", JOptionPane.ERROR_MESSAGE);
	}
}
