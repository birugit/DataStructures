package main.google.test;

import java.util.HashMap;
import java.util.Map;
/**
 * Two sum of numbers
 * @author swamy
 *
 */

public class TwoSum {

	public static void main(String[] args) {
		int[] num = {2, 4, 6, 7, 9};
		int target = 15;
		int[] n =twoSum(num, target);
		System.out.println("two sum:"+num[n[0]]+":"+num[n[1]]);
		
	}

	//Time complexity: O(n)
	//space complexity: O(n)
	private static int[] twoSum(int[] num, int target) {
		
		Map<Integer, Integer> map = new HashMap<>();
		
		for(int i=0;i<num.length;i++) {
			int complement = target - num[i];
			if(map.containsKey(complement)) {
				return new int[] {map.get(complement), i};
			}
			map.put(num[i], i);
		}
		throw new IllegalArgumentException("no two sum");
	}
	

}
