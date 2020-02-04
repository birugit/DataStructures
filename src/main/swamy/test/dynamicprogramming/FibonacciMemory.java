package main.swamy.test.dynamicprogramming;
/**
 * Fibonacci using taulation
 * O(n)
 * 
 *Normal recursion takes O(n^2)
 * @author swamy
 *
 */
public class FibonacciMemory {

	public static void main(String[] args) {
		int m = 7;
		
		 int n = fibonacci(m );
		 System.out.println("Fibonacci:"+ n);

	}

	private static int fibonacci(int m) {
		
		int[] fibMemory = new int[m+1];
		fibMemory[0] = 0;
		fibMemory[1]=1;
		for(int i = 2; i<= m ; i++) {
			fibMemory[i] = fibMemory[i - 2] + fibMemory[i - 1];
		}
		return fibMemory[m];
	}

}
