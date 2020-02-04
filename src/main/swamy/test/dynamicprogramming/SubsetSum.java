package main.swamy.test.dynamicprogramming;

public class SubsetSum {

	public static void main(String[] args) {
		int set[] = {3, 34, 4, 12, 5, 2};
		int sum = 9;
		int n = set.length;
		if(isSubSetSum(set, n, sum) == true)
			System.out.println("Found sum");
		else
			System.out.println("No subset with given sum");
		
		//Dynamic Programming
		if(isSubSetSumDP(set, n, sum) == true)
			System.out.println("Found sum");
		else
			System.out.println("No subset with given sum");

	}

	private static boolean isSubSetSumDP(int[] set, int n, int sum) {
			boolean subset[][] = new boolean[sum+1][n+1];
			
			//if sum is 0, then answer is true
			for(int i= 0; i<= n; i++)
				subset[0][i] = true;
			
			//if sum is not 0 and set is empty
			//then answer is false
			
			for(int i=1; i<= sum; i++)
				subset[i][0] = false;
			
			//Fill the subset table in bottom up manner
			for(int i=1; i<=sum; i++) {
				for(int j=1; j<=n; j++) {
					subset[i][j] = subset[i][j-1];
					if(i>=set[j-1])
						subset[i][j] = subset[i][j] ||
						subset[i - set[j-1]][j-1];
				}
			}
			
			//table
			for(int i=0; i<=sum;i++) {
				for(int j=0;j<=n;j++) {
					System.out.println(subset[i][j]);
				}
			}
			
		return subset[sum][n];
	}

	private static boolean isSubSetSum(int[] set, int n, int sum) {
		//base case
		if(sum == 0)
			return true;
		if(n == 0 && sum != 0)
			return false;
		
		//if last element is greater than sum, then ignore it
		if(set[n-1] > sum)
			return isSubSetSum(set, n-1, sum);
		//lese check is sum can be obtained by any of the following
			//a including the last element
		//b	excluding last element
		
		return isSubSetSum(set, n-1, sum) ||
				isSubSetSum(set, n-1, set[n-1]);
	}

}
