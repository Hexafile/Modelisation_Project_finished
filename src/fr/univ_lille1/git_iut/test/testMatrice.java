package fr.univ_lille1.git_iut.test;



import static org.junit.Assert.*;

import org.junit.Test;
import fr.univ_lille1.git_iut.util.Matrice;

public class testMatrice {


	@Test
	public void testMultiplication() {
		
		double[][] mat1 = {{1, 3}, {3, 4}};
		double[][] mat2 = {{2, 5, 1}, {4, 3, 1}};

		double[][] result = Matrice.multiplication(mat1, mat2);
		double[][] expected = {{2, 8, 19}, {4, 6, 13}};



		assertEquals(result, expected);
	}

}
