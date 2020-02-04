package main.swamy.bst;

/*
 * 
 * You have two very large binary trees: Tl, with millions of nodes, and T2, with hundreds of nodes. 
 * Create an algorithm to decide ifT2 is a subtree ofTl.

A tree T2 is a subtree of Tl if there exists a node n in Tl such that the subtree of n is identical to T2. 
That is, if you cut off the tree at node n, the two trees would be identical.

						11
					/	   \
				6			   19
			/	\            /     \
		   4		  8        17        43
		    \      \               /    \
		     5      10            31     49




 */
public class FindSubTree implements Comparable{
	Node root;
 public class Node{
	 int data;
	 
	 Node left,right;
	 Node(int x){
		 this.data = x;
		 left = right = null;
	 }
 }
	public static void main(String[] args) {
		Integer[] a = {11, 6, 8, 19, 4, 10, 5, 17, 43, 49, 31}; //BST Tree 1
		FindSubTree t1 = new FindSubTree();
		for(Integer n:a){
			System.out.println("items:"+n);
			t1.insert(n);
		}
		
		//Integer[] b = { 43, 49, 31}; //BST subtree T2  true
		//Integer[] b = { 6, 4, 8}; //BST subtree T29 false
		Integer[] b = { 19, 17, 43,31,49}; //BST subtree T2 true
		FindSubTree t2 = new FindSubTree();
		for(Integer n:b){
			System.out.println("items2:"+n);
			t2.insert(n);
		}
		
		System.out.println(containsTree(t1.root,t2.root));

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



	static boolean  containsTree(Node t1, Node t2) {
		if(t2 == null) {//empty tree is a always subtree
			return true;
		}
		return subTree(t1, t2);
	}




	private static boolean subTree(Node t1, Node t2) {
		if(t1 == null) {
			return false; // big tree empty and subtree still not found.
		}
		
		if(t1.data == t2.data) {
			System.out.println("t1 data = t2 data:"+t1.data +" = "+t2.data );
			if(matchTree(t1,t2)) return true;
			
		}
		return (subTree(t1.left,t2) || subTree(t1.right, t2));
	}




	private static boolean matchTree(Node t1, Node t2) {
		if(t2 == null && t1 == null) {//if both are empty return true
			return true;// nothing left in the subtree
		}
		//if one, but not both are empty
		if(t1 == null || t2 == null) {
			return false;
		}
		if(t1.data != t2.data) {
			System.out.println("t1 data != t2 data:"+t1.data +" = "+t2.data );
			return false;// data doesn't match
		}
		System.out.println("t1 data = t2 data:"+t1.data +" = "+t2.data );
		return (matchTree(t1.left, t2.left) && matchTree(t1.right, t2.right));
	}



	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
