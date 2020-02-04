package main.swamy.bst;

import main.swamy.bst.FindSubTree.Node;
/**
 * Implement a function to check if a binary tree is a binary search tree.
 * @author swamy
 *min max solution
 *
 *left.data <= current.data < right.data
 */
public class CheckBST {
	Node root;
 public class Node{
	 int data;
	 
	 Node left,right;
	 Node(int x){
		 this.data = x;
		 left = right = null;
	 }
 }
 
 private void insert(Integer n) {
		
		root = insert(root, n);
		
	}



	private Node insert(Node root, Integer insert) {
		if(root == null) {
			root = new Node(insert);
		}
		int comp = insert.compareTo(root.data);
		if(comp < 0)
			 root.left = insert(root.left, insert);
		else if (comp > 0)
			root.right = insert(root.right, insert);
		else
			root.data = insert;
		return root;
	}

	public static void main(String[] args) {
		
		CheckBST t = new CheckBST();
		//Integer[] a = {20,10, 30,5,15,3,7,17};
		Integer[] a = {20,10, 30,25};
		for(int n : a) {
			t.insert(n);
		}
		System.out.println( t.isBST(t.root));
	}



	private boolean isBST(Node root) {
		int min = Integer.MIN_VALUE;
		int max = Integer.MAX_VALUE;
		return isBST( root,  min,  max);
		
	}



	private boolean isBST(Node root, int min, int max) {
		if(root == null)
			return true;
		
		if(root.data <= min || root.data > max )
			return false;
		
		if(!isBST(root.left,min,root.data) && 
				!isBST(root.right, root.data, max)) {
			return false;
		}
		return true;
	}

}
