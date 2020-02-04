package main.swamy.pq;

import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;

public class MergeStreams {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 System.out.println(Arrays.toString(mergeStreams(
	                new int[][] { { 2, 4, 5, 6, 7, 8 },
	                        { 1, 3, 9, 12 },
	                        { 10, 11, 13, 14 } })));
	}
	private static int[] mergeStreams(int[][] is) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for (int i = 0; i < is.length; i++) {
            for (int j = 0; j < is[i].length; j++)
                pq.add(is[i][j]);
        }
        int[] result = new int[pq.size()];
        int counter = 0;
        Iterator<Integer> i = pq.iterator();
        while (i.hasNext()) {
            result[counter++] = pq.remove();
        }
        return result;
    }
}
