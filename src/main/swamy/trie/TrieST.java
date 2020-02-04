package main.swamy.trie;

import main.swamy.linkedqueue.LinkedQueue;
//m-way trie
//256 trie

public class TrieST<Value> {
	private static final int R = 256; //extended ASCII
	
	private Node root;
	private int n; //number of keys in trie
	
	//R-way trie node
	private static class Node {
		private Object val;
		private Node[] next = new Node[R];
	}
	
	//initialize empty string table
	public TrieST() {
		
	}

	public static void main(String[] args) {
		TrieST<Integer> st = new TrieST<Integer>();
		//input : she sells sea shells by the sea shore
		st.put("she", 0);
		st.put("sells", 1);	
		st.put("sea", 2);
		st.put("shells", 3);
		st.put("by", 4);
		st.put("the", 5);
		st.put("sea", 6);
		st.put("shore", 7);
		
		
		//print result
		/*
		  	by 4
			sea 6
			sells 1
			she 0
			shells 3
			shore 7
			the 5
		 */
		if(st.size() < 100) {
			for(String key : st.keys())
			System.out.println(key + " "+ st.get(key));
		}
		
		System.out.println("longestPrefixof");
		System.out.println(st.longestPrefixOf("shellsort"));
		
		System.out.println("Keys with prefix");
		for(String s: st.keyWithPrefix("shor"))
			System.out.println(s);

	}

	private String longestPrefixOf(String query) {
		if(query == null) throw new IllegalArgumentException();
		int length = longestPrefixOf(root, query, 0, -1);
		if(length == -1)return null;
		else return query.substring(0, length);
	}

	private int longestPrefixOf(Node x, String query, int d, int length) {
		if(x == null) return length;
		if(x.val != null) length = d;
		if(d == query.length()) return length;
		char c = query.charAt(d);
		
		return longestPrefixOf(x.next[c],query,d+1, length);
	}

	private Value get(String key) {
		if(key == null) throw new IllegalArgumentException();
		Node x = get(root, key, 0);
		return (Value) x.val;
	}

	private Iterable<String> keys() {
		
		return keyWithPrefix("");
	}

	private Iterable<String> keyWithPrefix(String prefix) {
		LinkedQueue<String> results = new LinkedQueue<String>();
		Node x = get(root, prefix, 0);
		collect(x, new StringBuilder(prefix), results);
		return results;
	}

	private void collect(Node x, StringBuilder prefix, LinkedQueue<String> results) {
		if(x == null) return;
		if(x.val != null) 
			results.enqueue(prefix.toString());
		for(char c = 0; c < R; c++) {
			prefix.append(c);
			collect(x.next[c], prefix, results);
			prefix.deleteCharAt(prefix.length() - 1);
		}
		
	}

	private Node get(Node x, String key, int d) {
		if(x == null) return null;
		if(d == key.length()) return x;
		char c = key.charAt(d);
		return get(x.next[c], key, d+1);

	}

	private void put(String key, Value val) {
		if(key == null)
			throw new IllegalArgumentException("First Argument to put");
		if(val == null) delete(key);
		else
			root = put(root, key, val, 0);
	}

	private void delete(String key) {
		// TODO Auto-generated method stub
		
	}

	private Node put(Node x, String key, Value val, int d) {
		if(x == null) x = new Node();
		if(d == key.length()) {
			if(x.val == null) 
				n++;
			x.val = val;
			return x;
			
		}
		char c = key.charAt(d);
		x.next[c] = put(x.next[c], key, val, d+1);
		return x;
	}
	
	public int size() {
		return n;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}

}
