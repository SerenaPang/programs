package com.mycompany.app;

/**
 * Implementation of a Stack data structure that has the feature of first in first out functionalities
 * */
public class MyQueue {
	private ListNode head;
	private int size = 0;
	
	/**
	 * Looks at the object at the top of this queue without removing it from the queue
	 * */
	public ListNode peekFirst() {
		if (head == null) {
			return null;
		}
		return head;
	}
	
	/**
	 * removes the head of the queue or return null if it's empty
	 * */
	public ListNode pollFirst() {
		ListNode nxt = head.next;
		head = nxt;
		size--;
		return head;
	}
	
	/**
	 * add the element to queue at the tail of the list
	 * */
	public ListNode offerLast(int value) {
		ListNode newNode = new ListNode(value);
		if (head == null) {
			head = newNode;
		} else {
			ListNode pre = null;
			ListNode cur = head;
			while (cur != null) {
				pre = cur;
				cur = cur.next;			
			}
			pre.next = newNode;
		}
		size++;
		return head;
	}
	
	/**
	 * Check if queue is empty
	 * */
	public boolean empty() {
		if (size == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the number of elements in this queue
	 * */
	public int size() {
		return size;
	}
}