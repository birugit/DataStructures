package main.google.test;

import java.util.Arrays;

/**
 * 
 * A program with cubic running time. Reads in n integers
 *  and counts the number of triples that sum to exactly 0.
 *  30 -30 0
 *  30 -20 -10
 *  -30 -10 40
 *  -10 0 10
 */
public class ThreeSum {

	public static void main(String[] args) {
		int[] a = {30, -30, -20, -10, 40, 0, 10, 15};
		int count = count(a);
		System.out.println(count);
		
		//O(n^2) solutions
		int n = a.length;
		threeSum(a,n);
	}

	private static void threeSum(int[] a, int n) {
		boolean found = false;
		
		//sort array elements
		Arrays.sort(a);
		
		for(int i=0; i<n-1; i++) {
			//initialize left and right
			int l = i + 1;
			int r = n - 1;
			int x = a[i];
			while(l < r) {
				if(x + a[l]+a[r] == 0) {
					//print elements if sum is zero
					System.out.print(x+" ");
					System.out.print(a[l]+" ");
					System.out.println(a[r]+ " ");
					
					l++;
					r--;
					found = true;
				}
				
				//if sum of three elements is less
				//than zero then increment in left
				else if(x + a[l] + a[r] < 0)
					l++;
				
				//if sum is greater than zero 
					//decrement in right side	
				else
					r--;
			}
		}
		if(found == false)
			System.out.println("No Triplet found");
		
	}

	//Time Complexity:O(n^3)
	//Space Complexity: O(1)
	private static int count(int[] a) {
		int n = a.length;
		int count = 0;
		for(int i = 0; i < n; i++)
			for(int j = i+1; j < n; j++) {
				for(int k = j+1; k<n; k++) {
					if(a[i] + a[j] + a[k] == 0) {
						System.out.println(a[i] +" "+a[j]+" "+a[k]);
						count++;
					}
				}
			}
		return count;
	}

	
	//
}
