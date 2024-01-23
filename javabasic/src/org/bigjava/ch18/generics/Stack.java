package org.bigjava.ch18.generics;
import java.util.Arrays;

public class Stack<E> {
	private Object[] elements;
	private int currentSize;
	
	private static final int INITIAL_SIZE = 10;
	
	public Stack() {
		elements = new Object[INITIAL_SIZE];
	}
	
	public void push(E value) {
		if (currentSize >= elements.length) {
			elements = Arrays.copyOf(elements, 2 * elements.length);
		} 
		elements[currentSize] = value;
		currentSize++;
	}
	
	@SuppressWarnings("unchecked")
	public E pop() {
		currentSize--;
		return (E) elements[currentSize]; //cast causes "unchecked" warning
	}
	
	public int size()
	{
		return currentSize;
	}
}
