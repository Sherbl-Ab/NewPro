import java.util.ArrayList;
/**
* The SharedData class is used to store shared data between threads.
* It holds an array of integers, a boolean array indicating which elements
* of the array are part of a solution, a flag indicating whether a solution
* has been found, and a target number `b` to match using a subset of the array.
*@author sherbl abdalla
*@author siraj khutaba
*/

public class SharedData 
{
	
	private ArrayList<Integer> array; // The arraylist of integers
	private boolean [] winArray; // Array indicating which elements are part of a solution
	private boolean flag; // Flag indicating if a solution was found
	private final int b; // The target number to achieve using a subset sum
	
		
	/**
	   * Constructs a SharedData object with array and sum. 
	   * @param array the arraylist of integers to check for a subset sum.
	   * @param b the target sum to be achieved by a subset of the array.
	   */
	
	
	public SharedData(ArrayList<Integer> array, int b) {
	
		
		this.array = new ArrayList<>();
		this.b = b;
	}
	
	
	/**
	 *  @return the boolean array where each element is:
	 *  `true` if it is part of the solution.
	 *  otherwise `false`.
	 */
	public boolean[] getWinArray() 
	{
		return winArray;
	}
	/**
	  *  @param winArray the boolean array where each element is:
	  *  `true` if it is part of the solution.
	  * otherwise `false`.
	  */
	public void setWinArray(boolean [] winArray) 
	{
		this.winArray = winArray;
	}
	/** 
	 * @return the array of integers.
	 */
	public ArrayList<Integer> getArray() 
	{
		return array;
	}
	/**
	 *  @return the target sum to achieve by a subset of the array.
	 */
	public int getB() 
	{
		return b;
	}
	/** 
	 * Returns the flag indicating whether a solution has been found.
	 * @return `true` if a solution has been found, otherwise `false`.
	 */
	public boolean getFlag() 
	{
		return flag;
	}
	/**
	 * Sets the flag indicating whether a solution has been found. 
	 * @param flag `true` if a solution has been found, otherwise `false`.
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
