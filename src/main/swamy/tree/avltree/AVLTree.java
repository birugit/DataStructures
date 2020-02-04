package main.swamy.tree.avltree;

public class AVLTree {
	
	class Node {
		int key, height;
		Node left, right;
		
		Node(int d){
			key = d;
			height = 1;
		}
	}
	
	Node root;
	
	

	public static void main(String[] args) {
		AVLTree tree = new AVLTree();
		
		tree.root = tree.insert(tree.root, 10);
		tree.root = tree.insert(tree.root, 20);
		tree.root = tree.insert(tree.root, 30);
		tree.root = tree.insert(tree.root, 40);
		tree.root = tree.insert(tree.root, 50);
		tree.root = tree.insert(tree.root, 25);

		System.out.println("Preorder traversal:");
		tree.preorder(tree.root);

	}



	private void preorder(Node node) {
		if(node != null) {
			System.out.println(node.key + " ");
			preorder(node.left);
			preorder(node.right);
		}
		
	}



	private Node insert(Node node, int key) {
		
		//1. perform the normal BST insertion
		if(node == null)
			return new Node(key);
		
		if(key < node.key)
			node.left = insert(node.left, key);
		else if(key > node.key)
			node.right = insert(node.right, key);
		else //duplicate keys not allowed
			return node;
		
		//2. Update height of this ancestor node
		 node.height = 1 + max(height(node.left), 
				 height(node.right));
		 
		 
		 //3. Get the balance factor of this ancestor node
		 //to check whether this node became unbalanced
		 
		 int balance = getBalance(node);
		 
		 //if this node becomes unbalanced, then there are 4 cases
		 // A) Left Left case
		 if(balance < -1) {
			 
			 if( getBalance(node.right) > 0)
				 node.right = rightRotate(node.right);
			 
			return leftRotate(node);
		 }else if(balance > 1) {
			 if( getBalance(node.left) < 0)
				 node.left = rightRotate(node.left);
			 return rightRotate(node);
		 }
		 /*	 if(balance > 1 && key < node.left.key)
			 return rightRotate(node);
		 
		 //B) Right Right case
		 if(balance < -1 && key > node.right.key)
			 return leftRotate(node);
		 
		 //C) Left Right case
		 if(balance > 1 && key > node.left.key) {
			 node.left = leftRotate(node.left);
			 return rightRotate(node);
		 }
		 //D) Right Left case
		 if(balance < -1 && key > node.right.key) {
			 node.right = rightRotate(node.right);
			 return leftRotate(node);
		 }*/
		 
		 //return the node pointer
		return node;
	}


/*private Node leftRotate(Node x) {
		/*Node y = x.right;
		//Node t2 = y.left;
		
		//perform rotation
		//y.left = x;
		x.right = t2;
		
		//update the heights
		x.height = max(height(x.left), height(x.right));
		y.height = max(height(y.left), height(y.right));
		
		return y;
	
		
		
		
	}*/

private Node leftRotate(Node x) {
	Node y = x.right;
	x.right = y.left;
	y.left = x;
	
	

	x.height = max(height(x.left), height(x.right));
	y.height = max(height(y.left), height(y.right));
	
	return y;
}

private Node rightRotate(Node x) {
	
	Node y = x.left;
	x.left = y.right;
	y.right = x;
	
	//rotate

	
		x.height = max(height(x.left), height(x.right));
		y.height = max(height(y.left), height(y.right));
		
		return y;
}


/*private Node rightRotate(Node y) {
		Node x = y.left;
		Node t2 = x.right;
		
		//perform the rotation
		x.right = y;
		y.left = t2;
		
		//update the heights
		y.height = max(height(y.left), height(y.right));
		x.height = max(height(x.left), height(x.right));
		
		//return new root
		return x;
	}
	
	*/



private int getBalance(Node node) {
		if(node == null)
		return 0;
		
		return height(node.left) - height(node.right);
	}



//get max height of two 
	private int max(int a, int b) {
		
		return (a > b ) ? a : b;
	}



	private int height(Node node) {
		if(node == null)
			return 0;
		
		
		return node.height;
	}

}
