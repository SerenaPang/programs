package com.mycompany.app;

import java.util.Arrays;
import java.util.NoSuchElementException;
/**
 * Implementation of the operations for a min heap(priority queue)
 * In a complete binary tree, the relation of parent and child nodes can be well transffered to the relation between two indexes
 * - The root of the tree is at index 0
 * - index of parent = i
 * - parent of index = (i - 1) / 2
 * - left child of index = 2 * i + 1
 * - right child of index = 2 * i + 2
 * */
public class MinHeap {
	private int[] array;//array.length is the capacity of the min heap
	private int size;//how many elements are in the min heap
	
	public MinHeap(int[] array) {
		if (array == null || array.length == 0) {
			throw new IllegalArgumentException("input array can not be null or empty");
		}
		this.array = array;
		size = array.length;
		heapify();
	}
	
	public MinHeap(int cap) {
		if (cap <= 0) {
			throw new IllegalArgumentException("Capacity can not be <= 0");
		}
	}
	
	/**
	 * returns how many elements in the min heap
	 * */
	public int size() {
		return size;
	}
	
	/**
	 * check if the min heap is empty
	 * */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * check if the min heap is full
	 * */
	public boolean isFull() {
		return size == array.length;
	}
	
	/**
	 * To make a tree maintain the heap's property
	 * By for each node that has at least one child(the parent nodes), we do a percolateDown in the order
	 * of from the nodes on the deepest level to the root
	 * */
	private void heapify() {
		
	}
	
	/**
	 * When the element need to be moved up to maintain the heap's property(offer)
	 * By compare the element with its parent, move it up when necessary.
	 * Do this until the element does not need to be moved
	 * */
	private void percolateUp(int index) {
		
	}
	
	/**
	 * When the element need to be moved down to maintain the heap's property.(poll)
	 * By comparing the elements with its 2 children, if the smallest one of the two children is 
	 * smaller than the element, swap the element with that child. 
	 * Do this ubtil the element does not need be moved.
	 * */
	private void percolateDown(int index) {
		
	}
	
	/**
	 * check the first element in the min heap
	 * */
	public int peek() {
		if (size == 0) {
			throw new NoSuchElementException("heap is empty");
		}
		return array[0];
	}
	
	/**
	 * remove the first element in the min heap
	 * perform percolate down to maintain the heap's property
	 * */
	public int poll() {
		if (size == 0) {
			throw new NoSuchElementException("heap is empty");
		}
		int result = array[0];
		array[0] = array[size - 1];
		size--;
		percolateDown(0);
		return result;
	}
	
	/**
	 * add an element to the min heap
	 * perform percolate up to maintain the heap's property
	 * */
	public void offer(int element) {
		if (size == array.length) {
			//when adding new element, expand the array if necessary
			array = Arrays.copyOf(array, (int)array,length * 1.5);
		}
		array[size] = element;//add to the end of the array
		size++;
		percolateUp(size - 1);//perform percolate up from the end of the array
	}
	
	/**
	 * update the element in the min heap
	 * @return the original value
	 * */
	public int update(int index, int element) {
		
		return -1;
	}
	
	/**
	 * swap the two elements in the array
	 * */
	private void swap(int indexLeft, int indexRight) {
		int temp = array[indexLeft];
		array[indexLeft] = array[indexRight];
		array[indexRight] = temp;
	}
}