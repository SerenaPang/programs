package com.mycompany.app;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import com.mycompany.app.ListNode;
import com.mycompany.app.MyQueue;

/**
 * Unit test for MyQueue in java collections App.
 */
public class MyQueueTest{
	@Test
	public void testSize() {
		System.out.println("TEST Size ");
		MyQueue queue = new MyQueue();

		int result = queue.size();
		System.out.println("Size = " + result);
		assertEquals(0, result);
	}

	@Test
	public void testEmpty() {
		System.out.println("TEST Empty ");
		MyQueue queue = new MyQueue();
		int size = queue.size();
		boolean result = queue.empty();
		System.out.println("size = " + result);
		assertTrue(result);
	}

	@Test
	public void testEmpty2() {
		System.out.println("TEST Empty ");
		MyQueue queue = new MyQueue();
		queue.offerLast(2);
		queue.offerLast(3);
		queue.offerLast(4);
		int size = queue.size();
		boolean result = queue.empty();
		System.out.println("size = " + result);
		assertFalse(result);
	}
	
	@Test
	public void testPeekFirst() {
		System.out.println("TEST PeekFirst");
		MyQueue queue = new MyQueue();
		ListNode result = queue.offerLast(1);
		queue.offerLast(2);
		queue.offerLast(3);
		queue.offerLast(4);
		int resultVal = result.value;
		System.out.println("first = " + resultVal);
		assertEquals(1, resultVal);
	}

	@Test
	public void testOfferLast() {
		System.out.println("TEST OfferLast");
		MyQueue queue = new MyQueue();
		queue.offerLast(1);
		queue.offerLast(2);
		ListNode result = queue.offerLast(3);
		int resultVal = result.value;
		System.out.println("first = " + resultVal);
		assertEquals(1, resultVal);
	}
	
	@Test
	public void testOfferLast2() {
		System.out.println("TEST OfferLast");
		MyQueue queue = new MyQueue();
		queue.offerLast(2);
		queue.offerLast(3);
		ListNode result = queue.offerLast(4);
		int resultVal = result.value;
		System.out.println("last = " + resultVal);
		assertEquals(2, resultVal);
	}

	@Test
	public void testPollFirst() {
		System.out.println("TEST PollFirst ");
		MyQueue queue = new MyQueue();
		queue.offerLast(2);
		queue.offerLast(3);
		queue.offerLast(4);	
		ListNode result = queue.pollFirst();
		int resultVal = result.value;
		System.out.println("first = " + resultVal);
		assertEquals(3, resultVal);
	}
}