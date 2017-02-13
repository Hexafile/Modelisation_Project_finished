package fr.univ_lille1.git_iut.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Faces implements Comparable<Faces>{

	int id;
	int nbr;
	public List<Coord> array = new ArrayList<Coord>();
	List<Integer> arrayInt = new ArrayList<Integer>();

	// Constructeur a 3 coordonnes
	public Faces(Coord coord, Coord coord2, Coord coord3) {
		array.add(coord);
		array.add(coord2);
		array.add(coord3);
		nbr = 3;
	}

	// Constructeur a 3 coordonnes
	public Faces(int id, int a, int b, int c) {
		this.id = id;
		arrayInt.add(a);
		arrayInt.add(b);
		arrayInt.add(c);
		nbr = 3;
	}

	// Constructeur a X coordonnes
	public Faces(int id,ArrayList<Coord> c) {
		this.id = id;
		this.array = c;
		nbr = array.size();
	}
	
	public Coord getCoord(int index){
		return array.get(index);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNbr() {
		return nbr;
	}

	public void setNbr(int nbr) {
		this.nbr = nbr;
	}

	public List<Coord> getCoord() {
		return array;
	}

	public void setCoord(List<Coord> a) {
		this.array = a;
	}

	
	// doit renvoyer un tableau d'entier des X de X elements
	public int[] getX_tab() {
		int[] res = new int[array.size()];
		for (int i = 0; i < array.size(); i++) {
			res[i] = (int) (array.get(i).getX());
		}
		return res;
	}

	// doit renvoyer un tableau d'entier des Y de X elements
	public int[] getY_tab() {
		int[] res = new int[array.size()];
		for (int i = 0; i < array.size(); i++) {
			res[i] = (int) (array.get(i).getY());
		}
		return res;
	}

	// doit renvoyer un tableau d'entier des Z de X elements
	public int[] getZ_tab() {
		int[] res = new int[array.size()];
		for (int i = 0; i < array.size(); i++) {
			res[i] = (int) (array.get(i).getZ() * -250) + 500;
		}
		return res;
	}
	
	// toString pour X elements
	public String toString_tab() {
		String res = "Faces [";
		for (int i = 0; i < array.size(); i++) {
			res += array.get(i) + ",";
			res += "\n";
		}
		return res;
	}

	public String toString() {
		return "Faces [" + array.get(0) + array.get(1) + array.get(2) + "]";
	}

	public String toString2() {
		return "Faces [" + arrayInt.get(0) + ", " + arrayInt.get(1) + ", " + arrayInt.get(2) + "]";
	}

	public BaryZ baryZ() {
		double z = 0;
		for (Coord c : array) {
			z += c.getZ();
		}
		z /= array.size();
		return new BaryZ(this.id, z);
	}

	public Coord barycentre() {
		double x = 0, y = 0, z = 0;
		for (Coord c : array) {
			x += c.getX();
			y += c.getY();
			z += c.getZ();
		}
		x /= array.size();
		y /= array.size();
		z /= array.size();
		return new Coord(this.id, x, y, z);
	}

	public int compareTo(Faces f1) {
		if(this.baryZ().getZ()<f1.baryZ().getZ()){
			return -1;
		}else if(this.baryZ().getZ()>f1.baryZ().getZ()){
			return 1;
		}
		return 0;
	}
}
