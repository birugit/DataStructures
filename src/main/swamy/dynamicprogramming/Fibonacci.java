package main.swamy.dynamicprogramming;
/**
 * A child is running up a staircase with n steps,
 *  and can hop either 1 step, 2 steps, or 3 steps at a time.
 *  Implement a method to count how many possible ways the child can run up the stairs.
 * @author swamy
 *
 */

public class Fibonacci {

	public static void main(String[] args) {
		int totSetps = 3;
		// exponential runtime O(3^N)
	/*	int steps = countWays(totSetps);
		System.out.println("Ways to hop:"+steps);*/
		
		//use Dynamic Programming
		int[] map = new int[totSetps+1];
	int 	steps = countWaysDP(totSetps, map);
		System.out.println("Ways to hop:"+steps);
		
		//Fibonacci 
	//	int n = 3;
	//	int fib = fibonacci(n);
	//	System.out.println("Fibonacci:"+fib);
		
		//Top down approach
		int[] fibCache =  new int[5];
	//int	 fib = fibonacciDP(n,fibCache);
	//	System.out.println("Fibonacci DP:"+fib);
		
		//Bottom up approach
		System.out.println("Fib DP Bottom up approach:"+fibonacciBUDP(3, fibCache));
		
	}

	private static int fibonacciBUDP(int n, int[] fibCache) {
		if(n <= 1) return n;
		fibCache[0] =  0;
		fibCache[1] = 1;
		
		for(int i = 2; i <= n; i++) {
			fibCache[i] = fibCache[i - 2] + fibCache[i-1];
		}
		return fibCache[n];
	}

	private static int fibonacciDP(int n, int[] fibCache) {
		
		if(n == 0) return 0;
		if(n == 1) return 1;
		if(fibCache[n] != 0 ) return fibCache[n];//Return cached result
		fibCache[n] = fibonacciDP(n - 1, fibCache) + fibonacciDP(n - 2, fibCache);//cache results
		
		return fibCache[n];
	}

	private static int fibonacci(int i) {
		if(i == 0) {
			return 0;
		}
		if(i == 1) return 1;
		return fibonacci(i -1) +
				fibonacci(i - 2);
		
	}

	private static int countWaysDP(int totSetps, int[] map) {
		if(totSetps < 0) {
			return 0;
		}else if(totSetps == 0) {
			return 1;
		}//else if(map[totSetps] > -1) {
			//return map[totSetps];
	//	}
	else {
			map[totSetps] = countWaysDP(totSetps - 1, map) +
					countWaysDP(totSetps - 2, map)  +
					countWaysDP(totSetps - 3, map) ;
		}
		return map[totSetps];
	}

	private static int countWays(int totSetps) {
		if(totSetps < 0) {
		return 0;
	}else if (totSetps == 0) {
		return 1;
	}else {
		return countWays(totSetps - 1) +
				countWays(totSetps - 2) +
				countWays(totSetps - 3);
	}
		
	}

}
