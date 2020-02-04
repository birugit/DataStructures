package main.swamy.bag.diagraph;

import java.util.Stack;

/**
 * Determines the strong components in a digraph
 * @author swamy
 *
 */
public class TarjanSCC {
	
	private boolean[] marked;		//marked[v] = has v been visited?
	private int[] id;				//id[v] = id of strong component containing
	private int [] low;				//low[v] = low number of v
	private int pre;					//preorder number counter
	private int count;				//number of strongly connected components
	private Stack<Integer> stack;
	public TarjanSCC(Diagraph G) {
		marked = new boolean[G.V()];
		stack = new Stack<Integer>();
		id = new int[G.V()];
		low = new int[G.V()];
		for(int v = 0; v < G.V(); v++) {
			if(!marked[v])
				dfs(G,v);
		}
	}
	private void dfs(Diagraph G, int v) {
		marked[v] = true;
		low[v]	= pre++;
		int min = low[v];
		stack.push(v);
		for(int w: G.adj(v)) {
			if(!marked[w])
				dfs(G, w);
			if	(low[w] < min)
				min = low[w];
		}
		if	(min < low[v]) {
			low[v] = min;
			return;
		}
		int w;
		do {
			w = stack.pop();
			id[w] = count;
			low[w] = G.V();
		}while(w != v);
		count++;
	}
	public int count() {
		return count;
	}
	public static void main(String[] args) {
		Diagraph G = new Diagraph();
		TarjanSCC scc = new TarjanSCC(G);
		
		int m = scc.count();
		System.out.println(m+" components");

	}

}
