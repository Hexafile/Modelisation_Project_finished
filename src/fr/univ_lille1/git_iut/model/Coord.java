package fr.univ_lille1.git_iut.model;

/**
 * Classe definissant les coordonnées lues dans le fichier ply
 * ainsi que toutes les methodes applicables dessus
 * @author plouvinm
 *
 */
public class Coord {
	private int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private double x;
	private double y;
	private double z;
	
	public Coord(int id){
		this.id=id;
	}

    /**
	 * Constructeur
	 * @param id identifiant des coordonnees
	 * @param x point en x
	 * @param y point en y
	 * @param z point en z
	 */
	public Coord(int id, double x,double y,double z){
		this.id=id;
		this.x=x;
		this.y=y;
		this.z=z;
	}

    /**
	 * Constructeur
	 * @param id identifiant des coordonnees
	 * @param x point en x
	 * @param y point en y
	 * @param z point en z
	 */
	public Coord(int id, int x, int y, int z){
		this.id=id;
		this.x=x;
		this.y=y;
		this.z=z;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}
	
	public void setCoord(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

    /**
 * methode permettant d'afficher les coordonnees de maniere lisible
 */
	@Override
	public String toString() {
		return "Coord "+id+"[x=" + x + ", y=" + y + ", z=" + z + "]";
	}
	
	
}
