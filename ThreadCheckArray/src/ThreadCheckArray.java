import java.util.ArrayList;

/**
 * The {@code ThreadCheckArray} class implements a thread that checks whether
 * a subset of an array of integers sums to a target value. It uses a recursive
 * approach to solve the subset-sum problem and communicates results via a shared
 * {@link SharedData} object.
 */
public class ThreadCheckArray implements Runnable 
{
	private boolean flag; // Indicates if the target sum has been found
	private boolean [] winArray;  // Tracks elements contributing to the solution
	SharedData sd;  // Tracks elements contributing to the solution
	ArrayList<Integer> array;  // Array of integers to check
	int b;  // Target sum value

	/**
     * Constructs a {@code ThreadCheckArray} object with a reference to shared data.
     * The constructor retrieves the array and target sum value from the shared data
     * while ensuring thread-safe access.
     *
     * @param sd the shared data object containing the array and target sum value
     */
	public ThreadCheckArray(SharedData sd) 
	{
		this.sd = sd;	
		synchronized (sd) 
		{
			array = sd.getArray();
			b = sd.getB();
		}		
		winArray = new boolean[array.size()];
	}
	
	/**
     * Recursive method to solve the subset-sum problem. It checks if a subset
     * of the first {@code n} elements in the array can sum to the target {@code b}.
     *
     * @param n the number of elements to consider
     * @param b the current target sum to achieve
     */
	void rec(int n, int b)
	{
		synchronized (sd) 
		{
			if (sd.getFlag())
				return; // Exit if another thread has already found a solution
		}	
		if (n == 1)
		{
			 // Base case: Only one element left to check
			if(b == 0 || b == array.get(n-1))
			{
				flag = true;
				synchronized (sd) 
				{
					sd.setFlag(true); // Notify other threads of the solution
				}			
			}
			if (b == array.get(n-1))
				winArray[n-1] = true; // Mark element as part of the solution
			return;
		}
		
		rec(n-1, b - array.get(n-1));
		if (flag)
			winArray[n-1] = true;
		synchronized (sd) 
		{
			if (sd.getFlag())
				return; // Exit if another thread has already found a solution
		}	
		rec(n-1, b); // Exclude the element
	}
	 /**
     * The entry point for the thread. It initializes the recursive subset-sum
     * calculation and updates the shared data if a solution is found.
     */
	public void run() {
		if (array.size() != 1)
			if (Thread.currentThread().getName().equals("thread1"))
				rec(array.size()-1, b - array.get(array.size() - 1));
			else 
				rec(array.size()-1, b);
		if (array.size() == 1)
			 // Special case: Array has only one element
			if (b == array.get(0) && !flag)
			{
				winArray[0] = true; // Mark element as part of the solution
				flag = true;
				synchronized (sd) 
				{
					sd.setFlag(true);
				}
			}
		if (flag)
		{
			if (Thread.currentThread().getName().equals("thread1"))
				winArray[array.size() - 1] = true; // Mark the last element
			synchronized (sd) 
			{
				sd.setWinArray(winArray); // Update shared data with the solution

			}	
		}
	}
}
