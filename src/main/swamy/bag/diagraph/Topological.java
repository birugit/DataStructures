package main.swamy.bag.diagraph;

import java.io.IOException;
/**
 *  Topological} class represents a data type for 
 *  determining a topological order of a <em>directed acyclic graph</em> (DAG).
 *  A digraph has a topological order if and only if it is a DAG.
 *  The <em>hasOrder</em> operation determines whether the digraph has
 *  a topological order, and if so, the <em>order</em> operation
 *  returns one.
 * @author swamy
 * 
 * 
 * given a digraph, 
 * put the vertices in order such that all its directed edges point from a vertex earlier in the order to a vertex later in the order
 *  (or report that doing so is not possible). 
 * Topological.java solves this problem using depth-first search.
 *  Remarkably, a reverse postorder in a DAG provides a topological order.
 *  
 *  ***Additional things to do***
 *  Hamiltonian path in DAGs. Given a DAG, design a linear-time algorithm to determine whether there is a directed path that visits each vertex exactly once.
Solution: Compute a topological sort and check if there is an edge between each consecutive pair of vertices in the topological order.
 *
 */

public class Topological {

	private Iterable<Integer> order;
	private int[] rank;
	
	public Topological(Diagraph G) {
		DirectedCycle finder = new DirectedCycle(G);
		if(!finder.hasCycle()) {
			DepthFirstOrder dfs = new DepthFirstOrder(G);
			order = dfs.reversePost();
			rank = new int[G.V()];
			int i= 0;
			for(int v: order)
				rank[v] = i++;
		}
	}
	
	public Iterable<Integer>  order(){
		return order;
	}
	
	public int rank(int v) {
		if(hasOrder())
			return rank[v];
		else return -1;
	}
	
	public boolean hasOrder() {
		return order != null;
	}
	public static void main(String[] args) throws IOException {
	//	String fileName = "https://algs4.cs.princeton.edu/42digraph/routes.txt";
		String fileName = "https://algs4.cs.princeton.edu/42digraph/jobs.txt";// for URL
	//	String fileName = "/Users/swamy/Downloads/job.rtf";//File type
		
		String delimeter = "/";
		SymbolDiagraph sg = new SymbolDiagraph(fileName, delimeter);
		Topological t = new Topological(sg.diagraph());
		System.out.println("**Topological Order**");
		for(int v: t.order) {
			System.out.println(sg.name(v));
		}

	}

}
