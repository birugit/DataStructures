package main.swamy.bag.diagraph;

import java.util.Queue;

import main.swamy.linkedqueue.LinkedQueue;

/**
 * Queue-based topological order algorithm. Develop a nonrecursive topological sort implementation TopologicalX.
 * java that maintains a vertex-indexed array that keeps track of the indegree of each vertex. 
 * Initialize the array and a queue of sources in a single pass through all the edges, as in Exercise 4.2.7. 
 * Then, perform the following operations until the source queue is empty:
Remove a source from the queue and label it.
Decrement the entries in the indegree array corresponding to the destination vertex of each of the removed vertex's edges.
If decrementing any entry causes it to become 0, insert the corresponding vertex onto the source queue.
 * @author swamy
 *
 */
public class TopologicalX {
	private LinkedQueue<Integer> order;		//verticies in topological order
	private int[] ranks;					//ranks[v] = order where vertex v appears in order
	
	public TopologicalX(Diagraph g) {
		//indegree of remaining verticies
		int[] indegree = new int[g.V()];
		for(int v = 0; v<g.V(); v++) {
			indegree[v] = g.indegree(v);
		}
		
		//initialize
		ranks = new int[g.V()];
		order =  new LinkedQueue<Integer>();
		int count = 0;
		
		//initialize queue to contain all vertices with indegree = 0
		LinkedQueue<Integer> queue = new LinkedQueue<Integer>();
		for(int v=0; v<g.V(); v++) {
			if(indegree[v] == 0) {
				queue.enqueue(v);
			}
		}
		
		while(!queue.isEmpty()) {
			int v = queue.dequeue();
			order.enqueue(v);
			ranks[v] = count++;
			for(int w : g.adj(v)) {
				indegree[w]--;
					if(indegree[w] == 0) {
						queue.enqueue(w);
					}
			}
		}
		
		//there is a directed cycle in subgraph of vertices with indegree >= 1
		if(count != g.V()) {
			order = null;
		}
	}

	public static void main(String[] args) {
		Diagraph dg = new Diagraph(13);
		//wihtout cycle
			dg.addEdge(2,3);
			dg.addEdge(0,6);
			dg.addEdge(0,1);
			dg.addEdge(2,0);
			dg.addEdge(11,12);
			dg.addEdge(9,12);
			dg.addEdge(9,10);
			dg.addEdge(9,11);
			dg.addEdge(3,5);
			dg.addEdge(8,7);
			dg.addEdge(5,4);
			dg.addEdge(0,5);
			dg.addEdge(6,4);
			dg.addEdge(6,9);
			dg.addEdge(7,6);
			
			//wiht Cycle small
			//this(4);
		/*	dg.addEdge(0,1);
			dg.addEdge(0,2);
			dg.addEdge(1,2);
			dg.addEdge(2,0);
			dg.addEdge(2,3);
			dg.addEdge(3,3);*/
			
			TopologicalX t = new TopologicalX(dg);
			if(!t.hasOrder()) {
				System.out.println("No Topological Order");
			}else {
				System.out.println("Topological order");
				for(int v: t.order()) {
					System.out.print(v+" ");
				}
			}

	}

	private Iterable<Integer> order() {
		
		return order;
	}

	private boolean hasOrder() {
		
		return order != null;
	}
	
	public int rank(int v) {
		if(hasOrder())
			return ranks[v];
		else
			return -1;
	}

}
