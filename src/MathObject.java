import java.io.Serializable;

public abstract class MathObject implements Serializable {

	/**
	 * serial uid
	 */
	private static final long serialVersionUID = 3248425995251545659L;

	/**
	 * @see MathScalar multiply()
	 */
	public abstract MathObject multiply(MathScalar m1);
	
	
	/**
	 * @see MathMatrix #multiply()
	 */
	public abstract MathObject multiply(MathMatrix m1)throws MathException;
	
	public abstract MathObject add(MathScalar m1);
	public abstract MathObject add(MathMatrix m1)throws MathException;	
	
}


