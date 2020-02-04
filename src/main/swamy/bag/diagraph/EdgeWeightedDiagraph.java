package main.swamy.bag.diagraph;

import main.swamy.bag.Bag;

/*
 * EdgeWeightedDigraph} class represents a edge-weighted
 *  digraph of vertices named 0 through <em>V</em> - 1, where each
 *  directed edge is of type {@link DirectedEdge} and has a real-valued weight.
 *  It supports the following two primary operations: add a directed edge
 *  to the digraph and iterate over all of edges incident from a given vertex.
 *  It also provides methods for returning the indegree or outdegree of a
 *  vertex, the number of vertices <em>V</em> in the digraph, and
 *  the number of edges <em>E</em> in the digraph.
 *  Parallel edges and self-loops are permitted.
 * 
 */
public class EdgeWeightedDiagraph {
	
	private static final String NEWLINE = System.getProperty("line.separator");
	
	private final int V;
	private  int E;
	private Bag<DirectedEdge>[] adj;
	private int[] indegree;
	
	public EdgeWeightedDiagraph(int V) {
		this.V = V;
		this.E = 0;
		this.indegree = new int[V];
		adj = (Bag<DirectedEdge>[]) new Bag[V];
		for(int v = 0; v < V; v++)
			adj[v] = new Bag<DirectedEdge>();
	}
	
	public EdgeWeightedDiagraph() {
		this(8);
		DirectedEdge e = new DirectedEdge(4,5,0.35);
		addEdge(e);
		addEdge(new DirectedEdge(5,4,0.35));
		addEdge(new DirectedEdge(4,7,0.37));
		addEdge(new DirectedEdge(5,7,0.28));
		addEdge(new DirectedEdge(7,5,0.28));
		addEdge(new DirectedEdge(5,1,0.32));
		addEdge(new DirectedEdge(0,4,0.38));
		addEdge(new DirectedEdge(0,2,0.26));
		addEdge(new DirectedEdge(7,3,0.39));
		addEdge(new DirectedEdge(1,3,0.29));
		addEdge(new DirectedEdge(2,7,0.34));
		addEdge(new DirectedEdge(6,2,0.40));
		addEdge(new DirectedEdge(3,6,0.52));
		addEdge(new DirectedEdge(6,0,0.58));
		addEdge(new DirectedEdge(6,4,0.93));
	}

	private void addEdge(DirectedEdge e) {
		int v = e.from();
		int w = e.to();
		adj[v].add(e);
		indegree[w]++;
		E++;
		
	}
	
	public int V() {
		return V;
	}

	public int E() {
		return E;
	}
	
	public Iterable<DirectedEdge> adj(int v){
		return adj[v];
	}
	
	public int outdegree(int v) {
		return adj[v].size();
	}
	
	public int indegree(int v) {
		return indegree[v];
	}
	
	
	public Iterable<DirectedEdge> edges(){
		Bag<DirectedEdge> list = new Bag<DirectedEdge>();
		for(int v=0; v<V; v++) {
			for(DirectedEdge e: adj(v)) {
				list.add(e);
			}
		}
		return list;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(V + " "+E +NEWLINE);
		for(int v = 0; v < V; v++) {
			sb.append(v+":");
			for(DirectedEdge e: adj[v]) {
				sb.append(e+" ");
			}
			sb.append(NEWLINE);
			
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		EdgeWeightedDiagraph ed = new EdgeWeightedDiagraph();
		System.out.println(ed);

	}

}
