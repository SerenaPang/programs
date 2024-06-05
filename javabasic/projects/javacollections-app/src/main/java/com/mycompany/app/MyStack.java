package com.mycompany.app;

/**
 * Implementation of a Stack data structure that has the feature of last in first out functionalities
 * */
public class MyStack {
	private ListNode head;
	private int size = 0;
	/**
	 * Check if stack is empty
	 * */
	public boolean empty() {
		if (size == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Looks at the object at the top of this stack without removing it from the stack
	 * */
	public ListNode peekFirst() {
		if (head == null) {
			return null;
		}
		return head;
	}
	
	/**
	 * Push an element onto the top of this stack
	 * */
	public ListNode addFirst(int value) {
		ListNode newHead = new ListNode(value);
		if (head == null) {
			head = newHead;
		} else {
			ListNode oldHead = head;
			newHead.next = oldHead;
			head = newHead;		
		}
		size++;
		return head;
	}
	
	/**
	 * Removes the object at the top of this stack and return the new head of the stack
	 * */
	public ListNode removeFirst() {
		ListNode nxt = head.next;
		head = nxt;
		size--;
		return head;
	}
	
	/**
	 * Returns the number of elements in this stack
	 * */
	public int size() {
		return size;
	}		
}