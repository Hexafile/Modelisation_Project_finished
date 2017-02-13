package fr.univ_lille1.git_iut.test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.*;

import fr.univ_lille1.git_iut.model.Coord;
import fr.univ_lille1.git_iut.model.Faces;
import fr.univ_lille1.git_iut.model.FigureModel;
import org.junit.Test;

public class testModel {

	@Test
	public void testFigureModel() {
	
		FigureModel f = null;
		try {
			f = new FigureModel("data/shark.ply");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		f.setName("name");
		assertEquals("name", f.getName());
		
		ArrayList<Faces> facesListe = new ArrayList<Faces>();
		Coord coord1 = new Coord(1, 1, 2, 3);
		Coord coord2 = new Coord(2, 2, 3, 4);
		Coord coord3 = new Coord(3, 3, 4, 5);
		Faces face = new Faces(coord1, coord2, coord3);
		facesListe.add(face);
		ArrayList<Faces> tmp = new ArrayList<Faces>();
		tmp = f.getFaces();
		assertEquals(tmp, f.getFaces());
		assertEquals(734, f.size());
		
	}
	
	@Test
	public void testDistance() {
		FigureModel f = null;
		try {
			f = new FigureModel("data/shark.ply");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Coord coord1 = new Coord(1, 2, 4, 6);
		Coord coord2 = new Coord(2, 1, 8, 3);
		assertEquals(5, (int)f.distance(coord1, coord2));
	}
	
	@Test
	public void testOption() {
		FigureModel f = null;
		try {
			f = new FigureModel("data/shark.ply");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		f.setOption("-s-f");
		assertEquals("-s-f", f.getOption());
	}

}
