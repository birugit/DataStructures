package main.swamy.bag.diagraph;

import java.util.Stack;

/**
 * 
 * Detects the cycle in a directed Graph
 * 
 * class represents a data type for 
 *  determining whether a digraph has a directed cycle.
 *  The <em>hasCycle</em> operation determines whether the digraph has
 *  a simple directed cycle and, if so, the <em>cycle</em> operation
 *  returns one.
 * @author swamy
 *
 */
public class DirectedCycle {
	private boolean[] marked;
	private int[] edgeTo;
	private boolean[] onStack;		//onStack[v] = is vertex on the stack
	private Stack<Integer> cycle; 	//directed cycle (or null of no such cycle)
	
	public DirectedCycle(Diagraph G) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		onStack = new boolean[G.V()];
		for(int v = 0; v < G.V(); v++)
			if(!marked[v] && cycle == null)
				dfs(G, v);
	}
	
	//check that either the topological order or cycle
	private void dfs(Diagraph G, int v) {
		onStack[v] = true;
		marked[v] = true;
		for(int w: G.adj(v))
		{
			//short circuit if directed cycle found
			if(cycle != null ) {
				return;
			}
			//found new vertex, so recur
			else if(!marked[w]) {
				edgeTo[w] = v;
				dfs(G, w);
			}			
			//trace back directed cycle
			else if(onStack[w]) {
				cycle = new Stack<Integer>();
				for(int x = v; x != w; x = edgeTo[x]) {
					cycle.push(x);
				}
				cycle.push(w);
				cycle.push(v);
			}
		}
		onStack[v] = false;
	}
	
	//Does the diagraph have a directed cycle
	public boolean hasCycle() {
		return cycle != null;
	}
	
	//Returns the directed cycle, if the diagraph has a directed cycle
	
	public Iterable<Integer> cycle(){
		return cycle;
	}
	public static void main(String[] args) {
			Diagraph dg = new Diagraph();
			DirectedCycle finder = new DirectedCycle(dg);
			if(finder.hasCycle()) {
				System.out.println("Directed Cycle");
				for(int v: finder.cycle()) {
					System.out.print(v +" ");
				}
				System.out.println();
			}
			else {
				System.out.println("No cycle detected");
			}
	}

}
