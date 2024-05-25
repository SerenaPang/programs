package org.laicode.class13.mapsethash;

import java.util.HashMap;

public class HashSet<K> {
	private HashMap<K, Object> map;
	//special object used for all the existing keys
	private static final Object PRESENT = new Object();
	
	public HashSet(int initialCapacity) {
		map = new HashMap<K, Object>(initialCapacity);
	}
	
	public boolean contains(K key) {
		return map.containsKey(key);
	}
	
	public boolean add(K key) {
		return map.put(key, PRESENT) == null;
	}
	
	public boolean remove(K key) {
		return map.remove(key) == PRESENT;
	}
}
