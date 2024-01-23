package org.bigjava.ch16.basicdatastructures;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * An implementation of a doubly linked list
 * */
public class LinkedList {
	private Node first;
	private Node last;
	
	/**
	 * constructs an empty linked list
	 * */
	public LinkedList() {
		first = null;
		last = null;
	}
	
	/**
	 * returns the first element in the linked list
	 * @return the first element in the linked list
	 * */
	public Object getFirst() {
		if (first == null) {
			throw new NoSuchElementException();
		}
		Object element = first.data;
		first = first.next();
		if (first == null) {
			last = null; //list is now empty
		} else {
			first.previous = null;
		}
		return element;
	}
	
	/**
	 * Adds an element to the front of the linked list
	 * @param element the element to add
	 * */
	public void addFirst(Object element) {
		Node newNode = new Node();
		newNode.data = element;
		newNode.next = first;
		newNode.previous = null;
		if (first == null) {
			last = newNode;
		} else {
			first.previous = newNode;
		}
		first = newNode;
	}	
	
	/**
	 * returns the last element in the linked list
	 * @return the last element in the linked list
	 * */
	public Object getLast() {
		if (last == null) {
			throw new NoSuchElementException();
		}
		return last.data;
	}
	
	/**
	 * Removes the last element in the linked list
	 * @return the removed element
	 * */
	public Object removeLast() {
		if (last == null) {
			throw new NoSuchElementException();
		}
		Object element = last.data;
		last = last.previous;
		if (last == null) {
			first = null;
		} //list is now empty
		else {
			last.next = null;
		}
		return element;
	}
	
	/**
	 * Adds an element to the back of the linked list
	 * @param element the element to add
	 * */
	public void addLast(Object element) {
		Node newNode = new Node();
		newNode.data = element;
		newNode.next = null;
		newNode.previous = last;
		if (last == null) {
			first = newNode;
		} else {
			last.next = newNode;
		}
		last = newNode;
	}
	
	/**
	 * returns an iterator for iterating through this list
	 * @return an iterator for iterating through this list
	 * */
	public ListIterator listIterator() {
		return new LinkedListIterator();
	}
	
	class LinkedListIterator implements ListIterator{
		private Node position;
		private boolean isAfterNext;
		private boolean isAfterPrevious;
		
		/**
		 * constructs an iterator that points to the front of the linked list
		 * */
		public LinkedListIterator() {
			position = null;
			isAfterNext = false;
			isAfterPrevious = false;
		}
		
		/**
		 * test if there is an element after the iterator position
		 * @return true if there is an element after the iterator position
		 * */
		@Override
		public boolean hasNext() {
			if (position == null) {
				return first != null;
			} 
			else {
				return position.next != null;
			}
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
			isAfterNext = true;
			isAfterPrevious = false;
			
			if (position == null) {
				position = first;
			}
			else {
				position = position.next;
			}
			return position.data;
		}

		/**
		 * tests if there is an element before the iterator position
		 * @return true if there is an element before the iterator position
		 * */
		@Override
		public boolean hasPrevious() {
			return position != null;
		}

		/**
		 * moves the iterator before the previous element
		 * @return the traversed element
		 * */
		@Override
		public Object previous() {
			if (!hasPrevious()) {
				throw new NoSuchElementException();
			}
			isAfterNext = false;
			isAfterPrevious = true;
			
			Object result = position.data;
			position = position.previous;
			return result;
		}

		@Override
		public int nextIndex() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int previousIndex() {
			// TODO Auto-generated method stub
			return 0;
		}

		/**
		 * removes the last traversed element. this method may 
		 * only be called after a call to the next method
		 * */
		@Override
		public void remove() {
			Node positionToRemove = lastPosition();
			if (positionToRemove == first) {
				removeFirst();
			}
			else if (positionToRemove == last) {
				removeLast();
			}
			else {
				positionToRemove.previous.next = positionToRemove.next;
				positionToRemove.next.previous = positionToRemove.previous;
			}
			
			if (isAfterNext) {
				position = position.previous;
			}
			isAfterNext = false;
			isAfterPrevious = false;
		}

		//remove the first element in the linked list
		private Object removeFirst() {
			if(first == null) {
				throw new NoSuchElementException();
			}
			Object element = first.data;
			first = first.next;
			if (first == null) {
				last = null; //list is now empty
			} else {
				first.previous = null;
			}
			return element;
		}

		/**
		 * returns the last node traversed by this iterator, or throws an IllegalStateException is there wasn't
		 * an immediately preceding call to next or previous
		 * */
		private Node lastPosition() {
			if (isAfterNext) 
			{
				return position;
			} 
			else if (isAfterPrevious) 
			{
				if (position == null) 
				{
					return first;
				}
				else {
					return position.next;
				}
			}
			else {
				throw new IllegalStateException();
			}
		}

		/**
		 * sets the last traversed element to a different value
		 * @param element the element to set
		 * */
		@Override
		public void set(Object element) {
			Node positionToSet = lastPosition();
			positionToSet.data = element;
		}

		/**
		 * adds an element before the iterator position and moves the iterator past the inserted element
		 * @param element the element to add
		 * */
		@Override
		public void add(Object element) {
			if (position == null) {
				addFirst(element);
				position = first;
			} else if (position == last) {
				addLast(element);
				position = last;
			} else {
				Node newNode = new Node();
				newNode.data = element;
				newNode.next = position.next;
				newNode.next.previous = newNode;
				position.next = newNode;
				newNode.previous = position;
				position = newNode;
			}
			isAfterNext = false;
			isAfterPrevious = false;
		}
		
	}
	
}
