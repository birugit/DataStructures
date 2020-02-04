package main.swamy.ood;

public class Cell<K, V> {
	private K key;
	private V value;

	public Cell(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	public boolean equivalent(Cell<K,V> c) {
	 return c.equivalent(c.getKey());
	}

	private K getKey() {
		return  key;
	}

	public boolean equivalent(K k) {
		
		return key.equals(k);
	}

	public V getValue() {
		
		return value;
	}

}
