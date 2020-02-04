package main.swamy.shortestpath;

import java.util.Stack;

import main.swamy.bag.diagraph.DirectedEdge;
import main.swamy.bag.diagraph.EdgeWeightedDiagraph;
import main.swamy.pq.IndexMinPQ;

/*
 * he {@code DijkstraSP} class represents a data type for solving the
 *  single-source shortest paths problem in edge-weighted digraphs
 *  where the edge weights are nonnegative.
 *  <p>
 *  This implementation uses <em>Dijkstra's algorithm</em> with a
 *  <em>binary heap</em>. The constructor takes
 *  &Theta;(<em>E</em> log <em>V</em>) time in the worst case,
 *  where <em>V</em> is the number of vertices and <em>E</em> is
 *  the number of edges. Each instance method takes &Theta;(1) time.
 *  It uses &Theta;(<em>V</em>) extra space (not including the
 *  edge-weighted digraph).
 * 
 */
public class Dijkstra {

	private double[] distTo;
	private DirectedEdge[] edgeTo;
	private IndexMinPQ<Double> pq;
	
	public Dijkstra(EdgeWeightedDiagraph G, int s) {
		for(DirectedEdge e: G.edges()) {
			if(e.weight() < 0) {
				throw new IllegalArgumentException();
			}
		}
			
			distTo = new double[G.V()];
			edgeTo = new DirectedEdge[G.V()];
			
			for(int v =0; v< G.V(); v++) {
				distTo[v] = Double.POSITIVE_INFINITY;
			}
			distTo[s] = 0.0;
			
			//relax vertices in order of distance from s
			pq = new IndexMinPQ<Double>(G.V());
			pq.insert(s, distTo[s]);
			
			while(!pq.isEmpty()) {
				int v = pq.delMin();
				for(DirectedEdge e: G.adj(v))
					relax(e);
			}
		
	}
	
	//relax edge e and updated pq if changed
	private void relax(DirectedEdge e) {
		int v = e.from();
		int w = e.to();
		
		if(distTo[w] > distTo[v] + e.weight()) {
			distTo[w] = distTo[v] + e.weight();
			edgeTo[w] = e;
			if(pq.contains(w))
				pq.decreaseKey(w, distTo[w]);
			/*
			 *   validateIndex(i);
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        if (keys[i].compareTo(key) == 0)
            throw new IllegalArgumentException("Calling decreaseKey() with a key equal to the key in the priority queue");
        if (keys[i].compareTo(key) < 0)
            throw new IllegalArgumentException("Calling decreaseKey() with a key strictly greater than the key in the priority queue");
        keys[i] = key;
        swim(qp[i]);
			 */
			else {
				pq.insert(w, distTo[w]);
			}
		}
		
	}
	
	
	  public boolean hasPathTo(int v) {
	  //      validateVertex(v);
	        return distTo[v] < Double.POSITIVE_INFINITY;
	    }

	
	   public Iterable<DirectedEdge> pathTo(int v) {
	      //  validateVertex(v);
	        if (!hasPathTo(v)) return null;
	        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
	        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
	            path.push(e);
	        }
	        return path;
	    }
	   
	    public double distTo(int v) {
	        //validateVertex(v);
	        return distTo[v];
	    }


	public static void main(String[] args) {
		
		EdgeWeightedDiagraph G= new EdgeWeightedDiagraph();
		int s = 0;
		Dijkstra sp = new Dijkstra(G,s);
		   // print shortest path
        for (int t = 0; t < G.V(); t++) {
            if (sp.hasPathTo(t)) {
            		System.out.println();
            		System.out.print( s+"-->" +t+":"+ sp.distTo(t));
               
                for (DirectedEdge e : sp.pathTo(t)) {
                  
                    System.out.println(e+" ");
                }
                System.out.println();
            }
            else {
              System.out.println("s:"+s+"t:"+t);
            }
        }
    }
	

}
