package main.swamy.bst;

import main.swamy.bst.CheckBST.Node;

/**
 * Write an algorithm to find the 'next'node (i.e., in-order successor) of a given node in a binary search tree. 
 * You may assume that each node has a link to its parent.
 * @author swamy
 *left -> current -> right
 *
 *But what if the node doesn't have a right subtree? This is where it gets a bit trickier.
 *
 *ANs:
 *If a node n doesn't have a right subtree, then we are done traversing n's subtree. 
 *We need to pick up where we left off with n's parent, which we'll call q.
 *
 */
public class InOrderSuccessor {
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
		Integer[] a = {20,10, 30,5,15,3,7,17};
		InOrderSuccessor t = new InOrderSuccessor();
		
		for(int n : a) {
			t.inorderSucc(t.root);
		}

	}



	private Node inorderSucc(Node root) {
		if(root == null)
			return null;
		/*
		 * Found right children -->return left most node of right subtree
		 */
		if(root.right != null) {
			lefmostchild(root.right);
		}else {
			Node q = root;
		//	Node x = q.data;
		}
		
	}



	private Node lefmostchild(Node right) {
		if(right == null) return null;
		while(right.left != null) {
			right = right.left;
		}
		return right;
		
	}

}
