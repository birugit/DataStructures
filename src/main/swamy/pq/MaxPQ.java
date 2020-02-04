package main.swamy.pq;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MaxPQ<Key> implements Iterable<Key> {

	private Key[] pq; // store items at indices 1 to n
	private int n;		//number of items in priority queue
	private Comparator<Key> comparator; // optional comparator
	
	
	public MaxPQ(int initCapacity) {
		pq = (Key[]) new Object[initCapacity];
		n = 0;
	}
	
	//initialize empty PQ
	public MaxPQ() {
		this(1);
	}
	
	//Initialize an empty PQ with the given initial capacity,
	//using the given comparator.
	
	public MaxPQ(int initCapacity, Comparator<Key> comparator) {
		this.comparator = comparator;
		pq = (Key[]) new Object[initCapacity + 1];
		n = 0;
	}
	
	//Initialize the empty PQ using given comparator
	public MaxPQ(Comparator<Key> comparator) {
		this(1, comparator);
	}
	
	//Initialize a PQ from the array of keys.
	public MaxPQ(Key[] keys) {
		n = keys.length;
		pq = (Key[]) new Object[keys.length + 1];
		for(int i = 0; i < n ; i++) {
			pq[i+1] = keys[i];
		for(int k = n/2; k >= 1; k--)
			sink(k);
		}
	}
	private void sink(int k) {
		while(2 * k <= n) {
			int j = 2 * k;
			if(j < n && less(j, j + 1))
				j++;
			exch(k, j);
			k = j;
		}
		
	}
	//compares and swaps

	private void exch(int i, int j) {
		Key swap = pq[j];
		pq[i] = pq[j];
		pq[j] = swap;
	}

	private boolean less(int i, int j) {
		if(comparator == null) {
			return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
		}
		else {
			return comparator.compare(pq[i], pq[j]) < 0;
		}

	}

	public static void main(String[] args) {
		MaxPQ<String> pq = new MaxPQ<String>();
		pq.insert("P");
		pq.insert("Q");
		pq.insert("E");
		pq.insert("X");
		pq.insert("A");
		pq.insert("M");
		pq.insert("P");
		pq.insert("L");
		pq.insert("E");

		Iterator it = pq.iterator();
		System.out.println("MAX PQ:");
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}

	private void insert(Key x) {
		//double size of array
		if(n == pq.length - 1)
			resize(2 * pq.length);
		//add x and percolate it up to maintain heap invariant
		pq[++n]  = x;
		swim(n);
		
	}
	//restore the heap invariant
	private void swim(int k) {
		while(k > 1 && less(k/2, k)) {
			exch(k, k/2);
			 k = k/2;
		}
		
	}

//double the size of heap array
	private void resize(int capacity) {
		
		Key[] temp = (Key[]) new Object[capacity];
		for(int i = 1; i <= n; i++) {
			temp[i] = pq[i];
		}
		pq = temp;
	}
	
	public int size() {
		return n;
	}

	public boolean isEmpty() {
		return n == 0;
	}
	
	public Key delMax() {
		if(isEmpty()) throw new NoSuchElementException();
		Key max = pq[1];
		exch(1, n--);
		sink(1);
		pq[n+1] = null;
		if((n > 0) && (n == (pq.length - 1) / 4))
			resize(pq.length/2);
		return max;
	}
	
	@Override
	public Iterator<Key> iterator() {
		
		return new HeapIterator();
	}
	
	
	private class HeapIterator implements Iterator<Key>{
		//create a new PQ
		private MaxPQ<Key> copy;
		
		//add all items to copy of heap
		//takes linear time since already in heap order
		public HeapIterator() {
			if(comparator == null) {
				copy = new MaxPQ<Key>(size());
			}
			else {
				copy = new MaxPQ<Key>(size(), comparator);
			}
			for(int i = 1; i <= n; i++)
				copy.insert(pq[i]);
		}

		@Override
		public boolean hasNext() {
			
			return !copy.isEmpty();
		}

		@Override
		public Key next() {
			if(!hasNext()) throw new NoSuchElementException();
			return copy.delMax();
		}

	}
}


