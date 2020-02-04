package main.swamy.ood;

import java.util.LinkedList;

/**
 * Design and implement a hash table which uses chaining (linked lists) to handle collisions.
 * @author swamy
 *
 */
/**
 * 
 * @author swamy
 *
 * @param <K>
 * @param <V>
 * Another common approach of implementing HashTable is using Binary Tree, retrieval time is O(1),
 *  also the unwanted allocation of array size  will be reduced.
 */

public class HashCollision<K, V> {
	private final int MAX_SIZE = 10;
	LinkedList<Cell<K,V>>[] items;
	
	public HashCollision(){
		items = (LinkedList<Cell<K, V>>[]) new LinkedList[MAX_SIZE];
	}
	
	//bad hashing
	public int hashCodeOfKey(K key) {
		System.out.println("HashCode:"+key.toString().length() % items.length);
		return key.toString().length() % items.length;
	}
	
	public void put(K key, V value){
		int x = hashCodeOfKey(key);
		if(items[x] == null) {
			items[x] = new LinkedList<Cell<K, V>>();
		}
		
		LinkedList<Cell<K, V>> collided = items[x];
		
		//look for items with same key and replace if found
		for(Cell<K, V> c: collided) {
			if(c.equivalent(key)) {
				collided.remove();
				break;
			}
		}
		Cell<K, V> cell = new Cell<K, V>(key, value);
		collided.add(cell);
	}
	
	public V get(K key) {
		int x = hashCodeOfKey(key);
		if(items[x] == null) {
			return null;
		}
		LinkedList<Cell<K, V>> collided = items[x];
		for(Cell<K, V> c: collided) {
			if(c.equivalent(key)) {
				return c.getValue();
			}
		}
		return null;
	}

	public static void main(String[] args) {
		
		HashCollision<String, String> h = new HashCollision<String, String>();
		h.put("jim", "Jim");
		h.put("bob", "Bob");
		h.put("Jim", "Bob");
		h.put("Rans", "Sam");
		System.out.println("HashTable:"+h.get("jim"));
		System.out.println("HashTable:"+h.get("bob"));
		System.out.println("HashTable:"+h.get("Jim"));
		System.out.println("HashTable:"+h.get("Rans"));
		
	}

}
