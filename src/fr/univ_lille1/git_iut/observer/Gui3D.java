package fr.univ_lille1.git_iut.observer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.sound.midi.VoiceStatus;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fr.univ_lille1.git_iut.model.FigureModel;
import fr.univ_lille1.git_iut.sqlite.BDD;
import fr.univ_lille1.git_iut.sqlite.BDDall;
import fr.univ_lille1.git_iut.sqlite.BDDfind;
import fr.univ_lille1.git_iut.sqlite.FichierPly;

public class Gui3D extends JFrame implements Observer, ListSelectionListener {
	private static final long serialVersionUID = 1L;
	private FigureModel f;
	public Objet3D o;
	/*-----------------------*/
	public JPanel bddPanel = new JPanel();
	public JPanel description;
	public JPanel liste = new JPanel();
	public JPanel modelisation = new JPanel();
	public JPanel bouttons = new JPanel();
	/*-----------------------*/
	public JButton btnZoomP = new JButton("Zoom +");
	public JButton btnZoomM = new JButton("Zoom -");
	/*-----------------------*/
	public JSlider sliTranslationX = new JSlider();
	public JSlider sliTranslationY = new JSlider();
	public JSlider slideRotationX = new JSlider();
	public JSlider slideRotationY = new JSlider();
	public JSlider slideRotationZ = new JSlider();
	/*-----------------------*/
	public JButton btnFaces = new JButton("Faces");
	public JButton btnSegments = new JButton("Segments");
	public JButton btnCentrer = new JButton("Centrer");
	public JButton btnShadow = new JButton("Ombre");
	/*-----------------------*/
	public double oldTransX = 0;
	public double oldTransY = 0;
	public double oldRotation = 0;

	// BDD ------------------------
	private JList<String> jliste;
	private JLabel jT2;
	private FichierPly file;
	private BDD bdd = new BDD();

	public Gui3D(FigureModel f) {
		this.setF(f);
		o = new Objet3D(f);
		f.addObserver(this);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		/*-----------------------*/
		/* Localisation et taille dynamique de la fenetre */
		setResizable(true);
		setUndecorated(true);
		setSize((int) (screenSize.getWidth() * 0.75),
				(int) (screenSize.getHeight() * 0.75));
		this.getContentPane().setMinimumSize(new Dimension(1000, 800));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation((int) (screenSize.getWidth() - this.getWidth()) / 2,
				(int) (screenSize.getHeight() - this.getHeight()) / 2);
		setVisible(true);
		getContentPane().setLayout(new BorderLayout());
		/*-----------------------*/
		/* Definition des bordures */
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		Border compound = BorderFactory.createCompoundBorder(raisedbevel,
				loweredbevel);
		/*-----------------------*/
		/* Definition des tailles */
		o.setBackground(new Color(103, 104, 105));
		o.setMinimumSize(new Dimension(800, 600));
		o.setPreferredSize(new Dimension((int) (getWidth() * 0.8),
				(int) (getHeight() * 0.8)));
		bouttons.setLayout(new GridLayout(5, 4));
		bouttons.setMinimumSize(new Dimension(800, 200));
		bouttons.setPreferredSize(new Dimension((int) (getWidth() * 0.8),
				(int) (getHeight() * 0.2)));
		modelisation.setLayout(new BorderLayout());
		modelisation.setMinimumSize(new Dimension(800, 800));
		modelisation.setPreferredSize(new Dimension((int) (getWidth() * 0.8),
				(int) getHeight()));
		modelisation.setBorder(compound);
		liste.setLayout(new BorderLayout());
		liste.setBackground(Color.RED);
		liste.setMinimumSize(new Dimension(200, 500));
		liste.setPreferredSize(new Dimension((int) (getWidth() * 0.2),
				(int) (getHeight() * 0.625)));

		description = new PanelDescription(bdd, f);
		description.setLayout(new BorderLayout());
		// description.setBackground(Color.BLUE);
		description.setMinimumSize(new Dimension(200, 300));
		description.setPreferredSize(new Dimension((int) (getWidth() * 0.2),
				(int) (getHeight() * 0.375)));
		bddPanel.setLayout(new BorderLayout());
		bddPanel.setMinimumSize(new Dimension(200, 800));
		bddPanel.setPreferredSize(new Dimension((int) (getWidth() * 0.2),
				(int) getHeight()));
		bddPanel.setBorder(compound);

		/*-----------------------*/
		/* BOUTON ZOOM + */
		btnZoomP = new JButton("Zoom +");
		bouttons.add(btnZoomP);
		/* BOUTON ZOOM - */
		btnZoomM = new JButton("Zoom -");
		bouttons.add(btnZoomM);
		/* BOUTON CENTRER */
		btnCentrer = new JButton("Centrer");
		bouttons.add(btnCentrer);
		/* BOUTON SEGMENTS */
		btnSegments = new JButton("Segments");
		bouttons.add(btnSegments);
		/*-----------------------*/

		/*-----------------------*/
		/* JLabel for description */
		bouttons.add(new JLabel("Rotation sur X"));
		bouttons.add(new JLabel("Rotation sur Y"));
		bouttons.add(new JLabel("Rotation sur Z"));
		/* BOUTON FACE */
		btnFaces = new JButton("Faces");
		bouttons.add(btnFaces);
		/* SLider rotation X */
		slideRotationX = new JSlider(SwingConstants.HORIZONTAL, 0, 360, 0);
		bouttons.add(slideRotationX);
		/* SLider rotation Y */
		slideRotationY = new JSlider(SwingConstants.HORIZONTAL, 0, 360, 0);
		bouttons.add(slideRotationY);
		/* SLider rotation Z */
		slideRotationZ = new JSlider(SwingConstants.HORIZONTAL, 0, 360, 0);
		bouttons.add(slideRotationZ);
		/* BOUTON OMBRE */
		btnShadow = new JButton("Ombre");
		bouttons.add(btnShadow);
		/*-----------------------*/

		/*-----------------------*/
		/* JLabel for description */
		bouttons.add(new JLabel("Translation sur X"));
		bouttons.add(new JLabel("Translation sur Y"));
		bouttons.add(new JLabel(""));
		bouttons.add(new JLabel(""));
		/* Slider TRANSLATION X */
		sliTranslationX = new JSlider(SwingConstants.HORIZONTAL, -100, 100, 0);
		bouttons.add(sliTranslationX);
		/* Slider TRANSLATION Y */
		sliTranslationY = new JSlider(SwingConstants.HORIZONTAL, -100, 100, 0);
		bouttons.add(sliTranslationY);
		bouttons.add(new JLabel(""));
		bouttons.add(new JLabel(""));
		/*-----------------------*/

		modelisation.add(bouttons, BorderLayout.SOUTH);
		/*-----------------------*/
		/* Appel d'Objet3D pour le paint */
		f.zoom(1);
		repaint();
		//
		modelisation.add(o, BorderLayout.NORTH);
		/*-----------------------*/
		/*
		 * CODE DE L IHM DE LA LISTE
		 */
		//
		bddPanel.add(liste, BorderLayout.NORTH);
		/*-----------------------*/
		/*
		 * CODE DE L IHM DE LA DESCRIPTION
		 */
		//
		bddPanel.add(description, BorderLayout.SOUTH);
		/*-----------------------*/

		// travail ici !
		// --all

		jliste = BDDall.showAll(jliste, liste);
		jliste.addListSelectionListener(this);
		liste.add(new JLabel("Selectionnez un modele"), BorderLayout.NORTH);

		// --name

		// jusque ici !

		getContentPane().add(bddPanel, BorderLayout.WEST);
		getContentPane().add(modelisation, BorderLayout.EAST);
		validate();
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		try {
			f.loadNewFigure("data/" + jliste.getSelectedValue());
			System.out.println(jliste.getSelectedValue());
			slideRotationX.setValue(0);
			slideRotationY.setValue(0);
			slideRotationZ.setValue(0);
			sliTranslationX.setValue(0);
			sliTranslationY.setValue(0);
		} catch (NoSuchFileException e2) {
			JOptionPane.showConfirmDialog(this,
					"Figure non trouvee.\nVerifier le nom et/ou le chemin",
					"Information", JOptionPane.WARNING_MESSAGE);
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (NullPointerException e3) {
			e3.printStackTrace();
		}
	}

	public FigureModel getF() {
		return f;
	}

	public void setF(FigureModel f) {
		this.f = f;
	}

	@Override
	public void update(Observable o, Object arg) {
		this.setF((FigureModel) arg);
		this.repaint();
	}
}
