import java.util.Vector;

class MathMatrix extends MathObject {

	/**
	 * serial uid
	 */
	private static final long serialVersionUID = -8727652976525026769L;
	
	public int rows;
	public int columns;
	double[][] content;

	/**
	 * Initialization of an instance of MathMatrix. The rows and columns 
	 * can be given as an input when setting the double arrays dimensions.
	 * @param m a 2D double array
	 */
	public MathMatrix(double[][] m) {
		super();
		
		rows = (int) m.length;
		columns = (int) m[0].length;
		
		content = new double [rows][columns];
		
	    for (int r = 0; r < m.length; r++) {
	        content[r] = m[r].clone();
	    }


	}
	
	public MathMatrix(Vector<Vector<MathObject>> m) {
		super();
		rows = (int) m.size();
		columns = (int) (m.get(0)).size();
		
		content = new double [rows][columns];
		
	    for (int r = 0; r < rows; r++) {
	    	for(int s = 0; s <columns; s++){
	    		MathScalar i = (MathScalar) (m.get(r)).get(s);
	    		content[r][s] = (double) i.value;
	    	}
	    }
	}
	

	/** Multiplies this, a MathObject can be a scalar or a MathMatrix, by a 
	 * MathMatrix. The multiplication algorithm is the naive one which runs in
	 * O(n^3). 
	 * @throws MathException in case the dimensions of the matrices do not agree
	 * @param m1 the matrix by which we multiply the MathObject ("this").
	 * @return the resulting matrix.
	 */

	@Override
	public MathObject multiply(MathMatrix m1) throws MathException{

		if (this.columns != m1.rows) {
			throw new MathException("Matrice dimensions incompatible");
		}

		int newrow = this.rows;
		int oldcol = this.columns;
		int newcol = m1.columns;

		double[][] m2 = new double[newrow][newcol];

		for (int i = 0; i < newrow; i++) {
			for (int j = 0; j < newcol; j++) {
				for (int k = 0; k < oldcol; k++) {

					m2[i][j] += this.content[i][k] * m1.content[k][j];

				}
			}
		}

		MathMatrix m3 = new MathMatrix(m2);
		return m3;

	}

	/** Multiplies this by a scalar m1. 
	 * @param m1 the scalar to multiply this with.
	 * @return The resulting matrix.
	 */

	public MathObject multiply(MathScalar m1) {

		double[][] m2 = new double[this.rows][this.columns];

		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {

				m2[i][j] = m1.value * this.content[i][j];

			}
		}

		MathMatrix m3 = new MathMatrix(m2);
		return m3;

	}
	
	public MathObject add(MathMatrix m1) throws MathException {

		double[][] m2 = new double[this.rows][this.columns];
		
		if (this.rows != m1.rows && this.columns != m1.columns) {
			throw new MathException("Matrice dimensions incompatible");
		}

		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {

				m2[i][j] = m1.content[i][j] + this.content[i][j];

			}
		}

		MathMatrix m3 = new MathMatrix(m2);
		return m3;
	}
	
	public MathObject add(MathScalar m1) {

		double[][] m2 = new double[this.rows][this.columns];

		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {

				m2[i][j] = m1.value + this.content[i][j];

			}
		}

		MathMatrix m3 = new MathMatrix(m2);
		return m3;
	}
	


}
