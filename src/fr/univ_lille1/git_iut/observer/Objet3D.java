package fr.univ_lille1.git_iut.observer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import fr.univ_lille1.git_iut.model.Faces;
import fr.univ_lille1.git_iut.model.FigureModel;
import fr.univ_lille1.git_iut.util.Matrice;

public class Objet3D extends JPanel {
	private static final long serialVersionUID = 1L;
	private FigureModel f;
	private Image croix, reduire;

	public Objet3D(FigureModel f) {
		this.f = f;
		croix = Toolkit.getDefaultToolkit().getImage("img/croix.jpg");
		reduire = Toolkit.getDefaultToolkit().getImage("img/reduire.jpg");
		this.setVisible(true);
	}

	public void set(FigureModel f) {
		this.f = f;
	}

	/**
	 * Génère l'affichage de l'objet 3D
	 */
	public void paint(Graphics gr) {
		gr.setColor(new Color(201, 201, 201));
		gr.fillRect(0, 0, this.getWidth(), this.getHeight());
		gr.setColor(Color.BLACK);
		int rgb = 0;
		int cpt = 0;
		// génération de l'ombre
		if (f.isOmbre()) {
			f.setFaces(Matrice.multiply(f.getFaces(), 1.25));
			for (Faces fa : f.getFaces()) {
				gr.setColor(new Color(128, 128, 128));
				gr.fillPolygon(fa.getX_tab(), fa.getY_tab(), fa.getNbr());
			}
			f.setFaces(Matrice.multiply(f.getFaces(), 0.8));
		}
		// génération de la figure avec segments et faces
		if (f.getOption().equals("-f -s") || f.getOption().equals("-s -f")) {
			for (Faces fa : f.getFaces()) {
				gr.setColor(new Color(rgb, rgb, rgb));
				gr.fillPolygon(fa.getX_tab(), fa.getY_tab(), fa.getNbr());
				gr.setColor(new Color(0, 0, 0));
				gr.drawPolygon(fa.getX_tab(), fa.getY_tab(), fa.getNbr());
				// Effet radio aux rayons X.
				// rgb=(int)
				// (((double)f.getFaces().size()-cpt)/(double)f.getFaces().size()*255);
				rgb = 255 - ((int) (((double) f.getFaces().size() - cpt)
						/ (double) f.getFaces().size() * 255));
				cpt++;
			}
		}
		// génération de la figure avec faces
		if (f.getOption().equals("-f")) {
			for (Faces fa : f.getFaces()) {
				if (cpt % 2 == 0) {
					gr.setColor(new Color(rgb, rgb, rgb));
				} else {
					gr.setColor(new Color(rgb, rgb, rgb));
				}
				gr.fillPolygon(fa.getX_tab(), fa.getY_tab(), fa.getNbr());
				// Effet radio aux rayons X.
				// rgb=(int)
				// (((double)f.getFaces().size()-cpt)/(double)f.getFaces().size()*255);
				rgb = 255 - ((int) (((double) f.getFaces().size() - cpt)
						/ (double) f.getFaces().size() * 255));
				cpt++;
			}
		}
		// génération de la figure avec segments
		else if (f.getOption().equals("-s")) {
			for (Faces fa : f.getFaces()) {
				gr.setColor(new Color(0, 0, 0));
				gr.drawPolygon(fa.getX_tab(), fa.getY_tab(), fa.getNbr());
			}
		}
		// initialisation des boutons élémentaires
		gr.drawImage(croix, this.getWidth() - 30, 2, this);
		gr.drawImage(reduire, this.getWidth() - 60, 2, this);
	}
}
