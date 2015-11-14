
class MathScalar extends MathObject {

	/**
	 * serial uid
	 */
	private static final long serialVersionUID = 6773984276446794298L;
	
	public double value;
	
	public MathScalar(double setval){	
		
		super();
		
		value = setval;
		
	}
	
	/**
	 * Multiplies a MathObject, scalar or matrix, by a MathScalar.
	 * @param m1 a MathScalar to multiply the MathObject ("this") by.
	 */
	@Override
	public MathObject multiply (MathScalar m1){
		
		MathScalar r = new MathScalar (this.value*m1.value);
		
		return r;
	}
	/**
	 * Calls MathMatrix's multiply method and multiplies the MathMatrix by this, the MathObject.
	 * @param m1 MathMatrix which multiplies the MathObject
	 */
	@Override
	public MathObject multiply(MathMatrix m1) {
		// TODO Auto-generated method stub
		return m1.multiply(this);
	}
	
	public MathObject add(MathMatrix m1) {

		double[][] m2 = new double[m1.rows][m1.columns];

		for (int i = 0; i < m1.rows; i++) {
			for (int j = 0; j < m1.columns; j++) {

				m2[i][j] = m1.content[i][j] + this.value;

			}
		}

		MathMatrix m3 = new MathMatrix(m2);
		return m3;
	}
	
	public MathObject add(MathScalar m1) {

		MathScalar m3 = new MathScalar(this.value + m1.value);
		return m3;
	}
		
	
}