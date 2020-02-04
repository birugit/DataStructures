package main.sort;

public class QuickSort {
	
	public QuickSort() {
		
	}

	public static void main(String[] args) {
	
		String[] a = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
	//	String[] a = {"-1", "6", "9", "-4", "-10", "-9", "8", "8", "4"};
		//binary array {0,1,1,0,0,1,0,0,1}
	//	String[] a = {"0", "1", "1", "0", "0", "1", "0", "0", "1"};
		QuickSort.sort(a);
		System.out.println("Quick Sort");
		for(int i = 0; i < a.length; i++) {
			System.out.print(a[i]);
		}
	}

	//uses the natural ordering
	private static void sort(String[] a) {
		sort(a, 0, a.length-1);
		
	}

	private static void sort(String[] a, int lo, int hi) {
		if(hi < lo) return;
		int j = partition(a, lo, hi);
		sort(a, lo, j-1);
		sort(a, j+1, hi);
		
	}

	//partition the subarray a[lo..hi] so that a[lo...j-1] <= a[j] <= a[j+1...hi]
	private static int partition(Comparable[] a, int lo, int hi) {
		int i = lo;
		int j = hi + 1;
		Comparable v = a[lo];
		while(true) {
			//find the item on the lo to swap
			while(less(a[++i], v))
					if(i == hi)break;
			//find the item on hi to swap
			while(less(v, a[--j]))
				if(j == lo) break;//redundant since a[lo] acts as sentinel
			//check if pointers cross
			if(i >= j) break;
			
			exch(a, i , j);
		}
		//put paritioning item v at a[j]
		exch(a, lo , j);
		//now, a[lo...j-1] <= a[j] <= a[j+1..hi]
		return j;
	}
	
	private static void exch(Object[] a, int i, int j) {
			Object swap = a[i];
			a[i] = a[j];
			a[j] = swap;
			
		}

	//is v < w?
	private static boolean less(Comparable v, Comparable w) {
		
		return v.compareTo(w) < 0;
	}

}
