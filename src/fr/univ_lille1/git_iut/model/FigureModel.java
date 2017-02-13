package fr.univ_lille1.git_iut.model;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

import fr.univ_lille1.git_iut.sqlite.BDD;
import fr.univ_lille1.git_iut.sqlite.FichierPly;
import fr.univ_lille1.git_iut.util.Matrice;
import fr.univ_lille1.git_iut.util.ply;

/**
 * Modele, regroupe les points qui composent la figure, extends Observable
 * 
 * @author plouvinm, fablety, plouchat
 *
 */
public class FigureModel extends Observable {
	private ArrayList<Faces> faces;
	private Coord barycentre;
	private String option = "";
	private boolean ombre = true;
	private String name = "";
	private BDD bdd = new BDD();
	private FichierPly file;

	/**
	 * Constructeur de la classe
	 * 
	 * @param url
	 *            l'URL de la figure que l'on veut afficher
	 * @param option
	 *            "-s" pour n'afficher que les segments ou "-f" pour n'afficher
	 *            que les faces
	 * @throws IOException
	 */
	public FigureModel(String url, String option) throws IOException {
		name = url.split("\\")[1];
		this.option = option;
		loadNewFigure(url);
		zoom(1);
	}

	/**
	 * Constructeur de la classe
	 * 
	 * @param url
	 *            l'URL de la figure que l'on veut afficher
	 * @throws IOException
	 */
	public FigureModel(String url) throws IOException {
		loadNewFigure(url);
	}

	/**
	 * 
	 * @param url
	 * @throws IOException
	 */
	public void loadNewFigure(String url) throws IOException {
		option = "-s -f";
		file = bdd.selection_unique_table("demo.sqlite", url.substring(5));
		faces = ply.readerFaces(url);
		TriFaces();
		reSize();
		centre();
		setChanged();
		notifyObservers();
	}

	/**
	 * Getter de l'activation de l'ombre
	 * 
	 * @return boolean
	 */
	public boolean isOmbre() {
		return ombre;
	}

	/**
	 * Switch l'état de l'ombre
	 */
	public void switchValueOmbre() {
		if (ombre)
			ombre = false;
		else
			ombre = true;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Getter du nom de la figure
	 * 
	 * @return le nom de la figure affichee
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter du nom de la figure
	 * 
	 * @param name
	 *            le nom que l'on veut donner a la figure
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter de l'ArrayList de Faces
	 * 
	 * @return une ArrayList des Faces de la figures
	 */
	public ArrayList<Faces> getFaces() {
		return faces;
	}

	/**
	 * Setter de l'ArayList de Faces
	 * 
	 * @param faces
	 *            l'ArrayList de Faces que l'on veut afficher
	 */
	public void setFaces(ArrayList<Faces> faces) {
		this.faces = faces;
	}

	/**
	 * Zoom ou dezoom la figure
	 * 
	 * @param n
	 *            le coefficient de zoom
	 */
	public void zoom(double n) {
		double[][] matrice = new double[][] { { n, 0, 0, 0 }, { 0, n, 0, 0 },
				{ 0, 0, n, 0 }, { 0, 0, 0, 1 } };
		for (Faces face : faces) {
			double[][] matrice2 = Matrice.listCoordToMatrice(face.getCoord());
			double[][] res = Matrice.multiplication(matrice, matrice2);
			face.setCoord(Matrice.MatriceToListMyPoints(res));
		}
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Getter du nombre de faces
	 * 
	 * @return le nombre de faces qui composent la figure
	 */
	public int size() {
		return faces.size();
	}

	/**
	 * Calcule le barycentre de la figure
	 * 
	 * @return Les Coordonnees du barycentre calcule
	 */
	public Coord barycentre() {
		ArrayList<Coord> bary = new ArrayList<Coord>();
		for (Faces f : faces) {
			bary.add(f.barycentre());
		}
		double x = 0, y = 0, z = 0;
		for (Coord c : bary) {
			x += c.getX();
			y += c.getY();
			z += c.getZ();
		}
		x /= bary.size();
		y /= bary.size();
		z /= bary.size();
		return new Coord(-1, x, y, z);
	}

	/**
	 * Calcule la distance entre deux points
	 * 
	 * @param c1
	 *            les Coordonnees du premier point
	 * @param c2
	 *            les coordonnees du deuxieme point
	 * @return la distance en double
	 */
	public double distance(Coord c1, Coord c2) {
		return Math.sqrt(Math.pow(c2.getX() - c1.getX(), 2)
				+ Math.pow(c2.getY() - c1.getY(), 2)
				+ Math.pow(c2.getZ() - c1.getZ(), 2));
	}

	public void centre() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) (screenSize.getWidth() * 0.75 * 0.8);
		int y = (int) (screenSize.getHeight() * 0.75 * 0.8);
		Coord bary = barycentre();
		for (Faces fa : faces) {
			for (Coord co : fa.getCoord()) {
				co.setY(co.getY() + (y / 2 - bary.getY()));
				co.setX(co.getX() + (x / 2 - bary.getX()));
			}
		}
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Rotation de la figure
	 * 
	 * @param x
	 *            le coefficient de rotation
	 */
	public void rotation(double x, int rot) {
		double rad = x * 0.01745;
		Coord z = barycentre();
		double[][] matriceRot = null;

		// Matrice rotation pour X, Y, Z respectivement
		switch (rot) {
		case 1:
			matriceRot = new double[][] { { 1, 0, 0, z.getX() },
					{ 0, Math.cos(rad), Math.sin(rad), z.getY() },
					{ 0, -Math.sin(rad), Math.cos(rad), 0 }, { 0, 0, 0, 1 } };
			break;
		case 2:
			matriceRot = new double[][] {
					{ Math.cos(rad), 0, -Math.sin(rad), z.getX() },
					{ 0, 1, 0, z.getY() },
					{ Math.sin(rad), 0, Math.cos(rad), 0 }, { 0, 0, 0, 1 } };
			break;
		case 3:
			matriceRot = new double[][] {
					{ Math.cos(rad), Math.sin(rad), 0, z.getX() },
					{ -Math.sin(rad), Math.cos(rad), 0, z.getY() },
					{ 0, 0, 1, 0 }, { 0, 0, 0, 1 } };
			break;
		default:
			matriceRot = new double[][] { { 1, 1, 0, 0 }, { 1, 1, 0, 0 },
					{ 0, 0, 1, 0 }, { 0, 0, 0, 1 } };
			break;
		}

		for (Faces face : faces) {
			double[][] matrice2 = Matrice.listCoordToMatrice(face.getCoord());
			double[][] res = Matrice.multiplication(matriceRot, matrice2);
			face.setCoord(Matrice.MatriceToListMyPoints(res));
		}
		TriFaces();
		setChanged();
		notifyObservers(this);
	}

	public void reSize() {
		double[][] matrice = new double[][] { { 250, 0, 0, 0 },
				{ 0, -250, 0, 0 }, { 0, 0, 250, 0 }, { 0, 0, 0, 1 } };
		for (Faces face : faces) {
			double[][] matrice2 = Matrice.listCoordToMatrice(face.getCoord());
			double[][] res = Matrice.multiplication(matrice, matrice2);
			face.setCoord(Matrice.MatriceToListMyPoints(res));
		}
	}

	/**
	 * Translation de la figure
	 * 
	 * @param x
	 *            coefficient de translation en x
	 * @param y
	 *            coefficient de translation en y
	 */
	public void translation(double x, double y) {
		if (x == 0) {
			for (Faces fa : faces) {
				for (Coord co : fa.array) {
					co.setY(co.getY() + y);
				}
			}
		} else if (y == 0) {
			for (Faces fa : faces) {
				for (Coord co : fa.array) {
					co.setX(co.getX() + x);
				}
			}
		} else {
			for (Faces fa : faces) {
				for (Coord co : fa.array) {
					co.setY(co.getY() + y);
					co.setX(co.getX() + x);
				}
			}
		}
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Tri des faces de la figure pour les afficher de la plus eloignee a la pus
	 * proche, calcule le barycentre de la face puis les trie selon leur
	 * eloignement, selon l'axe z
	 */
	public void TriFaces() {
		Faces[] facestemp = new Faces[faces.size()];
		facestemp = faces.toArray(facestemp);
		Arrays.parallelSort(facestemp);
		faces = new ArrayList<Faces>(Arrays.asList(facestemp));
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Getter du barycentre calcule
	 * 
	 * @return les Coordonnees du barycentre
	 */
	public Coord getBarycentre() {
		return this.barycentre;
	}

	/**
	 * Getter de l'option passee au lancement de l'application
	 * 
	 * @return une String contenant l'option passee
	 */
	public String getOption() {
		return option;
	}

	/**
	 * Setter de l'option
	 * 
	 * @param option
	 *            "-s" pour n'afficher que les faces, "-f" pour n'afficher que
	 *            les segments
	 */
	public void setOption(String option) {
		this.option = option;
		setChanged();
		notifyObservers(this);
	}

	public String bddGetName() {
		return file.getNom();
	}

	public String bddGetChemin() {
		return file.getChemin();
	}

	public String bddGetDate() {
		return file.getDate();
	}

	public String bddGetDescription() {
		return file.getDescription();
	}

}
