package com.mycompany.app;

public class MyLinkedList {
	private ListNode head;
	private int length;
	public MyLinkedList(ListNode head) {
		this.head = head;
	}

	/**
	 * Return the length of the list
	 */
	public int length() {
		int count = 0;
		ListNode cur = head;
		while (cur != null) {
			count++;
			cur = cur.next;
		}
		return count;
	}
	
	/**
	 * Return the length of the list
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Get the list node with specific index
	 */
	public ListNode get(ListNode head, int index) {
		ListNode cur = head;
		int count = 0;
		// count = index, and cur != null
		while (count < index && cur != null) {
			cur = cur.next;
			count++;
		}
		return cur;
	}

	/**
	 * Add the listnode to the end of the list
	 * 
	 * @return the head of the new list
	 */
	public ListNode appendTail(int value) {
		ListNode newNode = new ListNode(value);
		// case 1 head is empty, make it the head and the tail			
		if (head == null) {
			head = newNode;
			length++;
			return newNode;
		}
		// case 2 find the tail and attach it
		ListNode pre = null;
		ListNode current = head;
		while (current != null) {
			pre = current;
			current = current.next;		
		}
		// exit the while loop when current == null and pre == last node in list
		pre.next = newNode;
		length++;
		return head;
	}

	/**
	 * Add the listnode to the beginning of the list
	 * 
	 * @return the head of the new list
	 */
	public ListNode appendHead(ListNode head, int value) {
		ListNode newNode = new ListNode(value);
		// case 1 head is empty, make it the new head
		if (head == null) {
			return newNode;
		}
		// case 2 head is not empty, make the old head to be the next of the new head
		// and return the new head
		newNode.next = head;
		return newNode;
	}

	/**
	 * Remove the first matching value in the list
	 */
	public ListNode remove(ListNode head, int value) {
		// case 1: remove head, make target's next element the new head
		// case 2: remove middle, connect the previous element to the current's next
		// element
		// case 3: remove tail, find the element before last element and cut it
		if (head == null) {
			return null;
		} else if (head.value == value) {
			return head.next;
		}
		ListNode cur = head;
		while (cur.next != null && cur.next != null) {
			cur = cur.next;
		}
		//now cur.next is the node to be removed
		if (cur.next != null) {
			cur = cur.next.next;
		}
		return head;
	}
}
