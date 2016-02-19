import java.util.Random;

/**
 * Matrix class
 * Team SASS
 * Scott Shuffler, Zach Andrews, Chris Smith, Devin Sink
 * 2.5.2016
 */
public class Matrix {

	private double[][] matrix;
    public static void main(String[] args) {
        System.out.println("Hello");
    }

    /**
     * Constructors
     */

    /**
     * @chrissmith
     * Constructor that initializes an m*n matrix with all 0's.
     * @param m number of rows
     * @param n number of columns
     */
    public Matrix(int m, int n) {
    	matrix = new double[m][n];
    	for(int i = 0; i < m; i++)
    	{
    		for(int j = 0; j < n; j++)
    		{
    			matrix[i][j] = 0;
    		}
    	}
    }

    /**
    *
    * @param m
    * @param n
    * @param s
    */
   public Matrix(int m, int n, double s) {
   	matrix = new double[m][n];
   	for(int i = 0; i < m; i++)
   	{
   		for(int j = 0; j < n; j++)
   		{
   			matrix[i][j] = s;
   		}
   	}
   }

   /**
    *
    * @param A
    */
   public Matrix(double[][] A) {
   	int n = A.length;
   	int m = A[0].length;
   	matrix = new double[m][n];
   	for(int i = 0; i < m; i++)
   	{
   		for(int j = 0; j < n; j++)
   		{
   			matrix[i][j] = A[i][j];
   		}
   	}
   	
   }

    /**
     * scottshuffler
     * Matrix constructor that takes in three variables and sets the matrix field based of the parameters
     * @param A - Double array provided
     * @param m - m distance they need copied
     * @param n - n distance they need copied
     */
    public Matrix(double[][] A, int m, int n) {
        matrix = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = A[i][j];
            }
        }

    }

    public Matrix(double[] vals, int m) {
        matrix = new double[m][vals.length / m];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[j][i] = vals[j];
            }
        }
    }

    /**
     * Functions
     */

    /**
     * @zachandrews
     * Function that makes a copy of the 2D array A.
     * @param A - 2D array to copy
     * @return new Matrix object containing a copy of 2D array A
     */
    public static Matrix constructWithCopy(double[][] A) {
        return new Matrix(A);
    }

    /**
     * @chrissmith
     * Function that returns a new Matrix object with a copy of
     * the matrix field
     * @return new Matrix object with a copy of the 2D array
     */
    public Matrix copy() {
        return new Matrix(getArrayCopy());
    }

    /**
     * Clones the matrix field
     * @return clone of the matrix field
     */
    @Override
    public java.lang.Object clone() {
        return matrix.clone();
    }

    /**
     * @scottshuffler
     *
     * @return
     */
    public double[][] getArray() {
        return matrix;
    }

    /**
     * @zachandrews
     * Function that makes a copy of the internal 2-d array.
     * @return new 2D array coppy of matrix elements
     */
    public double[][] getArrayCopy() {
        double[][] internArray = new double[getRowDimension()][getColumnDimension()];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                internArray[i][j] = matrix[i][j];
            }
        }
        return internArray;
    }

    /**
     * @zachandrews
     * Function that creates a 1D column-packed copy of internal array
     * @return matrix elements packed in a 1D array by columns
     */
    public double[] getColumnPackedCopy() {
        double[] colPacked = new double[getRowDimension() * getColumnDimension()];
        int counter = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                colPacked[counter++] = matrix[j][i];
            }
        }
        return colPacked;
    }

    /**
     *
     * @return
     */
    public double[] getRowPackedCopy() {
        double[] rowpacked = new double[getRowDimension() * getColumnDimension()];
        for (int i = 0; i > matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                rowpacked[j] = matrix[i][j];
            }
        }
        return rowpacked;
    }

    /**
     * @chrissmith
     * Function that gets the number of rows in the matrix.
     * @return the number of rows
     */
    public int getRowDimension() {
        return matrix.length;
    }

    /**
     *
     * @return
     */
    //do this
    public int getColumnDimension() {
        return matrix.length;
    }

    /**
     *
     * @param i
     * @param j
     * @return
     */
    //do this
    public double get(int i, int j) {
        return matrix[i][j];
    }

    /**
     *
     * @param i0
     * @param i1
     * @param j0
     * @param j1
     * @return
     */
    public Matrix getMatrix(int i0, int i1, int j0, int j1) {
        double[][] submatrix = new double[i1-i0+1][j1-j0+1];
        for(int i = i0; i <= i1; i++)
        {
            for(int j = j0; j <= j1; j++)
            {
                submatrix[i][j] = matrix[i][j];
            }
        }
        return new Matrix(submatrix);
    }

    /**
     * @chrissmith
     * Function that gets a submatrix of the current matrix.
     * @param r number of rows in array form
     * @param c number of columns in array form
     * @return returns a submatrix of the current matrix
     */ 
    public Matrix getMatrix(int[] r, int[] c) {
    	double[][] submatrix = new double[r.length][c.length];
        for(int i = 0; i < r.length; i++)
        {
        	for(int j = 0; j < c.length; j++)
        	{
        		submatrix[i][j] = matrix[r[i]][c[j]];
        	}
        }
    	return new Matrix(submatrix);
    }

    /**
     * @chrissmith
     * Function that gets all columns from row i0 to row i1.
     * @param i0 first row index desired
     * @param i1 last row index desired
     * @param c the columns desired
     * @return a submatrix of the current matrix
     */
    public Matrix getMatrix(int i0, int i1, int[] c) {
        double[][] submatrix = new double[i1-i0+1][c.length];
        for(int i = i0; i <= i1; i++)
        {
        	for(int j = 0; j < c.length; j++)
        	{
        		submatrix[i][j] = matrix[i][c[j]];
        	}
        }
    	return new Matrix(submatrix);
    }

    /**
     *
     * @param r - Array of indicies
     * @param j0 - Initial column index
     * @param j1 - Final column index
     * @return New array
     */
    public Matrix getMatrix(int[] r, int j0, int j1) {

        double[][] submatrix = new double[r.length][j1-j0+1];
        for(int i = 0; i < r.length; i++)
        {
            for(int j = j0; j <= j1; j++)
            {
                submatrix[i][j] = matrix[r[i]][j];
            }
        }
        return new Matrix(submatrix);
    }

    /**
     * @chrissmith
     * Function that sets a single element in the matrix
     * @param i row index
     * @param j column index
     * @param s new item in matrix
     */
    public void set(int i, int j, double s) {
    	matrix[i][j] = s;
    }

    /**
     * @chrissmith
     * Function that sets the matrix field to the one in X.
     * @param i0 first row index
     * @param i1 last row index
     * @param j0 first column index
     * @param j1 last column index
     * @param X Matrix object used to set the field
     */
    //do this
    public void setMatrix(int i0, int i1, int j0, int j1, Matrix X) {
    	for(int i = i0; i <= i1; i++)
    	{
    		for(int j = j0; j <= j1; j++)
    		{
    			matrix[i][j] = X.matrix[i][j];
    		}
    	}
    }

    /**
     * scottshuffler
     *
     * @param r - One dimensional array
     * @param c - One dimensional array
     * @param X - Matrix variable
     */
    public void setMatrix(int[] r, int[] c, Matrix X) {
        for (int i = 0; i < r.length; i++) {
            for (int j = 0; j < c.length; j++) {
                matrix[r[i]][c[j]] = X.matrix[r[i]][c[j]];
            }
        }
    }

    /**
     *
     * @param r
     * @param j0
     * @param j1
     * @param X
     */
    public void setMatrix(int[] r, int j0, int j1, Matrix X) {

    }

    /**
     * @zachandrews
     * Function that sets a submatrix.
     * @param i0 - initial row index
     * @param i1 - final row index
     * @param c - array of column indices
     * @param X - matrix object used to set the field
     */
    public void setMatrix(int i0, int i1, int[] c, Matrix X) {
        for (int i = i0; i <= i1; i++) {
            for (int j = 0; j < c.length; j++) {
                matrix[i][j] = X.matrix[i][c[j]];
            }
        }
    }

    /**
     * Transposes the matrix
     * @return - the new matrix
     */
    public Matrix transpose() {
        // transpose in-place
        Matrix ma = new Matrix(matrix);
        for (int i = 0; i < ma.matrix.length; i++) {
            for (int j = i+1; j < ma.matrix[i].length; j++) {
                Double temp = ma.matrix[i][j];
                ma.matrix[i][j] = ma.matrix[j][i];
                ma.matrix[j][i] = temp;
            }
        }
        return ma;
    }

    /**
     * @zachandrews
     * Function that provides the maximum column sum.
     * @return the maximum column sum
     */
    public double norm1() {
        double sum = 0.0;
        double max = 0.0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sum += matrix[j][i];
            }
            if (sum >= max) {
                max = sum;
            }
        }
        return max;
    }

    /**
     * @zachandrews
     * Function that provides the maximum row sum.
     * @return the maximum row sum
     */
    public double normInf() {
        double sum = 0.0;
        double max = 0.0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sum += matrix[i][j];
            }
            if (sum >= max) {
                max = sum;
            }
        }
        return max;
    }

    /**
     *
     * @return
     */
    //do this
    public double normF() {
    	double sum = 0.0;
    	for(int i = 0; i < matrix.length; i++)
    	{
    		for(int j = 0; j < matrix[i].length; j++)
    		{
    			sum += (matrix[i][j] * matrix[i][j]);
    		}
    	}
        return Math.sqrt(sum);
    }

    /**
     * @chrissmith
     * Function that negates each element.
     * @return Matrix that is the reverse-signed version of the original
     */
    public Matrix uminus() {
    	double[][] newMatrix = new double[getRowDimension()][getColumnDimension()];
    	for(int i = 0; i < newMatrix.length; i++)
    	{
    		for(int j = 0; j < newMatrix[i].length; j++)
    		{
    			newMatrix[i][j] = -matrix[i][j];
    		}
    	}
    	return new Matrix(newMatrix);
    }

    /**
     *
     * @param B
     * @return
     */
    //do this
    public Matrix plus(Matrix B) {
    	Matrix newMatrix = new Matrix(B.matrix.length, B.matrix[0].length);
        for (int i = 0; i < B.matrix.length; i++)
        {
        	for(int j = 0; j < B.matrix[i].length; j++)
        	{
        		newMatrix.matrix[i][j] = (matrix[i][j] + B.matrix[i][j]);
        	}
        }
        return newMatrix;
    }

    /**
     * @zachandrews
     * Function that addition on a matrix A by adding a matrix B
     * @param B - matrix being added to A
     * @return matrix A = A + B
     */
    public Matrix plusEquals(Matrix B) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] += B.matrix[i][j];
            }
        }
        return new Matrix(matrix);
    }

    /**
     * @zachandrews
     * Function that subtracts matrix B from matrix A.
     * @param B - matrix being subtracted from A
     * @return - new Matrix containing the result of A - B
     */
    public Matrix minus(Matrix B) {
        double[][] newMatrix = new double[getRowDimension()][getColumnDimension()];
        for (int i = 0; i < newMatrix.length; i++) {
            for (int j = 0; j < newMatrix[i].length; j++) {
                newMatrix[i][j] = matrix[i][j] - B.matrix[i][j];
            }
        }
        return new Matrix(newMatrix);
    }

    /**
     *
     * @param B
     * @return
     */
    //do this
    public Matrix minusEquals(Matrix B) {
        for(int i = 0; i < B.matrix.length; i++)
        {
        	for(int j = 0; j < B.matrix[i].length; j++)
        	{
        		matrix[i][j] -= B.matrix[i][j];
        	}
        }
    	return new Matrix(matrix);
    }

    /**
     * @chrissmith
     * Function that multiplies the current matrix with the parameter B.
     * This multiplication is element-by-element.
     * @param B Matrix that is used to multiply the current matrix by
     * @return new Matrix object with the resultant matrix
     */
    public Matrix arrayTimes(Matrix B) {
    	double[][] newMatrix = new double[getRowDimension()][getColumnDimension()];
    	for(int i = 0; i < newMatrix.length; i++)
    	{
    		for(int j = 0; j < newMatrix[i].length; j++)
    		{
    			newMatrix[i][j] = matrix[i][j] * B.matrix[i][j];
    		}
    	}
    	return new Matrix(newMatrix);
    }

    /**
     *
     * @param B
     * @return
     */
    //do this
    public Matrix arrayTimesEquals(Matrix B) {
    	for (int i = 0; i < B.matrix.length; i ++)
    	{
    		for(int j = 0; j < B.matrix[i].length; j++)
    		{
    			matrix[i][j] *= B.matrix[i][j];
    		}
    	}
        return new Matrix(matrix);
    }

    /**
     *
     * @param B
     * @return
     */
    public Matrix arrayRightDivide(Matrix B) {
        double[][] newMatrix = new double[getRowDimension()][getColumnDimension()];
        for(int i = 0; i < newMatrix.length; i++)
        {
            for(int j = 0; j < newMatrix[i].length; j++)
            {
                newMatrix[i][j] = matrix[i][j] / B.matrix[i][j];
            }
        }
        return new Matrix(newMatrix);
    }

    /**
     * @zachandrews
     * Function that performs element-by-element right division in place.
     * @param B - matrix used in the division of A = A / B
     * @return matrix that is the result of A = A / B
     */
    public Matrix arrayRightDivideEquals(Matrix B) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = matrix[i][j] / B.matrix[i][j];
            }
        }
        return new Matrix(matrix);
    }

    /**
     * @chrissmith
     * Function that left divides the current matrix with the parameter B.
     * This division is element-by-element.
     * @param B Matrix that is used to divide the current matrix by
     * @return new Matrix object with the resultant matrix
     */
    public Matrix arrayLeftDivide(Matrix B) {
    	double[][] newMatrix = new double[getRowDimension()][getColumnDimension()];
    	for(int i = 0; i < newMatrix.length; i++)
    	{
    		for(int j = 0; j < newMatrix[i].length; j++)
    		{
    			newMatrix[i][j] = B.matrix[i][j] / matrix[i][j];
    		}
    	}
    	return new Matrix(newMatrix);
    }

    /**
     *
     * @param B
     * @return
     */
    //do this
    public Matrix arrayLeftDivideEquals(Matrix B) {
    	for (int i = 0; i < B.matrix.length; i++)
    	{
    		for(int j = 0; j < B.matrix[i].length; j++)
    		{
    			matrix[i][j] /= B.matrix[i][j];
    		}
    	}
        return new Matrix(matrix);
    }

    /**
     * @zachandrews
     * Function that multiplies a matrix A by a scalar s.
     * @param s - scalar used for multiplication
     * @return - new Matrix object containing the results of the multiplication
     */
    public Matrix times(double s) {
        double[][] newMatrix = new double[getRowDimension()][getColumnDimension()];
        for (int i = 0; i < newMatrix.length; i++) {
            for (int j = 0; j < newMatrix[i].length; j++) {
                newMatrix[i][j] = matrix[i][j] * s;
            }
        }
        return new Matrix(newMatrix);
    }

    /**
     * @zachandrews
     * Function that multiplies a matrix A by a scalar s in place.
     * @param s - scalar used for multiplication
     * @return matrix containing the results of the multiplication
     */
    public Matrix timesEquals(double s) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] *= s;
            }
        }
        return new Matrix(matrix);
    }

    /**
     *
     * @param B
     * @return
     */
    //do this
    public Matrix times(Matrix B) {
    	//columns of first match rows of second
    	//matrix[0].length = B.length
        Matrix newMatrix = null;
    	if(matrix[0].length == B.matrix.length)
    	{
            newMatrix = new Matrix(matrix.length, B.matrix[0].length);
    		for(int i = 0; i < matrix.length; i++)
    		{
    			for(int j = 0; j < B.matrix[i].length; j++)
    			{
    				for(int k = 0; k < B.matrix.length; k++)
    				{
    					newMatrix.matrix[i][k] += matrix[i][k] * B.matrix[k][j];
    				}
    			}
    		}
    	}
    	return newMatrix;
    }

    /**
     *
     * @return
     */
    //do this
    public double trace() {
    	double diagonal = 0.0;
    	for(int i = 0; i < matrix.length; i++)
    	{
    		for(int j = 0; j < matrix[i].length; j++)
    		{
    			if(i == j)
    			{
    				diagonal += matrix[i][j];
    			}
    		}
    	}
        return diagonal;
    }

    /**
     * @chrissmith
     * Function that generates a Matrix full of uniformly
     * generated doubles.
     * @param m number of rows
     * @param n number of columns
     * @return Matrix object that contains uniformly distributed data
     */
    public static Matrix random(int m, int n) {
    	double[][] rMatrix = new double[m][n];
    	Random r = new Random();
    	for(int i = 0; i < m; i++)
    	{
    		for(int j = 0; j < n; j++)
    		{
    			rMatrix[i][j] = r.nextDouble();
    		}
    	}
    	return new Matrix(rMatrix);
    }

    /**
     *
     * @param m
     * @param n
     * @return
     */
    //do this
    public static Matrix identity(int m, int n) {
    	double[][] temp = new double[m][n];
    	for(int i = 0; i < temp.length; i ++)
    	{
    		for(int j = 0; j < temp[i].length; j++)
    		{
    			if(i == j)
    			{
    				temp[i][j] = 1;
    			}
    			else
    			{
    				temp[i][j] = 0;
    			}
    		}
    	}
    	Matrix identity = new Matrix(temp);
        return identity;
    }

    /**
     * @chrissmith
     * Function that prints out each item in the form x.y,
     * where w is the max number of digits in x and
     * d is the max number of digits in y.
     * @param w number of sig. digits before the decimal place
     * @param d number of sig. digits after the decimal place
     */
    public void print(int w, int d) {
    	for(int i = 0; i < getRowDimension(); i++)
    	{
    		for(int j = 0; j < getColumnDimension(); j++)
    		{
    			System.out.print(String.format("%w.df ", matrix[i][j]));
    		}
    	}
    }

    /**
     *
     * @param output
     * @param w
     * @param d
     */
    public void print(java.io.PrintWriter output, int w, int d) {
    	for(int i = 0; i < getRowDimension(); i++)
    	{
    		for(int j = 0; j < getColumnDimension(); j++)
    		{
    			output.print(String.format("%w.df ", matrix[i][j]));
    		}
    	}
    }

    /**
     *
     * @param format
     * @param width
     */
    public void print(java.text.NumberFormat format, int width) {
        
    }

    /**
     *
     * @param output
     * @param format
     * @param width
     */
    public void print(java.io.PrintWriter output, java.text.NumberFormat format, int width) {

    }

    /**
     *
     * @param input
     * @return
     * @throws java.io.IOException
     */
    public static Matrix read(java.io.BufferedReader input) throws java.io.IOException {

        input.mark(0);
        String temp = input.readLine();
        int colLen = temp.length() - temp.replaceAll(" ", "").length();
        int rowLen = 0;
        while(temp != null) {
            rowLen++;
            temp = input.readLine();
        }

        double[][] newMatrix = new double[rowLen][colLen];
        input.reset();
        temp = input.readLine();
        int i;
        int j = 0;
        while(temp != null) {
            String[] arr = temp.split(" ");
            for(i = 0; i < arr.length; i++) {
                newMatrix[j][i] = Double.parseDouble(arr[i]);
            }
            temp = input.readLine();
            j++;
        }
        return null;
    }
}