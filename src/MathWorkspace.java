import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class MathWorkspace implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6195402209261340576L;
	/**
	 * HashMap stores MathVariable as both key and value.
	 */
	
	HashMap<MathVariable, MathVariable> storage = new HashMap<MathVariable, MathVariable>();
	
	/**
	 * Takes a MathVariable and stores it our HashMap
	 * @param v MathVariable to store
	 */
	public void storeVar(MathVariable v){
		
		storage.put(v, v);
		
	}
	
	/**
	 * Look for MathVariable by using the string name which designates it 
	 * @param name string which designates the name of the variable to look for in our HashMap storage
	 * @return var MathVariable if its found 
	 * @return null if no MathVariable with this name is found
	 */
   public MathVariable search(String name){
	   
	   for(MathVariable var:storage.keySet()){
		   if(var.name.equals(name)){
			   return var;
		   }
	   }
	   return null;
   }
   
   /**
    * Gets the value of a MathVariables by looking for it through its string name
    * and calling search
    * @param name string which designates the name of the variable to look for in our HashMap storage
    * @return var.entity the MathObject field of a MathVariable
    * @see #search
    */
   
   public MathObject getValue(String name){
	   
	   MathVariable var = search(name);
	   if(var!=null){
		   return var.entity;
	   }
	   
	   return null;
	   
   }
   
   /**
    * Method to either set or change the value of a variable in our HashMap
    * @param name string which designates the name of the variable to look for and change in our HashMap storage
	*
    * @param val the new MathObject value to set
    */
   public void setValue(String name, MathObject val){
	   
	   MathVariable var = search(name);
	   var.entity = val;
	   
   }
	
   /**
    * Deletes a single MathVariable by searching for it and removing it
    * @param name string which designates the name of the variable to look for and delete in our HashMap storage
    */
	public void delete(String name){
		
	   MathVariable var = search(name);
	   
		storage.remove(var);
		
	}
	/**
	 * Deletes all variables in our HashMap storage by looking for the keys in storage.keySet(),
	 * then deleting the variables one at a time by calling delete()
	 * @see #delete
	 */
	
	public void deleteall(){
		storage.clear();
	}
	
	/**
	 * List all our MathVariables by taking them as a keySet then copying them
	 * @return the list of MathVariables in storage
	 */
	
	public List<MathVariable> listem(){
		
		return new ArrayList<MathVariable>(storage.keySet());	
		
	}
	
	/**
	 * Print all MathVariables in storage after listing them by calling listem()
	 * @see #listem
	 */
	public void printem(){
		System.out.print("Variable List:");
		System.out.print(listem().toString().replace(",", "\n").replace("[", "").replace("]", ""));
		
	}
	
	
	
	
	
	
}
