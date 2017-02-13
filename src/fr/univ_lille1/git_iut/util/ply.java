package fr.univ_lille1.git_iut.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import fr.univ_lille1.git_iut.model.Coord;
import fr.univ_lille1.git_iut.model.Faces;

public class ply {

	// affichage du ply
	public static void screen() throws IOException {
		Charset charset = Charset.forName("US-ASCII");
		try (BufferedReader reader = Files.newBufferedReader(Paths.get("./res/shark.ply"), charset)) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		}
	}

	public static ArrayList<Coord> readerCoord(String url, JFrame f) throws IOException {
		ArrayList<Coord> coords = new ArrayList<>();
		Charset charset = Charset.forName("US-ASCII");
		int id = 0;
		int elementVertex = 0;

		try (BufferedReader reader = Files.newBufferedReader(Paths.get(url), charset)) {

			// test lapremiere ligne
			if (!reader.readLine().contentEquals("ply")) {
				JOptionPane.showMessageDialog(f, "Type file .ply only supported.", "BufferedReader error",
						JOptionPane.ERROR_MESSAGE);
			}

			// passage de l'en-tete
			String tmp = "";
			tmp = reader.readLine();
			while (tmp.contains("end header")) {
				if (tmp.contains("element vertex"))
					elementVertex = Integer.parseInt(tmp.substring(13, tmp.length() - 1));
				tmp = reader.readLine();
			}

			// construction de Coord
			String[] cord = reader.readLine().split(" ");
			for (int i = 0; i < elementVertex; i++) {
				Coord c = new Coord(id, Double.parseDouble(cord[0]), Double.parseDouble(cord[1]),
						Double.parseDouble(cord[2]));
				coords.add(c);
				System.out.println(c.toString());
				cord = reader.readLine().split(" ");
				id++;
			}

		}
		return coords;
	}

	public static ArrayList<Faces> readerFaces(String url) throws IOException {
		ArrayList<Faces> arrayFaces = new ArrayList<>();
		Map<Integer,Coord> coords = new HashMap<Integer,Coord>();
		Charset charset = Charset.forName("US-ASCII");
		int elementVertex = 0;
		int elementFace = 0;

		try (BufferedReader reader = Files.newBufferedReader(Paths.get(url), charset)) {
			String[] faces;
			String tmp = "";
			String[] cord;

			// test lapremiere ligne
			if (!reader.readLine().contentEquals("ply")) {
				System.out.println("Type file .ply only supported.");
			}

			// passage de l'entete

			tmp = reader.readLine();
			while (tmp!=null&&(!tmp.contains("end_header"))) {
				
				if (tmp.contains("element vertex")) {
					elementVertex = Integer.parseInt(tmp.substring(15, tmp.length()));
				}
				if (tmp.contains("element face")) {
					elementFace = Integer.parseInt(tmp.substring(13, tmp.length()));
				}
				tmp = reader.readLine();
			}
			
			// construction de Coord
			for (int i = 0; i < elementVertex; i++) {
				cord = reader.readLine().split(" ");
				Coord c = new Coord(i, Double.parseDouble(cord[0]), Double.parseDouble(cord[1]),
						Double.parseDouble(cord[2]));
				coords.put(i,c);
				//System.out.println(c.toString());
			}

			// construction des Faces avec les coordonnees de Coord
			for (int i = 0; i < elementFace; i++) {
				tmp = reader.readLine();
				faces = tmp.split(" ");
				ArrayList<Coord> fa = new ArrayList<Coord>();
				for (int j=1; j<=Integer.parseInt(faces[0]);j++){
					fa.add(coords.get(Integer.parseInt(faces[j])));
				}
				arrayFaces.add(new Faces(i,fa));
				
			}
		}
		return arrayFaces;
	}


}
