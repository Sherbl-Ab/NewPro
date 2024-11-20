import java.util.ArrayList;
import java.util.Scanner;

/**
* The TestThreadCheckArray class is a simple test program that demonstrates the 
* use of multiple threads to solve the subset-sum problem. It reads an array of 
* integers and a target sum `b` from the user, and then spawns two threads to 
* check if there is a subset of the array whose sum equals `b`. 
*/


public class TestThreadCheckArray {
	public static void main(String[] args) {
		try (Scanner input = new Scanner(System.in)) {
			Thread thread1, thread2;
			System.out.println("Enter array size");
			int num  = input.nextInt();
			ArrayList<Integer> array = new ArrayList<>();
			System.out.println("Enter numbers for array");
			
			 // Read numbers into the array
			for (int index = 0; index < num; index++) 
				array.add(input.nextInt());
			
			System.out.println("Enter number");
			num = input.nextInt();
			
			// Create SharedData object with the array and target sum
			SharedData sd = new SharedData(array, num);
			
			
			// Create and start two threads
			thread1 = new Thread(new ThreadCheckArray(sd), "thread1");
			thread2 = new Thread(new ThreadCheckArray(sd), "thread2");
			thread1.start();
			thread2.start();
			try 
			{    // Wait for both threads to finish

				thread1.join();
				thread2.join();
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			// If no solution is found, print "Sorry"
			if (!sd.getFlag())
			{
				System.out.println("Sorry");
				return;
			}
			
		    // Print the solution
			System.out.println("Solution for b : " + sd.getB() + ",n = " + sd.getArray().size());
			System.out.print("I:    ");
			for(int index = 0; index < sd.getArray().size() ; index++)
				System.out.print(index + "    ");
			System.out.println();
			
			// Print the array values
			System.out.print("A:    ");
			for (int index : sd.getArray())
			{
				System.out.print(index);
				int counter = 5;
				while (true)
				{
					index = index / 10;
					counter--;
					if (index == 0)
						break;
				}
				// Adjust spacing for alignment
				for (int i = 0; i < counter; i++)
					System.out.print(" ");
			}

			System.out.println();
			System.out.print("C:    ");
			
			// Print the solution (1 and 0) for each element in the array
			for (boolean index : sd.getWinArray())
			{
				if (index)
					System.out.print("1    ");
				else
					System.out.print("0    ");	
			}
		}
	}

}
