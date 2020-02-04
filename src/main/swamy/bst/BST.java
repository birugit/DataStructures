package main.swamy.bst;

import java.util.NoSuchElementException;

import main.swamy.linkedqueue.LinkedQueue;

public class BST<Key extends Comparable<Key>> {
	
	Node root;
	
	public class Node{
		private Key data;
		int height = 1;
		private Node left, right;
		private int size;
		public Node(Key toInsert,int size, int height){
			data = (Key) toInsert;
			this.size = size;
			this.height = height;
		}
	
	
		public String toString(){
			System.out.println("BST nodeß:"+data.toString());
		/*	StringBuffer sb = new StringBuffer();
			for(Node n : this){
			sb.append(n.data.toString());
		*/
			return data.toString();
	}
}

	
	
	public BST() {
		root = null;
		//int insert = 1;
		//Node root = new Node(insert,1);
	}

	

	
	public static void main(String[] args){
//	Integer[] a = {3,5,7,10,15,17,20,30};
		//Integer[] a = {30,5,3,40,35,10,17};
	//	Integer[] a = {20,10,30,5,15,3,7,17};// check Binary tree is a Binary Search Tree
	//	Integer[] a = {3,1,5,8};// find common ancestor , send a nonexisting element for search	
	//	Integer[] a = {1,1,1,2,3,1,1,4,2,-1,5,6};
		//Integer[] a = {2,3,1,1,1,1,4,2,-1,5,2};//find sum
		//Integer[] a = {11, 6, 8, 19, 4, 10, 5, 17, 43, 49, 31}; //BST
		Integer[] a = {10, 20, 30, 40, 50, 25}; //AVL Tree
		BST<Integer> bst = new BST<Integer>();
		for(Integer n:a){
			System.out.println("items:"+n);
			bst.insert(n);
		}
		
		
		
	//	bst.delete(10);
	//	System.out.println("del:"+bst.delete(7));
		System.out.println("BST:"+bst.toString());
		System.out.println("get:"+bst.get(5));
		System.out.println("Min:"+bst.min());
		System.out.println("Max:"+bst.max());
		
		
	//	System.out.println("is Balanced:"+isBalanced());
		
		
		for(Integer i:bst.levelOrder()){
			System.out.println(i+" :levelorder"+bst.get(i));
		}
		
		System.out.println("*preOrder Traversal*");
		bst.preOrder();
		System.out.println();
		System.out.println("*InOrder Traversal*");
		bst.inOrder();
		/*for(Integer i:bst.keys()){
			System.out.println("keys:"+bst.get(i));
		}*/
		
		//find common ancestor of two nodes, without links to parents, not a BST
		
		
	}

	private void preOrder() {


		    preOrder(root);
		
	}
	private void inOrder() {


		inOrder(root);
	
}




	private void preOrder(BST<Key>.Node root) {
        if (root != null) { 
            System.out.print(root.data + " "); 
            preOrder(root.left); 
            preOrder(root.right); 
        } 
		
	}
	private void inOrder(BST<Key>.Node root) {
        if (root != null) { 
            
        	inOrder(root.left); 
            System.out.print(root.data + " "); 
            inOrder(root.right); 
        } 
		
	}




	public int height() {
		return height(root);
	}
	
	public int height(Node root) {
		if(root == null) return -1;
		return root.height;
	}
	//check Binary tree as Binary Search Tree
	//formula: left.data <= current.data < right.data
	//branch left, right(max) get updated, 
	//branch right, left(min) get updated.
	boolean checkBST(Node n) {
		//Integer minValue = Integer.MIN_VALUE;
	//	return checkBST(n, Integer.MIN_VALUE, Integer.MAX_VALUE);
	return false;
	}

	private boolean checkBST(BST<Key>.Node n, Key minValue, Key maxValue) {
		if(n == null) {
			return true;
		}

		if((int)n.data <= (int)minValue || (int)n.data > (int)maxValue) {
			return false;
		}
		if(!checkBST(n.left, minValue, n.data) ||
				!checkBST(n.right, n.data, maxValue)	) {
			return false;
		}
	return true;
}


	//find sum
	public void findSum(Node root, int sum){
		int depth = depth(root);
		int[] path = new int[depth];
		findSum(root,sum,path,0);
	}
	
	private void findSum(BST<Key>.Node node, int sum, int[] path, int level) {
		if(node == null)
			return;
		
		//insert current node into path
		path[level] = (int) node.data;
		
		//look for paths that ends at this node
		int t = 0;
		for(int i= level; i>=0; i--){
			t += path[i];
			if(t == sum){
				print(path,i,level);
			}
		}
		
		//find beneath this nodes
		
		findSum(node.left,sum,path,level+1);
		findSum(node.right,sum,path,level+1);
		
		//Remove current node from path, not strictly necessary, since we would ignore this value, but its good practice
		path[level] = Integer.MIN_VALUE;
	}


	private void print(int[] path, int start, int end) {
		for(int i=start; i<=end; i++){
			System.out.print(path[i]+" ");
		} 
		System.out.println();
	}




	private int depth(BST<Key>.Node node) {
		if(node == null) return 0;
		else
			return 1+ Math.max(depth(node.left),depth(node.right));
		
	}




	//private static void commonAncestor(Node p, Node q) {
		
	//	commonAncestor(root,p,q);
	//}
//O(n) time
	//1l 3n 5r 8r
	private Node commonAncestor(Node root,Node p, Node q){
		/*if(!covers(root,p)||!covers(root,q)){
			return null;//Error check
		}
		return commonAncesotHelper(root,p,q);*/
		Result r = commonAncesotHelper(root, p, q);
		if(r.isAncestor) {
			return r.node;
		}
		return null;
		
	}

	private Result commonAncesotHelper(BST<Key>.Node root, BST<Key>.Node p, BST<Key>.Node q) {
		// TODO Auto-generated method stub
		if(root == null)
			return new Result(null, false);
		if(root == p || root == q)
			return new Result(root, true);
		
		Result rx = commonAncesotHelper(root.left, p, q);
		if(rx.isAncestor) {//Found common ancestor
			return rx;
		}
		
		Result ry = commonAncesotHelper(root.right, p, q);
		if(ry.isAncestor) {//Found common ancestor
			return ry;
		}
		
		if(rx.node != null && ry.node != null) {
			return new Result(root, true);//This is common ancestor
		}else if(root == p || root == q) {
			/*
			 * if we're currently at p or q, and we also found one of those
			 * nodes in a subtree, then this is truly an ancestor
			 * and the flag should be true
			 */
			boolean isAncestor = rx.node != null || ry.node != null?true : false;
			
			return new Result(root, isAncestor);
		}else {
			return new Result(rx.node != null ? rx.node : ry.node, false);
		}
	
		//just commented , added above to fix null bug
	/*	boolean is_p_on_left = covers(root.left,p);
		boolean is_q_on_left = covers(root.left,q);
		//if both are on different sides return root
		if(is_p_on_left != is_q_on_left) return root;
		//Else they are on same side traverse this side
		Node child = is_p_on_left? root.left:root.right;
		return commonAncesotHelper(child, p, q);*/
		
	}

	private boolean covers(BST<Key>.Node root, BST<Key>.Node p) {
		// TODO Auto-generated method stub
		if(root == null)
			return false;
		if(root == p)
			return true;
		return covers(root.left,p) || covers(root.right,p);
		
	}

	private Node inOrderSucc(Node n){
		if(n == null)
			return null;
		/*
		 * Found right children return , return leftmost child of right subtree
		 * */
		if(n.right != null){
			return leftmostChild(n.right);
		}
			else{
				Node q = n;
				Node x = (BST<Key>.Node) q.data;
				
			}
		return n;
		
	}



	private BST<Key>.Node leftmostChild(BST<Key>.Node right) {
		// TODO Auto-generated method stub
		return null;
	}




	private Key delete(Key key){
		if(key == null)throw new NoSuchElementException("Empty Element");
		root = delete(root,key);
		return root.data;
	}

	private BST<Key>.Node delete(BST<Key>.Node x, Key key) {
		if(x == null) return null;
		int comp = key.compareTo(x.data);
		if(comp < 0) x.left = delete(x.left,key);
		else if(comp > 0) x.right = delete(x.right,key);
		else{
			if(x.right == null) return x.left;
			if(x.left == null) return x.right;
			Node t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		x.size = size(x.left) + size(x.right) + 1;
		return x;
	}




	private BST<Key>.Node deleteMin(BST<Key>.Node x) {
		if(x.left == null) return x.right;
		x.left = deleteMin(x.left);
		x.size = size(x.left) + size(x.right) +1;
		return x;
	}




	void insert(Key data) {
		root = insert(root, data);
		
	}

	
	private Node insert(Node p, Key toInsert) {
		if(p == null)
			return new Node(toInsert, 1, 0);
	
		int comp = toInsert.compareTo((Key) p.data);
		if(comp < 0)
			 p.left = insert(p.left,toInsert);
		else if (comp > 0 )
			 p.right =  insert(p.right, toInsert);
		else 
		   p.data = toInsert;
		
		p.size = 1 + size(p.left) + size(p.right);
		p.height = 1 + Math.max(height(p.left), height(p.right));
		return balance(p);
	}
	
	//RED-BLACK and AVL are balanced trees ,
	//RedBlack tree good for insertions n deletions
	
	//AVL Trees are logn performance and strictly height balanced. Good for Searching.
	private BST<Key>.Node balance(BST<Key>.Node p) {
		// AVL Tree: left and right height difference  at a node should be -1 , 0 , 1.
		//Other wise , if its < -1 rotateLeft
		// if its > 1 rotate Right
		// < -1 and right node height diff > 0 ==> first rotateRight with right node, then rotateLeft with that node.
		// > 1 and left node diff < 0 ==> first rotate Left with Left nodem then rotate right with that node.
		int bf = balanceFactor(p);
		if(bf < -1) {
			bf = balanceFactor(p.right);
			if(bf > 0) {//case: Right Left
				p.right = rotateRight(p.right);
			}
			//case Right Right
			p = rotateLeft(p);
		}
		else if ( bf > 1) {
			bf =  balanceFactor(p.left);
			if(bf < 0) {//case : Left Right
				p.left = rotateLeft(p.left);
			}
			//Case: Left Left
			p = rotateRight(p);
		}
		return p;
	}




	private BST<Key>.Node rotateRight(BST<Key>.Node x) {
		Node y = x.left;
		x.left = y.right;
		y.right = x;
		x.height = 1+ Math.max(height(x.left), height(x.right));
		y.height = 1 + Math.max(height(y.left), height(y.right));
		return y;
	}

	private BST<Key>.Node rotateLeft(BST<Key>.Node x) {
		Node y = x.right;
		x.right = y.left;
		y.left = x;
		x.height = 1+ Math.max(height(x.left), height(x.right));
		y.height = 1 + Math.max(height(y.left), height(y.right));
		return y;
	}



	private int balanceFactor(BST<Key>.Node p) {
		
		return height(p.left) - height(p.right);
	}




	public Key get(Key i){
		
		findSum(root, 5);
		//System.out.println("is balanced:"+isBalancedImproved(root));
		//System.out.println("is balanced:"+isBalanced(root));
		//System.out.println("is common Ancestor:"+commonAncestor(root, root.left,root.right));
		return get(root,i);
	}
	
	public Key get(Node root,Key i){
		if(root == null) return null;
		int comp = i.compareTo(root.data);
		if(comp > 0)
			return get(root.right,i);
		else if(comp < 0)
			return get(root.left,i);
		else
			return root.data;
	}
	
	private Key min(){
		if(root.size == 0) throw new NoSuchElementException("Empty Tree");
		else return min(root).data;
	}
	
	private Node min(Node x){
		if(x.left == null) return x;
		else return min(x.left);
	}
	
	private Key max(){
		if(root.size == 0) throw new NoSuchElementException("Empty Tree");
		else return max(root).data;
	}
	
	private Node max(Node x){
		if(x.right == null) return x;
		else return max(x.right);
	}
	
	
	public Iterable<Key> keys(){
		return keys(min(),max());
		
	}
	private Iterable<Key> keys(Key lo, Key hi) {
		if(lo == null) throw new NoSuchElementException("Lo is empty");
		if(hi == null) throw new NoSuchElementException("Hi is empty");
		LinkedQueue<Key> queue = new LinkedQueue<Key>();	
		keys(root,queue,lo,hi);
		return queue;
	}

   public boolean isEmpty(){
	   return size(root) == 0;
   }

   private Iterable<Key> levelOrder(){
	   LinkedQueue<Key> keys = new LinkedQueue<Key>();
	   LinkedQueue<Node> que = new LinkedQueue<Node>();
	   
	   que.enqueue(root);
	   while(!que.isEmpty()){
		   Node x = que.dequeue();
		   if(x == null) continue;
		   keys.enqueue(x.data);
		   que.enqueue(x.left );
		   que.enqueue(x.right);
	   }
	   return keys;
   }
	private void keys(BST<Key>.Node x, LinkedQueue<Key> que, Key lo, Key hi) {
		if(x == null) return;
		int complo = lo.compareTo(x.data);
		int comphi = hi.compareTo(x.data);
		if(complo < 0) keys(x.left,que,lo,hi);
		if(complo <= 0 && comphi >= 0)que.enqueue(x.data);
		if(comphi > 0)keys(x.right,que,lo,hi);
	}




	private int size(BST<Key>.Node x) {
		if(x == null) return 0;
		else
		return x.size;
	}
	
	public  int getHeight(Node root){
		if(root == null)return 0;
		return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
	}
	
	
	//This approach will take O(n2) time, each time sub tree will be called
	public boolean isBalanced(Node root){
		if(root == null)return true;
		int heightDiff = getHeight(root.left) - getHeight(root.right);
		if(Math.abs(heightDiff) > 1){
			return false;
		}else{
			return isBalanced(root.left) && isBalanced(root.right);
		}
	}
	
	//Two fix above problem, we can subtree itself first and return -1 if subtree is not balanced.
	//This code runs in 0(N)time and 0(H) space,where H is  the height of the tree.

	public boolean isBalancedImproved(Node root){
		if(checkHeight(root) == -1) return false;
		else{
			return true;
		}
		
	}
	/*public String toString(){
		StringBuffer sb = new StringBuffer();
		for(Node n: this){
			sb.append(n.data.toString());
		}
		return sb.toString();
				
	}*/




	private int checkHeight(BST<Key>.Node root) {

		if(root == null){
			return 0;//height of the root
		}
		//check if left is balanced
		int leftHeight = checkHeight(root.left);
		if(leftHeight == -1){
			return -1;//not balanced
		}
		//check if right is balanced
		int rightHeight = checkHeight(root.right);
		if(rightHeight == -1){
			return -1;//not balanced
		}
		int heightDiff = leftHeight - rightHeight;
		if(Math.abs(heightDiff) > 1){
			return -1;//not balanced
		}else{
			//return the height
			return Math.max(leftHeight, rightHeight) +1;
		}
		
		
	}
	


}

