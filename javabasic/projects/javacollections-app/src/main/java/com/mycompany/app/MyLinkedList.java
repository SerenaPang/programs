package com.mycompany.app;

public class MyLinkedList {
	ListNode head;

	public MyLinkedList(ListNode head) {
		this.head = head;
	}

	/**
	 * Return the length of the list
	 */
	public int length(ListNode head) {
		int count = 0;
		ListNode cur = head;
		while (cur != null) {
			count++;
			cur = cur.next;
		}
		return count;
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
		}
		return cur;
	}

	/**
	 * Add the listnode to the end of the list
	 * 
	 * @return the head of the new list
	 */
	public ListNode appendTail(ListNode head, int value) {
		ListNode newNode = new ListNode(value);
		ListNode pre = head;
		// case 1 head is empty, make it the head and the tail
		if (pre == null) {
			return newNode;
		}
		// case 2 find the tail and attach it
		while (pre != null) {
			pre = pre.next;
		}
		pre.next = newNode;
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
		ListNode pre = null;
		ListNode cur = head;
		while (cur != null) {
			if (cur.value == value) {
				// case 1: remove head, make target's next element the new head
				// case 2: remove middle, connect the previous element to the current's next
				// element
				// case 3: remove tail, find the element before last element and cut it
				if (pre == null) {
					return cur.next;
				} else if (cur.next == null) {
					pre.next = null;
					return head;
				} else {
					pre.next = cur.next;
				}
			}
			pre = cur;
			cur = cur.next;
		}
		return head;
	}
}
