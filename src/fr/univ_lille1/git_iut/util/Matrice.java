package fr.univ_lille1.git_iut.util;

import java.util.ArrayList;
import java.util.List;

import fr.univ_lille1.git_iut.model.Coord;
import fr.univ_lille1.git_iut.model.Faces;

public class Matrice {
	
	
	public static ArrayList<Faces> multiply(ArrayList<Faces> f, double d){
		for(Faces face : f){
			List<Coord> l = face.getCoord();
			for(int i=0 ; i<l.size(); i++){ 
				l.set(i, new Coord(l.get(i).getId(),l.get(i).getX()*d,l.get(i).getY()*d,l.get(i).getZ()*d));
			}
		}
		return f;
	}
	
	/**
	 * multiplie deux matrices entre elles
	 * 
	 * @param double[][]
	 * @param double[][]
	 * @return double[][]
	 */
	public static double[][] multiplication(double[][] mat2, double[][] mat1) {
		double v1 = 0;
		double v2 = 0;
		double valeurCase = 0;
		double[][] tempo = new double[mat1.length][mat2[0].length+1];
		if ((mat1[0].length-1 == mat2.length)) {
			for (int i = 0; i < mat1.length; i++) {
				tempo[i][0] = mat1[i][0];
				for (int j = 0; j < mat2[0].length; j++) {
					valeurCase = 0;
					for (int ind = 1; ind < mat1[0].length; ind++) {
						v1 = mat1[i][ind];
						v2 = mat2[ind-1][j];
						valeurCase += v1 * v2;
					}
					tempo[i][j+1] = valeurCase;
				}
			}
		} else {
			System.out.println("Taille des matrices pour la multiplcation invalide.");
			return null;
		}
		return tempo;
	}

	public static double[][] listCoordToMatrice(List<Coord> list) {
		double[][] res = new double[list.size()][5];
		int i = 0;
		for (Coord myPoint : list) {
			res[i][0] = myPoint.getId();
			res[i][1] = myPoint.getX();
			res[i][2] = myPoint.getY();
			res[i][3] = myPoint.getZ();
			res[i][4] = 1;
			i++;
		}
		return res;
	}

	/**
	 * retourne la liste des points
	 * 
	 * @param double[][]
	 * @return List<Coord>
	 */
	public static List<Coord> MatriceToListMyPoints(double[][] coord) {
		List<Coord> liste = new ArrayList<Coord>();
		for (int i = 0; i < coord.length; i++) {
			Coord temp = new Coord((int)coord[i][0],coord[i][1],coord[i][2],coord[i][3]);
			liste.add(temp);
		}
		return liste;
	}
}
