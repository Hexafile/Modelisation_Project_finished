package fr.univ_lille1.git_iut.observer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.univ_lille1.git_iut.model.FigureModel;
import fr.univ_lille1.git_iut.sqlite.BDD;
import fr.univ_lille1.git_iut.sqlite.BDDadd;
import fr.univ_lille1.git_iut.sqlite.BDDdelete;
import fr.univ_lille1.git_iut.sqlite.BDDedit;
import fr.univ_lille1.git_iut.sqlite.BDDfind;
import fr.univ_lille1.git_iut.sqlite.FichierPly;

public class PanelDescription extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;
	private JLabel name, path, fecha, desc;
	private FigureModel maFigureModele;
	private JTextField text;

	public PanelDescription(BDD bdd, FigureModel f) {
		maFigureModele = f;
		maFigureModele.addObserver(this);
		
		setLayout(new BorderLayout());
		
		JLabel nom = new JLabel("Nom :");
		nom.setBounds(12, 20, 40, 15);
		add(nom);
		
		JLabel chemin = new JLabel("Chemin :");
		chemin.setBounds(12, 50, 70, 15);
		add(chemin);
		
		JLabel date = new JLabel("Date :");
		date.setBounds(12, 80, 51, 15);
		add(date);
		
		JLabel descritpion = new JLabel("Description :");
		descritpion.setBounds(12, 110, 90, 15);
		add(descritpion);
		
		name = new JLabel(bdd.getFile().getNom());
		name.setBounds(64, 20, 305, 15);
		add(name);
		
		path = new JLabel(bdd.getFile().getChemin());
		path.setBounds(94, 50, 275, 15);
		add(path);
		
		fecha = new JLabel(bdd.getFile().getDate());
		fecha.setBounds(75, 80, 294, 15);
		add(fecha);
		
		desc = new JLabel(bdd.getFile().getDescription());
		desc.setBounds(114, 110, 295, 15);
		add(desc);
		
		/*JPanel j = new JPanel();
		j.setPreferredSize(new Dimension((int) (getWidth() * 1),
				(int) (getHeight() * 0.5)));
		
		GridLayout gridLayout = new GridLayout(2, 3);
		j.setLayout(gridLayout);*/
		
		JButton boutonADD = new JButton("ADD");
		boutonADD.setVisible(true);
		boutonADD.setBounds(0, 150 ,70, 50);
		boutonADD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new BDDadd();
			}
		});
		add(boutonADD);
		
		JButton boutonEDIT = new JButton("EDIT");
		boutonEDIT.setVisible(true);
		boutonEDIT.setBounds(70, 150 ,70, 50);
		boutonEDIT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new BDDedit(maFigureModele.bddGetName(),
						maFigureModele.bddGetChemin(),
						maFigureModele.bddGetDate(),
						maFigureModele.bddGetDescription());
			}
		});
		add(boutonEDIT);
		
		JButton boutonDELETE = new JButton("DELETE");
		boutonDELETE.setVisible(true);
		boutonDELETE.setBounds(140, 150 ,90, 50);
		boutonDELETE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new BDDdelete(maFigureModele.bddGetName(),
						maFigureModele.bddGetChemin(),
						maFigureModele.bddGetDate(),
						maFigureModele.bddGetDescription());
			}
		});
		add(boutonDELETE);
		
		text = new JTextField("Nom du ply ...");
		text.setVisible(true);
		text.setBounds(0, 210, 140, 40);
		text.getFont().deriveFont(Font.ITALIC);
		text.setForeground(Color.gray);
		text.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				text = ((JTextField)e.getSource());
		        text.setText("");
		        text.getFont().deriveFont(Font.PLAIN);
		        text.setForeground(Color.black);
		        text.removeMouseListener(this);	
			}

			public void mouseEntered(MouseEvent e) {}

			public void mouseExited(MouseEvent e) {}

			public void mousePressed(MouseEvent e) {}

			public void mouseReleased(MouseEvent e) {}
		});
		add(text);
		
		JButton boutonFIND = new JButton("FIND");
		boutonFIND.setVisible(true);
		boutonFIND.setBounds(140, 210, 90, 40);
		boutonFIND.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BDDfind(text.getText().split(","));
			}
		});
		add(boutonFIND);
		setVisible(true);
		//setBackground(Color.BLACK);
		//add(j);
		validate();
	}

	public PanelDescription(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public PanelDescription(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public PanelDescription(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Observable o, Object arg) {
		name.setText(maFigureModele.bddGetName());
		path.setText(maFigureModele.bddGetChemin());
		fecha.setText(maFigureModele.bddGetDate());
		desc.setText(maFigureModele.bddGetDescription());		
	}

}
