package org.bigjava.ch16.basicdatastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashSetIterator implements Iterator {
	private int bucketIndex;
	private Node current;
	private Node[] buckets;
	
	/**
	 * constructs a hash set iterator that points to the first element of the hash set
	 * */
	public HashSetIterator() {
		current = null;
		bucketIndex = -1;
	}
	
	
	@Override
	public boolean hasNext() {
		if (current != null && current.next != null) {
			return true;
		}
		for (int b = bucketIndex + 1; b < buckets.length; b++) {
			if (buckets[b] != null) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Object next() {
		if (current != null && current.next != null) {
			current = current.next; //move to next element in bucket
		}
		else { //move to next bucket
			do {
				bucketIndex++;
				if (bucketIndex == buckets.length) {
					throw new NoSuchElementException();
				}
				current = buckets[bucketIndex];
			} while (current == null);		
		}
		return current.data;
	}
	
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
