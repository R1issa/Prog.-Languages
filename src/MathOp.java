
public class MathOp {
	/**
	 *  Method to multiply two MathObjects
	 * @param m1 First MathObject to multiply. Can be a matrix or a scalar
	 * @param m2 Second MathObject to multiply. Can be a matrix or a scalar
	 * @return m3 the MathObject result
	 * @throws MathException
	 */
	public static MathObject multiply(MathObject m1, MathObject m2)throws MathException{
		
		MathObject m3;
		
		if(m2 instanceof MathMatrix ){
			
			MathMatrix m4;
			m4 = (MathMatrix) m2;
			m3 = m1.multiply(m4);
			
		}
		
		else{
			MathScalar m4;
			m4 = (MathScalar) m2;
			m3 = m1.multiply(m4);
			
		}
		
		return m3;
	}
	
	public static MathObject add(MathObject m1, MathObject m2)throws MathException{
		
		MathObject m3;
		
		if(m2 instanceof MathMatrix){
			
			MathMatrix m5 = (MathMatrix) m2;
			m3 = m1.add(m5);

		}
		else {
			
			MathScalar m5 = (MathScalar) m2;
			m3 = m1.add(m5);
		}

		return m3;	
	}
	
	
	public static MathObject u_minus(MathObject m1){
		
		MathScalar m2 = new MathScalar(-1);
		return m1.multiply(m2);
		
	}
	
}
