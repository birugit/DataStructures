package main.swamy.bst;

import java.util.Iterator;
import java.util.Stack;

/**
 * Iterative traversal of tree instead of recursive traversal
 * @author swamy
 * 
 * 			           11
					/	   \
				6			   19
			/	\            /     \
		   4		  8        17        43
		    \      \               /    \
		     5      10            31     49
 *
 */

public class TreeIterativeTraversal implements Iterable {
	Node root;
	class Node {
		Integer data;
		Node left, right;
		Node(int x){
			this.data = x;
			left = right = null;
		}
		
		public String toString() {
			return data.toString();
		}
		
	}
	
	private class PreOrderiterator implements Iterator{
		
		Stack<Node> stk = new Stack<Node>();
		public PreOrderiterator() {
			if( root != null)
				stk.push(root);
		}

		@Override
		public boolean hasNext() {
			
			return !stk.isEmpty();
		}

		@Override
		public Object next() {
			Node cur = stk.peek();
			if(cur.left != null) {
				stk.push(cur.left);
			}else {
				Node tmp = stk.pop();
				while(tmp.right == null) {
					if(stk.isEmpty())
						return cur.data;
					tmp = stk.pop();
				}
				stk.push(tmp.right);
			}
			
			return cur.data;
		}
		
	}
	
	public static void main(String[] args) {
		
		TreeIterativeTraversal t = new TreeIterativeTraversal();
	//	Integer[] a = {1,2,4,6,5,7,8,3};
		Integer[] a = {11, 6, 8, 19, 4, 10, 5, 17, 43, 49, 31};
		for(Integer n : a) {
			t.insert(n);
		}
		//PreOrderiterator p = new PreOrderiterator();
		System.out.println("Iterative preorder");
		Iterator it = t.iterator();
		while(it.hasNext() != false)
		System.out.print(it.next()+" ");
		System.out.println();
		//recursive call
		System.out.println("Recursive Preorder");
		preorder(t.root);
	}

	private static void preorder(Node root) {
		if(root == null)return;
		System.out.print(root.data+" ");
		preorder(root.left);
	
		preorder(root.right);
		
	}

	private void insert(Integer n) {
		root = insert(root, n);
		
	}

	private Node insert(Node root, Integer insert) {
		if(root == null)
			root = new Node(insert);
		int comp = insert.compareTo(root.data);
		if(comp < 0 )
			root.left = insert(root.left, insert);
		else if (comp > 0)
			root.right = insert(root.right, insert);
		else
			root.data = insert;
		
		return root;
	}


	@Override
	public Iterator iterator() {
		
		return new PreOrderiterator();
	}

}
