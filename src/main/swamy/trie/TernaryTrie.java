package main.swamy.trie;

import main.swamy.linkedqueue.LinkedQueue;

/**
 * Ternary Search Trie
 * 
 * @author swamy
 *
 * @param <Value>
 */
public class TernaryTrie<Value> {
	
	int n;//size
	private Node<Value> root; //root of Trie
	
	private static class Node<Value>{
		private char c;	//character
		private Node<Value> left, mid, right; //left, middle, and right subtries
		private Value val;		//Value associcate with String
		
	}
	
	public TernaryTrie() {
		
	}
	
	private int size() {
		return n;
	}

	public static void main(String[] args) {
		TernaryTrie<Integer> st = new TernaryTrie<Integer>();
		//input : she sells sea shells by the sea shore
		st.put("she", 0);
		st.put("sells", 1);	
		st.put("sea", 2);
		st.put("shells", 3);
		st.put("by", 4);
		st.put("the", 5);
		st.put("sea", 6);
		st.put("shore", 7);
		
		if(st.size() < 100) {
			for(String key : st.keys()) {
				System.out.println(key + ""+st.get(key));
			}
		}
		
		System.out.println("Keys that Match:");
		for(String s: st.keysThatMatch(".he"))
			System.out.println(s);
		
		System.out.println("Longest prefix:");
			System.out.println(st.longestPrefixOf("shellsorts"));
			System.out.println(st.longestPrefixOf("shell"));

	}
//Returns the string in the symbol table that is the longest prefix of query
	private String longestPrefixOf(String query) {
		if(query == null) {
			throw new IllegalArgumentException();
		}
		if(query.length() == 0) return null;
		int length = 0;
		Node<Value> x = root;
		int i = 0;
		while(x != null && i < query.length()) {
			char c = query.charAt(i);
			if			(c < x.c)
				x = x.left;
			else if		(c > x.c)
				x = x.right;
			else {
				i++;
				if(x.val != null)
					length = i;
				x = x.mid;
			}
		}
		
		return query.substring(0, length);
	}

	//Returns all the keys of symbol table that match
	//where . symbol is treated as a wildcard character.
	//pattern param
	private Iterable<String> keysThatMatch(String pattern) {
		LinkedQueue<String> queue = new LinkedQueue<String>();
		collect(root, new StringBuilder(), 0, pattern, queue);
		return queue;
	}

	private void collect(Node<Value> x, StringBuilder prefix, int i, String pattern,
			LinkedQueue<String> queue) {
		if(x == null) return;
		char c = pattern.charAt(i);
		if(c == '.' || c < x.c) 
			collect(x.left, prefix, i, pattern, queue );
		if(c == '.' || c == x.c)
			if(i == pattern.length() - 1 && x.val != null)
				queue.enqueue(prefix.toString() + x.c);
			if(i < pattern.length() - 1) {
				collect(x.mid, prefix.append(x.c), i+1, pattern, queue);
				prefix.deleteCharAt(prefix.length() - 1);
			}
		if(c == '.' || c > x.c)
			collect(x.right, prefix, i, pattern, queue);
		
	}

	private Iterable<String> keys() {
		LinkedQueue<String> queue = new LinkedQueue<String>();
		collect(root, new StringBuilder(), queue);
		return queue;
	}

	private void collect(Node<Value> x, StringBuilder prefix, LinkedQueue<String> queue) {
		if(x == null) return;
		collect(x.left, prefix, queue);
		if(x.val != null) queue.enqueue(prefix.toString() + x.c);
		collect(x.mid, prefix.append(x.c), queue);
		prefix.deleteCharAt(prefix.length() - 1);
		collect(x.right, prefix, queue);
		
	}

	private void put(String key, Value val) {
		if(key == null) {
			throw new IllegalArgumentException();
		}
		if(!contains(key)) n++;
		else if(val == null) n --;//delete existing key
		root = put(root, key, val, 0);
		
	}

	private Node<Value> put(Node<Value> x, String key, Value val, int d) {
		char c = key.charAt(d);
		if(x == null) {
			x = new Node<Value>();
			x.c = c;
		}
		if			(c < x.c)	x.left = put(x.left, key, val, d);
		else if		(c > x.c)	x.right = put(x.right, key, val, d);
		else if		(d < key.length() - 1) x.mid = put(x.mid, key, val, d+1);
		else			x.val = val;
		return x;
	}

	private boolean contains(String key) {
		if(key == null) {
			throw new IllegalArgumentException();
		}
		return get(key) != null;
	}

	private Value get(String key) {
		if(key == null) {
			throw new IllegalArgumentException();
		}
		if(key.length() == 0)
			throw new IllegalArgumentException();
		Node<Value> x = get(root, key, 0);
		if(x == null) return null;
		return x.val;
	}

	private Node<Value> get(Node<Value> x, String key, int d) {
		if(x == null) return null;
		if(key.length() == 0)
			throw new IllegalArgumentException();
		char c = key.charAt(d);
		if		(c < x.c) 			return get(x.left, key, d);
		else if	(c > x.c)			 return get(x.right, key, d);
		else if	(d < key.length() - 1)return get(x.mid, key, d+1);
		else return x;

	}

}
