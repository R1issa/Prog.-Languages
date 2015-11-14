

public class MathException extends Exception{
	
	/**
	 * serial uid.
	 */
	private static final long serialVersionUID = -377715669156926801L;
	
	/**
	 * Instance of MathException 
	 * @param message string which is that within the exception
	 * @author Rawane
	 *
	 */
    public MathException(String message){
        super(message);
    }
    /**
     * Override toString method to return message
     * @return string 
     * @see Exception #getMessage()
     */
    
    @Override
    public String toString() {
        return getMessage();
    }
 
}
