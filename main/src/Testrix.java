import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;


public class Testrix {

	private double[][] initArray = new double[][]{{1,2,3},{4,5,6},{7,8,9}};
	private Matrix initMatrix = new Matrix(initArray);
	
	@Test
	public void testConstructor() {
		Matrix m = new Matrix(3,3);
		assertArrayEquals("Not initialized correctly.", new double[][]{{0,0,0},{0,0,0},{0,0,0}}, m.getArray());
	}

	@Test
	public void testConstructor2() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testConstructor3() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testConstructor4() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testConstructor5() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testConstructWithCopy() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testCopy() {
		assertArrayEquals("Copied matrix not equal.", initMatrix.getArray() ,initMatrix.copy().getArray());
	}
	
	@Test
	public void testClone() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetArray() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetArrayCopy() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetColumnPackedCopy() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testgetRowPackedCopy() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetRowDimension() {
		assertEquals("Get row dim. failed", 3, initMatrix.getRowDimension());
	}
	
	@Test
	public void testGetColumnDimension() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGet() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetMatrix() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetMatrix2() {
		assertArrayEquals("Get matrix with two index arrays failed",
				new double[][]{{1,3}, {4, 6}},
				initMatrix.getMatrix(new int[]{0, 1}, new int[]{0, 2}).getArrayCopy());
	}
	
	@Test
	public void testGetMatrix3() {
		assertArrayEquals("Get matrix with two index arrays failed",
				new double[][]{{1,3}, {4, 6}},
				initMatrix.getMatrix(0, 1, new int[]{0, 2}).getArrayCopy());
	}
	
	@Test
	public void testGetMatrix4() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testSet() {
		initMatrix.set(0, 0, 6);
		assertEquals("Set failed", 6, initMatrix.get(0,0), 0);
	}
	
	@Test
	public void testSetMatrix() {
		Matrix tempMatrix = new Matrix(3,3);
		tempMatrix.setMatrix(0, 2, 0, 2, initMatrix);
		assertArrayEquals("Set Matrix failed", initMatrix.getArray(), tempMatrix.getArray());
	}
	
	@Test
	public void testSetMatrix2() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testSetMatrix3() {
		Matrix tempMatrix = new Matrix(3,3);
		tempMatrix.setMatrix(new int[]{0,1,2}, 0, 2, initMatrix);
		assertArrayEquals("Set Matrix failed", initMatrix.getArray(), tempMatrix.getArray());
	}
	
	@Test
	public void testSetMatrix4() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testTranspose() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testNorm1() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testNormInf() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testNormF() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testUminus() {
		Matrix m = new Matrix(initMatrix.getArrayCopy());
		m = m.uminus();
		for(int i = 0; i < m.getRowDimension(); i++)
		{
			for(int j = 0; j < m.getColumnDimension(); j++)
			{
				assertEquals("Point " + i + " " + j + " not equal.",
						-m.get(i, j), initMatrix.get(i, j), 0);
			}		
		}
	}
	
	@Test
	public void testPlus() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testPlusEquals() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testMinus() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testMinusEquals() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testArrayTimes() {
		Matrix m = new Matrix(initMatrix.getArray());
		Matrix a = m.arrayTimes(initMatrix);
		for(int i = 0; i < m.getRowDimension(); i++)
		{
			for(int j = 0; j < m.getColumnDimension(); j++)
			{
				assertEquals("Point " + i + " " + j + " not equal.",
						a.get(i, j), m.get(i, j) * initMatrix.get(i, j), 0);
			}		
		}
	}
	
	@Test
	public void testArrayTimesEquals() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testArrayRightDivide() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testArrayRightDivideEquals() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testArrayLeftDivide() {
		Matrix m = new Matrix(initMatrix.getArray());
		Matrix mCopy = m.copy();
		Matrix a = m.arrayLeftDivide(initMatrix);
		for(int i = 0; i < m.getRowDimension(); i++)
		{
			for(int j = 0; j < m.getColumnDimension(); j++)
			{
				assertEquals("Point " + i + " " + j + " not equal.",
						a.get(i, j), mCopy.get(i, j) / m.get(i, j), 0);
			}		
		}
	}
	
	@Test
	public void testArrayLeftDivideEquals() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testTimes() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testTimesEquals() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testTimes2() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testTrace() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testRandom() {
		Matrix m = Matrix.random(3,3);
		for(int i = 0; i < m.getRowDimension(); i++)
		{
			for(int j = 0; j < m.getColumnDimension(); j++)
			{
				assertTrue("Point " + i + " " + j + " not equal.",
							0 <= m.get(i,j) && m.get(i, j) <= 1);
				
			}		
		}
	}
	
	@Test
	public void testIdentity() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testPrint() {
		 ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	     System.setOut(new PrintStream(outContent));
	     initMatrix.print(3, 1);
	     String output  = " 1.0  2.0  3.0 \n 4.0  5.0  6.0 \n 7.0  8.0  9.0 \n";

	     assertEquals(output, outContent.toString());
	}
	
	@Test
	public void testPrint2() throws IOException {
	    StringWriter a = new StringWriter();
	    PrintWriter b = new PrintWriter("test.txt", "UTF-8");
	    initMatrix.print(b, 2, 1);
	    a.write("1.0 2.0 3.0 \n4.0 5.0 6.0 \n7.0 8.0 9.0 \n");
	    assertEquals(a.toString(), new String(Files.readAllBytes(Paths.get("test.txt"))));
	}
	
	@Test
	public void testPrint3() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testPrint4() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testRead() {
		fail("Not yet implemented");
	}
}
