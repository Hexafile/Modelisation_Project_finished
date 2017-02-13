package fr.univ_lille1.git_iut.test;

import static org.junit.Assert.*;

import java.time.Year;

import org.junit.Test;

import fr.univ_lille1.git_iut.model.Coord;



public class testCoord {

	@Test
	public void testCoord() {
		
		Coord coord = new Coord(1, 0, 0, 0);
		 coord.setCoord(1.0, 1.5, 2.3);
		 assertEquals((int)1.0, (int)coord.getX());
		 assertEquals((int)1.5, (int)coord.getY());
		 assertEquals((int)2.3, (int)coord.getZ());
		 
		 coord.setX(3.3);
		 coord.setY(4.4);
		 coord.setZ(5.5);
		 assertEquals((int)3.3, (int)coord.getX());
		 assertEquals((int)4.4, (int)coord.getY());
		 assertEquals((int)5.5, (int)coord.getZ());
		 coord.setId(1);
		 assertEquals(new Coord(1).getId(), coord.getId());
		 assertEquals(coord.toString(), new Coord(1, 3.3, 4.4, 5.5).toString());	
		 
		 Coord coord2 = new Coord(1, 1.1, 2.2, 3.3);
		
		 assertEquals(100, (int)(coord2.getX())*100);
		 assertEquals(200, (int)(coord2.getY())*100);
		 assertEquals(300, (int)(coord2.getZ())*100);
		
	}
	
	

}
