package org.bigjava.ch17.treestructures;
import java.util.*;
/**
 * This class implements a heap
 * */
public class MinHeap {
	private ArrayList<Comparable> elements;
	/**
	 * Constructs an empty heap
	 * */
	public MinHeap() {
		elements = new ArrayList<>();
		elements.add(null);
	}
	
	/**
	 * adds a new element to this heap
	 * @param newElement the element to add
	 * */
	public void add(Comparable newElement) {
		//add a new leaf
		elements.add(null);
		int index = elements.size() - 1;
		//demote parents that are larger than the new element
		while(index > 1 && getParent(index).compareTo(newElement) > 0) {
			elements.set(index, getParent(index));
			index = getParentIndex(index);
		}
		//store the new element in the vacant slot
		elements.set(index, newElement);
	}
	
	/**
	 * gets the minimum element stored in this heap
	 * @return the minimum element
	 * */
	public Comparable peek() {
		return elements.get(1);
	}
	
	/**
	 * removed the minimum element from this heap
	 * @return the minimum element 
	 * */
	public Comparable remove() {
		Comparable minimum = elements.get(1);
		//remove last element
		int lastIndex = elements.size() - 1;
		Comparable last = elements.remove(lastIndex);
		
		if (lastIndex > 1) {
			elements.set(1, last);
			fixHeap();
		}
		return minimum;
	}

	/**
	 * turns the tree back into a heap, provided only the root node violates the heap condition
	 * */
	private void fixHeap() {
		Comparable root = elements.get(1);
		int lastIndex = elements.size() - 1;
		//promote children of removed root while they are smaller than root
		int index = 1;
		boolean more = true;
		while(more) {
			int childIndex = getLeftChildIndex(index);
			if(childIndex <= lastIndex) {
				//get smaller child
				//get left child first
				Comparable child = getLeftChild(index);
				//use right child instead if it is smaller
				if (getRightChildIndex(index) <= lastIndex && getRightChild(index).compareTo(child) < 0) {
					childIndex = getRightChildIndex(index);
					child = getRightChild(index);
				}
				//check if smaller child is smaller than root
				if (child.compareTo(root) < 0) {
					//promote child
					elements.set(index, child);
					index = childIndex;
				}
				else {
					//root is smaller than both children 
					more = false;
				}
			}
			else {
				//no children 
				more = false;
			}
		}
		//store root element in vacant slot
		elements.set(index, root);
	}
	
	/**
	 * checks whether this heap is empty
	 * */
	public boolean empty() {
		return elements.size() == 1;
	}

	private Comparable getRightChild(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * returns the index of the right child
	 * @param index the index of a node in this heap
	 * @return the index of the right child of the given node
	 * */
	private int getRightChildIndex(int index) {
		return 2 * index + 1;
	}

	private Comparable getLeftChild(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * returns the index of the left child
	 * @param index the index of a node in this heap
	 * @return the index of the left child of the given node
	 * */
	private int getLeftChildIndex(int index) {
		return 2 * index;
	}

	/**
	 * returns the index of the parent
	 * @param index the index of a node in this heap
	 * @return the index of the parent of the given node
	 * */
	private int getParentIndex(int index) {
		return index / 2;
	}

	private boolean getParent(int index) {
		// TODO Auto-generated method stub
		return false;
	}
}

