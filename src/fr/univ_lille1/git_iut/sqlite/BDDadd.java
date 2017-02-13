package fr.univ_lille1.git_iut.sqlite;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class BDDadd {

	private static JTextField textField;
	private static JTextField textField_1;
	private static JTextField textField_2;
	private static JTextField textField_3;
	private JFrame fenetre;
	private BDD bdd = new BDD();
	
	/**
	 * Creer une fentre pour l ajout d une valeur
	 */
	public BDDadd() {
		fenetre = new JFrame("Fenetre d'ajout de fichier ply");

		fenetre.setSize(400, 269);
		
		//Nous demandons maintenant à notre objet de se positionner au centre
		fenetre.setLocationRelativeTo(null);

		fenetre.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nom :");
		lblNewLabel.setBounds(12, 12, 40, 15);
		fenetre.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Chemin :");
		lblNewLabel_1.setBounds(12, 57, 70, 15);
		fenetre.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Date :");
		lblNewLabel_2.setBounds(12, 111, 51, 15);
		fenetre.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Description :");
		lblNewLabel_3.setBounds(12, 164, 90, 15);
		fenetre.getContentPane().add(lblNewLabel_3);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(140, 203, 117, 25);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bdd.insertion_table("demo.sqlite", textField.getText(),
						textField_1.getText(), textField_2.getText(), textField_3.getText());
				fenetre.setVisible(false);
				fenetre.dispose();
				message_confirmation(textField.getText());
			}
		});
		fenetre.getContentPane().add(btnAjouter);
		
		textField = new JTextField();
		textField.setBounds(70, 10, 114, 19);
		fenetre.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(100, 55, 114, 19);
		fenetre.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(81, 109, 114, 19);
		fenetre.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(120, 162, 114, 19);
		fenetre.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		       
		fenetre.setVisible(true);
	}

	/**
	 * Afficjhe un message de confirmation d ajout
	 * @param nom du fichier a ajouter
	 */
	public static void message_confirmation(String a) {
		JOptionPane.showMessageDialog(null, "Le modele " + a + " a bien ete ajouter", "Ajout", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Affiche un message d erreur si le fichier ne s est pas ajouter
	 */
	public static void message_erreur() {
		JOptionPane.showMessageDialog(null,"Le modele n'a pas pu etre ajouter", "Erreur", JOptionPane.ERROR_MESSAGE);
	}
}
