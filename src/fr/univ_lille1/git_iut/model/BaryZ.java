package fr.univ_lille1.git_iut.model;

/**
 * Classe permettant de stocker les informations relatives
 * au barycentre de la figure
 * @author plouvinm
 *
 */
public class BaryZ {
	private double z = 0;
	private int id;

    /**
 * Constructeur
 * @param id identifiant du barycentre
 * @param z distance en z
 */
	public BaryZ(int id, double z) {
		this.id = id;
		this.z = z;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
