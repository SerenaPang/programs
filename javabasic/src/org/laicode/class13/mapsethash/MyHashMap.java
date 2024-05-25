package org.laicode.class13.mapsethash;

import java.util.Arrays;

public class MyHashMap<K, V> {
	// static final variable are global constants
	private static final int DEFAULT_CAPACITY = 16;
	private static final float DEFAULT_LOAD_FACTOR = 0.75f;
	private static final int SCALE_FACTOR = 0;

	private Node<K, V>[] array;
	private int size; // how many key - value pairs are actually stored in the HashMap
	private float loadFactor; // determine when to rehash

	public MyHashMap() {
		this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
	}

	public MyHashMap(int capacity, float loadFactor) {
		if (capacity <= 0) {
			throw new IllegalArgumentException("cap can not be <= 0");
		}
		this.array = (Node<K, V>[]) (new Node[capacity]);
		this.size = 0;
		this.loadFactor = loadFactor;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void clear() {
		Arrays.fill(this.array, null);
		size = 0;
	}

	/**
	 * Get a hash number for the key
	 */
	private int hash(K key) {
		// 1. null key
		if (key == null) {
			return 0;
		}
		// 2.3 hashCode()
//		int code = key.hashCode();
//		return code >= 0 ? code : -code;
//		int range = [-2^31, 2^31 - 1];
		// use mask to guarantee non negative
		return key.hashCode() & 0X7FFFFFFF;
	}

	/**
	 * According to the hash number of the key, assign a index position in array for
	 * the key
	 */
	private int getIndex(K key) {
		return hash(key) % array.length;
	}

	/**
	 * compare if two values are equal
	 */
	private boolean equalsValue(V v1, V v2) {
		// v1, v2 all possibly to be null
		if (v1 == null && v2 == null) {
			return true;
		}
		if (v1 == null || v2 == null) {
			return false;
		}
		return v1.equals(v2);
	}

	public boolean containsKey(K key) {
		// get the index of the key
		int index = getIndex(key);
		Node<K, V> node = array[index];
		while (node != null) {
			// check if the key equals() key, node.key() all possible to be null
			if (equalsKey(node.key, key)) {
				return true;
			}
			node = node.next;
		}
		return false;
	}

	private boolean equalsKey(K key1, K key2) {
		// k1 k2 are all possibly to be null
		if (key1 == null && key2 == null) {
			return true;
		}
		if (key1 == null || key2 == null) {
			return false;
		}
		return key1.equals(key2);
	}

	// traverse the whole array, and traverse each of the linked list in the array
	// to check for the value. O(n),
	public boolean containsValue(V value) {
		// special case
		if (isEmpty()) {
			return false;
		}
		for (Node<K, V> node : array) {
			while (node != null) {
				// check if the value equals() value, node.getValue() all possible to be null
				if (equalsValue(node.value, value)) {
					return true;
				}
				node = node.next;
			}
		}
		return false;
	}

	// if key does not exists in the HashMap, return null
	public V get(K key) {
		int index = getIndex(key);
		Node<K, V> node = array[index];
		while (node != null) {
			// check if they key equals
			if (equalsKey(node.key, key)) {
				return node.value;
			}
		}
		return null;
	}

	// if the key not exists, return null
	public V put(K key, V value) {
		int index = getIndex(key);
		Node<K, V> head = array[index];
		Node<K, V> node = head;
		while (node != null) {
			// check if the key equals() key, node.key() all possible to be null
			if (equalsKey(node.key, key)) {
				V result = node.value;
				node.value = value;
				return result;
			}
		}
		// append the new node before the head and update the new head insert operation
		Node<K, V> newNode = new Node(key, value);
		newNode.next = head;
		array[index] = newNode; // new head is here
		size++;
		if (needRehashing()) {
			rehashing();
		}
		return null;
	}

	/**
	 * Get index, delete operation on the linked list, size--
	 */
	public V remove(K key) {
		int index = getIndex(key);
		Node<K, V> node = array[index];
		Node<K, V> pre = null;
		while (node != null) {
			if (equalsKey(node.key, key)) {
				// in the middle or at the end
				if (pre != null) {
					pre.next = node.next;
				} else { // at the beginning of the list
					array[index] = node.next;
				}
				// we delete an element
				size--;
				return node.value;
			}
			pre = node;
			node = node.next;
		}
		return null;
	}

	/**
	 * create a new double sized array, for each node in the old array, put to the
	 * new larger array( add to the head of the linked list). if the key exists,
	 * remove the <key, value> from the HashMap, return the value, if the key not
	 * exists, return null
	 */
	private void rehashing() {
		Node<K, V>[] oldArray = array;
		array = (Node<K, V>[]) (new Node[array.length * SCALE_FACTOR]);
		for (Node<K, V> node : oldArray) {
			while (node != null) {
				// record the next node
				Node<K, V> next = node.next;
				int index = getIndex(node.key);
				// copy the key
				node.next = array[index];
				// make it the head
				array[index] = node;
				// move to next node
				node = next;
			}
		}
	}

	private boolean needRehashing() {
		float ratio = (size + 0.0f) / array.length;
		return ratio >= loadFactor;
	}

	/**
	 * Node is a static class of MyHashMap, since it is: very closely bonded to
	 * MyHashMap class
	 */
	public static class Node<K, V> {
		final K key;
		V value;
		Node<K, V> next;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}
	}
}
