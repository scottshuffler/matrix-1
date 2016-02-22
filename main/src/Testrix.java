import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import static org.junit.Assert.*;

/**
 * Testrix class
 * Team SASS
 * Scott Shuffler, Zach Andrews, Chris Smith, Devin Sink
 * 2.5.2016
 */
public class Testrix {

    private double[][] initArray = new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    private Matrix initMatrix = new Matrix(initArray);

    @Test
    /**
     * Test for the constructor that takes in two arguments
     */
    public void testConstructor() {
        Matrix m = new Matrix(3, 3);
        assertArrayEquals("Not initialized correctly.", new double[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}, m.getArray());
    }

    @Test
    /**
     * Test for the constructor that takes in three arguments
     */
    public void testConstructor2() {
        Matrix m = new Matrix(3, 3, 4);
        assertArrayEquals("Not initialized correctly.", new double[][]{{4, 4, 4}, {4, 4, 4}, {4, 4, 4}}, m.getArray());
    }

    @Test
    /**
     * Test for the constructor that takes in an array
     */
    public void testConstructor3() {
        Matrix m = new Matrix(initArray);
        assertArrayEquals("Not initialized correctly.", new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, m.getArray());
    }

    @Test
    /**
     * Test for the constructor that takes in an array and width / height
     */
    public void testConstructor4() {
        Matrix m = new Matrix(initArray, 3, 3);
        assertArrayEquals("Not initialized correctly.", new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, m.getArray());
    }

    @Test
    /**
     * Test for the constructor that takes in a single array and the number of rows
     */
    public void testConstructor5() {
        double[] arr = {1., 2., 3., 4., 5., 6., 7., 8.};
        Matrix m = new Matrix(arr, 4);
        assertArrayEquals("Not initialized correctly.", new double[][]{{1., 5.}, {2., 6.}, {3., 7.}, {4., 8.}}, m.getArray());
    }

    @Test
    /**
     * Test for ConstructWithCopy
     */
    public void testConstructWithCopy() {
        assertArrayEquals("Construct with copy matrix not equal.", initMatrix.getArray(), Matrix.constructWithCopy(initArray).getArray());
    }

    @Test
    /**
     * Test for copy
     */
    public void testCopy() {
        assertArrayEquals("Copied matrix not equal.", initMatrix.getArray(), initMatrix.copy().getArray());
    }

    @Test
    /**
     * Test for clone
     */
    public void testClone() {
        assertArrayEquals("Cloned matrix not equal.", initMatrix.getArray(), (double[][]) initMatrix.clone());
    }

    @Test
    /**
     * Test for getArray
     */
    public void testGetArray() {
        assertArrayEquals("Get array failed", new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, initMatrix.getArray());
    }

    @Test
    /**
     * Test for getArrayCopy
     */
    public void testGetArrayCopy() {
        assertArrayEquals("Copied array not equal.", initMatrix.getArray(), initMatrix.copy().getArray());
    }

    @Test
    /**
     * Test for getColumnPackedCopy
     */
    public void testGetColumnPackedCopy() {
        double[] actual = {1, 4, 7, 2, 5, 8, 3, 6, 9};
        double[] compare = initMatrix.getColumnPackedCopy();
        assertArrayEquals("Copied array not equal", actual, compare, 0);
    }

    @Test
    /**
     * Test for getRowPackedCopy
     */
    public void testGetRowPackedCopy() {
        double[] actual = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        double[] compare = initMatrix.getRowPackedCopy();
        assertArrayEquals("Copied array not equal", actual, compare, 0);
    }

    @Test
    /**
     * Test for getRowDimensions
     */
    public void testGetRowDimension() {
        assertEquals("Get row dim. failed", 3, initMatrix.getRowDimension());
    }

    @Test
    /**
     * Test for getColumnDimensions
     */
    public void testGetColumnDimension() {
        Matrix m = Matrix.random(3, 3);
        assertEquals(3.0, m.getColumnDimension(), 0.0);
    }

    @Test
    /**
     * Test for get
     */
    public void testGet() {
        assertEquals("Get failed", 3, initMatrix.get(0, 2), 0);
    }

    @Test
    /**
     * Test for getMatrix that takes in four indices
     */
    public void testGetMatrix() {
        assertArrayEquals("Get matrix with two index arrays failed",
                new double[][]{{1, 2}, {4, 5}},
                initMatrix.getMatrix(0, 1, 0, 1).getArrayCopy());
    }

    @Test
    /**
     * Test for getmatrix that takes in two arrays of indices
     */
    public void testGetMatrix2() {
        assertArrayEquals("Get matrix with two index arrays failed",
                new double[][]{{1, 3}, {4, 6}},
                initMatrix.getMatrix(new int[]{0, 1}, new int[]{0, 2}).getArrayCopy());
    }

    @Test
    /**
     * Test for getMatrix that takes in two indices and an array of row indices
     */
    public void testGetMatrix3() {
        assertArrayEquals("Get matrix with two index arrays failed",
                new double[][]{{1, 3}, {4, 6}},
                initMatrix.getMatrix(0, 1, new int[]{0, 2}).getArrayCopy());
    }

    @Test
    /**
     * Test for getMatrix that takes in an array of row indices and two column indices
     */
    public void testGetMatrix4() {
        assertArrayEquals("Get matrix with two index arrays failed",
                new double[][]{{1, 2}, {7, 8}},
                initMatrix.getMatrix(new int[]{0, 2}, 0, 1).getArrayCopy());
    }

    @Test
    /**
     * Test for set
     */
    public void testSet() {
        initMatrix.set(0, 0, 6);
        assertEquals("Set failed", 6, initMatrix.get(0, 0), 0);
    }

    @Test
    /**
     * Test for setMatrix that takes in four indices
     */
    public void testSetMatrix() {
        Matrix tempMatrix = new Matrix(3, 3);
        tempMatrix.setMatrix(0, 2, 0, 2, initMatrix);
        assertArrayEquals("Set Matrix failed", initMatrix.getArray(), tempMatrix.getArray());
    }

    @Test
    /**
     * Test for setMatrix that takes in two single arrays of indices
     */
    public void testSetMatrix2() {
        Matrix tempMatrix = new Matrix(3, 3);
        tempMatrix.setMatrix(new int[]{0, 1, 2}, new int[]{0, 1, 2}, initMatrix);
        assertArrayEquals("Set Matrix failed", initMatrix.getArray(), tempMatrix.getArray());
    }

    @Test
    /**
     * Test for setMatrix that takes in a single array of row indices, two column indices and a matrix
     */
    public void testSetMatrix3() {
        Matrix tempMatrix = new Matrix(3, 3);
        tempMatrix.setMatrix(new int[]{0, 1, 2}, 0, 2, initMatrix);
        assertArrayEquals("Set Matrix failed", initMatrix.getArray(), tempMatrix.getArray());
    }

    @Test
    /**
     * Test for setMatrix that takes in two row indices, a column array of indices and a matrix
     */
    public void testSetMatrix4() {
        Matrix tempMatrix = new Matrix(3, 3);
        tempMatrix.setMatrix(new int[]{0, 1, 2}, new int[]{0, 1, 2}, initMatrix);
        assertArrayEquals("Set Matrix failed", initMatrix.getArray(), tempMatrix.getArray());
    }

    @Test
    /**
     * Test for transpose
     */
    public void testTranspose() {
        Matrix tempMatrix = new Matrix(initMatrix.getArrayCopy());
        tempMatrix = tempMatrix.transpose();
        double[][] correctArray = new double[][]{{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        assertArrayEquals("Set Matrix failed", correctArray, tempMatrix.getArray());
    }

    @Test
    /**
     * Test for norm1
     */
    public void testNorm1() {
        double norm = initMatrix.norm1();
        assertEquals("Norm1 failed.", 18, norm, 0);
    }

    @Test
    /**
     * Test for normInf
     */
    public void testNormInf() {
        double norm = initMatrix.normInf();
        assertEquals("Norm Inf failed", 24, norm, 0);
    }

    @Test
    /**
     * Test for normF
     */
    public void testNormF() {
        Matrix m = new Matrix(3, 3, 3.0);
        double normf = m.normF();
        assertEquals(9.0, normf, 0);
    }

    @Test
    /**
     * Test for Uminus
     */
    public void testUminus() {
        Matrix m = new Matrix(initMatrix.getArrayCopy());
        m = m.uminus();
        for (int i = 0; i < m.getRowDimension(); i++) {
            for (int j = 0; j < m.getColumnDimension(); j++) {
                assertEquals("Point " + i + " " + j + " not equal.",
                        -m.get(i, j), initMatrix.get(i, j), 0);
            }
        }
    }

    @Test
    /**
     * Test for plus
     */
    public void testPlus() {
        Matrix m = new Matrix(initMatrix.getArray());
        Matrix s = new Matrix(new double[][]{{10, 11, 12}, {13, 14, 15}, {16, 17, 18}});
        m = m.plus(s);
        for (int i = 0; i < m.getRowDimension(); i++) {
            for (int j = 0; j < m.getColumnDimension(); j++) {
                assertEquals("Point " + i + " " + j + " not equal.",
                        m.get(i, j), initMatrix.get(i, j) + s.get(i, j), 0);
            }
        }
    }

    @Test
    /**
     * Test for plusEquals
     */
    public void testPlusEquals() {
        Matrix m = new Matrix(initMatrix.getArray());
        Matrix p = new Matrix(new double[][]{{10, 11, 12}, {13, 14, 15}, {16, 17, 18}});
        m = m.plusEquals(p);
        for (int i = 0; i < m.getRowDimension(); i++) {
            for (int j = 0; j < m.getColumnDimension(); j++) {
                assertEquals("Point " + i + " " + j + " not equal.",
                        m.get(i, j), p.get(i, j) + initMatrix.get(i, j), 0);
            }
        }
    }

    @Test
    /**
     * Test for minus
     */
    public void testMinus() {
        Matrix m = new Matrix(initMatrix.getArray());
        Matrix s = new Matrix(new double[][]{{10, 11, 12}, {13, 14, 15}, {16, 17, 18}});
        m = m.minus(s);
        for (int i = 0; i < m.getRowDimension(); i++) {
            for (int j = 0; j < m.getColumnDimension(); j++) {
                assertEquals("Point " + i + " " + j + " not equal.",
                        m.get(i, j), initMatrix.get(i, j) - s.get(i, j), 0);
            }
        }
    }

    @Test
    /**
     * Test for minusEquals
     */
    public void testMinusEquals() {
        Matrix m = new Matrix(initMatrix.getArray());
        Matrix p = new Matrix(new double[][]{{10, 11, 12}, {13, 14, 15}, {16, 17, 18}});
        m = m.minusEquals(p);
        for (int i = 0; i < m.getRowDimension(); i++) {
            for (int j = 0; j < m.getColumnDimension(); j++) {
                assertEquals("Point " + i + " " + j + " not equal.",
                        m.get(i, j), initMatrix.get(i, j) - p.get(i, j), 0);
            }
        }
    }

    @Test
    /**
     * Test for arrayTimes
     */
    public void testArrayTimes() {
        Matrix m = new Matrix(initMatrix.getArray());
        Matrix a = m.arrayTimes(initMatrix);
        for (int i = 0; i < m.getRowDimension(); i++) {
            for (int j = 0; j < m.getColumnDimension(); j++) {
                assertEquals("Point " + i + " " + j + " not equal.",
                        a.get(i, j), m.get(i, j) * initMatrix.get(i, j), 0);
            }
        }
    }

    @Test
    /**
     * Test for arrayTimesEquals
     */
    public void testArrayTimesEquals() {
        Matrix m = new Matrix(initMatrix.getArray());
        Matrix a = m.copy();
        m.arrayTimesEquals(initMatrix);
        for (int i = 0; i < m.getRowDimension(); i++) {
            for (int j = 0; j < m.getColumnDimension(); j++) {
                assertEquals("Point " + i + " " + j + " not equal.",
                        m.get(i, j), a.get(i, j) * initMatrix.get(i, j), 0);
            }
        }
    }

    @Test
    /**
     * Test for arrayRightDivide
     */
    public void testArrayRightDivide() {
        Matrix m = new Matrix(initMatrix.getArray());
        Matrix a = m.arrayRightDivide(initMatrix);
        for (int i = 0; i < m.getRowDimension(); i++) {
            for (int j = 0; j < m.getColumnDimension(); j++) {
                assertEquals("Point " + i + " " + j + " not equal.",
                        a.get(i, j), m.get(i, j) / initMatrix.get(i, j), 0);
            }
        }
    }

    @Test
    /**
     * Test for arrayRightDivideEquals
     */
    public void testArrayRightDivideEquals() {
        Matrix m = new Matrix(initMatrix.getArray());
        Matrix a = m.copy();
        a.arrayRightDivideEquals(initMatrix);
        for (int i = 0; i < m.getRowDimension(); i++) {
            for (int j = 0; j < m.getColumnDimension(); j++) {
                assertEquals("Point " + i + " " + j + " not equal.",
                        a.get(i, j), m.get(i, j) / initMatrix.get(i, j), 0);
            }
        }
    }

    @Test
    /**
     * Test for arrayLeftDivide
     */
    public void testArrayLeftDivide() {
        Matrix m = new Matrix(initMatrix.getArray());
        Matrix mCopy = m.copy();
        Matrix a = m.arrayLeftDivide(initMatrix);
        for (int i = 0; i < m.getRowDimension(); i++) {
            for (int j = 0; j < m.getColumnDimension(); j++) {
                assertEquals("Point " + i + " " + j + " not equal.",
                        a.get(i, j), mCopy.get(i, j) / m.get(i, j), 0);
            }
        }
    }

    @Test
    /**
     * Test for arrayLeftDivideEquals
     */
    public void testArrayLeftDivideEquals() {
        Matrix m = new Matrix(initMatrix.getArray());
        Matrix a = m.copy();
        a.arrayLeftDivideEquals(initMatrix);
        for (int i = 0; i < m.getRowDimension(); i++) {
            for (int j = 0; j < m.getColumnDimension(); j++) {
                assertEquals("Point " + i + " " + j + " not equal.",
                        m.get(i, j), initMatrix.get(i, j), 0 / a.get(i, j));
            }
        }
    }

    @Test
    /**
     * Test for times
     */
    public void testTimes() {
        Matrix m = new Matrix(initMatrix.getArray());
        Matrix a = m.times(4);
        for (int i = 0; i < m.getRowDimension(); i++) {
            for (int j = 0; j < m.getColumnDimension(); j++) {
                assertEquals("Point " + i + " " + j + " not equal.",
                        a.get(i, j), 4 * initMatrix.get(i, j), 0);
            }
        }
    }

    @Test
    /**
     * Test for timesEquals
     */
    public void testTimesEquals() {
        Matrix m = new Matrix(initMatrix.getArray());
        m = m.timesEquals(4);
        for (int i = 0; i < m.getRowDimension(); i++) {
            for (int j = 0; j < m.getColumnDimension(); j++) {
                assertEquals("Point " + i + " " + j + " not equal.",
                        m.get(i, j), 4 * initMatrix.get(i, j), 0);
            }
        }
    }

    @Test
    /**
     * Test for times2
     */
    public void testTimes2() {
        Matrix m = new Matrix(initMatrix.getArray());
        Matrix a = m.copy();
        m = m.arrayTimes(initMatrix);
        for (int i = 0; i < m.getRowDimension(); i++) {
            for (int j = 0; j < m.getColumnDimension(); j++) {
                assertEquals("Point " + i + " " + j + " not equal.",
                        m.get(i, j), a.get(i, j) * initMatrix.get(i, j), 0);
            }
        }
    }

    @Test
    /**
     * Test for trace
     */
    public void testTrace() {
        Matrix m = new Matrix(3, 3, 3.0);
        double trace = m.trace();
        assertEquals(9.0, trace, 0);
    }

    @Test
    /**
     * Test for random
     */
    public void testRandom() {
        Matrix m = Matrix.random(3, 3);
        for (int i = 0; i < m.getRowDimension(); i++) {
            for (int j = 0; j < m.getColumnDimension(); j++) {
                assertTrue("Point " + i + " " + j + " not equal.",
                        0 <= m.get(i, j) && m.get(i, j) <= 1);

            }
        }
    }

    @Test
    /**
     * Test for identity
     */
    public void testIdentity() {
        Matrix m = Matrix.identity(3, 3);
        for (int i = 0; i < m.getRowDimension(); i++) {
            for (int j = 0; j < m.getColumnDimension(); j++) {
                if (i == j) {
                    assertEquals(1.0, 1.0, 0.0);
                }
                assertEquals(0.0, 0.0, 0.0);
            }
        }
    }

    @Test
    /**
     * Test for print that takes in a column width and the number of digits after the decimal
     */
    public void testPrint() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        initMatrix.print(3, 1);
        String output = " 1.0  2.0  3.0 \n 4.0  5.0  6.0 \n 7.0  8.0  9.0 \n";

        assertEquals(output, outContent.toString());
    }

    @Test
    /**
     * Test for that takes in an output, column width, and the number of digits after the decimal
     */
    public void testPrint2() throws IOException {
        StringWriter a = new StringWriter();
        PrintWriter b = new PrintWriter("test.txt", "UTF-8");
        initMatrix.print(b, 2, 1);
        a.write("1.0 2.0 3.0 \n4.0 5.0 6.0 \n7.0 8.0 9.0 \n");
        assertEquals(a.toString(), new String(Files.readAllBytes(Paths.get("test.txt"))));
    }

    @Test
    /**
     * Test for that takes in a format object and the width of each column
     */
    public void testPrint3() throws IOException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        NumberFormat d = new DecimalFormat("#.00");
        initMatrix.print(d, 4);
        String output = " 1.00  2.00  3.00 \n 4.00  5.00  6.00 \n 7.00  8.00  9.00 \n";

        assertEquals(output, outContent.toString());
    }

    @Test
    /**
     * Test for that takes in an output object, format object, and the width of each column
     */
    public void testPrint4() throws IOException {
        StringWriter a = new StringWriter();
        PrintWriter b = new PrintWriter("test.txt", "UTF-8");
        NumberFormat d = new DecimalFormat("#.00");
        initMatrix.print(b, d, 2);
        a.write("1.00 2.00 3.00 \n4.00 5.00 6.00 \n7.00 8.00 9.00 \n");
        assertEquals(a.toString(), new String(Files.readAllBytes(Paths.get("test.txt"))));
    }

    @Test
    /**
     * Test for read
     */
    public void testRead() throws IOException {
        PrintWriter b = new PrintWriter("test.txt", "UTF-8");
        NumberFormat d = new DecimalFormat("#.00");
        initMatrix.print(b, d, 2);
        Matrix actual = Matrix.read(new BufferedReader(new FileReader("test.txt")));
        assertArrayEquals(initMatrix.getArrayCopy(), actual.getArray());
    }
}
