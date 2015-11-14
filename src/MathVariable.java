import java.io.Serializable;

public class MathVariable implements Serializable {
	
	/**
	 * serial uid
	 */
	private static final long serialVersionUID = -6323586720541411613L;
	
	public String name;
	public MathObject entity;
	/**
	 * Initialize a MathVariable by taking a string as its name. The MathVariable can then be called
	 * through its name. The value must be set using MathWorkspaces setValue
	 * @param setname the string name of the variables
	 */
	MathVariable(String setname){		
		name = setname;
		// No need to initialize entity since user will have to call setValue to set it 
	}
	
	
	/**
	 * Can also initialize variable with respect to name and entity
	 * @param setname name of variable 
	 * @param entity MathObject content of variable
	 */
	MathVariable(String setname, MathObject entity){		
		name = setname;
		this.entity = entity;
	}
	/**
	 * Override default toString method and return string name of MathVariable
	 * @return this MathVariables string name 
	 */
	
	@Override
	public String toString(){
		
		return this.name;
	}
	
	/**
	 * Override default hasCode() so as to have better distribution within HashMap.
	 * @return the resulting hash code which is the hashCode of the name (since variable names are unique).
	 */
	@Override
	public int hashCode() {	
		return name.hashCode();
	}
	/**
	 * Override default equals: two MathVariables are equal if they are both scalar and have the same values or both matrices
	 * and have the same content and the same dimensions. Type has been taken into consideration when checking for equality.
	 * @param o an Object
	 * @return true if both are equal,false otherwise
	 */

	
	@Override
	public  boolean equals(Object o) 
	{
		if(o instanceof MathVariable){
			MathVariable b = (MathVariable) o;
			
			if ( this.entity instanceof MathMatrix && b.entity instanceof MathMatrix){
				
				MathMatrix m1 = (MathMatrix) this.entity;
				MathMatrix m2 = (MathMatrix) b.entity;
				
				if (m1.columns == m2.columns && m1.rows == m2.rows){
					if(m1.content == m2.content){
						
						return true;
						
					}
					
				}
			}
			
			else if(this.entity instanceof MathScalar && b.entity instanceof MathScalar )
			{
				MathScalar m1 = (MathScalar) this.entity;
				MathScalar m2 = (MathScalar) b.entity;
				
				if (m1.value == m2.value){
					return true;
				}
			}

		}
		return false;
	}
}
