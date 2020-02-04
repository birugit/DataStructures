package main.swamy.bag.diagraph;

import java.util.Stack;

import main.swamy.linkedqueue.LinkedQueue;

//pre order, post order and reverse order
public class DepthFirstOrder {

	private boolean[] marked;
	private int[] pre;
	private int[] post;
	private LinkedQueue<Integer> preorder;
	private LinkedQueue<Integer> postorder;
	private int preCounter;
	private int postCounter;
	
	public DepthFirstOrder(Diagraph G) {
		pre = new int [G.V()];
		post = new int[G.V()];
		postorder = new LinkedQueue<Integer>();
		preorder = new LinkedQueue<Integer>();
		marked = new boolean[G.V()];
		for(int v = 0; v < G.V(); v++)
			if(!marked[v])
				dfs(G, v);
	}
	
	private void dfs(Diagraph G, int v) {
		marked[v] = true;
		pre[v] = preCounter++;
		preorder.enqueue(v);
		for(int w: G.adj(v)) {
			if(!marked[w]) {
				dfs(G, w);
			}
		}
		postorder.enqueue(v);
		post[v] = postCounter++;
		
	}

	public static void main(String[] args) {
		Diagraph G = new Diagraph();
		DepthFirstOrder dfo = new DepthFirstOrder(G);
		for(int v: dfo.pre()) {
			System.out.print(v + " ");
		}
		System.out.println();
		for(int v: dfo.post()) {
			System.out.print(v + " ");
		}
		System.out.println();
		for(int v: dfo.reversePost()) {
			System.out.print(v + " ");
		}
	}

	public Iterable<Integer> reversePost() {
		Stack<Integer> reverse = new Stack<Integer>();
		for(int v : postorder) {
			reverse.push(v);
		}
		return reverse;
	}

	public Iterable<Integer> post() {
		
		return postorder;
	}

	public Iterable<Integer> pre() {
	
		return preorder;
	}

}
