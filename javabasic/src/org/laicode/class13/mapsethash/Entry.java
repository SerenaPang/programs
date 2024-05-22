package org.laicode.class13.mapsethash;

import java.lang.reflect.Array;

public class Entry<Key, Value>{
	final Key key;
	Value value;
	Entry<Key, Value> next;
	
	Entry(Key key, Value value) {
		this.key = key;
		this.value = value;
	}
	
	public Key getKey() {
		return key;
	}
	
	public Value getValue() {
		return value;
	}
	
	public void setValue(Value value) {
		this.value = value;
	}
	
	/**
	 * hash the key to get the hash number
	 * */
	private int hash(Key key) {
		//return the hash number of the key
		if (key == null) {
			return 0;
		}
		//post processing to make the hash number non negative
		int hashNumber = key.hashCode();
		return hashNumber;
	}
	
	/**
	 * from the hash number, mapped to the entry index
	 * */
	public int getIndex(int hashNumber) {
		//return the corresponding index of array
		return hashNumber % Array.getLength(key);
	}
	
	public void iterateEntry(Entry<Key, Value>[] array, int startingIndex) {
		Entry<Key, Value> cur = array[startingIndex];
		while (cur != null) {
			Key curKey = cur.getKey();
//			if (curKey is the same as given key) {
//				...
//			}
			cur = cur.next;
		}
	}
	
}
