package main.swamy.bag.diagraph;
/**
 * DirectedEdge} class represents a weighted edge in an 
 *  {@link EdgeWeightedDigraph}. Each edge consists of two integers
 *  (naming the two vertices) and a real-value weight. The data type
 *  provides methods for accessing the two endpoints of the directed edge and
 *  the weight.
 * @author swamy
 *
 */
public class DirectedEdge {
	private final int v;
	private final int w;
	private final double weight;
	
	public DirectedEdge(int v, int w, double weight) {
		if(v < 0) throw new IllegalArgumentException();
		if(w < 0) throw new IllegalArgumentException();
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
	//return tail vertex of directed edge
	public int from() {
		return v;
	}
	
	//return the head vertex of the directed edge
	public int to() {
		return w;
	}
	
	//returns the weight of the directed edge
	public double weight() {
		return weight;
	}
	
	public String toString() {
		return v + "-->" + w +" "+weight;
	}
	

	public static void main(String[] args) {
		DirectedEdge e = new DirectedEdge(12, 34, 5.67);
		System.out.println(e);

	}

}
