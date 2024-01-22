package generic_classes;
import java.util.NoSuchElementException;

/**
 * A linked list is a sequence of nodes with efficient element insertion and removal. 
 * This class contains a subset of the methods of the standard java.util LinkedList class
 * */
public class LinkedList {
	private Node first;
	/**
	 * constructs an empty linked list
	 * */
	public LinkedList() {
		first = null;
	}
	
	/**
	 * returns the first element in the linked list
	 * @return the first element in the linked list
	 * */
	public Object getFirst() {
		if (first == null) {
			throw new NoSuchElementException();
		}
		return first.data;
	}
	
	/**
	 * returns an iterator for iterating through this list
	 * @return an iterator for iterating through this list
	 * */
	public ListIterator listIterator() {
		return new LinkedListIterator();
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
