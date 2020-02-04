package main.google.test;

import java.util.HashSet;

/***
*Service Now Interview Questions
*
* Instruction
*     Implement a custom hashmap that only supports String keys and String values.
*     The structure is incomplete. Feel free to change things around as appropriate.

Example data. 
CustomHashMap  map = new CustomHashMap(); 
map.put("bob", "blue"); 
map.put("Joe", "green"); 
map.put("Jerry", "blue");

*/
public class CustomHashMap {
private final int BUCKET_SIZE = 10;

 //**** PART 1
//INSTANCE VARIABLES

class Entry {
	private String key;
	private String value;
	Entry next;

	public Entry(String key, String value) {
		this.key = key;
		this.value = value;
	//this.next = next;
	//ADD ANY ADDITIONAL CODE BELOW
	}

	public String getValue() {
		return value;
	}
}


	public CustomHashMap() {
	//	buckets = new Entry[BUCKET_SIZE];
	}
	private Entry buckets[] = new Entry[BUCKET_SIZE];

	//HashSet s = new HashSet();

	private int calculateHash(String key) {
	//insert code
		return  key.hashCode()%BUCKET_SIZE;
	}
	
	public static void main(String[] args) {
		CustomHashMap myMap = new CustomHashMap();
		myMap.put("xyz", "Architect");
		myMap.put("Swamy", "Engineer");
		myMap.put("Biru", "CEO");
		myMap.put("ABC", "CTO");
		
		myMap.put("XXX", "PDM");
		myMap.put("YYY", "IM");
		myMap.put("GGG", "Dev");
		
		Entry e = myMap.get("GGG");
		System.out.println(e.getValue());
		
		System.out.println(myMap.remove("xyz"));
		System.out.println(myMap.get("GGG").getValue());
		
		System.out.println("size:"+myMap.size());
		System.out.println("Contains:"+myMap.contains("Swamy"));
	}
	
	private  boolean contains(String searchKey) {
		int hash = calculateHash(searchKey);
	
		if(buckets[hash] != null) {
			Entry current = buckets[hash];
		while(current != null) {
			if(current.key.equals(searchKey)) {
				return true;
			}
			current = current.next;
		}
		
		}
		return false;
	}

	private int size() {
		int count = 0;
		for(int i = 0; i< buckets.length; i++) {
			if(buckets[i] != null) {
				int nodeCount = 0;
				for(Entry e= buckets[i]; e.next != null; e = e.next) {
					nodeCount++;
				}
				count += nodeCount;
				count++;//consider also vertical count
			}
		}
		return count;
	}

	private  boolean remove(String removeKey) {
		int hash = calculateHash(removeKey);
	//	Entry e = buckets[hash];
		if(buckets[hash] == null) {
			return false;
		}else {
			Entry previous = null;
			Entry current = buckets[hash];
			
			while(current != null) {//reaches last entry
				if(current.key.equals(removeKey)) {
					if(previous == null) {
						//delete first entry node
						buckets[hash] = buckets[hash].next;
						return true;
					}else {
						previous.next = current.next;
						return true;
					}
				}
				previous = current;
				current = current.next;
				
			}
			return false;
		}
		
	}

	private Entry get(String getKey) {
		int hash = calculateHash(getKey);
		Entry e = buckets[hash];
		while(e != null) {
			if(e.key.equals(getKey)) {
				return e;
			}
			e = e.next;
		}
		return null;
	}

	private void put(String insertKey, String value) {
		int  hash = calculateHash(insertKey);
		System.out.println("hash:"+hash);
		
		Entry e = buckets[hash];
		
		if(e != null) {
			//if we insert duplicate key value pair
			//Old value will be replaced by new one.
			if(e.key.equals(insertKey)) {
				e.value = value;
			}else {
				
				//collision: insert new element at the end of the list in same bucket
				while(e.next != null) {
					e = e.next;
				}
				Entry entryInOldBucket = new Entry(insertKey, value);
				e.next = entryInOldBucket;
			}
		}else {
			//create new bucket for new lement in the map
			Entry entryInOldBucket = new Entry(insertKey, value);
			buckets[hash] = entryInOldBucket;
			
		}
		
	}

}
