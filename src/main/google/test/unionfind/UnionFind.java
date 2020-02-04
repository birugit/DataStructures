package main.google.test.unionfind;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;
//Weighted + paht
//Weighted quick-union by rank with path compression by halving
//Time (M + N)log*N
//M union-find operations on a set of N objects

public class UnionFind {
	private int[] parent; //parent[i] = parent of i
	private byte[] rank; //rank[i] = rank of subtree rooted at i//never more than 31
	private int count; //count of components
	
	public UnionFind(int n) {
		if(n < 0) throw new IllegalArgumentException();
		count = n;
		parent = new int[n];
		rank = new byte[n];
		for(int i = 0; i<n; i++) {
			parent[i] = i;
			rank[i] = 0;
		}
	}
	public static void main(String[] args) {
		  String fileName = "/Users/swamy/Downloads/uf.txt";
	        Path path = Paths.get(fileName);
	        Scanner scanner;
	    	int n = 10;
			UnionFind uf = new UnionFind(n);
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
	
	private int count() {
		
		return count;
	}
	private void union(int p, int q) {
		int rootP = find(p);
		int rootQ = find(q);
		if(rootP == rootQ) return;
		
		//make root of the smaller rank point to root of larger rank
		if(rank[rootP] < rank[rootQ])
			parent[rootP] = rootQ;
		else if(rank[rootP] > rank[rootQ])
			parent[rootQ] = rootP;
		else {
			parent[rootQ] = rootP;
			rank[rootP]++;
		}
		count--;
		
	}
	//returns the canonical element of the set containing element
	private int find(int p) {
		while(p != parent[p]) {
			parent[p] = parent[parent[p]]; //path compression by halving
			p = parent[p];
		}
		return p;
	}

}
