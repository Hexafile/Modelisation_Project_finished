package fr.univ_lille1.git_iut.sqlite;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BDDedit {
	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JFrame fenetre;
	private BDD bdd = new BDD();
	
	/**
	 * affiche la Jframe avec les informations deja charges du modele 
	 * demande et modifiable en appuyant sur "modifier"
	 * @param a nom avant modification du modele
	 * @param b chemin avant modification du modele
	 * @param c date avant modification du modele
	 * @param d mots avant modification cles du modele
	 */
	public BDDedit(String a, String b, String c, String d) {
		final String name = a;
		fenetre = new JFrame("Fenetre de modification du ply");

		fenetre.setSize(400, 269);
		
		//Nous demandons maintenant à notre objet de se positionner au centre
		fenetre.setLocationRelativeTo(null);

		//fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					bdd.mise_a_jour_table("demo.sqlite", name, textField.getText(), textField_1.getText(), textField_2.getText(), textField_3.getText());
					fenetre.setVisible(false);
					message_confirmation(name);
					fenetre.dispose();
				} catch (Exception e2) {
					message_erreur(name);
				}
			}
		});
		btnModifier.setBounds(140, 203, 117, 25);
		fenetre.getContentPane().add(btnModifier);
		
		textField = new JTextField(a);
		textField.setBounds(70, 10, 114, 19);
		fenetre.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setEditable(false);
		
		textField_1 = new JTextField(b);
		textField_1.setBounds(100, 55, 114, 19);
		fenetre.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField(c);
		textField_2.setBounds(81, 109, 114, 19);
		fenetre.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField(d);
		textField_3.setBounds(120, 162, 114, 19);
		fenetre.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		//Et enfin, la rendre visible        
		fenetre.setVisible(true);
	}
	
	/**
	 * affiche un message de confirmation quand le modele est modifie
	 */
	public static void message_confirmation(String a) {
		JOptionPane.showMessageDialog(null, "Le modele " + a + " a bien ete modifie", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Affiche un message d erreur si probleme
	 */
	public static void message_erreur(String a) {
		JOptionPane.showMessageDialog(null,"Le modele " + a + " n'a pas pu etre charge\nC'est qu'il n'existe", "Erreur", JOptionPane.ERROR_MESSAGE);
	}
}
