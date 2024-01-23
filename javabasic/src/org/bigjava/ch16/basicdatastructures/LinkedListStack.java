package org.bigjava.ch16.basicdatastructures;
import java.util.NoSuchElementException;

/**
 * an implementation of a stack as a sequence of nodes
 * */
public class LinkedListStack {
	private Node first;
	
	/**
	 * constructs an empty stack
	 * */
	public LinkedListStack() {
		first = null;
	}
	
	/**adds an element to the top of the stack
	 * @param element the element to add
	 * */
	public void push(Object element) {
		Node newNode = new Node();
		newNode.data = element;
		newNode.next = first;
		first = newNode;
	}
	
	/**
	 * Removes the element from the top of the stack
	 * @return the removed element
	 * */
	public Object pop() {
		if (first == null) {
			throw new NoSuchElementException();
		}
		Object element = first.data;
		first = first.next;
		return element;
	}
	
	/**
	 * checks whether this stack is empty
	 * @return true if the stack is empty
	 * */
	public boolean empty() {
		return first == null;
	}
}
