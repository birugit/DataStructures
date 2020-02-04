package main.swamy.bag.diagraph;

import main.swamy.bag.Bag;

public class Diagraph {
	
	private static final String NEWLINE = System.getProperty("line.separator");
	
	private final int V;		//number of vertices in this diagraph
	private int E;			//number of edges
	private Bag<Integer>[] adj;	//adj[v] = adjacency list for vertex v;
	private int[] indegree;		//indegree[v] = indegree of vertex v
	
	
	public Diagraph(int V) {
		if(V < 0 ) throw new IllegalArgumentException();
		this.V = V;
		this.E = 0;
		indegree = new int[V];
		adj = (Bag<Integer>[]) new Bag[V];
		for(int v = 0; v < V; v++) {
			adj[v] = new Bag<Integer>();
		}
	}
	
	public Diagraph() {
	//	this(13);
		//with cycle
	/*	
		addEdge(4,2);
		addEdge(2,3);
		addEdge(3,2);
		addEdge(6,0);
		addEdge(0,1);
		addEdge(2,0);
		addEdge(11,12);
		addEdge(12,9);
		addEdge(9,10);
		addEdge(9,11);
		addEdge(7,9);
		addEdge(10,12);
		addEdge(11,4);
		addEdge(4,3);
		addEdge(3,5);
		addEdge(6,8);
		addEdge(8,6);
		addEdge(5,4);
		addEdge(0,5);
		addEdge(6,4);
		addEdge(6,9);
		addEdge(7,6);*/
		
		//wihtout cycle
	/*	addEdge(2,3);
		addEdge(0,6);
		addEdge(0,1);
		addEdge(2,0);
		addEdge(11,12);
		addEdge(9,12);
		addEdge(9,10);
		addEdge(9,11);
		addEdge(3,5);
		addEdge(8,7);
		addEdge(5,4);
		addEdge(0,5);
		addEdge(6,4);
		addEdge(6,9);
		addEdge(7,6);*/
		
		//wiht Cycle small
		this(4);
		addEdge(0,1);
		addEdge(0,2);
		addEdge(1,2);
		addEdge(2,0);
		addEdge(2,3);
		addEdge(3,3);
	}
	
	public int indegree(int v) {
		return indegree[v];
	}
	//Adds the directed edge v-->w to this diagraph
	public void addEdge(int v, int w) {
		adj[v].add(w);
		indegree[w]++;
		E++;
		//adj[w].add(v);
		
	}
	
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(V + "vertices, " + E + "edges" + NEWLINE);
		for(int v = 0; v < V; v++) {
			sb.append(String.format("%d", v));
			sb.append(": ");
			for(int w: adj[v]) {
				sb.append(String.format("%d", w));
				sb.append(" ");
			}
			sb.append(NEWLINE);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		Diagraph dg = new Diagraph();
		System.out.println(dg);
	}

	public int V() {
		
		return V;
	}

}
