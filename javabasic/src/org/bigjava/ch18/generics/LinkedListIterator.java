package org.bigjava.ch18.generics;

import java.util.NoSuchElementException;

public class LinkedListIterator implements ListIterator{
	private static Node first = null;
	private Node position;
	private Node previous;
	private  boolean isAfterNext;

	/**
	 * constructs an iterator that points to the front of the linked list
	 * */
	public LinkedListIterator() {
		position = null;
		previous = null;
		isAfterNext = false;
	}
	
	/**
	 * moves the iterator past the next element
	 * @return the traversed element
	 * */
	
	@Override
	public Object next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		previous = position; //remember for remove
		isAfterNext = true;
		
		if (position == null) {
			position = first;
		}
		else
		{
			position = position.next;
		}
		return position.data;
	}

	/**
	 * Tests if there is an element after the iterator position
	 * @return true if there is an element after the iterator position
	 * */
	@Override
	public boolean hasNext() {
		if (position == null) {
			return first != null;
		}
		else
		{
			return position.next != null;
		}
	}

	/**
	 * Adds an element before the iterator position and moves the iterator past the inserted element
	 * @param element the element to add
	 * */
	@Override
	public void add(Object element) {
		if (position == null) {
			addFirst(element);
			position = first;
		}
		else 
		{
			Node newNode = new Node();
			newNode.data = element;
			newNode.next = position.next;
			position.next = newNode;
			position = newNode;
		}
		isAfterNext = false;
	}

	

	/**
	 * Removes the last traversed element. This method may only be called after a call to the next() method
	 * */
	@Override
	public void remove() {
		if (!isAfterNext) {
			throw new IllegalStateException();
		}
		if (position == first) 
		{
			removeFirst();
		}
		else
		{
			previous.next = position.next;
		}
		position = previous;
		isAfterNext = false;
	}
	
	/**
	 * Removes the first element in the linked list
	 * @return the removed element
	 * */
	public Object removeFirst() {
		if (first == null) {
			throw new NoSuchElementException();
		}
		Object element = first.data;
		first = first.next;
		return element;
	}
	
	/**
	 * adds an element to the front of the linked list
	 * @param element the element to add
	 * */
	public void addFirst(Object element) {
		Node newNode = new Node();
		newNode.data = element;
		newNode.next = first;
		first = newNode;
	}

	/**
	 * Sets the last traversed element to a different value
	 * @param element the element to set
	 * */
	@Override
	public void set(Object element) {
		if (!isAfterNext) {
			throw new IllegalStateException();}
			position.data = element;
		}
		
	}
	
