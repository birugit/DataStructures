package main.google.test.unionfind;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

public class WeightedQuickUnionPathCompressionUF {
	
	private int[] parent; //parent[i] = parent of i
	private int[] size;		//size[i] = number of sites in tree rooted at i
					//Note: not necessarily correct if i is not a root node
	private int count;		//number of components
	
	/**
	 * Initializes an empty union-find data structure
	 * @param args
	 */
	public WeightedQuickUnionPathCompressionUF(int n) {
			count = n;
			parent = new int[n];
			size = new int[n];
			for(int i = 0; i< n; i++) {
				parent[i] = i;
				size[i] = 1;
			}
		}
	public static void main(String[] args) {
		 String fileName = "/Users/swamy/Downloads/uf.txt";
	        Path path = Paths.get(fileName);
	        Scanner scanner;
	    	int n = 10;
	    	WeightedQuickUnionPathCompressionUF uf = new WeightedQuickUnionPathCompressionUF(n);
			try {
				scanner = new Scanner(path);
		
	//	Scanner scanner = new Scanner("");
	
		while(scanner.hasNext()) {

			int p = scanner.nextInt();
			int q = scanner.nextInt();
			if(uf.find(p) == uf.find(q)) continue;
			uf.union(p,q);
			System.out.println(p+" "+q);
		
		}
			}catch(InputMismatchException e) {
				System.out.println();
			
				/*   String token = scanner.next();
		            throw new InputMismatchException("attempts to read an 'int' value from standard input, "
		                                           + "but the next token is \"" + token + "\"");*/
			}	 catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	//	scanner.close();
		System.out.println(uf.count()+"componenets");
		

	}
	
	//merges the set containing element with the set
	
	private void union(int p, int q) {
		int rootP = find(p);
		int rootQ = find(q);
		if(rootP = root) return;
		
		//make smaller root point to larger one
		if(size[rootP] < size[rootQ])
		
	}
	//returns the canonical element of the set containing the element p
	private int find(int p) {
		int root = p;
		while(root != parent[root])
			root = parent[root];
		while(p != root) {
			int newp = parent[p];
			parent[p] = root;
			p = newp;
		}
		return root;
	}

}
