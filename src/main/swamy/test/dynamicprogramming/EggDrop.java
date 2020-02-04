package main.swamy.test.dynamicprogramming;

public class EggDrop {

	public static void main(String[] args) {
		EggDrop eggDP = new EggDrop();
		int eggs = 2;
		int floors = 10;
		System.out.printf("DP Minimum number of Eggs required in worst case with eggs:%s and floors %s is : %s:",eggs,floors, +getDrops(eggs, floors));

		//by using Binary Search and Binomial coefficient
		//A binomial coefficient C(n, k) also gives the number of ways, 
		//disregarding order, that k objects can be chosen from among n objects; more formally, 
		//the number of k-element subsets (or k-combinations) of an n-element set.
		System.out.println();
		System.out.println("Binary Serach and Binomical coefficient");
		System.out.println(minTrails(2, 10));
	}
	
	//*******************************Binary search
	//Time Complexity O(nLogK)

	//do binary search to find minimum number of trails in worst case
	
	private static int minTrails(int e, int f) {
		//initialize low and high
		//as first and last floors
		int low = 1, high = f;
		
		//Do binary search, for every mid,
		//find sum of binomial coefficients and 
		//check of the sum is greater than k or not.
		while(low < high) {
			int mid = (low + high) / 2;
			if(binomicalCoefficient(mid, e) < f)
				low = mid +1;
			else
				high = mid;
		}
		return low;
	}

	//Find sum of binomial coefficeints mCi
	//where n varies from 1 to e;
	private static int binomicalCoefficient(int m, int e) {
		int sum = 0, term = 1;
		for(int i = 1; i <= e; ++i) {
			term *= m - i +1;
			term /= i;
			sum += term;
		}
		return sum;
	}

	
	//*****************
	
	//############Dynamic Programming
	private static int getDrops(int eggs, int floors) {
		int [][] eggDrops = new int[eggs+1][floors+1];
		
		//base case 1:
		//if floors = 0 then no drops re required
		//OR floors =1 then 1 drops is required
		for(int i = 0; i <= eggs; i++) {
			eggDrops[i][0] = 0;//0 floor
			eggDrops[i][1] = 1; //1 floor
		}
		
	//	base case 2:
			//if only one egg is there then drops = floors
			for(int i = 1; i<=floors; i++) {
				eggDrops[1][i]=i;
			}
			
		for(int i =2; i<=eggs; i++) {
			for(int j = 2; j<=floors; j++) {
				eggDrops[i][j] = Integer.MAX_VALUE;
				int tempResult;
				for(int k = 1; k<= j; k++) {
					tempResult = 1 + Math.max(eggDrops[i-1][k-1],//broken eggs
							eggDrops[i][j-k] );//s
					eggDrops[i][j] = Math.min(tempResult, eggDrops[i][j]);
				}
			}
		}
		return eggDrops[eggs][floors];
	}
	//################

}
