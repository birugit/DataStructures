package main.swamy.tree.redblacktree;

import main.swamy.linkedqueue.LinkedQueue;
/**
 * 0)Root is always BLACK
 * 1)A node is either RED or BLACK
 * 2)The root and leaves (NIL) are black.
 * 3)If a node is red, then its children are black.
 * 4) All paths from a node to its NIL descendants
 *   contain the same number of black nodes.
 *   
 *   ####Program#####
 *   1)If tree is empty, create new node as root node with color black.
 *   2)If tree is not empty, create new node as leaf node with color RED
 *   3)If parent of new node is black then exit.
 *   4)If parent of new node is RED, then check the color <b> Parent's sibling</b> of new node.
 *      a) if color is black or null then do suitable rotation & recolor
 *      b) if color is RED then recolor & also check if parent's parent of new node is not root node then 
 *         recolor & recheck.
 *   
 * @author swamy
 *
 * @param <Key>
 */
//Key extends Comparable<Key>
//Key extends Comparable<Key>, Value
public class RedBlackTree<Key extends Comparable<Key>, Value> {
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	private Node root;
	
	private class Node{
		private Key key;
		private Value val;
		private Node left, right;
		private boolean color;
		private int size;
		
		public Node(Key key,Value val, boolean color, int size) {
			this.key = key;
			this.val = val;
			this.color = color;
			this.size = size;
		}
	}
	
	public RedBlackTree() {
		
	}

	public static void main(String[] args) {
		RedBlackTree<String, Integer> rbt = new RedBlackTree<String,Integer>();
		//Integer[] keys = {10, 20, 30, 40 ,50, 25};
		String[] keys = {"S", "E","A", "R", "C", "H"};
		Integer i=1;
		for(String k: keys) {
			System.out.println("Key:"+k);
			rbt.put(k,i);
			i++;
		}
		
		for(String k: rbt.levelOrder()) {
			System.out.println(k);
		}
		
		System.out.println();
		for(String k : keys) {
		System.out.print(rbt.get(k));
		}
		System.out.println();
		System.out.println("**isBST**");
		
		System.out.println("isBST:"+rbt.isBST());
		
		System.out.println("isBalanced:"+rbt.isBalanced());
		
		System.out.println("Delete**S:");
		rbt.delete("S");
		
		/*for(String k: rbt.levelOrder()) {
			System.out.println(k);
		}
		*/
		System.out.println("*Keys*");
		for(String k: rbt.keys()) {
			System.out.println(k);
		}
	}
	
	private Iterable<Key> keys() {
		return keys(min(), max());
	}

	private Iterable<Key> keys(Key lo, Key hi) {
		LinkedQueue<Key> que = new LinkedQueue<Key>();
		keys(root,que, lo, hi);
		return que;
	}

	private void keys(RedBlackTree<Key, Value>.Node x, LinkedQueue<Key> que, Key lo, Key hi) {
		if (x == null )return;
		int complo = lo.compareTo(x.key);
		int comphi = hi.compareTo(x.key);
		
		if(complo < 0)
			keys(x.left, que, lo, hi);
		 if (complo <= 0 && comphi >= 0)
			que.enqueue(x.key);
		 if(comphi > 0)
			keys(x.right, que, lo, hi);
	}

	public Key min() {
		
		return min(root).key;
	}


	public Key max() {
	
		return max(root).key;
	}
	
	private RedBlackTree<Key, Value>.Node max(RedBlackTree<Key, Value>.Node x) {
		
		if(x.right == null)
			return x;
		else 
			return max(x.right);
	}

	private void delete(Key key) {
	
		//check key is not null
		
		//check key exist in RBT
		
		//if both children of root are black, set root to red
		if(!isRed(root.left) && !isRed(root.right))
			root.color = RED;
		
		root = delete(root, key);
		
	
	}

	private RedBlackTree<Key, Value>.Node delete(RedBlackTree<Key, Value>.Node node, Key key) {
		
		if(key.compareTo(node.key) < 0) {
			if(!isRed(node.left) && !isRed(node.left.left))
				node = moveRedLeft(node);
			node.left = delete(node.left, key);
		}else {
			if(isRed(node.left))
				node = rotateRight(node);
			if(key.compareTo(node.key) == 0 &&
					(node.right == null))
				return null;
			if(!isRed(node.right) && !isRed(node.right.left))
				node = moveRedRight(node);
			if(key.compareTo(node.key) == 0) {
				Node x = min(node.right);
				node.key = x.key;
				node.val = x.val;
				
				node.right = deleteMin(node.right);
			}else
				node.right = delete(node.right, key);
		}
		return balance(node);
	}
	

	//delete the kye-value pair with the minimum key rooted at h
	private RedBlackTree<Key, Value>.Node deleteMin(RedBlackTree<Key, Value>.Node h) {
		if(h.left == null)
			return null;
		if(!isRed(h.left) && !isRed(h.left.left))
			h = moveRedLeft(h);
		h.left = deleteMin(h.left);
		return balance(h);
	}

	//restore red-black tree invariant
	private RedBlackTree<Key, Value>.Node balance(RedBlackTree<Key, Value>.Node h) {
		if(isRed(h.right))
			h= rotateLeft(h);
		if(isRed(h.left) && isRed(h.left.left))
			h = rotateRight(h);
		if(isRed(h.left) && isRed(h.right))
			flipColors(h);
		
		h.size = size(h.left) + size(h.right) + 1;
		
		return h;
	}

	//return the smallest key in the symbol table
	private RedBlackTree<Key, Value>.Node min(RedBlackTree<Key, Value>.Node x) {
			if(x.left == null) 
				return x;
			else
				return min(x.left);
		
	}

	//assuming that node is red and both node.right and node.right.left are black
	//make node.right or one of its children red
	private RedBlackTree<Key, Value>.Node moveRedRight(RedBlackTree<Key, Value>.Node node) {
		flipColors(node);
		if(isRed(node.left.left)) {
			node = rotateRight(node);
			flipColors(node);
		}
		return node;
	}

	//assuming that node is red and both node.left and node.left.left are black
	//make node.left or one of its children red
	private RedBlackTree<Key, Value>.Node moveRedLeft(RedBlackTree<Key, Value>.Node node) {
		flipColors(node);
		if(isRed(node.right.left)) {
			node.right = rotateRight(node.right);
			node = rotateLeft(node);
			flipColors(node);
		}
		return node;
	}
	
	
	//do all paths from root to leaf have same number of black edges?
	
	private boolean isBalanced() {
		int black = 0;
		Node x =root;
		while(x != null) {
			if(!isRed(x)) 
				black ++;
			x = x.left;
		}
		System.out.println("Black Nodes:"+black);
		return isBalanced(root, black);
	}

	private boolean isBalanced(RedBlackTree<Key, Value>.Node x, int black) {
		if(x == null)
			return black == 0;
		if(!isRed(x))
			black--;
		
		return isBalanced(x.left, black) && isBalanced(x.right, black);
	}

	//does this binary tree satisfy symmetric order
	//Note: this test also ensures data structure is binary tree since order is strict
	private boolean isBST() {
		return isBST(root, null, null);
	}

	private boolean isBST(RedBlackTree<Key, Value>.Node node, Key min, Key max) {
		if(node == null) return true;
		if(min != null && 
				node.key.compareTo(min) <= 0)
			return false;
		if(max != null && 
				node.key.compareTo(max) >= 0) 
			return false;
		return isBST(node.left, min, node.key) && 
				isBST(node.right, node.key, max); 
		
	}

	private  Value get(Key key) {
		if(key == null)
			throw new IllegalArgumentException("Empty");
		return get(root, key);
		
	}

	private Value get(RedBlackTree<Key, Value>.Node node, Key key) {
		while(node != null) {
			int cmp = key.compareTo(node.key);
			if(cmp < 0) node = node.left;
			else if(cmp > 0) node = node.right;
			else return node.val;
		//return null;
		}
		return null;
	}

	private Iterable<Key> levelOrder() {
		LinkedQueue<Key> keys = new LinkedQueue<Key>();
		LinkedQueue<Node> que = new LinkedQueue<Node>();
		que.enqueue(root);
		while(!que.isEmpty()) {
			Node x = que.dequeue();
			if(x == null)continue;
			keys.enqueue( x.key);
			que.enqueue(x.left);
			que.enqueue(x.right);
		}
		return keys;
	}

	private void put(Key data, Value val) {
		root = put(root, data, val);
		root.color = BLACK;
		
	}

	private RedBlackTree<Key,Value>.Node put(RedBlackTree<Key,Value>.Node node, Key data, Value val) {
		if(node == null) {
			return new Node(data,val, RED, 1);
		}
		int comp = data.compareTo(node.key);
		
		if(comp < 0) {
			node.left = put(node.left, data, val);
		}else if (comp > 0) {
			node.right = put(node.right, data, val);
		}else {
			node.val = val;
		}
		
		//fix-up any right-leaning links
		if(isRed(node.right) && !isRed(node.left))
			node = rotateLeft(node);
		if(isRed(node.left) && isRed(node.left.left))
			node = rotateRight(node);
		if(isRed(node.left) && isRed(node.right))
			flipColors(node);
		
		node.size = 1 + size(node.left) + size(node.right);
		
		return node;
	}

	private void flipColors(RedBlackTree<Key,Value>.Node h) {
		h.color = !h.color;
		h.left.color = !h.left.color;
		h.right.color = !h.right.color;
		
	}

	private RedBlackTree<Key,Value>.Node rotateRight(RedBlackTree<Key,Value>.Node h) {
			Node x = h.left;
			h.left = x.right;
			x.right = h;
			x.color = x.right.color;
			x.right.color = RED;
			x.size = h.size;
			h.size = size(h.left) + size(h.right) +1;
		return x;
	}

	private RedBlackTree<Key,Value>.Node rotateLeft(RedBlackTree<Key,Value>.Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = x.left.color;
		x.left.color = RED;
		x.size = h.size;
		x.size = size(h.left) + size(h.right) + 1;
		return x;
	}

	private boolean isRed(RedBlackTree<Key,Value>.Node x) {
		if( x == null) return false;
		return x.color == RED;
	}

	private int size(RedBlackTree<Key,Value>.Node x) {
		if(x == null) return 0;
		else
		return x.size;
	}

}
