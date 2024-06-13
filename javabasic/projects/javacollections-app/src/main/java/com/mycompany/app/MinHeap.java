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
		//keep percolate up until - reach the top; the heap peroperty is maintained
		while (index > 0) {
			int parentIndex = (index - 1) / 2; //compare parent node with its children
			//if the value of the parent node is greater than index's value
			if (array[parentIndex] > array[index]) {//the priority of parent is less than index
				swap(parentIndex, index); //node has to go up to exchange position with its parent
			} else {
				break;
			}
			index = parentIndex;//compare the next parent node
		}
	}
	
	/**
	 * When the element need to be moved down to maintain the heap's property.(poll)
	 * By comparing the elements with its 2 children, if the smallest one of the two children is 
	 * smaller than the element, swap the element with that child. 
	 * Do this ubtil the element does not need be moved.
	 * */
	private void percolateDown(int index) {
		//cut the array in half, and review the node from back to the begining
		while (index <= size / 2 - 1) { //last parent node, we don't want to review more than this
			//first compare the 2 children node
			int leftChild = index * 2 + 1;
			int rightChild = index * 2 + 2;
			//choose left to be swap candidate, because it must have left child but not necessary right child
			int swap = leftChild;
			//check if right child exist,and if left or right has more priority
			if (rightChild <= size - 1 && array[leftChild] >= array[rightChild]) {
					swap = rightChild; //right has more priority, swap with the right child
			}
			//now we have the result of left/right is the smallest, compare the parent node
			if (array[index] > array[swap]) {//check if parent node or the smallest child node is less priority
				swap(index, swap);
			} else {//parent node has more priority, we do not need to swap
				break;
			}
			index = swap;//update the current index to next swap
		}
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
			array = Arrays.copyOf(array, (int)(array.length * 1.5));
		}
		array[size] = element;//add to the end of the array
		size++;
		percolateUp(size - 1);//perform percolate up from the end of the array
	}
	
	/**
	 * update the element in the min heap, either perform percolate up/down to maintain the heap's property
	 * @return the original value
	 * */
	public int update(int index, int element) {
		int originalElement = array[index];
		if (index < 0 || index > size - 1) {
			throw new ArrayIndexOutOfBoundsException("invalid index range");
		}
		array[index] = element; //replace the new element with the original one in array
		if (originalElement > element) { //which means new element is smaller, should goes up
			percolateUp(index);
		} else {//means new element is greater than old one, should goes down
			percolateDown(index);			
		}
		return originalElement;
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