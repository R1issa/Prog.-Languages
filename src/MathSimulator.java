import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Scanner;

public class MathSimulator {
	
	/** Create an instance of MathWorkspace to use during simulation for things like
	 * looking up a variable in our variable table 
	 *@see MathWorkspace 
	 */
	public static MathWorkspace workspace = new MathWorkspace();
	
	/** Create an instance of MathOp so as to use it while multiplying two
	 * MathObjects
	 *@see MathOp
	 */
	public static MathOp oper = new MathOp();
	
	/** This array of strings will allow to display the set of
	 * commands possible using this simulator.
	 *@see MathWorkspace 
	 */
	
	public static String[] comands = new String[] {
			" 1. define scalar variable ", 
			" 2. define matrix variable ",
			" 3. set value",
			" 4. print value",
			" 5. perform multiplication",
			" 6. list all variables",
			" 7. clear all varliables",
			" 8. quit" };
	/**
	 * This method includes the possible actions to take for each 
	 * choice the user makes
	 * @param args command line arguments
	 * @throws MathException necessary for exception thrown in case of wrong matrix dimensions
	 */
	public static void main(String[] args) {
		System.out.println("Loading workspace");
		try {
			File f = new File("saved.sav");
			
			if(f.exists()) {
				FileInputStream fis = new FileInputStream(f);
				ObjectInputStream ois = new ObjectInputStream(fis);
				
				workspace = (MathWorkspace) ois.readObject();
				
				ois.close();
				fis.close();
			} else {
				System.out.println("No workspace saved");
			}
			
		} catch(IOException ex) {
			System.out.println("Error loading workspace: " + ex.getMessage());
			workspace = new MathWorkspace();
		} catch(Exception ex) {
			System.out.println("Error loading workspace: " + ex.getMessage());
			workspace = new MathWorkspace();
		}
		
		
		for(String s: comands){
			System.out.println(s);
		}
		Scanner a = new Scanner(System.in);
		System.out.println("Enter Choice: ");
		
		int choice = 9; // non existing choice as default 
		
		while( choice != 8){
			
			
			Action a1 = new Action(){
				@Override
				public void doAction() {
					
					System.out.println("%%%Menu item 1: define scalar variable");
					System.out.print("Enter variable name: ");
					
					String s = a.nextLine();
					
					//Check if variables is not existing
					if(workspace.search(s)==null){
						
						MathVariable var = new MathVariable(s);
						
						// Instantiate a MathScalar with a default value of -999
						// and add it as an entity to our MathVariable.
						// Note 1: this is done so our var.entity becomes an instance of MathScalar
						// Note that this default value will be overwritten
						// when user calls method setValue()
						
						MathScalar scal = new MathScalar(-999);
						var.entity = scal;
						workspace.storeVar(var);
						System.out.println("");
						System.out.println("Your scalar "+s+" is now defined.");
						
					}
					else{
						System.out.println("");
						System.out.println("Cannot create variable "+s+". A variable with the same name already exists!");
						
					}
					System.out.println("");
				}		
				
			};
			
			
			Action a2 = new Action(){
				@Override
				public void doAction() {
					
					System.out.println("%%%Menu item 2: define matrix");
					System.out.print("Enter variable name: ");
					String s = a.nextLine();
					
					if(workspace.search(s)==null){
						
						MathVariable var = new MathVariable(s);
						System.out.println("");
						System.out.println("Your matrix name is: "+ s);
						System.out.flush();
						System.out.println("Enter number of rows: ");
						int rows = a.nextInt();
						System.out.println("");
						System.out.println("The number of rows is: "+ rows);
						System.out.flush();
						System.out.println("");
						System.out.println("Enter number of columns: ");
						int col = a.nextInt();
						
						System.out.println("");
						System.out.println("The number of columns is: "+ col);
						
						double [][]t = new double[rows][col];
						

						MathMatrix m = new MathMatrix(t);
						m.columns = col;
						m.rows= rows;
						
						// Here we are making our entity be an instance of MathMatrix
						// with a content equal to a 2D array with the correct set dimensions
						var.entity = m;
						workspace.storeVar(var);
						
					}
					else{
						System.out.println("");
						System.out.println("Cannot create variable "+s+". A variable with the same name already exists!");
						
					}
					System.out.println("");
				}
						
			};
			
			Action a3 = new Action(){
				@Override
				public void doAction() {
					
					System.out.println("%%%Menu item 3: set value");
					System.out.print("Enter variable name: ");
					
					String s = a.nextLine();
					System.out.println("");
					
					MathVariable m3 = workspace.search(s);
					
					if(m3 != null){
						
						System.out.print("Set value:");
						
						// Check if our entity is a MathScalar or MathMatrix to set content accordingly
						
						if(m3.entity instanceof MathScalar){
							
							int valu = a.nextInt();
							MathScalar var = new MathScalar(valu);
							
							workspace.setValue(s, (MathObject)var);
							System.out.println("");
							System.out.println("Variable "+s+" is now set to:");
							System.out.println(valu);

							
						}
						else{
							
							MathMatrix m2 = (MathMatrix) m3.entity;
							double [][] d = new double[m2.rows][m2.columns];
							
							for(int i =0; i < m2.rows; i++){
								System.out.println("");
								System.out.println("Enter numbers in the "+i+"th row and press enter after each number entered");
								
								for(int k =0; k< m2.columns; k++){
									
									d[i][k] = a.nextInt();

									
								}	
							}
							
							MathMatrix m1 = new MathMatrix(d);
							
							workspace.setValue(s, (MathObject) m1);
							System.out.println("Variable "+s+" is now set to:");
							System.out.println("");
							System.out.println(Arrays.deepToString(m1.content));
							

						}
						

						
					}
					else{
						System.out.println("Variable "+s+" does not exists!");
					}
				}
	
			};
			
			Action a4 = new Action(){
				
				@Override
				public void doAction() {
					
					System.out.println("%%%Menu item 4: print value");
					System.out.print("Enter variable name: ");
					
					String s = a.nextLine();
					
					MathObject m = workspace.getValue(s);
					
					if(m != null){
					System.out.println("");	
					System.out.println("The value of "+s+" is: ");
					System.out.println("");
						if(m instanceof MathScalar){
							
							MathScalar m1 = (MathScalar) m;
							
							System.out.println(m1.value);
							
						}
						else{
							
							MathMatrix m1 = (MathMatrix) m;
							
							// Print out the MathMatrix in a more significant way using deepToString() method
							System.out.println(Arrays.deepToString(m1.content));
							
						}
					}	
					System.out.println("");
				}			
			};
			
			Action a5 = new Action() {
				@Override
				public void doAction() {
					
					System.out.println("");
					System.out.println("%%%Menu item 5: multiply");
					System.out.print("Enter left variable name: ");
					String leftvar = a.nextLine();
					
					System.out.println("");
					System.out.print("Enter right variable name: ");
					String rightvar = a.nextLine();

					System.out.print("Enter a NEW non-used name for the variable where you want to store the result:");
					String storevar = a.nextLine();
					
					MathVariable var1 = workspace.search(leftvar);
					MathVariable var2 = workspace.search(rightvar);
					
					// Check if none of the variables we are using is non existent
					if(var1 != null && var2 != null){
						
						if (workspace.search(storevar)== null){
							
							if(var1.entity instanceof MathMatrix && var2.entity instanceof MathMatrix){
								
								MathVariable var3 = new MathVariable(storevar);
								
	
								MathMatrix m1 = (MathMatrix) var1.entity;
								MathMatrix m2 = (MathMatrix) var2.entity;
								double [][]t = new double[m1.columns][m2.rows];
								MathMatrix m = new MathMatrix(t);
								// Here we are making our entity be an instance of MathMatrix
								// with a content equal to a 2D array with the correct set dimensions
								var3.entity = m;
								workspace.storeVar(var3);
								
								
							}
							
							else if(var1.entity instanceof MathMatrix && var2.entity instanceof MathScalar){
								
								MathVariable var3 = new MathVariable(storevar);
								
								MathMatrix m1 = (MathMatrix) var1.entity;
								double [][]t = new double[m1.columns][m1.rows];
								MathMatrix m = new MathMatrix(t);
								// Here we are making our entity be an instance of MathMatrix
								// with a content equal to a 2D array with the correct set dimensions
								var3.entity = m;
								workspace.storeVar(var3);
	
							}
							
							else if(var2.entity instanceof MathMatrix && var1.entity instanceof MathScalar){
								MathVariable var3 = new MathVariable(storevar);
								
								MathMatrix m1 = (MathMatrix) var2.entity;
								double [][]t = new double[m1.columns][m1.rows];
								MathMatrix m = new MathMatrix(t);
								// Here we are making our entity be an instance of MathMatrix
								// with a content equal to a 2D array with the correct set dimensions
								var3.entity = m;
								workspace.storeVar(var3);
	
							}
							
						}
						else{
							
							System.out.println("The storiage variable name is already taken, next time specify a new one ! ");
							
						}
						
						try{
						MathObject result;
						result = MathOp.multiply((MathObject)var1.entity, (MathObject)var2.entity);
						workspace.setValue(storevar, result);
						}catch(MathException e){
							System.out.println("Error: "+e);
							workspace.delete(storevar);
							
						}
						
					}
					else{
						System.out.println("");
						
					}	
					System.out.println("");
				}			
			};
			
			Action a6 = new Action() {
				@Override
				public void doAction(){
					
					System.out.println("%%%Menu item 6: List all variables");
					workspace.printem();
					System.out.println("");
					
				}			
			};
			
			Action a7 = new Action() {
				@Override
				public void doAction(){
					
					System.out.println("%%%Menu item 7: delete all variables");
					workspace.deleteall();
					System.out.println("");
					
				}			
			};
			
			Action a8 = new Action() {
				@Override
				public void doAction(){
					
					System.out.println("%%%Menu item 8: quit");
					System.out.println("Saving workspace");
					
					try { 
						File f = new File("saved.sav");
						if(f.exists())
							f.delete();
						
						f.createNewFile();
						
						FileOutputStream fos = new FileOutputStream(f);
						ObjectOutputStream oos = new ObjectOutputStream(fos);
						
						oos.writeObject(workspace);
						
						oos.close();
						fos.close();
					} catch(IOException ex) {
						ex.printStackTrace();
						System.out.println("Error encountered while saving: "+ex.getMessage());
					}
						
					System.out.println("Bye bye");
					a.close();
					System.exit(0);
					
					
				}			
			};
			
			Action[] actions = new Action[] {a1,a2,a3,a4,a5,a6,a7,a8};
			choice = a.nextInt();
			a.nextLine();
			
			if(choice >= 1 && choice <= actions.length)			
				actions[choice-1].doAction();
			
			System.out.println("Enter Choice: ");
			
		}
		
	}
	
	/**
	 * An interface made to make choosing actions according to users choice easier
	 * @author rawane
	 *
	 */
	public static interface Action {
		
		public void doAction();
		
	}
	
}

